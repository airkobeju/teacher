package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ciclo")
public class Ciclo extends AbstractDocument {

    private int code;
    private String name;
    @DBRef
    private List<GradoBase> grados = new ArrayList<>();
    @DBRef
    @Nullable
    private List<Competencia> competencias = new ArrayList<>();

    public Ciclo() {
    }

    public Ciclo(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Ciclo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<GradoBase> getGrados() {
        return grados;
    }

    public void setGrados(List<GradoBase> grados) {
        this.grados = grados;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    @Override
    public String toString() {
        return name;
    }
}
