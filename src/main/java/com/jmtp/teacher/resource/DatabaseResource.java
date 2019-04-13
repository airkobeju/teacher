package com.jmtp.teacher.resource;

import com.jmtp.teacher.model.Area;
import com.jmtp.teacher.model.Ciclo;
import com.jmtp.teacher.model.Competencia;
import com.jmtp.teacher.model.Nivel;
import com.jmtp.teacher.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/db")
public class DatabaseResource {

    private AlumnoRepository alumnoRepository;
    private AreaRepository areaRepository;
    private NivelRepository nivelRepository;
    private CicloRepository cicloRepository;
    private AnyoLectivoRepository anyoLectivoRepository;
    private CompetenciaRepository competenciaRepository;

    private EvaluacionRepository evaluacionRepository;

    public DatabaseResource(AlumnoRepository alumnoRepository,
                            AreaRepository areaRepository,
                            NivelRepository nivelRepository,
                            CicloRepository cicloRepository,
                            AnyoLectivoRepository anyoLectivoRepository,
                            CompetenciaRepository competenciaRepository,
                            EvaluacionRepository evaluacionRepository) {
        this.alumnoRepository = alumnoRepository;
        this.areaRepository = areaRepository;
        this.nivelRepository = nivelRepository;
        this.cicloRepository = cicloRepository;
        this.anyoLectivoRepository = anyoLectivoRepository;
        this.competenciaRepository = competenciaRepository;

        this.evaluacionRepository = evaluacionRepository;
    }

    @GetMapping("/star")
    public List<Competencia> createMatrix() {

        initMatrixArea();
        initMatrixNivel();
        initMatrixCiclo();
        initMatrixCompetencias();

        return competenciaRepository.findAll();
    }


    private void initMatrixNivel() {
        nivelRepository.save(new Nivel("Inicial"));
        nivelRepository.save(new Nivel("Primaria"));
        nivelRepository.save(new Nivel("Secundaria"));
    }

    private void initMatrixArea() {
        Nivel nivel_inicial = nivelRepository.findByName( "Inicial" );
        Nivel nivel_primaria = nivelRepository.findByName( "Primaria" );
        Nivel nivel_secundaria = nivelRepository.findByName( "Secundaria" );

        areaRepository.save(new Area(1,"Personal Social",nivel_inicial));
        areaRepository.save(new Area(2,"Psicomotriz",nivel_inicial));
        areaRepository.save(new Area(3,"Comunicación",nivel_inicial));
        areaRepository.save(new Area(4,"Castellano como segunda lengua",nivel_inicial));
        areaRepository.save(new Area(5,"Descubrimiento del mundo",nivel_inicial));
        areaRepository.save(new Area(6,"Matemática",nivel_inicial));
        areaRepository.save(new Area(7,"Ciencia y Tecnología",nivel_inicial));

        areaRepository.save(new Area(8,"Matemática",nivel_primaria));
        areaRepository.save(new Area(9,"Comunicación",nivel_primaria));
        areaRepository.save(new Area(10,"Inglés",nivel_primaria));
        areaRepository.save(new Area(11,"Personal Social",nivel_primaria));
        areaRepository.save(new Area(12,"Arte y Cultura",nivel_primaria));
        areaRepository.save(new Area(13,"Ciencia y Tecnología",nivel_primaria));
        areaRepository.save(new Area(14,"Educación Física",nivel_primaria));
        areaRepository.save(new Area(15,"Educación Religiosa",nivel_primaria));
        areaRepository.save(new Area(16,"Castellano como segunda lengua",nivel_primaria));

        areaRepository.save(new Area(17,"Matemática",nivel_secundaria));
        areaRepository.save(new Area(18,"Comunicación",nivel_secundaria));
        areaRepository.save(new Area(19,"Inglés",nivel_secundaria));
        areaRepository.save(new Area(20,"Arte y Cultura",nivel_secundaria));
        areaRepository.save(new Area(21,"Ciencias Sociales",nivel_secundaria));
        areaRepository.save(new Area(22,"Desarrollo personal, ciudadanía y cívica",nivel_secundaria));
        areaRepository.save(new Area(23,"Educación Física",nivel_secundaria));
        areaRepository.save(new Area(24,"Educación Religiosa",nivel_secundaria));
        areaRepository.save(new Area(25,"Ciencia y tecnología",nivel_secundaria));
        areaRepository.save(new Area(26,"Educación para el Trabajo",nivel_secundaria));
        areaRepository.save(new Area(27,"Tutoría y orientación educativa",nivel_secundaria));

    }

    private void initMatrixCiclo() {
        Nivel nivel_inicial = nivelRepository.findByName( "Inicial" );
        Nivel nivel_primaria = nivelRepository.findByName( "Primaria" );
        Nivel nivel_secundaria = nivelRepository.findByName( "Secundaria" );

        List<Ciclo> ciclos = new ArrayList<>();
        ciclos.add(new Ciclo(nivel_inicial,"I"));
        ciclos.add(new Ciclo(nivel_inicial,"II"));
        ciclos.add(new Ciclo(nivel_primaria,"III"));
        ciclos.add(new Ciclo(nivel_primaria,"IV"));
        ciclos.add(new Ciclo(nivel_primaria,"V"));
        ciclos.add(new Ciclo(nivel_secundaria,"VI"));
        ciclos.add(new Ciclo(nivel_secundaria,"VII"));

        cicloRepository.saveAll(ciclos);
    }

    private void initMatrixCompetencias() {
        List<Competencia> competencias = new ArrayList<>();

        Area area_1 = areaRepository.findByCode(1);
        Ciclo ciclo_i = cicloRepository.findByName("I");
        Ciclo ciclo_ii = cicloRepository.findByName("II");


        competencias.add(new Competencia(area_1, ciclo_i,1,"Construye su indentidad"));
        competencias.add(new Competencia(area_1, ciclo_i,2,"Se relaciona con las personas"));

        Area area_2 = areaRepository.findByCode(2);
        competencias.add(new Competencia(area_2, ciclo_i,3,"Se desarrolla motrizmente"));

        Area area_3 = areaRepository.findByCode(3);
        competencias.add(new Competencia(area_3, ciclo_i,4,"Se comunica oralmente en legua materna"));

        Area area_4 = areaRepository.findByCode(4);
        //competencias.add(new Competencia(area_4, ciclo_i,5,""));

        Area area_5 = areaRepository.findByCode(5);
        competencias.add(new Competencia(area_5, ciclo_i,5,"Construye la noción de cantidad"));
        competencias.add(new Competencia(area_5, ciclo_i,6,"Establece relaciones espaciales"));
        competencias.add(new Competencia(area_5, ciclo_i,7,"Explora su entorno para conocerlo"));

        /**
         * Area_1
         */
        competencias.add(new Competencia(area_1, ciclo_ii,8,"Construye su identidad"));
        competencias.add(new Competencia(area_1, ciclo_ii,9,"Convive y participa"));
        competencias.add(new Competencia(area_1, ciclo_ii,10,"Comprende que es una persona amada por Dios"));

        /**
         * Area_2
         */
        competencias.add(new Competencia(area_2, ciclo_ii,11,"Se desenvuelve de manera autónoma a través de su motricidad"));


        /**
         * Area_3
         */
        competencias.add(new Competencia(area_3, ciclo_ii,12,"Se comunica oralmente en legua materna"));
        competencias.add(new Competencia(area_3, ciclo_ii,13,"Lee diversos tipos de textos escritos"));
        competencias.add(new Competencia(area_3, ciclo_ii,14,"Escribe diversos tipos de textos"));
        competencias.add(new Competencia(area_3, ciclo_ii,15,"Crea proyectos artísticos"));

        /**
         * Area_4
         */
        competencias.add(new Competencia(area_4, ciclo_ii,16,"Se comunica oralmente en castellano como segunda lengua"));

        /**
         * Area_6
         */
        Area area_6 = areaRepository.findByCode(6);
        competencias.add(new Competencia(area_6, ciclo_ii,17,"Construye la noción de cantidades"));
        competencias.add(new Competencia(area_6, ciclo_ii,18,"Establece relaciones espaciales"));

        /**
         * Area_7
         */
        Area area_7 = areaRepository.findByCode(7);
        competencias.add(new Competencia(area_7, ciclo_ii,19,"Explora su entorno para conocerlo"));


        /**
         * Area_8: "Mate prim"
         */
        Area area_8 = areaRepository.findByCode(8);
        competencias.add(new Competencia(area_8,20,"Resuelve problemas de cantidad"));
        competencias.add(new Competencia(area_8,21,"Resuelve problemas de regularidad, equivalencia y cambio"));
        competencias.add(new Competencia(area_8,22,"Resuelve problemas de movimiento, forma y localización"));
        competencias.add(new Competencia(area_8,23,"Resuelve problemas de gestión de datos e incertidumbre"));

        /**
         * Area_9: "Comu prim"
         */
        Area area_9 = areaRepository.findByCode(9);
        competencias.add(new Competencia(area_9,24,"Se comunica oralmente en lengua materna"));
        competencias.add(new Competencia(area_9,25,"Lee diversos tipos de textos escritos"));
        competencias.add(new Competencia(area_9,26,"Escribe diversos tipos de textos"));

        /**
         * Area_10: "Ingles prim"
         */
        Area area_10 = areaRepository.findByCode(10);
        competencias.add(new Competencia(area_10,27,"Se comunica oralmente en inglés como lengua extranjera"));
        competencias.add(new Competencia(area_10,28,"Lee diversos tipos de textos en inglés como lengua extranjera"));
        competencias.add(new Competencia(area_10,29,"Escribe diversos tipos de textos inglés como lengua extranjera"));

        /**
         * Area_11: "Personal Social prim"
         */
        Area area_11 = areaRepository.findByCode(11);
        competencias.add(new Competencia(area_11,30,"Construye su identidad"));
        competencias.add(new Competencia(area_11,31,"Convive y participa democráticamente"));
        competencias.add(new Competencia(area_11,32,"Construye interpretaciones históricas"));
        competencias.add(new Competencia(area_11,33,"Gestiona responsablemente el ambiente y el espacio"));
        competencias.add(new Competencia(area_11,34,"Gestiona responsablemente los recursos económicos"));

        /**
         * Area_12: "Arte y cultura prim"
         */
        Area area_12 = areaRepository.findByCode(12);
        competencias.add(new Competencia(area_12,35,"Aprecia de manera crítica manifestaciones artístico-culturales"));
        competencias.add(new Competencia(area_12,36,"Crea proyectos desde los lenguajes artísticos"));

        /**
         * Area_13: "Ciencia y Tecnología prim"
         */
        Area area_13 = areaRepository.findByCode(13);
        competencias.add(new Competencia(area_13,37,"Indaga mediante métodos científicos"));
        competencias.add(new Competencia(area_13,38,"Explica el mundo físico basándose en conocimientos sobre los seres vivos; materia y energía; biodiversidad, Tierra y universo"));
        competencias.add(new Competencia(area_13,39,"Diseña y construye soluciones tecnológicas para resolver problemas"));

        /**
         * Area_14: "Educación Física prim"
         */
        Area area_14 = areaRepository.findByCode(14);
        competencias.add(new Competencia(area_14,40,"Se desenvuelve de manera autónoma a través de su motricidad"));
        competencias.add(new Competencia(area_14,41,"Asume una vida saludable"));
        competencias.add(new Competencia(area_14,42,"Interactúa a través de sus habilidades sociomotrices"));

        /**
         * Area_15: "Educación Religiosa prim"
         */
        Area area_15 = areaRepository.findByCode(15);
        competencias.add(new Competencia(area_15,43,"Construye su identidad como persona humana, amada por Dios, digna, libre y trascendente"));
        competencias.add(new Competencia(area_15,44,"Asume la experiencia el encuentro personal y comunitario con Dios"));

        /**
         * Area_16: "Castellano como segunda lengua prim"
         */
        Area area_16 = areaRepository.findByCode(16);
        competencias.add(new Competencia(area_16,45,"Se comunica oralmente en castellano como segunda lengua"));
        competencias.add(new Competencia(area_16,46,"Lee diversos tipos de textos escritos en castellano como segunda lengua"));
        competencias.add(new Competencia(area_16,47,"Escribe diversos tipos de textos castellano como segunda lengua"));

        /**
         * Area_17: "Matemática sec"
         */
        Area area_17 = areaRepository.findByCode(17);
        competencias.add(new Competencia(area_17,48,"Resuelve problemas de cantidad"));
        competencias.add(new Competencia(area_17,49,"Resuelve problemas de regularidad, equivalencia y cambio"));
        competencias.add(new Competencia(area_17,50,"Resuelve problemas de movimiento, forma y localización"));
        competencias.add(new Competencia(area_17,51,"Resuelve problemas de gestión de datos e incertidumbre"));

        /**
         * Area_18: "Comunicación sec"
         */
        Area area_18 = areaRepository.findByCode(18);
        competencias.add(new Competencia(area_18,52,"Se comunica oralmente en lengua materna"));
        competencias.add(new Competencia(area_18,53,"Lee diversos tipos de textos escritos"));
        competencias.add(new Competencia(area_18,54,"Escribe diversos tipos de textos"));

        /**
         * Area_19: "Inglés sec"
         */
        Area area_19 = areaRepository.findByCode(19);
        competencias.add(new Competencia(area_19,55,"Se comunica oralmente en inglés como lengua extranjera"));
        competencias.add(new Competencia(area_19,56,"Lee diversos tipos de textos en inglés como lengua extranjera"));
        competencias.add(new Competencia(area_19,57,"Escribe diversos tipos de textos inglés como lengua extranjera"));

        /**
         * Area_20: "Arte y Cultura SEC"
         */
        Area area_20 = areaRepository.findByCode(20);
        competencias.add(new Competencia(area_20,58,"Aprecia de manera crítica manifestaciones artístico-culturales"));
        competencias.add(new Competencia(area_20,59,"Crea proyectos desde los lenguajes artísticos"));

        /**
         * Area_21: "Ciencias Sociales SEC"
         */
        Area area_21 = areaRepository.findByCode(21);
        competencias.add(new Competencia(area_21,60,"Construye interpretaciones históricas"));
        competencias.add(new Competencia(area_21,61,"Gestiona responsablemente el ambiente y el espacio"));
        competencias.add(new Competencia(area_21,62,"Gestiona responsablemente los recursos económicos"));

        /**
         * Area_22: "Desarrollo personal, ciudadanía y cívica SEC"
         */
        Area area_22 = areaRepository.findByCode(22);
        competencias.add(new Competencia(area_22,63,"Construye su identidad"));
        competencias.add(new Competencia(area_22,64,"Convive y participa democráticamente"));

        /**
         * Area_23: "Educación Física SEC"
         */
        Area area_23 = areaRepository.findByCode(23);
        competencias.add(new Competencia(area_23,65,"Se desenvuelve de manera autónoma a través de su motricidad"));
        competencias.add(new Competencia(area_23,66,"Asume una vida saludable"));
        competencias.add(new Competencia(area_23,67,"Interactúa a través de sus habilidades sociomotrices"));

        /**
         * Area_24: "Educación Religiosa SEC"
         */
        Area area_24 = areaRepository.findByCode(24);
        competencias.add(new Competencia(area_24,68,"Construye su identidad como persona humana, amada por Dios, digna, libre y trascendente"));
        competencias.add(new Competencia(area_24,69,"Asume la experiencia el encuentro personal y comunitario con Dios"));

        /**
         * Area_25: "Ciencia y tecnología SEC"
         */
        Area area_25 = areaRepository.findByCode(25);
        competencias.add(new Competencia(area_25,70,"Indaga mediante métodos científicos"));
        competencias.add(new Competencia(area_25,71,"Explica el mundo físico basándose en conocimientos sobre los seres vivos; materia y energía; biodiversidad, Tierra y universo"));
        competencias.add(new Competencia(area_25,72,"Diseña y construye soluciones tecnológicas para resolver problemas"));

        /**
         * Area_26: "Educación para el Trabajo SEC"
         */
        Area area_26 = areaRepository.findByCode(26);
        competencias.add(new Competencia(area_26,73,"Gestiona proyectos de emprendimiento económico y social"));

        /**
         * Area_27: "Castellano como segunda lengua SEC"
         */
        Area area_27 = areaRepository.findByCode(27);
        competencias.add(new Competencia(area_27,74,"Se comunica oralmente en castellano como segunda lengua"));
        competencias.add(new Competencia(area_27,75,"Lee diversos tipos de textos escritos en castellano como segunda lengua"));
        competencias.add(new Competencia(area_27,76,"Escribe diversos tipos de textos castellano como segunda lengua"));

        competenciaRepository.saveAll(competencias);
    }

}
