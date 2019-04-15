package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ciclo")
public class Ciclo extends AbstractDocument {

    private String name;

    public Ciclo() {
    }

    public Ciclo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
