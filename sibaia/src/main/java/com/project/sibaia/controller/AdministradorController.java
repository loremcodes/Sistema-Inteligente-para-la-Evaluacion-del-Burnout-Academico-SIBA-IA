package com.project.sibaia.controller;

import com.project.sibaia.entity.Administrador;
import com.project.sibaia.entity.Estudiante;
import com.project.sibaia.entity.Evaluacion;
import com.project.sibaia.service.AdministradorService;
import com.project.sibaia.service.EscuelaProfesionalService;
import com.project.sibaia.service.EstudianteService;
import com.project.sibaia.service.EvaluacionService;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    private final EstudianteService estudianteService;
    private final EvaluacionService evaluacionService; // Renombrado para mayor claridad
    private final EscuelaProfesionalService escuelaProfesionalService;
    private final AdministradorService administradorService;
    private final PasswordEncoder passwordEncoder;

    public AdministradorController(
            EstudianteService estudianteService,
            EvaluacionService evaluacionService,
            EscuelaProfesionalService escuelaProfesionalService,
            AdministradorService administradorService,
            PasswordEncoder passwordEncoder) {
        this.estudianteService = estudianteService;
        this.evaluacionService = evaluacionService;
        this.escuelaProfesionalService = escuelaProfesionalService;
        this.administradorService = administradorService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/evaluaciones")
    public String listarEvaluaciones(Model model) {
        model.addAttribute("evaluaciones", evaluacionService.listar());
        return "admin/evaluaciones";
    }

    @GetMapping("/evaluacion/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Evaluacion evaluacion = evaluacionService.buscarPorId(id);
        model.addAttribute("detalle", evaluacion);
        return "admin/detalle";
    }

    @GetMapping("/reportes")
    public String reportes(
            @RequestParam(required = false) Long escuela,
            @RequestParam(required = false) String ciclo,
            @RequestParam(required = false) String nivel,
            Model model) {

        model.addAttribute("evaluaciones", evaluacionService.buscarConFiltros(escuela, ciclo, nivel));
        model.addAttribute("escuelas", escuelaProfesionalService.listar());
        return "admin/reportes";
    }

    @GetMapping("/exportar/excel")
    public void exportarExcel(
            @RequestParam(required = false) Long escuela,
            @RequestParam(required = false) String ciclo,
            @RequestParam(required = false) String nivel,
            HttpServletResponse response) throws Exception {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=resultados_burnout_academico.xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Evaluaciones");

            Row header = sheet.createRow(0);
            String[] columns = {
                    "Nombres", "Apellidos", "Sexo", "Edad",
                    "Escuela Profesional", "Ciclo Académico",
                    "Promedio Ponderado", "Fecha de registro",
                    "Ptj. Agotamiento", "Ptj. Cinismo", "Ptj. Baja Eficacia", "Ptj. Estrés",
                    "Ptj. Total", "Nivel de Burnout", "Recomendaciones"
            };

            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            List<Evaluacion> lista = evaluacionService.buscarConFiltros(escuela, ciclo, nivel);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            int rowNum = 1;

            for (Evaluacion e : lista) {
                Row row = sheet.createRow(rowNum++);
                Estudiante est = e.getEstudiante();

                row.createCell(0).setCellValue(est != null && est.getNombres() != null ? est.getNombres() : "");
                row.createCell(1).setCellValue(est != null && est.getApellidos() != null ? est.getApellidos() : "");
                row.createCell(2).setCellValue(est != null && est.getSexo() != null ? est.getSexo() : "");
                row.createCell(3).setCellValue(est != null ? est.getEdad() : 0);

                row.createCell(4).setCellValue(
                        (est != null && est.getEscuelaProfesional() != null)
                                ? est.getEscuelaProfesional().getNombre()
                                : ""
                );

                row.createCell(5).setCellValue(
                        (est != null && est.getCicloAcademico() != null) ? est.getCicloAcademico() : ""
                );

                row.createCell(6).setCellValue(
                        (est != null && est.getPromedioPonderado() != null) ? est.getPromedioPonderado() : 0.0
                );

                row.createCell(7).setCellValue(
                        e.getFechaRegistro() != null
                                ? e.getFechaRegistro().format(formatter)
                                : ""
                );

                row.createCell(8).setCellValue(e.getAgotamiento() != null ? e.getAgotamiento() : 0);
                row.createCell(9).setCellValue(e.getCinismo() != null ? e.getCinismo() : 0);
                row.createCell(10).setCellValue(e.getBaja_eficacia() != null ? e.getBaja_eficacia() : 0);
                row.createCell(11).setCellValue(e.getEstres() != null ? e.getEstres() : 0);
                row.createCell(12).setCellValue(e.getPuntajeTotal() != null ? e.getPuntajeTotal() : 0);
                row.createCell(13).setCellValue(e.getNivelBurnout() != null ? e.getNivelBurnout() : "");
                row.createCell(14).setCellValue(e.getRecomendaciones() != null ? e.getRecomendaciones() : "");
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(response.getOutputStream());
        }
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", administradorService.listar());
        model.addAttribute("usuario", new Administrador());
        return "admin/usuarios";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Administrador administrador) {
        // Validación segura contra NullPointerException
        boolean tieneNuevaPassword = administrador.getPassword() != null && !administrador.getPassword().trim().isEmpty();

        if (administrador.getId() == null || tieneNuevaPassword) {
            administrador.setPassword(passwordEncoder.encode(administrador.getPassword()));
        } else {
            Administrador adminActual = administradorService.buscarPorId(administrador.getId());
            administrador.setPassword(adminActual.getPassword());
        }
        administradorService.guardar(administrador);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Administrador adminEncontrado = administradorService.buscarPorId(id);
        if (adminEncontrado != null) {
            adminEncontrado.setPassword(""); // Limpia el pass para la vista
        }
        model.addAttribute("usuario", adminEncontrado);
        model.addAttribute("usuarios", administradorService.listar());
        return "admin/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        administradorService.eliminarUsuario(id);
        return "redirect:/admin/usuarios";
    }
}