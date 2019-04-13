package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nivel")
public class Nivel extends AbstractDocument {

    private String name;

    public Nivel() {
    }

    public Nivel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
