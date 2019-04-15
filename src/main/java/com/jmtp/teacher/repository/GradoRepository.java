package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Grado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GradoRepository extends MongoRepository<Grado, String>, GradoCustomRepository {



}
