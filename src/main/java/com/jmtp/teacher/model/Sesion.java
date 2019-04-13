package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "sesion")
public class Sesion extends AbstractDocument {

    private String title;
    private Date dateProgram;
    private Date dateApply;
    @DBRef(lazy = true)
    private List<Evaluacion> evaluaciones;

    public Sesion() {
    }

    public Sesion(String title, Date dateProgram) {
        this.title = title;
        this.dateProgram = dateProgram;
    }

    public Sesion(String title, Date dateProgram, Date dateApply, List<Evaluacion> evaluaciones) {
        this.title = title;
        this.dateProgram = dateProgram;
        this.dateApply = dateApply;
        this.evaluaciones = evaluaciones;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateProgram() {
        return dateProgram;
    }

    public void setDateProgram(Date dateProgram) {
        this.dateProgram = dateProgram;
    }

    public Date getDateApply() {
        return dateApply;
    }

    public void setDateApply(Date dateApply) {
        this.dateApply = dateApply;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}

