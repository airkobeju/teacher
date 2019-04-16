package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Capacidad;
import com.jmtp.teacher.repository.CapacidadRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/capacidad")
public class CapacidadController {

    private CapacidadRepository capacidadRepository;

    public CapacidadController(CapacidadRepository capacidadRepository) {
        this.capacidadRepository = capacidadRepository;
    }

    @GetMapping("/all")
    public List<Capacidad> getCapacidades(){
        return capacidadRepository.findAll();
    }

    @GetMapping("/get/{capacidadId}")
    public Capacidad getCapacidad(@PathVariable(name = "capacidadId") String id){
        return capacidadRepository.findById(id).get();
    }
}
