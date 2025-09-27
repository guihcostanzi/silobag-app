package com.uel.silobag_app.controller;

import java.sql.SQLException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uel.silobag_app.model.dto.BagAddDTO;
import com.uel.silobag_app.model.dto.BagUpdateDTO;
import com.uel.silobag_app.service.BagService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bags")
public class BagViewController {

    @Autowired
    private BagService bagService;

    @GetMapping
    public String listarBags(@RequestParam(required = false) String busca, Model model) {
        if (busca != null && !busca.trim().isEmpty()) {
            model.addAttribute("bags", bagService.buscarPorFiltros(busca));
        } else {
            model.addAttribute("bags", bagService.listar());
        }
        model.addAttribute("busca", busca);
        return "bags/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormNovo(Model model) {
        model.addAttribute("bagAddDto", new BagAddDTO(null, null, null, null));
        return "bags/form";
    }

    @PostMapping
    public String adicionarBag(@Valid BagAddDTO bagAddDTO,BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    	
    	// Tratamento de erro de @Valid, já que é lançado antes do try catch, temos que usar BindingResult
    	if (bindingResult.hasErrors()) {
         
            String errorMsg = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            redirectAttributes.addFlashAttribute("error", "Erro de validação: " + errorMsg);
            
            return "redirect:/bags";
        }
    	
        try {
            bagService.adicionar(bagAddDTO);
            redirectAttributes.addFlashAttribute("success", "Bag cadastrada com sucesso!");
        } catch (BadRequestException | SQLException e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar bag: " + e.getMessage());
        }
        return "redirect:/bags";
    }

    @GetMapping("/editar/{uid}")
    public String mostrarFormEditar(@PathVariable UUID uid, Model model) {
        var bagExistente = bagService.buscar(uid);
        var bagUpdateDto = new BagUpdateDTO(bagExistente.volume(), bagExistente.produto(), bagExistente.uid());
        
        model.addAttribute("bagUpdateDto", bagUpdateDto);
        model.addAttribute("bagCodigo", bagExistente.codigo());
        model.addAttribute("bagCapacidade", bagExistente.capacidade());

        return "bags/form";
    }

    @PutMapping("/{uid}")
    public String atualizarBag(@PathVariable UUID uid, @Valid BagUpdateDTO bagUpdateDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        
    	// Tratamento de erro de @Valid, já que é lançado antes do try catch, temos que usar BindingResult
    	if (bindingResult.hasErrors()) {
         
            String errorMsg = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            redirectAttributes.addFlashAttribute("error", "Erro de validação: " + errorMsg);
            
            return "redirect:/bags";
        }
    	
    	try {
            bagService.atualizar(bagUpdateDTO);
            redirectAttributes.addFlashAttribute("success", "Bag atualizada com sucesso!");
        } catch (BadRequestException e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar bag: " + e.getMessage());
        }
        return "redirect:/bags";
    }

    @DeleteMapping("/remover/{uid}")
    public String removerBag(@PathVariable UUID uid, RedirectAttributes redirectAttributes) {
        try {
            bagService.remover(uid);
            redirectAttributes.addFlashAttribute("success", "Bag removida com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao remover bag: " + e.getMessage());
        }
        return "redirect:/bags";
    }
}