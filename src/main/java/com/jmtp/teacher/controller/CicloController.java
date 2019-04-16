package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Ciclo;
import com.jmtp.teacher.repository.CicloRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/ciclo")
public class CicloController {

    private CicloRepository cicloRepository;

    public CicloController(CicloRepository cicloRepository) {
        this.cicloRepository = cicloRepository;
    }

    @GetMapping("/all")
    public List<Ciclo> getCiclos(){
        return cicloRepository.findAll();
    }

    @GetMapping("/get/{cicloId}")
    public Ciclo getCiclo(@PathVariable(name = "cicloId") String id){
        return cicloRepository.findById(id).get();
    }

}
