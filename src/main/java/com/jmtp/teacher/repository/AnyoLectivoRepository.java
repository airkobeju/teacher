package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.AnyoLectivo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnyoLectivoRepository extends MongoRepository<AnyoLectivo, String> {

    AnyoLectivo findByYear(int year);

}
