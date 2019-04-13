package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "area")
public class Area extends AbstractDocument {

    private String name, description;
    @Nullable
    private String abbreviature;
    @DBRef
    private Nivel nivel;
    @Indexed(unique = true)
    private int code;


    public Area() {
    }

    public Area(int code, String name, Nivel nivel) {
        this.code = code;
        this.name = name;
        this.description = null;
        this.nivel =nivel;
    }

    public Area(String name, String description, Nivel nivel) {
        this.name = name;
        this.description = description;
        this.nivel =nivel;
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

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
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
}
