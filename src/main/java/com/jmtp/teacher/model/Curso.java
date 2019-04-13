package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "curso")
public class Curso extends AbstractDocument {

    private Area area;
    @DBRef
    private Grado grado;
    @DBRef(lazy = true)
    private List<Desempenyo> desempenyos;
    @DBRef(lazy = true)
    private List<Sesion> sesions;




    public Curso() {
    }

    public Curso(Area area, Grado grado, List<Desempenyo> desempenyos, List<Sesion> sesions) {
        this.area = area;
        this.grado = grado;
        this.desempenyos = desempenyos;
        this.sesions = sesions;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public List<Desempenyo> getDesempenyos() {
        return desempenyos;
    }

    public void setDesempenyos(List<Desempenyo> desempenyos) {
        this.desempenyos = desempenyos;
    }

    public List<Sesion> getSesions() {
        return sesions;
    }

    public void setSesions(List<Sesion> sesions) {
        this.sesions = sesions;
    }

}
