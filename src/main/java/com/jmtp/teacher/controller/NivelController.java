package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Nivel;
import com.jmtp.teacher.repository.NivelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/nivel")
public class NivelController {

    private NivelRepository nivelRepository;

    public NivelController(NivelRepository nivelRepository) {
        this.nivelRepository = nivelRepository;
    }

    @GetMapping("/all")
    public List<Nivel> getNiveles(){
        return nivelRepository.findAll();
    }
    @GetMapping("/get/{nivelId}")
    public Nivel getNivel(@PathVariable(name = "nivelId") String id){
        //TODO: Cuidado con el objeto Optional
        return nivelRepository.findById(id).get();
    }

}
