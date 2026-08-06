package com.project.sibaia.service.Impl;

import com.project.sibaia.entity.Evaluacion;
import com.project.sibaia.repository.EvaluacionRepository;
import com.project.sibaia.service.EvaluacionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {
    private final EvaluacionRepository repository;

    public EvaluacionServiceImpl(EvaluacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Evaluacion guardar(Evaluacion evaluacion) {
        return repository.save(evaluacion);
    }

    @Override
    public List<Evaluacion> listar() {
        return repository.findAll();
    }

    @Override
    public List<Evaluacion> buscarConFiltros(Long escuelaId, String ciclo, String nivel) {
        return repository.buscarConFiltros(
                escuelaId,
                ciclo,
                nivel
        );
    }

    @Override
    public Evaluacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public void procesarEvaluacion(Evaluacion evaluacion) {
        evaluacion.setFechaRegistro(LocalDateTime.now());

        int agotamiento = evaluacion.getBa1() + evaluacion.getBa2()
                + evaluacion.getBa3() + evaluacion.getBa4()
                + evaluacion.getBa5();

        int cinismo = evaluacion.getBa6() + evaluacion.getBa7()
                + evaluacion.getBa8() + evaluacion.getBa9()
                + evaluacion.getBa10();

        int bajaEficacia = evaluacion.getBa11() + evaluacion.getBa12()
                + evaluacion.getBa13() + evaluacion.getBa14()
                + evaluacion.getBa15();

        int estres = evaluacion.getBa16() + evaluacion.getBa17()
                + evaluacion.getBa18() + evaluacion.getBa19()
                + evaluacion.getBa20();

        int total = agotamiento + cinismo + bajaEficacia + estres;

        String nivel = (total >= 20 && total <= 46) ? "BAJO" : (total <= 73) ? "MODERADO" : (total <= 100) ? "ALTO" : "NO VÁLIDO";

        StringBuilder recomendaciones = new StringBuilder();

        if (nivel.equals("BAJO")) {
            recomendaciones.append("El estudiante presenta bajo nivel de burnout académico. Se recomienda mantener hábitos saludables de estudio y descanso.\n");
        }

        if (nivel.equals("MODERADO")) {
            recomendaciones.append("El estudiante presenta nivel moderado de burnout académico. Se recomienda reorganizar la carga académica, mejorar la planificación y fortalecer estrategias de afrontamiento.\n");
        }

        if (nivel.equals("ALTO")) {
            recomendaciones.append("El estudiante presenta alto nivel de burnout académico. Se recomienda tutoría académica, orientación psicológica y seguimiento personalizado.\n");
        }

        if (agotamiento >= 18) {
            recomendaciones.append("Reducir la sobrecarga académica, establecer pausas activas y mejorar la calidad del descanso.\n");
        }

        if (cinismo >= 18) {
            recomendaciones.append("Fortalecer la motivación académica mediante metas personales, participación activa y actividades significativas.\n");
        }

        if (bajaEficacia >= 18) {
            recomendaciones.append("Reforzar la autoeficacia académica mediante metas alcanzables, retroalimentación docente y seguimiento del progreso.\n");
        }

        if (estres >= 18) {
            recomendaciones.append("Aplicar técnicas de manejo del estrés como respiración, planificación semanal y organización de tareas.\n");
        }

        evaluacion.setAgotamiento(agotamiento);
        evaluacion.setCinismo(cinismo);
        evaluacion.setBaja_eficacia(bajaEficacia);
        evaluacion.setEstres(estres);
        evaluacion.setPuntajeTotal(total);
        evaluacion.setNivelBurnout(nivel);
        evaluacion.setRecomendaciones(recomendaciones.toString());

        repository.save(evaluacion);
    }

    @Override
    public List<Map<String, Object>> obtenerBurnoutPorCarrera() {
        return repository.findBurnoutPorCarrera();
    }

    @Override
    public List<Map<String, Object>> obtenerBurnoutPorCiclo() {
        return repository.findBurnoutPorCiclo();
    }

    @Override
    public List<Map<String, Object>> obtenerBurnoutPorSexo() {
        return repository.findBurnoutPorSexo();
    }

}
