package com.jmtp.teacher.utils;

import java.util.ArrayList;
import java.util.List;

public class GradoAlumnoWrapper {

    private String gradoId;
    private List<String> alumnosId = new ArrayList<>();

    public GradoAlumnoWrapper() {
    }

    public GradoAlumnoWrapper(String gradoId, List<String> alumnosId) {
        this.gradoId = gradoId;
        this.alumnosId = alumnosId;
    }

    public String getGradoId() {
        return gradoId;
    }

    public void setGradoId(String gradoId) {
        this.gradoId = gradoId;
    }

    public List<String> getAlumnosId() {
        return alumnosId;
    }

    public void setAlumnosId(List<String> alumnosId) {
        this.alumnosId = alumnosId;
    }
}
