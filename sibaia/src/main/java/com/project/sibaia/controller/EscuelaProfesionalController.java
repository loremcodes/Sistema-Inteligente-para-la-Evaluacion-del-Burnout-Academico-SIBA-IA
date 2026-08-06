package com.project.sibaia.controller;

import com.project.sibaia.entity.EscuelaProfesional;
import com.project.sibaia.service.EscuelaProfesionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/escuelas")
public class EscuelaProfesionalController {

    private final EscuelaProfesionalService service;

    public EscuelaProfesionalController(EscuelaProfesionalService service) {
        this.service = service;
    }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("escuelas", service.listar());
        model.addAttribute("escuela", new EscuelaProfesional()); // Para th:object="${escuela}"
        return "admin/escuelas"; // Tu archivo en templates/admin/escuelas.html
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EscuelaProfesional escuela) {
        service.guardar(escuela);
        return "redirect:/admin/escuelas";
    }

    @PostMapping("/editar")
    public String editar(@RequestParam("id") Long id, Model model) {
        model.addAttribute("escuela", service.buscarPorId(id));
        model.addAttribute("escuelas", service.listar());
        return "admin/escuelas";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Long id) {
        service.eliminar(id);
        return "redirect:/admin/escuelas";
    }
}