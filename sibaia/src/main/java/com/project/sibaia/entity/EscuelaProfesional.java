package com.project.sibaia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "escuela_profesional")
public class EscuelaProfesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(nullable = false, length = 100)
    private String nombre;

    public EscuelaProfesional(){}
    public EscuelaProfesional(Long id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
