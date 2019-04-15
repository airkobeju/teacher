package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Area;
import com.jmtp.teacher.model.Grado;

import java.util.List;

public interface GradoCustomRepository {

    Grado save(Grado grado, List<Area> areas) throws Exception;

}
