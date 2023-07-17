package br.com.clinictime.controller;


import br.com.clinictime.model.Paciente;
import br.com.clinictime.model.Secretaria;
import br.com.clinictime.repository.SecretariaRepository;
import br.com.clinictime.service.doutor.DoutorService;
import br.com.clinictime.service.secretaria.SecretariaService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SessionAttributes("session")
@Controller
@RequestMapping("/doutores/secretaria")
public class DoutorControllerSecretarias {


    @Autowired
    private SecretariaRepository secretariaRepository;

    @Autowired
    private SecretariaService secretariaService;

    @Autowired
    private DoutorService doutorService;


    @GetMapping("/")
    public String home() {
        return "doutor/indexSecretaria";
    }

    @ModelAttribute
    private void index(Model model) {
        List<Secretaria> listarSecretaria = secretariaRepository.findAll();
        model.addAttribute("listarSecretaria", listarSecretaria);
    }

    @GetMapping("/pesquisar")
    public String pesquisarPacientes(@RequestParam("name") String name, Model model) {
        List<Secretaria> secretarias = secretariaRepository.findByName(name);
        model.addAttribute("listarSecretaria", secretarias);
        return "doutor/indexSecretaria";
    }


    @GetMapping("/novasecretaria")
    public String novaSecretaria() {
        return "doutor/novasecretaria";
    }


    @PostMapping("/novasecretaria")
    public String criarSecretaria(@ModelAttribute Secretaria user, HttpSession session) {
        boolean d = doutorService.checkEmail(user.getEmail());
        boolean s = secretariaService.checkEmail(user.getEmail(), user);

        if (s || d) {
            session.setAttribute("mensagem", "E-mail já existe.");
            return "doutor/novasecretaria";
        } else {
            Secretaria createdSecretaria = secretariaService.createUser(user);
            secretariaRepository.save(createdSecretaria);
            session.setAttribute("mensagem", "Registrado Com Sucesso!");
            return "redirect:/doutores/secretaria/";
        }
    }






    @GetMapping("/{id}/alterarsecretaria")
    public String editarSecretaria(@PathVariable Long id, Model model) {
        Optional<Secretaria> secretariaOptional = secretariaRepository.findById(id);
        if (!secretariaOptional.isPresent()) {
            return "redirect:/doutores/secretaria/";
        }

        Secretaria secretaria = secretariaOptional.get();
        model.addAttribute("secretaria", secretaria);
        return "doutor/editarSecretaria";
    }


    @PostMapping("/alterarsecretaria/{id}")
    public String salvarAlteracoes(@PathVariable Long id, @ModelAttribute("secretaria") Secretaria user, HttpSession session) {
        Secretaria secretaria = secretariaService.getSecretariaById(id);



        boolean d = doutorService.checkEmail(user.getEmail());
        boolean s = secretariaService.checkEmail(user.getEmail(), secretaria);

        if (s || d) {
            session.setAttribute("mensagem", "E-mail já existe para outra secretária.");
            return "doutor/editarSecretaria";
        } else {
            secretariaService.createUser(user);
            session.setAttribute("mensagem", "Atualizado Com Sucesso!");
            return "redirect:/doutores/secretaria/";
        }
    }



    @GetMapping("/{id}")
    public String excluirSecretaria(@PathVariable Long id) {
        secretariaRepository.deleteById(id);
        return "redirect:/doutores/secretaria/";
    }

}
