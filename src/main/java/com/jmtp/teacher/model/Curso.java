package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "curso")
public class Curso extends AbstractDocument {

    @DBRef(lazy = true)
    private List<Desempenyo> desempenyos;
    @DBRef(lazy = true)
    private List<Sesion> sesions;

    public Curso() {
    }

    public Curso(List<Desempenyo> desempenyos, List<Sesion> sesions) {
        this.desempenyos = desempenyos;
        this.sesions = sesions;
    }

    public List<Desempenyo> getDesempenyos() {
        return desempenyos;
    }

    public void setDesempenyos(List<Desempenyo> desempenyos) {
        this.desempenyos = desempenyos;
    }

    public List<Sesion> getSesions() {
        return sesions;
    }

    public void setSesions(List<Sesion> sesions) {
        this.sesions = sesions;
    }


}

