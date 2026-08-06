package com.project.sibaia.repository;

import com.project.sibaia.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    @Query("SELECT est.escuelaProfesional.nombre as carrera, e.nivelBurnout as nivel, COUNT(e) as total " +
            "FROM Evaluacion e " +
            "JOIN e.estudiante est " +
            "GROUP BY est.escuelaProfesional.nombre, e.nivelBurnout " +
            "ORDER BY total DESC")
    List<Map<String, Object>> findBurnoutPorCarrera();

    @Query("SELECT est.cicloAcademico as ciclo, e.nivelBurnout as nivel, COUNT(e) as total " +
            "FROM Evaluacion e " +
            "JOIN e.estudiante est " +
            "GROUP BY est.cicloAcademico, e.nivelBurnout " +
            "ORDER BY est.cicloAcademico ASC")
    List<Map<String, Object>> findBurnoutPorCiclo();

    @Query("SELECT est.sexo as sexo, e.nivelBurnout as nivel, COUNT(e) as total " +
            "FROM Evaluacion e " +
            "JOIN e.estudiante est " +
            "GROUP BY est.sexo, e.nivelBurnout")
    List<Map<String, Object>> findBurnoutPorSexo();

    @Query("""
    SELECT e
    FROM Evaluacion e
    JOIN e.estudiante est
    WHERE
    (:escuelaId IS NULL OR est.escuelaProfesional.id = :escuelaId)
    AND (:ciclo IS NULL OR :ciclo = '' OR est.cicloAcademico = :ciclo)
    AND (:nivel IS NULL OR :nivel = '' OR e.nivelBurnout = :nivel)
    """)
    List<Evaluacion> buscarConFiltros(
            @Param("escuelaId") Long escuelaId,
            @Param("ciclo") String ciclo,
            @Param("nivel") String nivel
    );
}
