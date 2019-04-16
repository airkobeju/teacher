package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.GradoBase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GradoBaseRepository extends MongoRepository<GradoBase, String> {

    GradoBase findByOrdinal(String ordinal);

}
