package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "grado")
public class Grado extends AbstractDocument {

    @DBRef
    private Ciclo ciclo;
    /**
     * Ejemplo: "1ro" de primaria o "2do" de Secundaria
     */
    private String ordinal;
    @DBRef(lazy = true)
    private List<Curso> cursos;
    @DBRef(lazy = true)
    private List<Alumno> alumnos;

    public Grado() {
    }

    public Grado(Ciclo ciclo, String ordinal, List<Curso> cursos) {
        this.ciclo = ciclo;
        this.ordinal = ordinal;
        this.cursos = cursos;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public String getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(String ordinal) {
        this.ordinal = ordinal;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}

