package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "alumno")
public class Alumno extends AbstractDocument {

    private String firstname, lastname;
    private Date bornth;
    @Indexed(unique = true)
    @Nullable
    private String dni;
    @DBRef(lazy = true)
    private List<Evaluacion> evaluaciones;



    public Alumno() {
    }

    public Alumno(String firstname, String lastname, Date bornth, String dni) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornth = bornth;
        this.dni = dni;
    }

    public Alumno(String firstname, String lastname, Date bornth, String dni, List<Evaluacion> evaluaciones) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornth = bornth;
        this.dni = dni;
        this.evaluaciones = evaluaciones;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBornth() {
        return bornth;
    }

    public void setBornth(Date bornth) {
        this.bornth = bornth;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
