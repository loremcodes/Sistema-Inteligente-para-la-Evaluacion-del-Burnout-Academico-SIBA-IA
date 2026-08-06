package com.project.sibaia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")

public class Administrador{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String usuario;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 100)
    private  String nombre;

    public Administrador(){}
    public Administrador(Long id, String usuario, String password, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
