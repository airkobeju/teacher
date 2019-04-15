package com.jmtp.teacher.repository;

import com.jmtp.teacher.model.Area;
import com.jmtp.teacher.model.Curso;
import com.jmtp.teacher.model.Grado;
import com.jmtp.teacher.model.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class GradoCustomRepositoryImpl implements GradoCustomRepository {

    @Autowired
    MongoTemplate mTemplate;

    /**
     *
     * @param grado -> Objeto que será creado y guardado
     * @param areas -> Lista de Areas que permitiran crear los cursos
     * @return
     */
    @Override
    public Grado save(Grado grado, List<Area> areas) throws Exception {

        if (grado == null) throw new Exception("Grado debe ser diferente de un valor nulo.");
        if (grado.getId() != null) throw new Exception("Este objeto Grado ya existe.");
        if (areas == null) throw new Exception("Se requiere la lista de Areas para la creación de cursos.");
        if (areas.size() < 1) throw new Exception("La lista de Areas debe tener elementos");

        Grado gr = mTemplate.save(grado);

        areas.stream().forEach(area->{
            gr.getCursos().add(mTemplate.save(new Curso(area, gr)));
        });

        return gr;
    }

}
