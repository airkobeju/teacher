package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Area;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AreaRepository extends MongoRepository<Area, String> {

    Area findByCode(int code);

}
