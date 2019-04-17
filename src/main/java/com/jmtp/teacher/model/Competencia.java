package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "competencia")
public class Competencia extends AbstractDocument {

    @DBRef
    private Area area;

    @DBRef
    private List<Ciclo> ciclos = new ArrayList<>();

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

    public Competencia(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public Competencia(int code, String description, String detailed) {
        this.code = code;
        this.description = description;
        this.detailed = detailed;
    }

    public Competencia(int code, Area area, List<Ciclo> ciclos, String description, @Nullable String detailed) {
        this.area = area;
        this.ciclos = ciclos;
        this.description = description;
        this.detailed = detailed;
        this.code = code;
    }

    public Competencia(int code, Area area, List<Ciclo> ciclos, String description) {
        this.area = area;
        this.ciclos = ciclos;
        this.description = description;
        this.code = code;
    }

    public Competencia(int code, Area area, String description, @Nullable String detailed) {
        this.area = area;
        this.description = description;
        this.detailed = detailed;
        this.code = code;
    }

    public Competencia(int code, Area area, String description) {
        this.area = area;
        this.description = description;
        this.code = code;
    }

    public Competencia(String description, List<Capacidad> capacidades, List<Desempenyo> desempenyos) {
        this.description = description;
        this.capacidades = capacidades;
        this.desempenyos = desempenyos;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Ciclo> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<Ciclo> ciclos) {
        this.ciclos = ciclos;
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


