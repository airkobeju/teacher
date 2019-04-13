package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Nivel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NivelRepository extends MongoRepository<Nivel, String> {

    Nivel findByName(String name);

}
