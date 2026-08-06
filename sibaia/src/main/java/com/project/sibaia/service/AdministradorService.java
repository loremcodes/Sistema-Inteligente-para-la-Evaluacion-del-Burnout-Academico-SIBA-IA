package com.project.sibaia.service;

import com.project.sibaia.entity.Administrador;

import java.util.List;

public interface AdministradorService {
    Administrador guardar(Administrador administrador);
    Administrador buscarPorId(Long id);
    List<Administrador> listar();
    void eliminarUsuario(Long id);
    Administrador buscarPorUsuario(String usuario);
}
