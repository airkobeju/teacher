package com.jmtp.teacher.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "desempenyo")
public class Desempenyo extends AbstractDocument {

    private int gradoBaseCode;
    @Nullable
    private List<String> tag = new ArrayList<>();
    private String description;

    public Desempenyo() {
    }

    public Desempenyo(String description) {
        this.description = description;
    }

    public Desempenyo(int gradoBaseCode, String description) {
        this.gradoBaseCode = gradoBaseCode;
        this.description = description;
    }

    public int getGradoBaseCode() {
        return gradoBaseCode;
    }

    public void setGradoBaseCode(int gradoBaseCode) {
        this.gradoBaseCode = gradoBaseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
