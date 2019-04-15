package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Capacidad;
import com.jmtp.teacher.model.Competencia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CapacidadRepository extends MongoRepository<Capacidad, String>, TestRepository {



}
