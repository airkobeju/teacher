package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gradobase")
public class GradoBase extends AbstractDocument {

    private String ordinal;

    public GradoBase() {
    }

    public GradoBase(String ordinal) {
        this.ordinal = ordinal;
    }

    public String getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(String ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String toString() {
        return ordinal;
    }
}
