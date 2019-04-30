package com.jmtp.teacher.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gradobase")
public class GradoBase extends AbstractDocument {

    private int code;
    private String ordinal;

    public GradoBase() {
    }

    public GradoBase(String ordinal) {
        this.ordinal = ordinal;
    }

    public GradoBase(int code, String ordinal) {
        this.code = code;
        this.ordinal = ordinal;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
