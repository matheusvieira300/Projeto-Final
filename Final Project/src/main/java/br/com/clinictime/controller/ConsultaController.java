package br.com.clinictime.controller;

import br.com.clinictime.model.Consulta;
import br.com.clinictime.model.Doutor;
import br.com.clinictime.model.Paciente;
import br.com.clinictime.repository.ConsultaRepository;
import br.com.clinictime.repository.DoutorRepository;
import br.com.clinictime.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doutores")
public class ConsultaController {

    /**
     * injetando as dependências
     */
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private PDFController pdfController;



    @GetMapping("/consulta/{id}")
    public String consultarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (!pacienteOptional.isPresent()) {
            return "redirect:/doutores/";
        }

        Paciente paciente = pacienteOptional.get();
        List<Consulta> consultasAnteriores = consultaRepository.findAllByPaciente(paciente);

        model.addAttribute("paciente", paciente);
        model.addAttribute("consultasAnteriores", consultasAnteriores);

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        model.addAttribute("consulta", consulta);

        return "doutor/consulta";
    }


    @Secured("ROLE_ADMIN")
    @PostMapping("/consulta/{id}/salvar")
    public String salvarConsulta(@PathVariable Long id, @ModelAttribute("consulta") Consulta consulta) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (!pacienteOptional.isPresent()) {
            return "redirect:/doutores/";
        }

        Paciente paciente = pacienteOptional.get();

        // obtém a autenticação atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null || !authentication.isAuthenticated()) {

            return "redirect:/login";
        }

        // obtém o nome do usuário autenticado, que é o email no  caso
        String userEmail = authentication.getName();

        // busca o doutor logado pelo email
        Doutor doutor = doutorRepository.findByEmail(userEmail);
        if (doutor == null) {

            return "redirect:/doutores/";
        }



        Consulta novaConsulta = new Consulta();
        novaConsulta.setReceita(consulta.getReceita());
        novaConsulta.setPaciente(paciente);
        novaConsulta.setDoutor(doutor); // Definindo o doutor associado a consulta


        consultaRepository.save(novaConsulta);

        // Redireciona para o método que realiza o download do PDF
        return "redirect:/doutores/consulta/" + novaConsulta.getId() + "/download";
    }

    // esse é o metodo que gera o PDF da consulta e faz o download
    @GetMapping("/consulta/{id}/download")
    public ResponseEntity<byte[]> downloadPDFConsulta(@PathVariable Long id) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);
        if (!consultaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Consulta consulta = consultaOptional.get();
        Doutor doutor = consulta.getDoutor();

        if (doutor == null) {

            return ResponseEntity.badRequest().body("Doutor associado à consulta é nulo.".getBytes());
        }

        try {

            byte[] pdfBytes = pdfController.gerarPDFConsulta(consulta);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Receita "+consulta.getPaciente().getName()+".pdf");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
