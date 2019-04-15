package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Capacidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class TestRepositoryImpl implements TestRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String saludar() {
        List<Capacidad> capacidades = mongoTemplate.findAll(Capacidad.class);
        long len = capacidades.stream().count();
        return "Hola a todos | Largo de capacidades: "+ len;
    }
}
