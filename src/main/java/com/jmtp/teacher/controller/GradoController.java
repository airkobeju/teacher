package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Alumno;
import com.jmtp.teacher.model.Grado;
import com.jmtp.teacher.repository.AlumnoRepository;
import com.jmtp.teacher.repository.GradoRepository;
import com.jmtp.teacher.utils.GradoAlumnoWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/grado")
public class GradoController {

    private GradoRepository gradoRepository;
    private AlumnoRepository alumnoRepository;

    public GradoController(GradoRepository gradoRepository,
                           AlumnoRepository alumnoRepository) {
        this.gradoRepository = gradoRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @GetMapping("/all")
    public List<Grado> getGrados(){
        return gradoRepository.findAll();
    }

    @GetMapping("/get/{gradoId}")
    public Grado getGrado(@PathVariable(name = "gradoId") String id){
        return gradoRepository.findById(id).get();
    }

    @PutMapping("/save")
    public Grado save(@RequestBody Grado grado){
        return gradoRepository.save(grado);
    }

    @PutMapping("/save/alumnos")
    public Grado addAlumnos(@RequestBody GradoAlumnoWrapper wrapper) {
        Grado grado = gradoRepository.findById(wrapper.getGradoId()).get();
        alumnoRepository.findAllById(wrapper.getAlumnosId())
                .forEach(alumno -> {
                    grado.getAlumnos().add(alumno);
                });
        return gradoRepository.save(grado);
    }

}
