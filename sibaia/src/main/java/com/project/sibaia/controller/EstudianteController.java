package com.project.sibaia.controller;

import com.project.sibaia.entity.Estudiante;
import com.project.sibaia.entity.Evaluacion;
import com.project.sibaia.service.EscuelaProfesionalService;
import com.project.sibaia.service.EstudianteService;
import com.project.sibaia.service.EvaluacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/estudiante")
@SessionAttributes({"estudiante", "evaluacion"})
public class EstudianteController {

    private final EstudianteService service;
    private final EscuelaProfesionalService escuelaProfesionalService;
    private final EvaluacionService evaluacionService;

    public EstudianteController(EstudianteService service, EscuelaProfesionalService escuelaProfesionalService, EvaluacionService evaluacionService) {
        this.service = service;
        this.escuelaProfesionalService = escuelaProfesionalService;
        this.evaluacionService = evaluacionService;
    }

    // Define la instancia base manejada por @SessionAttributes
    @ModelAttribute("estudiante")
    public Estudiante estudiante() {
        return new Estudiante();
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Carga la lista de escuelas para el combo
        model.addAttribute("escuelas", escuelaProfesionalService.listar());
        return "estudiante/formulario";
    }

    @PostMapping("/guardar-temporal")
    public String procesarFormulario(@ModelAttribute("estudiante") Estudiante estudiante) {
        // Al usar @SessionAttributes, los datos enviados en el formulario
        // se vinculan al estudiante guardado en la sesión HTTP
        return "redirect:/estudiante/cuestionario";
    }

    @GetMapping("/cuestionario")
    public String mostrarCuestionario(@ModelAttribute("estudiante") Estudiante estudiante, Model model) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setEstudiante(estudiante);
        model.addAttribute("evaluacion", evaluacion);
        return "estudiante/cuestionario";
    }

    @PostMapping("/guardar-evaluacion")
    public String guardarEvaluacion(
            @ModelAttribute("estudiante") Estudiante estudiante,
            @ModelAttribute("evaluacion") Evaluacion evaluacion,
            SessionStatus sessionStatus) {

        try {
            // 1. Guardamos primero al estudiante en SQLite
            Estudiante estudianteGuardado = service.guardar(estudiante);

            // 2. Asociamos el estudiante guardado a la evaluación y procesamos
            evaluacion.setEstudiante(estudianteGuardado);
            evaluacionService.procesarEvaluacion(evaluacion);

            // 3. Limpiamos la sesión de Spring
            sessionStatus.setComplete();

            return "redirect:/estudiante/agradecimiento";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/estudiante/cuestionario?error";
        }
    }

    @GetMapping("/agradecimiento")
    public String mostrarAgradecimiento() {
        return "estudiante/agradecimiento";
    }
}