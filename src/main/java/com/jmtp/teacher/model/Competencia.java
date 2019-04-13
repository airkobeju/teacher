package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "competencia")
public class Competencia extends AbstractDocument {

    @DBRef
    private Area area;
    @DBRef(lazy = true)
    @Nullable
    private Ciclo ciclo;
    private String description;
    @DBRef(lazy = true)
    private List<Capacidad> capacidades;
    @DBRef(lazy = true)
    private List<Desempenyo> desempenyos;
    @Indexed(unique = true)
    private int code;

    public Competencia() {
    }

    public Competencia(Area area,int code, String description) {
        this.area = area;
        this.ciclo = null;
        this.code = code;
        this.description = description;
    }
    public Competencia(Area area,Ciclo ciclo, int code, String description) {
        this.area = area;
        this.ciclo = ciclo;
        this.code = code;
        this.description = description;
    }

    public Competencia(Area area, String description, List<Capacidad> capacidades, List<Desempenyo> desempenyos) {
        this.area = area;
        this.description = description;
        this.capacidades = capacidades;
        this.desempenyos = desempenyos;
    }

    public Competencia(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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
    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(@Nullable Ciclo ciclo) {
        this.ciclo = ciclo;
    }
}


