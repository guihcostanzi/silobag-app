package com.uel.silobag_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uel.silobag_app.model.Usuario;
import com.uel.silobag_app.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
    private LoginService usuarioService;

    @GetMapping("/")
    public String login(HttpSession session) {
        Object usuario = session.getAttribute("usuarioLogado");

        if (usuario != null) {
            return "redirect:/bags";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        Usuario usuario = usuarioService.autenticar(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/bags";
        } else {
            model.addAttribute("error", "E-mail ou senha inválidos!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

