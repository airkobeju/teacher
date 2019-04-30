package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Desempenyo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DesempenyoRepository extends MongoRepository<Desempenyo, String> {


    List<Desempenyo> findByGradoBaseCode(int code);

}
