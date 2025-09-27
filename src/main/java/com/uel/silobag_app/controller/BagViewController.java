package com.uel.silobag_app.controller;

import com.uel.silobag_app.model.dto.BagAddDTO;
import com.uel.silobag_app.model.dto.BagUpdateDTO;
import com.uel.silobag_app.service.BagService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.UUID;

@Controller
@RequestMapping("/bags")
public class BagViewController {

    @Autowired
    private BagService bagService;

    @GetMapping
    public String listarBags(Model model) {
        model.addAttribute("bags", bagService.listar());
        return "bags/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormNovo(Model model) {
        model.addAttribute("bagAddDto", new BagAddDTO(null, null, null, null));
        return "bags/form";
    }

    @PostMapping
    public String adicionarBag(BagAddDTO bagAddDTO, RedirectAttributes redirectAttributes) {
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
    public String atualizarBag(@PathVariable UUID uid, BagUpdateDTO bagUpdateDTO, RedirectAttributes redirectAttributes) {
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