package com.project.sibaia.service;

import com.project.sibaia.entity.Estudiante;

import java.util.List;

public interface EstudianteService {
    List<Estudiante> listar();
    Estudiante guardar(Estudiante estudiante);
    Estudiante buscarPorId(Long id);
}
