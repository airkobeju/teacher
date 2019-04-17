package com.jmtp.teacher.controller;

import com.jmtp.teacher.model.Area;
import com.jmtp.teacher.repository.AreaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/area")
public class AreaController {

    private AreaRepository areaRepository;

    public AreaController(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @GetMapping("/all")
    public List<Area> getAreas(){
        return areaRepository.findAll();
    }

    @GetMapping("/get/{areaId}")
    public Area getArea(@PathVariable(name = "areaId") String id){
        return areaRepository.findById(id).get();
    }

    @GetMapping("/getbycode/{code}")
    public Area getArea(@PathVariable(name = "code") Integer code){
        return areaRepository.findByCode(code);
    }

}
