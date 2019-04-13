package com.jmtp.teacher.model;


public class CursoAlumno {

    private int id, cursoId, alumnoId;

    public CursoAlumno() {
    }

    public CursoAlumno(int id, int cursoId, int alumnoId) {
        this.id = id;
        this.cursoId = cursoId;
        this.alumnoId = alumnoId;
    }

    public CursoAlumno(int cursoId, int alumnoId) {
        this.cursoId = cursoId;
        this.alumnoId = alumnoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }
}
