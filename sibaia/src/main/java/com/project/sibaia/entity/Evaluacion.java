package com.project.sibaia.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluacion")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="estudiante_id")
    private Estudiante estudiante;
    private Integer ba1, ba2,ba3, ba4, ba5, ba6, ba7, ba8, ba9, ba10, ba11, ba12, ba13, ba14, ba15, ba16, ba17, ba18, ba19, ba20;
    private Integer agotamiento, cinismo, baja_eficacia, estres;
    private Integer puntajeTotal;
    private String nivelBurnout;
    @Column(columnDefinition = "TEXT")
    private String recomendaciones;
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Evaluacion(){}

    public Evaluacion(Long id, Estudiante estudiante, Integer ba1, Integer ba2, Integer ba3, Integer ba4, Integer ba5, Integer ba6, Integer ba7, Integer ba8, Integer ba9, Integer ba10, Integer ba11, Integer ba12, Integer ba13, Integer ba14, Integer ba15, Integer ba16, Integer ba17, Integer ba18, Integer ba19, Integer ba20, Integer agotamiento, Integer cinismo, Integer baja_eficacia, Integer estres, Integer puntajeTotal, String nivelBurnout, String recomendaciones, LocalDateTime fechaRegistro) {
        this.id = id;
        this.estudiante = estudiante;
        this.ba1 = ba1;
        this.ba2 = ba2;
        this.ba3 = ba3;
        this.ba4 = ba4;
        this.ba5 = ba5;
        this.ba6 = ba6;
        this.ba7 = ba7;
        this.ba8 = ba8;
        this.ba9 = ba9;
        this.ba10 = ba10;
        this.ba11 = ba11;
        this.ba12 = ba12;
        this.ba13 = ba13;
        this.ba14 = ba14;
        this.ba15 = ba15;
        this.ba16 = ba16;
        this.ba17 = ba17;
        this.ba18 = ba18;
        this.ba19 = ba19;
        this.ba20 = ba20;
        this.agotamiento = agotamiento;
        this.cinismo = cinismo;
        this.baja_eficacia = baja_eficacia;
        this.estres = estres;
        this.puntajeTotal = puntajeTotal;
        this.nivelBurnout = nivelBurnout;
        this.recomendaciones = recomendaciones;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Integer getBa1() {
        return ba1;
    }

    public Integer getBa2() {
        return ba2;
    }

    public Integer getBa3() {
        return ba3;
    }

    public Integer getBa4() {
        return ba4;
    }

    public Integer getBa5() {
        return ba5;
    }

    public Integer getBa6() {
        return ba6;
    }

    public Integer getBa7() {
        return ba7;
    }

    public Integer getBa8() {
        return ba8;
    }

    public Integer getBa9() {
        return ba9;
    }

    public Integer getBa10() {
        return ba10;
    }

    public Integer getBa11() {
        return ba11;
    }

    public Integer getBa12() {
        return ba12;
    }

    public Integer getBa13() {
        return ba13;
    }

    public Integer getBa14() {
        return ba14;
    }

    public Integer getBa15() {
        return ba15;
    }

    public Integer getBa16() {
        return ba16;
    }

    public Integer getBa17() {
        return ba17;
    }

    public Integer getBa18() {
        return ba18;
    }

    public Integer getBa19() {
        return ba19;
    }

    public Integer getBa20() {
        return ba20;
    }

    public Integer getAgotamiento() {
        return agotamiento;
    }

    public Integer getCinismo() {
        return cinismo;
    }

    public Integer getBaja_eficacia() {
        return baja_eficacia;
    }

    public Integer getEstres() {
        return estres;
    }

    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }

    public String getNivelBurnout() {
        return nivelBurnout;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setBa1(Integer ba1) {
        this.ba1 = ba1;
    }

    public void setBa2(Integer ba2) {
        this.ba2 = ba2;
    }

    public void setBa3(Integer ba3) {
        this.ba3 = ba3;
    }

    public void setBa4(Integer ba4) {
        this.ba4 = ba4;
    }

    public void setBa5(Integer ba5) {
        this.ba5 = ba5;
    }

    public void setBa6(Integer ba6) {
        this.ba6 = ba6;
    }

    public void setBa7(Integer ba7) {
        this.ba7 = ba7;
    }

    public void setBa8(Integer ba8) {
        this.ba8 = ba8;
    }

    public void setBa9(Integer ba9) {
        this.ba9 = ba9;
    }

    public void setBa10(Integer ba10) {
        this.ba10 = ba10;
    }

    public void setBa11(Integer ba11) {
        this.ba11 = ba11;
    }

    public void setBa12(Integer ba12) {
        this.ba12 = ba12;
    }

    public void setBa13(Integer ba13) {
        this.ba13 = ba13;
    }

    public void setBa14(Integer ba14) {
        this.ba14 = ba14;
    }

    public void setBa15(Integer ba15) {
        this.ba15 = ba15;
    }

    public void setBa16(Integer ba16) {
        this.ba16 = ba16;
    }

    public void setBa17(Integer ba17) {
        this.ba17 = ba17;
    }

    public void setBa18(Integer ba18) {
        this.ba18 = ba18;
    }

    public void setBa19(Integer ba19) {
        this.ba19 = ba19;
    }

    public void setBa20(Integer ba20) {
        this.ba20 = ba20;
    }

    public void setAgotamiento(Integer agotamiento) {
        this.agotamiento = agotamiento;
    }

    public void setCinismo(Integer cinismo) {
        this.cinismo = cinismo;
    }

    public void setBaja_eficacia(Integer baja_eficacia) {
        this.baja_eficacia = baja_eficacia;
    }

    public void setEstres(Integer estres) {
        this.estres = estres;
    }

    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public void setNivelBurnout(String nivelBurnout) {
        this.nivelBurnout = nivelBurnout;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
