package com.uel.silobag_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/silobagvw")
@CrossOrigin("*")
public class ViewController {
	
	@GetMapping("/")
    public String sayHello(Model model) {
        
        // Adiciona um atributo ao "model".
        // Este atributo "message" estará disponível dentro do HTML.
        model.addAttribute("message", "Bem-vindo ao Silo-Bag App!");

        // O Spring Boot automaticamente procurará por "inicio.html"
        // dentro da pasta /src/main/resources/templates/
        return "inicio";
    }

}
