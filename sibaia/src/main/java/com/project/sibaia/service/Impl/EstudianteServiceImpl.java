package com.project.sibaia.service.Impl;

import com.project.sibaia.entity.Estudiante;
import com.project.sibaia.repository.EstudianteReporistory;
import com.project.sibaia.service.EstudianteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteReporistory repository;

    public EstudianteServiceImpl(EstudianteReporistory estudianteReporistory) {
        this.repository = estudianteReporistory;
    }

    @Override
    public List<Estudiante> listar() {
        return repository.findAll();
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public Estudiante buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
