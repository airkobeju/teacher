package com.jmtp.teacher.resource;

import com.jmtp.teacher.model.Alumno;
import com.jmtp.teacher.repository.AlumnoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/alumnos")
public class AlumnoResource {

    private AlumnoRepository alumnoRepository;

    public AlumnoResource(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @GetMapping("/all")
    public List<Alumno> getAll() {
        return alumnoRepository.findAll();
    }
}
