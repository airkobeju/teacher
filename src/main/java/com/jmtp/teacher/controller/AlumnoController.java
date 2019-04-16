package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Alumno;
import com.jmtp.teacher.repository.AlumnoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {

    private AlumnoRepository alumnoRepository;

    public AlumnoController(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @GetMapping("/all")
    public List<Alumno> getAll(){
        return alumnoRepository.findAll();
    }

    @GetMapping("/get/{alumnoId}")
    public Alumno getAlumno(@PathVariable(name = "alumnoId") String id){
        return alumnoRepository.findById(id).get();
    }

    @PutMapping("/save")
    public Alumno save(@RequestBody Alumno alumno){
        return alumnoRepository.save(alumno);
    }

}
