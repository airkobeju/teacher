package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {

    List<Alumno> findByLastname(String lastname);
    Alumno findByDni(String dni);

}