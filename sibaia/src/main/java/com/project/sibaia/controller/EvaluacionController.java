package com.project.sibaia.controller;

import com.project.sibaia.entity.Evaluacion;
import com.project.sibaia.service.EvaluacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/admin/evaluacion")
public class EvaluacionController {

    private final EvaluacionService service;

    public EvaluacionController(EvaluacionService service) {
        this.service = service;
    }

    @PostMapping("/detalle")
    public String mostrarDetalle(@RequestParam("id") Long id, Model model) {
        Evaluacion evaluacion = service.buscarPorId(id);
        model.addAttribute("detalle", evaluacion);
        return "admin/detalle";
    }
}