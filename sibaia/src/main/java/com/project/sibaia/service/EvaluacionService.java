package com.project.sibaia.service;

import com.project.sibaia.entity.Evaluacion;

import java.util.List;
import java.util.Map;

public interface EvaluacionService {
    Evaluacion guardar(Evaluacion evaluacion);

    Evaluacion buscarPorId(Long id);
    List<Evaluacion> listar();
    List<Evaluacion> buscarConFiltros(
            Long escuelaId,
            String ciclo,
            String nivel
    );
    //void eliminar(Long id);
    void procesarEvaluacion(Evaluacion evaluacion);

    List<Map<String, Object>> obtenerBurnoutPorCarrera();
    List<Map<String, Object>> obtenerBurnoutPorCiclo();
    List<Map<String, Object>> obtenerBurnoutPorSexo();

}
