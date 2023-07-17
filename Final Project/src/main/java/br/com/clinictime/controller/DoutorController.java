package br.com.clinictime.controller;

import br.com.clinictime.model.Consulta;
import br.com.clinictime.model.Doutor;
import br.com.clinictime.model.Paciente;
import br.com.clinictime.model.Secretaria;
import br.com.clinictime.repository.ConsultaRepository;
import br.com.clinictime.repository.DoutorRepository;
import br.com.clinictime.repository.PacienteRepository;
import br.com.clinictime.service.doutor.DoutorService;
import br.com.clinictime.service.secretaria.SecretariaService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author Matheus Gomes Vieira
 *
 * controlador responsável por lidar com as requisições relacionadas aos doutores
 */

@SessionAttributes("session")
@Controller
@RequestMapping("/doutores")
public class DoutorController {

    /**
     * injentando instâncias utilizando o @Autowired que é utilizado para injeção de dependência
     *
     */
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoutorService doutorService;

    @Autowired
    private SecretariaService secretariaService;


    /**
     * Método que retorna a página inicial para o doutor
     * @return retornando a página doutor/index
     */
    @GetMapping("/")
    public String home() {
        return "doutor/index";
    }


    /**
     * @param httpServletRequest requisição HTTP
     * @return para a página doutor/register
     */
    @GetMapping("/register")
    public String register(@ModelAttribute HttpServletRequest httpServletRequest) {
        return "doutor/register";
    }

    /**
     * Método para criar um novo doutor;
     * E validar atráves do metódo checkEmail se já existe o e-mail a ser cadastrado;
     * Seja no repositório da Secretária ou do Doutor e se caso existir irá aparecer a mensagem E-mail já existe
     *
     * @param user seria o objeto Doutor, novo Doutor a ser criado
     * @param session sessão HTTP
     * @param secretaria objeto do Tipo Secretaria
     * @return para o GetMapping /register
     */
    @PostMapping("/criardoutor")
    public String createuser(@ModelAttribute Doutor user, HttpSession session, Secretaria secretaria) {

        boolean d = doutorService.checkEmail(user.getEmail());
        boolean s = secretariaService.checkEmail(user.getEmail(), secretaria);

        if (d || s) {
            session.setAttribute("msg", "E-mail já existe.");
        } else {
            Doutor doutor = doutorService.createUser(user);
            if (doutor != null) {
                session.setAttribute("msg", "Registrado Com Sucesso!");
            } else {
                session.setAttribute("msg", "Um erro Inesperado Ocorreu");
            }
        }

        return "redirect:/doutores/register";
    }


    /**
     *
     * @param model repassando a lista de pacientes para a view
     */
    @ModelAttribute
    private void index(Model model) {
        List<Paciente> listarPaciente = pacienteRepository.findAll();
        model.addAttribute("listarPaciente", listarPaciente);
    }

    /**
     *
     * @param name nome do paciente a ser pesquisado
     * @param model repassando uma lista de pacientes com o nome digitado
     * @return retornando a página inicial index dos doutores
     */
    @GetMapping("/pesquisar")
    public String pesquisarPacientes(@RequestParam("name") String name, Model model) {
        List<Paciente> pacientes = pacienteRepository.findByName(name);
        model.addAttribute("listarPaciente", pacientes);
        return "doutor/index";
    }


    /** Método que retorna a página para criar um novo paciente
     */
    @GetMapping("/novopaciente")
    public String novoPaciente() {
        return "doutor/novopaciente";
    }


    /**Método para criar um novo Paciente
     *
     * @param paciente o objetop de tipo Paciente a ser criado
     * @return redirecionando para a página /doutores/
     *
     * try e catch para validar se o CPF contém 11 digitos se não tiver é dada uma mensagem de erro no front atráves
     * do model
     */
    @PostMapping("/novopaciente")
    public String criarPaciente(Paciente paciente, Model model) {
        try {
            if (paciente.getCpf().length() != 11) {
                throw new IllegalArgumentException("O CPF deve ter 11 dígitos.");
            }

            pacienteRepository.save(paciente);
            return "redirect:/doutores/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("cpfError", e.getMessage());
            return "doutor/novopaciente";
        }
    }





    /** Método que retorna a página de alteração de dados do paciente
     *
     * @param id para coletar o ID do paciente a ser alterado
     *
     * No primeiro trecho de código a primeira linha está retornando um objeto Optional;
     * ou seja que pode ou não existir um paciente, verificando se o ID é existente no banco de dados;
     * Em seguida na linha abaixo é verificado se o paciente não existe no banco de dados;
     * Se não for encontrado é efetuado um return para /doutores/
     *
     *No segundo trecho de código é obtido o paciente existente e na segunda linha o paciente;
     * É adicionado ao model e se torna disponível para a view com o nome "paciente"
     * E em seguida é retornado o hmtl de edição de paciente.
     */
    @GetMapping("/{id}/alterarpaciente")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (!pacienteOptional.isPresent()) {
            return "redirect:/doutores/";
        }

        Paciente paciente = pacienteOptional.get();
        model.addAttribute("paciente", paciente);
        return "doutor/editarPaciente";
    }


    /**
     *
     * @param id representa o valor ID recebido da URL
     * @param paciente representa os atributos do objeto paciente que são repassados na requisição
     * SetID atribuindo o valor do parâmetro ID ao paciente
     * pacienteRepository.save para atualizar os dados do paciente no banco de dados
     */
    @PostMapping("/alterarpaciente/{id}")
    public String salvarAlteracoes(@PathVariable Long id, @ModelAttribute("paciente") Paciente paciente) {
        paciente.setId(id);
        pacienteRepository.save(paciente);
        return "redirect:/doutores/";
    }


    /**
     *
     * @param id passando o atributo id na URL /doutores/id para assim excluir o id especificado na URL atráves do
     * pacienteRepository.deleteById(id)
     * @return retornando para a página /doutores/
     */
    @GetMapping("/{id}")
    public String excluirDoutor(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/doutores/";
    }
}
