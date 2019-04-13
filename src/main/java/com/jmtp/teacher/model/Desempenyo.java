package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "desempenyo")
public class Desempenyo extends AbstractDocument {

    @DBRef(lazy = true)
    private Capacidad capacidad;
    @DBRef(lazy = true)
    private Curso curso;
    private String description;

    public Desempenyo() {
    }

    public Desempenyo(Capacidad capacidad, Curso curso, String description) {
        this.capacidad = capacidad;
        this.curso = curso;
        this.description = description;
    }

    public Desempenyo(String description) {
        this.description = description;
    }

    public Capacidad getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Capacidad capacidad) {
        this.capacidad = capacidad;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
