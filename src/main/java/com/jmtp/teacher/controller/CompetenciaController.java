package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Competencia;
import com.jmtp.teacher.repository.CompetenciaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/competencia")
public class CompetenciaController {

    private CompetenciaRepository competenciaRepository;

    public CompetenciaController(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    @GetMapping("/all")
    public List<Competencia> getCompetencias(){
        return competenciaRepository.findAll();
    }

    @GetMapping("/get/{competenciaId}")
    public Competencia getCompetencia(@PathVariable(name = "competenciaId") String id){
        return competenciaRepository.findById(id).get();
    }

    @GetMapping("/code/{competenciaCode}")
    public Competencia getCompetenciaByCode(@PathVariable(name = "competenciaCode") Integer code){
        return competenciaRepository.findByCode(code);
    }

}
