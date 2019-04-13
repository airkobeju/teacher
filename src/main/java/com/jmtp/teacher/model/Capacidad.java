package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "capacidad")
public class Capacidad extends AbstractDocument {

    @DBRef(lazy = true)
    private List<Desempenyo> desempenyos;
    private String description;

    public Capacidad() {
    }

    public Capacidad(List<Desempenyo> desempenyos, String description) {
        this.desempenyos = desempenyos;
        this.description = description;
    }

    public List<Desempenyo> getDesempenyos() {
        return desempenyos;
    }

    public void setDesempenyos(List<Desempenyo> desempenyos) {
        this.desempenyos = desempenyos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
