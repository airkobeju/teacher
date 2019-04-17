package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.GradoBase;
import com.jmtp.teacher.repository.GradoBaseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/gradobase")
public class GradoBaseController {

    private GradoBaseRepository gradoBaseRepository;

    public GradoBaseController(GradoBaseRepository gradoBaseRepository) {
        this.gradoBaseRepository = gradoBaseRepository;
    }

    @GetMapping("/all")
    public List<GradoBase> getAll(){
        return gradoBaseRepository.findAll();
    }

    @GetMapping("/get/{gradobaseId}")
    public GradoBase getOne(@PathVariable(name = "gradobaseId") String id){
        return gradoBaseRepository.findById(id).get();
    }

}
