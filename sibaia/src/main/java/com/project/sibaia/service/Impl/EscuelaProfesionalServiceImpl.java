package com.project.sibaia.service.Impl;

import com.project.sibaia.entity.EscuelaProfesional;
import com.project.sibaia.repository.EscuelaProfesionalRepository;
import com.project.sibaia.service.EscuelaProfesionalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscuelaProfesionalServiceImpl implements EscuelaProfesionalService {
    private final EscuelaProfesionalRepository repository;

    public EscuelaProfesionalServiceImpl(EscuelaProfesionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EscuelaProfesional> listar() {
        return repository.findAll();
    }

    @Override
    public EscuelaProfesional guardar(EscuelaProfesional escuelaProfesional) {
        return repository.save(escuelaProfesional);
    }

    @Override
    public EscuelaProfesional buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
