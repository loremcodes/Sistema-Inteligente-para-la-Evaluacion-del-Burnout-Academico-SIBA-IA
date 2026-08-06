package com.project.sibaia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sibaia.entity.Evaluacion;
import com.project.sibaia.service.EvaluacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final EvaluacionService service;

    public AdminDashboardController(EvaluacionService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Evaluacion> lista = service.listar();
        long total = lista.size();

        long alto = lista.stream()
                .filter(e -> "ALTO".equals(e.getNivelBurnout()))
                .count();

        long moderado = lista.stream()
                .filter(e -> "MODERADO".equals(e.getNivelBurnout()))
                .count();

        long bajo = lista.stream()
                .filter(e -> "BAJO".equals(e.getNivelBurnout()))
                .count();

        model.addAttribute("totalEvaluaciones", total);
        model.addAttribute("alto", alto);
        model.addAttribute("moderado", moderado);
        model.addAttribute("bajo", bajo);

        ObjectMapper mapper = new ObjectMapper();
        try {
            model.addAttribute("datosCarreraJson", mapper.writeValueAsString(service.obtenerBurnoutPorCarrera()));
            model.addAttribute("datosCicloJson", mapper.writeValueAsString(service.obtenerBurnoutPorCiclo()));
            model.addAttribute("datosSexoJson", mapper.writeValueAsString(service.obtenerBurnoutPorSexo()));
        } catch (Exception e) {
            model.addAttribute("error", "Error cargando métricas de gráficos: " + e.getMessage());
        }

        return "admin/dashboard";
    }
}