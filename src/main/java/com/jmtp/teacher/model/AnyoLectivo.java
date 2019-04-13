package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "anyo_lectivo")
public class AnyoLectivo extends AbstractDocument {

    @Indexed(unique = true)
    private int  year;
    private String frace;
    @DBRef(lazy = true)
    private List<Grado> grados;

    public AnyoLectivo() {
    }

    public AnyoLectivo(int year, String frace) {
        this.year = year;
        this.frace = frace;
    }

    public AnyoLectivo(int year, String frace, List<Grado> grados) {
        this.year = year;
        this.frace = frace;
        this.grados = grados;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFrace() {
        return frace;
    }

    public void setFrace(String frace) {
        this.frace = frace;
    }

    public List<Grado> getGrados() {
        return grados;
    }

    public void setGrados(List<Grado> grados) {
        this.grados = grados;
    }
}

