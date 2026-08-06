package com.project.sibaia.service;

import com.project.sibaia.entity.EscuelaProfesional;

import java.util.List;

public interface EscuelaProfesionalService {
    List<EscuelaProfesional> listar();
    EscuelaProfesional guardar(EscuelaProfesional escuelaProfesional);
    EscuelaProfesional buscarPorId(Long id);
    void eliminar(Long id);
}
