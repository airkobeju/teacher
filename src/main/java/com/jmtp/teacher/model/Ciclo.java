package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ciclo")
public class Ciclo extends AbstractDocument {

    @DBRef
    private Nivel nivel;
    private String name;

    public Ciclo() {
    }

    public Ciclo(String name) {
        this.name = name;
    }

    public Ciclo(Nivel nivel, String name) {
        this.nivel = nivel;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
