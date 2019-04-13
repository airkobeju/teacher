package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Area;
import com.jmtp.teacher.model.Competencia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompetenciaRepository extends MongoRepository<Competencia, String> {

    List<Competencia> findByArea(Area area);
    Competencia findByCode(int code);

}
