package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Competencia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetenciaRepository extends MongoRepository<Competencia, String> {

    Competencia findByCode(int code);

}
