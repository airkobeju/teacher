package com.jmtp.teacher.resource;

import com.jmtp.teacher.model.Area;
import com.jmtp.teacher.repository.AreaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/area")
public class AreaResource {

    private AreaRepository areaRepository;

    public AreaResource(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @GetMapping("/all")
    public List<Area> getAll() {
        return areaRepository.findAll();
    }
}
