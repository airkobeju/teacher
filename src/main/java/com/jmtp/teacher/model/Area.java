package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "area")
public class Area extends AbstractDocument {

    private String name, description;
    @Nullable
    private String abbreviature;
    @Indexed(unique = true)
    private int code;
    @DBRef(lazy = true)
    private List<Competencia> competencias = new ArrayList<>();


    public Area() {
    }

    public Area(int code, String name) {
        this.code = code;
        this.name = name;
        this.description = null;
    }

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public String getAbbreviature() {
        return abbreviature;
    }

    public void setAbbreviature(@Nullable String abbreviature) {
        this.abbreviature = abbreviature;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    @Override
    public String toString() {
        return name + " - " + abbreviature;
    }
}
