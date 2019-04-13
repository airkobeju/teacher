package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Ciclo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CicloRepository extends MongoRepository<Ciclo, String> {

    Ciclo findByName(String name);

}
