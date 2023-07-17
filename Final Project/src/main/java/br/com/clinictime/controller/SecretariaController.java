package br.com.clinictime.controller;


import br.com.clinictime.model.Paciente;
import br.com.clinictime.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secretaria")
public class SecretariaController {


    @Autowired
    private PacienteRepository pacienteRepository;


    @GetMapping("/")
    public String home() {
        return "secretaria/index";
    }


    @ModelAttribute
    private void index(Model model) {
        List<Paciente> listarPaciente = pacienteRepository.findAll();
        model.addAttribute("listarPaciente", listarPaciente);
    }

    @GetMapping("/pesquisar")
    public String pesquisarPacientes(@RequestParam("name") String name, Model model) {
        List<Paciente> pacientes = pacienteRepository.findByName(name);
        model.addAttribute("listarPaciente", pacientes);
        return "secretaria/index";
    }


    @GetMapping("/novopaciente")
    public String novoPaciente() {
        return "secretaria/novopaciente";
    }


    @PostMapping("/novopaciente")
    public String criarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
        return "redirect:/secretaria/";
    }


    @GetMapping("/{id}/alterarpaciente")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (!pacienteOptional.isPresent()) {
            return "redirect:/secretaria/";
        }

        Paciente paciente = pacienteOptional.get();
        model.addAttribute("paciente", paciente);
        return "secretaria/editarPaciente";
    }


    @PostMapping("/alterarpaciente/{id}")
    public String salvarAlteracoes(@PathVariable Long id, @ModelAttribute("paciente") Paciente paciente) {
        paciente.setId(id);
        pacienteRepository.save(paciente);
        return "redirect:/secretaria/";
    }


    @GetMapping("/{id}")
    public String excluirDoutor(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/secretaria/";
    }

}
