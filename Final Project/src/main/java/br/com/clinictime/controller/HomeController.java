package br.com.clinictime.controller;

import br.com.clinictime.model.Doutor;
import br.com.clinictime.model.Secretaria;
import br.com.clinictime.service.secretaria.SecretariaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.clinictime.service.doutor.DoutorService;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("session")
public class HomeController {


    @Autowired
    private SecretariaService secretariaService;

    @Autowired
    private DoutorService doutorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String logado() {
        return "novo";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute HttpServletRequest httpServletRequest) {
        return "register";
    }

    @PostMapping("/logout")
    public String logout() {
        System.out.println("Logout");
        return "redirect:/login";
    }

    @PostMapping("/createUser")
    public String createuser(@ModelAttribute Secretaria user, HttpSession session) {

        boolean d = doutorService.checkEmail(user.getEmail());
        boolean s = secretariaService.checkEmail(user.getEmail(), user);

        if (s || d) {
            session.setAttribute("msg", "E-mail já existe.");
        } else {
            Secretaria secretaria = secretariaService.createUser(user);
            if (secretaria != null) {
                session.setAttribute("msg", "Registrado Com Sucesso!");
            } else {
                session.setAttribute("msg", "Um erro Inesperado Ocorreu");
            }
        }

        return "redirect:/register";
    }

}
