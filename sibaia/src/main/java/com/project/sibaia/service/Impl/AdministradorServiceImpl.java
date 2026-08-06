package com.project.sibaia.service.Impl;

import com.project.sibaia.entity.Administrador;
import com.project.sibaia.repository.AdministradorRepository;
import com.project.sibaia.service.AdministradorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {
    private final AdministradorRepository repository;

    public AdministradorServiceImpl(AdministradorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Administrador guardar(Administrador administrador) {
        return repository.save(administrador);
    }

    @Override
    public Administrador buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Administrador> listar() {
        return repository.findAll();
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Administrador buscarPorUsuario(String usuario) {
        return repository.findByUsuario(usuario).orElse(null);
    }
}
