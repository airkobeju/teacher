package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "capacidad")
public class Capacidad extends AbstractDocument {

    @DBRef(lazy = true)
    private List<Desempenyo> desempenyos;
    private String description;
    @Nullable
    private String detailed = null;

    public Capacidad() {
    }

    public Capacidad(String description) {
        this.description = description;
    }

    public Capacidad(String description, String detailed) {
        this.description = description;
        this.detailed = detailed;
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

    @Nullable
    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(@Nullable String detailed) {
        this.detailed = detailed;
    }
}
