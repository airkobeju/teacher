package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Evaluacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluacionRepository extends MongoRepository<Evaluacion, String> {



}
