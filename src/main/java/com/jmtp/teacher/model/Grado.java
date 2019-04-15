package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "grado")
public class Grado extends AbstractDocument {

    @DBRef
    private Ciclo ciclo;
    /**
     * Ejemplo: "1ro" de primaria o "2do" de Secundaria
     */
    private String ordinal;
    @Nullable
    private String description;
    @Nullable
    private Integer anyoLectivo;
    @DBRef(lazy = true)
    private List<Curso> cursos = new ArrayList<>();
    @DBRef(lazy = true)
    private List<Alumno> alumnos;

    public Grado() {
    }

    public Grado(Ciclo ciclo, String ordinal) {
        this.ciclo = ciclo;
        this.ordinal = ordinal;
    }

    public Grado(Ciclo ciclo, String ordinal, @Nullable String description) {
        this.ciclo = ciclo;
        this.ordinal = ordinal;
        this.description = description;
    }

    public Grado(Ciclo ciclo, String ordinal, @Nullable String description, @Nullable Integer anyoLectivo) {
        this.ciclo = ciclo;
        this.ordinal = ordinal;
        this.description = description;
        this.anyoLectivo = anyoLectivo;
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

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Nullable
    public Integer getAnyoLectivo() {
        return anyoLectivo;
    }

    public void setAnyoLectivo(@Nullable Integer anyoLectivo) {
        this.anyoLectivo = anyoLectivo;
    }

    @Override
    public String toString() {
        return ordinal;
    }
}

