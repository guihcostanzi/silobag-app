package com.uel.silobag_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class InicioViewController {
	
	@GetMapping
    public String mostrarInicio() {
        return "inicio";
    }

}
