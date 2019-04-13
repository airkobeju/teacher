package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "evaluacion")
public class Evaluacion extends AbstractDocument {

    private String nota;
    @DBRef
    Sesion sesion;
    @DBRef
    Alumno alumno;

    public Evaluacion() {
    }

    public Evaluacion(String nota, Sesion sesion, Alumno alumno) {
        this.nota = nota;
        this.sesion = sesion;
        this.alumno = alumno;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
