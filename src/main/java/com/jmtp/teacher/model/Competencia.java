package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "competencia")
public class Competencia extends AbstractDocument {

    private String description;
    @Nullable
    private String detailed;
    @DBRef(lazy = true)
    @Nullable
    private List<Capacidad> capacidades = new ArrayList<>();
    @DBRef(lazy = true)
    @Nullable
    private List<Desempenyo> desempenyos = new ArrayList<>();
    @Indexed(unique = true)
    private int code;

    public Competencia() {
    }

    public Competencia(int code, String description, @Nullable String detailed) {
        this.description = description;
        this.detailed = detailed;
        this.code = code;
    }

    public Competencia(int code, String description) {
        this.description = description;
        this.code = code;
    }

    public Competencia(String description, List<Capacidad> capacidades, List<Desempenyo> desempenyos) {
        this.description = description;
        this.capacidades = capacidades;
        this.desempenyos = desempenyos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Capacidad> getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(List<Capacidad> capacidades) {
        this.capacidades = capacidades;
    }

    public List<Desempenyo> getDesempenyos() {
        return desempenyos;
    }

    public void setDesempenyos(List<Desempenyo> desempenyos) {
        this.desempenyos = desempenyos;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(@Nullable String detailed) {
        this.detailed = detailed;
    }
}


