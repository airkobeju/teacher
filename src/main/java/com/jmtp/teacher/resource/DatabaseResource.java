package com.jmtp.teacher.resource;

import com.jmtp.teacher.model.*;
import com.jmtp.teacher.repository.*;
import org.springframework.web.bind.annotation.*;

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
    private CapacidadRepository capacidadRepository;
    private GradoRepository gradoRepository;
    private GradoBaseRepository gradoBaseRepository;

    private EvaluacionRepository evaluacionRepository;

    public DatabaseResource(AlumnoRepository alumnoRepository,
                            AreaRepository areaRepository,
                            NivelRepository nivelRepository,
                            CicloRepository cicloRepository,
                            AnyoLectivoRepository anyoLectivoRepository,
                            CompetenciaRepository competenciaRepository,
                            CapacidadRepository capacidadRepository,
                            GradoRepository gradoRepository,
                            GradoBaseRepository gradoBaseRepository,
                            EvaluacionRepository evaluacionRepository) {
        this.alumnoRepository = alumnoRepository;
        this.areaRepository = areaRepository;
        this.nivelRepository = nivelRepository;
        this.cicloRepository = cicloRepository;
        this.anyoLectivoRepository = anyoLectivoRepository;
        this.competenciaRepository = competenciaRepository;
        this.capacidadRepository = capacidadRepository;
        this.gradoRepository = gradoRepository;
        this.gradoBaseRepository = gradoBaseRepository;

        this.evaluacionRepository = evaluacionRepository;
    }




    @GetMapping("/star")
    public List<Capacidad> createMatrix() {

        initMatrixNivel();
        initMatrixCiclo();
        initMatrixGradoBase();
        initMatrixArea();
        initMatrixCompetencias();
        //initMatrixCapacidad();

        return capacidadRepository.findAll();
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

        List<Area> areas_inicial = new ArrayList<>();
        areas_inicial.add(new Area(1,"Personal Social"));
        areas_inicial.add(new Area(2,"Psicomotriz"));
        areas_inicial.add(new Area(3,"Comunicación"));
        areas_inicial.add(new Area(4,"Castellano como segunda lengua"));
        areas_inicial.add(new Area(5,"Descubrimiento del mundo"));
        areas_inicial.add(new Area(6,"Matemática"));
        areas_inicial.add(new Area(7,"Ciencia y Tecnología"));

        nivel_inicial.getAreas().addAll(areaRepository.saveAll(areas_inicial));
        nivelRepository.save(nivel_inicial);

        List<Area> areas_primaria = new ArrayList<>();
        areas_primaria.add(new Area(8,"Matemática"));
        areas_primaria.add(new Area(9,"Comunicación"));
        areas_primaria.add(new Area(10,"Inglés"));
        areas_primaria.add(new Area(11,"Personal Social"));
        areas_primaria.add(new Area(12,"Arte y Cultura"));
        areas_primaria.add(new Area(13,"Ciencia y Tecnología"));
        areas_primaria.add(new Area(14,"Educación Física"));
        areas_primaria.add(new Area(15,"Educación Religiosa"));
        areas_primaria.add(new Area(16,"Castellano como segunda lengua"));

        nivel_primaria.getAreas().addAll(areaRepository.saveAll(areas_primaria));
        nivelRepository.save(nivel_primaria);

        List<Area> areas_secundaria = new ArrayList<>();
        areas_secundaria.add(new Area(17,"Matemática"));
        areas_secundaria.add(new Area(18,"Comunicación"));
        areas_secundaria.add(new Area(19,"Inglés"));
        areas_secundaria.add(new Area(20,"Arte y Cultura"));
        areas_secundaria.add(new Area(21,"Ciencias Sociales"));
        areas_secundaria.add(new Area(22,"Desarrollo personal, ciudadanía y cívica"));
        areas_secundaria.add(new Area(23,"Educación Física"));
        areas_secundaria.add(new Area(24,"Educación Religiosa"));
        areas_secundaria.add(new Area(25,"Ciencia y tecnología"));
        areas_secundaria.add(new Area(26,"Educación para el Trabajo"));
        areas_secundaria.add(new Area(27,"Tutoría y orientación educativa"));
        nivel_secundaria.getAreas().addAll(areaRepository.saveAll(areas_secundaria));
        nivelRepository.save(nivel_secundaria);
    }

    private void initMatrixCiclo() {
        Nivel nivel_inicial = nivelRepository.findByName( "Inicial" );
        Nivel nivel_primaria = nivelRepository.findByName( "Primaria" );
        Nivel nivel_secundaria = nivelRepository.findByName( "Secundaria" );

        List<Ciclo> ciclos = new ArrayList<>();

        ciclos.add(new Ciclo(1,"I"));
        ciclos.add(new Ciclo(2,"II"));
        nivel_inicial.getCiclos().addAll(cicloRepository.saveAll(ciclos));
        nivelRepository.save(nivel_inicial);

        ciclos.clear();

        ciclos.add(new Ciclo(3,"III"));
        ciclos.add(new Ciclo(4,"IV"));
        ciclos.add(new Ciclo(5,"V"));
        nivel_primaria.getCiclos().addAll(cicloRepository.saveAll(ciclos));
        nivelRepository.save(nivel_primaria);

        ciclos.clear();

        ciclos.add(new Ciclo(6,"VI"));
        ciclos.add(new Ciclo(7,"VII"));
        nivel_secundaria.getCiclos().addAll(cicloRepository.saveAll(ciclos));
        nivelRepository.save(nivel_secundaria);

    }

    private void initMatrixGradoBase() {
        List<GradoBase> grados = new ArrayList<>();

        Ciclo ciclo_i = cicloRepository.findByName("I");
        grados.add( new GradoBase("2 años") );
        grados.add( new GradoBase("3 años") );
        ciclo_i.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_i);

        grados.clear();

        Ciclo ciclo_ii = cicloRepository.findByName("II");
        grados.add( new GradoBase("4 años") );
        grados.add( new GradoBase("5 años") );
        ciclo_ii.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_ii);

        grados.clear();

        Ciclo ciclo_iii = cicloRepository.findByName("III");
        grados.add( new GradoBase("1ro") );
        grados.add( new GradoBase("2do") );
        ciclo_iii.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_iii);

        grados.clear();

        Ciclo ciclo_iv = cicloRepository.findByName("IV");
        grados.add( new GradoBase("3ro") );
        grados.add( new GradoBase("4to") );
        ciclo_iv.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_iv);

        grados.clear();

        Ciclo ciclo_v = cicloRepository.findByName("V");
        grados.add( new GradoBase("5to") );
        grados.add( new GradoBase("6to") );
        ciclo_v.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_v);

        grados.clear();

        Ciclo ciclo_vi = cicloRepository.findByName("VI");
        grados.add( new GradoBase("1ro") );
        grados.add( new GradoBase("2do") );
        ciclo_vi.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_vi);

        grados.clear();

        Ciclo ciclo_vii = cicloRepository.findByName("VII");
        grados.add( new GradoBase("3ro") );
        grados.add( new GradoBase("4to") );
        grados.add( new GradoBase("5to") );
        ciclo_vii.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_vii);

    }

    private void initMatrixCompetencias() {
        List<Area> areas = new ArrayList<>();
        List<Ciclo> ciclos = new ArrayList<>();
        List<Competencia> competencias = new ArrayList<>();

        Ciclo ciclo_i = cicloRepository.findByName("I");
        Ciclo ciclo_ii = cicloRepository.findByName("II");
        Ciclo ciclo_iii = cicloRepository.findByName("III");
        Ciclo ciclo_iv = cicloRepository.findByName("IV");
        Ciclo ciclo_v = cicloRepository.findByName("V");
        Ciclo ciclo_vi = cicloRepository.findByName("VI");
        Ciclo ciclo_vii = cicloRepository.findByName("VII");

        //Personal Social - Inicial
        Area area_1 = areaRepository.findByCode(1);
        ciclos.add(ciclo_i);
        ciclos.add(ciclo_ii);
        competencias.add(new Competencia(1, "Construye su indentidad",
                "El estudiante conoce y valora su cuerpo, su forma de " +
                        "sentir, de pensar y de actuar desde el reconocimiento de las distintas identidades que lo definen " +
                        "(histórica, étnica, social, sexual, cultural, de género, entre otras) como producto de las interacciones " +
                        "continuas entre los individuos y los diversos contextos en los que se desenvuelven (familia, escuela, " +
                        "comunidad). No se trata que los estudiantes construyan una identidad “ideal”, sino que cada " +
                        "estudiante pueda –a su propio ritmo y criterio– ser consciente de las características que lo hacen " +
                        "único y de aquellas que lo hacen semejantes a otros. " +
                        "En el nivel Inicial, la competencia Construye su identidad parte de la comprensión de los niños sobre " +
                        "el conocimiento de su propio cuerpo, sus gustos, preferencias y habilidades. En esta etapa, la familia " +
                        "es el principal espacio en el que se reciben los cuidados y la atención, en un marco de cariño, lo que " +
                        "permite la construcción de vínculos seguros. En la medida que estos vínculos estén bien establecidos, " +
                        "el niño será capaz de relacionarse con otros niños, maestra y otros adultos con mayor seguridad e " +
                        "iniciativa. En estas interacciones el niño va construyendo su propia identidad, la visión de sí mismo, " +
                        "de los demás y del mundo afirmándose como sujeto activo, con iniciativa, derechos y con " +
                        "competencias. Asimismo va reconociendo sus emociones y aprendiendo a expresarlas de manera " +
                        "adecuada con la compañía del adulto."));
        competencias.add(new Competencia(2, "Convive y participa democráticamente en la búsqueda del bien común",
                "Es actuar en la sociedad relacionándose " +
                        "con los demás de manera justa y equitativa, reconociendo que todas las personas tienen los mismos " +
                        "derechos y responsabilidades. Implica una disposición a conocer, comprender y enriquecerse con los " +
                        "aportes de las diversas culturas, respetando las diferencias. De igual forma, supone tomar posición " +
                        "frente a aquellos asuntos que los involucran como ciudadanos y contribuir en la construcción del " +
                        "bienestar general, en la consolidación de los procesos democráticos y en la promoción de los " +
                        "derechos humanos. " +
                        "En el nivel Inicial esta competencia se entiende como la convivencia y la participación de los niños y " +
                        "niñas por propia iniciativa, es decir, cómo los niños y niñas se relacionan con las personas y empiezan " +
                        "a interactuar entre pares y con otros adultos distintos a su familia en el primer espacio público que " +
                        "es la escuela. Lo hacen a través del juego, la exploración y las actividades cotidianas que surgen en " +
                        "su convivencia. Además, con el acompañamiento del adulto, conocen los límites y las normas en la " +
                        "interacción, necesarias para la interacción y la convivencia armónica. Por otro lado, inician su " +
                        "participación dando su opinión, buscando soluciones o tomando acción a partir de su propia iniciativa " +
                        "en asuntos comunes que afectan a todo el grupo y que le interesan."));
        area_1.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_1);
        ciclo_i.getCompetencias().add(area_1.getCompetencias().get(0));
        ciclo_i.getCompetencias().add(area_1.getCompetencias().get(1));
        ciclo_ii.getCompetencias().add(area_1.getCompetencias().get(0));
        ciclo_ii.getCompetencias().add(area_1.getCompetencias().get(1));
        cicloRepository.save(ciclo_i);
        cicloRepository.save(ciclo_ii);

        competencias.clear();
        ciclos.clear();

        //Psicomotriz
        Area area_2 = areaRepository.findByCode(2);
        ciclos.add(ciclo_i);
        ciclos.add(ciclo_ii);
        competencias.add(new Competencia(3, "Se desenvuelve de manera autónoma a través de su motricidad",
                "Es la comprensión progresiva y toma conciencia de sí mismo en interacción con el espacio y las personas " +
                        "de su entorno, lo que le permite construir su identidad y autoestima. Esto supone que el estudiante " +
                        "se desenvuelva por propia iniciativa y de manera placentera, interiorizando y organizando sus " +
                        "movimientos eficazmente según sus posibilidades, en la práctica de actividades físicas como el juego, " +
                        "el deporte y aquellas que se desarrollan en la vida cotidiana. Este proceso se acompaña naturalmente " +
                        "de la expresión y comunicación a través de su cuerpo, lo que le permite manifestar ideas, " +
                        "emociones y sentimientos con gestos, posturas, tono muscular, entre otros. Esta competencia " +
                        "implica una mirada de la motricidad en la que una acción no solo involucra el movimiento sino que " +
                        "conlleva intención, emoción y pensamiento. " +
                        "En el nivel de Educación Inicial, la competencia Se desenvuelve de manera autónoma a través de su " +
                        "motricidad está orientada a que los niños y niñas puedan conocerse e ir tomando conciencia de sí " +
                        "mismos, expresándose libremente a través de la vía corporal y motriz. Es decir, los niños irán " +
                        "logrando, progresivamente, la construcción de su imagen y su esquema corporal, a partir de la " +
                        "exploración de sus movimientos, posturas y desplazamientos, en la interacción con el entorno, " +
                        "durante el juego y la actividad autónoma. Todo esto les permitirá lograr, de manera paulatina, el " +
                        "dominio de su cuerpo, el desarrollo y control de sus posturas, la coordinación de sus movimientos, y " +
                        "el sentido de ubicación y organización en razón al tiempo, al espacio y los otros. " +
                        "Igualmente, es importante tomar en cuenta que durante estas vivencias lúdicas, cotidianas y " +
                        "autónomas, los niños y niñas manifiestan de manera espontánea las diversas sensaciones, emociones " +
                        "y sentimientos. Para ello se valen de gestos, posturas, tono, ritmo y movimientos. De este modo, " +
                        "podrán ir ampliando sus posibilidades expresivas y creativas, con un acompañamiento adecuado y " +
                        "respetuoso por su propia forma de ser y de expresarse."));
        area_2.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_2);
        ciclo_i.getCompetencias().add(area_2.getCompetencias().get(0));
        ciclo_ii.getCompetencias().add(area_2.getCompetencias().get(0));
        cicloRepository.save(ciclo_i);
        cicloRepository.save(ciclo_ii);

        ciclos.clear();
        competencias.clear();

        //Comunicacion
        Area area_3 = areaRepository.findByCode(3);
        Competencia comp_4 = new Competencia(4, "Se comunica oralmente en legua materna",
                "Se define como una " +
                        "interacción dinámica entre uno o más interlocutores para expresar y comprender ideas y " +
                        "emociones. Supone un proceso activo de construcción del sentido de los diversos tipos de textos " +
                        "orales ya que el estudiante alterna los roles de hablante y oyente con el fin de lograr su propósito " +
                        "comunicativo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos provenientes " +
                        "del lenguaje oral y del mundo que lo rodea. Esto significa considerar los modos de cortesía de acuerdo " +
                        "al contexto sociocultural, así como los recursos no verbales y paraverbales y las diversas estrategias " +
                        "de manera pertinente para expresarse, intercambiar información, persuadir, consensuar, entre otros " +
                        "fines. De igual forma, supone tomar conciencia del impacto de las nuevas tecnologías en la oralidad. " +
                        "La comunicación oral es una herramienta fundamental para la constitución de las identidades y el " +
                        "desarrollo personal. Esta competencia se asume como una práctica social donde el estudiante " +
                        "interactúa con distintos individuos o comunidades socioculturales, ya sea de forma presencial o " +
                        "virtual. Al hacerlo, tiene la posibilidad de usar el lenguaje oral de manera creativa y responsable, " +
                        "considerando la repercusión de lo expresado o escuchado, y estableciendo una posición crítica con " +
                        "los medios de comunicación audiovisuales.");
        competencias.add(comp_4);
        Competencia comp_5 = new Competencia(5, "Lee diversos tipos de textos escritos",
                "Se define como una interacción " +
                        "dinámica entre el lector, el texto y los contextos socioculturales que enmarcan la lectura. Supone un " +
                        "proceso activo de construcción del sentido ya que el estudiante no solo decodifica o comprende la " +
                        "información explícita de los textos que lee sino que es capaz de interpretarlos y establecer una " +
                        "posición sobre ellos. " +
                        "En esta competencia el estudiante pone en juego saberes de distinto tipo y recursos provenientes de " +
                        "su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia de la diversidad de " +
                        "propósitos que tiene la lectura, del uso que se hace de esta en distintos ámbitos de la vida, del papel " +
                        "de la experiencia literaria en la formación de lectores y de las relaciones intertextuales que se " +
                        "establecen entre los textos leídos. Esto es crucial en un mundo donde las nuevas tecnologías y la " +
                        "multimodalidad han transformado los modos de leer. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una práctica " +
                        "social situada en distintos grupos o comunidades de lectores. Al involucrarse con la lectura, el " +
                        "estudiante contribuye con su desarrollo personal, así como el de su propia comunidad, además de " +
                        "conocer e interactuar con contextos socioculturales distintos al suyo. " +
                        "En el nivel Inicial, los niños y niñas se inician en el aprendizaje de la lectura en su lengua materna " +
                        "estableciendo los primeros contactos con los textos escritos de su entorno (cuentos, enciclopedias, " +
                        "recetarios, revistas infantiles, poemas, etc.). Se acercan a ellos con diferentes propósitos (disfrutar, " +
                        "buscar información, etc.), los exploran, realizan anticipaciones sobre su significado antes de haberlos " +
                        "leído. En un inicio se centran en las ilustraciones y paulatinamente identifican algunas palabras " +
                        "conocidas. De esta manera, cada niño o niña le da un significado al texto y logran comunicar su agrado " +
                        "o desagrado en relación al texto leído. En este nivel, se espera que los niños se interesen por los " +
                        "textos escritos y se acerquen al mundo escrito.");
        competencias.add(comp_5);
        Competencia comp_6 = new Competencia(6, "Escribe diversos tipos de textos",
                "Se define como el uso " +
                        "del lenguaje escrito para construir sentidos en el texto y comunicarlos a otros. Se trata de un proceso " +
                        "reflexivo porque supone la adecuación y organización de los textos considerando los contextos y el " +
                        "propósito comunicativo, así como la revisión permanente de lo escrito con la finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos provenientes " +
                        "de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el sistema alfabético y un " +
                        "conjunto de convenciones de la escritura, así como diferentes estrategias para ampliar ideas, " +
                        "enfatizar o matizar significados en los textos que escribe. Con ello, toma conciencia de las " +
                        "posibilidades y limitaciones que ofrece el lenguaje, la comunicación y el sentido. Esto es crucial en " +
                        "una época dominada por nuevas tecnologías que han transformado la naturaleza de la comunicación " +
                        "escrita. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como una " +
                        "práctica social que permite participar en distintos grupos o comunidades socioculturales. Además de " +
                        "participar en la vida social, esta competencia supone otros propósitos, como la construcción de " +
                        "conocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se ofrece la posibilidad " +
                        "de interactuar con otras personas empleando el lenguaje escrito de manera creativa y responsable, " +
                        "teniendo en cuenta su repercusión en los demás. " +
                        "En el nivel Inicial, los niños se inician en el aprendizaje de la escritura en su lengua materna desde el " +
                        "interés por comprender el mundo escrito. Utilizan la escritura para expresar sus necesidades, " +
                        "intereses, emociones, etc. En estas edades, los niños plantean hipótesis sobre la escritura a partir de " +
                        "su contacto con el mundo escrito. La producción de textos se inicia cuando los niños diferencian el " +
                        "dibujo de la escritura y aparecen los trazos o grafismos para escribir textos auténticos en situaciones " +
                        "reales de comunicación. Los niños pueden escribir diferentes tipos de textos con propósitos diversos " +
                        "(una nota para su mamá, una invitación para el festival de la escuela, un cuento, entre otros). En este " +
                        "nivel se espera que los niños se inicien en la adquisición del sistema de escritura/ lenguaje escrito .");
        competencias.add(comp_6);
        Competencia comp_7 = new Competencia(7, "Crea proyectos desde los lenguajes artísticos",
                "Significa que el estudiante " +
                        "desarrolla un proyecto artístico a través de un proceso creativo en el que imagina sus creaciones, " +
                        "experimenta, investiga, evoca recuerdos y experiencias, propone, construye y deconstruye una idea, " +
                        "prueba, se expresa simbólicamente, elabora y socializa su producción artística. De esta forma, el " +
                        "estudiante, concretiza lo imaginado, interpreta la realidad, se apropia de ella, la transforma. Estos " +
                        "proyectos a través del arte tienen sentido como proceso y como fin. " +
                        "En el nivel Inicial, los niños actúan, exploran, experimentan y juegan, ya que esta es su manera de ir " +
                        "descubriendo y conociendo el mundo que le rodea. Todas sus vivencias, interacciones y movimientos " +
                        "van formando imágenes en la mente, las cuales pueden ser representadas a través de simulaciones " +
                        "de objetos y personajes que corresponden a su entorno más cercano (la familia, su hogar, la " +
                        "naturaleza), de dibujos o símbolos, de exploraciones sonoras de los objetos y del movimiento con su " +
                        "cuerpo (danza). Cada una de estas formas que tiene el niño de expresarse se traducen en proyectos " +
                        "para los cuales hace uso de los diferentes lenguajes del arte. De esta manera lo que se espera es que " +
                        "los niños cuenten con oportunidades y condiciones que les permitan descubrir las formas que más le " +
                        "interesan, manifestándose como un ser único y especial.");
        competencias.add(comp_7);
        area_3.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_3);
        ciclo_i.getCompetencias().add(area_3.getCompetencias().get(0));
        ciclo_ii.getCompetencias().add(area_3.getCompetencias().get(0));
        ciclo_ii.getCompetencias().add(area_3.getCompetencias().get(1));
        ciclo_ii.getCompetencias().add(area_3.getCompetencias().get(2));
        ciclo_ii.getCompetencias().add(area_3.getCompetencias().get(3));
        cicloRepository.save(ciclo_i);
        cicloRepository.save(ciclo_ii);

        ciclos.clear();
        competencias.clear();

        //Castellano como segunda lengua
        Area area_4 = areaRepository.findByCode(4);
        Competencia comp_8 = new Competencia(8, "Se comunica oralmente en castellano como segunda lengua");
        competencias.add(comp_8);
        area_4.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_4);
        ciclo_ii.getCompetencias().add(area_4.getCompetencias().get(0));
        cicloRepository.save(ciclo_ii);

        competencias.clear();

        //Descubrimiento del mundo
        Area area_5 = areaRepository.findByCode(5);
        Competencia comp_9 = new Competencia(9, "Resuelve problemas de cantidad",
                "Consiste en estimar, comparar y relacionar " +
                        "cantidades o medidas, a partir de la construcción y comprensión de las nociones de cantidad, número " +
                        "y sistema de numeración decimal; y usarlas en situaciones retadoras de diversos contextos. Buscar " +
                        "solución a estos retos supone poner en juego el pensamiento lógico y desplegar procesos " +
                        "relacionados con la comunicación, la representación, la argumentación de relaciones numéricas y el " +
                        "uso de estrategias, procedimientos y propiedades de las operaciones. Discernir si la solución buscada " +
                        "requiere hacer uso de aproximaciones, estimaciones o cálculos exactos, para lo cual es necesario que " +
                        "el estudiante comprenda y dote de significado a los números y sus operaciones. " +
                        "En el primer ciclo del nivel de Educación Inicial la competencia Resuelve problemas de cantidad se " +
                        "denomina Construye la noción de cantidad. Esta competencia implica el desarrollo de las primeras " +
                        "nociones relacionadas con la cantidad; a partir de la exploración es que niños y niñas empiezan a " +
                        "establecer relaciones entre los objetos, personas y situaciones que están en su entorno.");
        competencias.add(comp_9);
        Competencia comp_10 = new Competencia(10, "Indaga mediante métodos científicos para construir sus conocimientos",
                "Consiste en orientarse en el espacio al visualizar, interpretar y relacionar las características de los objetos, sus " +
                        "atributos medibles, posiciones y movimientos, con formas bidimensionales y tridimensionales y sus " +
                        "propiedades, y usarlas en situaciones retadoras de diversos contextos. Buscar solución a estos retos " +
                        "implica poner en juego el pensamiento lógico, clasificar las formas estableciendo relaciones entre sus " +
                        "propiedades. Así como desplegar procesos de representación desde diversas perspectivas usando " +
                        "sistemas de referencia, modelos, lenguaje geométrico, estrategias y diversos recursos. " +
                        "En el primer ciclo del nivel de Educación Inicial esta competencia se denomina Establece relaciones " +
                        "espaciales. Los niños y las niñas desarrollan esta competencia a partir de la estructuración de sus " +
                        "primeras nociones espaciales, lo que implica que establezcan relaciones entre su posición, la posición " +
                        "de otros y sus desplazamientos en el espacio a partir de la exploración de su entorno.");
        competencias.add(comp_10);
        Competencia comp_11 = new Competencia(11, "Resuelve problemas de forma, movimiento y localización",
                "El estudiante es capaz de construir su conocimiento acerca del funcionamiento y estructura del " +
                        "mundo que le rodea a través de procedimientos propios de la ciencia, desarrollando habilidades " +
                        "científicas, reflexionando acerca de lo que sabe y de cómo ha llegado a saberlo poniendo en juego " +
                        "actitudes como la curiosidad, asombro, escepticismo, entre otros. Esta competencia implica la " +
                        "combinación de las siguientes capacidades: " +
                        "En el primer ciclo, la competencia Indaga mediante métodos científicos para construir sus " +
                        "conocimiento se denomina Explora su entorno para conocerlo. Esta competencia supone la " +
                        "exploración que niños y niñas realizan para conocer su entorno y construir de manera progresiva su " +
                        "propio conocimiento acerca del funcionamiento y estructura del mundo que le rodea, poniendo en " +
                        "juego actitudes que son innatas en ellos como la curiosidad y el asombro, y que son promovidas a " +
                        "través de la exploración libre y situaciones de indagación.");
        competencias.add(comp_11);
        area_5.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_5);
        ciclo_i.getCompetencias().add(area_5.getCompetencias().get(0));
        ciclo_i.getCompetencias().add(area_5.getCompetencias().get(1));
        ciclo_i.getCompetencias().add(area_5.getCompetencias().get(2));
        cicloRepository.save(ciclo_i);

        competencias.clear();

        /**
         * Area_6: Matemática
         */
        Area area_6 = areaRepository.findByCode(6);
        Competencia comp_12 = new Competencia(12, "Resuelve problemas de cantidad",
                "Consiste en estimar, comparar y relacionar " +
                        "cantidades o medidas, a partir de la construcción y comprensión de las nociones de cantidad, " +
                        "número y sistema de numeración decimal; y usarlas en situaciones retadoras de diversos contextos. " +
                        "Buscar solución a estos retos supone poner en juego el pensamiento lógico y desplegar procesos " +
                        "relacionados con la comunicación, la representación, la argumentación de relaciones numéricas y el " +
                        "uso de estrategias, procedimientos y propiedades de las operaciones. Discernir si la solución " +
                        "buscada requiere hacer uso de aproximaciones, estimaciones o cálculos exactos, para lo cual es " +
                        "necesario que el estudiante comprenda y dote de significado a los números y sus operaciones. " +
                        "En el ciclo II del nivel de Educación Inicial, esta competencia se denomina Construye la noción de " +
                        "cantidad. Los niños y niñas empiezan a desarrollar esta competencia desde edades tempranas a " +
                        "partir de la curiosidad y el deseo por comprender el mundo. A través de la exploración de su entorno " +
                        "va desarrollan su capacidad de establecer relaciones entre objetos y su capacidad de diseñar " +
                        "estrategias y explicar sus ideas, soluciones o dudas en relación a su exploración del entorno.");
        competencias.add(comp_12);
        Competencia comp_13 = new Competencia(13, "Resuelve problemas de forma, movimiento y localización",
                "Consiste en " +
                        "orientarse en el espacio al visualizar, interpretar y relacionar las características de los objetos, sus " +
                        "atributos medibles, posiciones y movimientos, con formas bidimensionales y tridimensionales y sus " +
                        "propiedades, y usarlas en situaciones retadoras de diversos contextos. Buscar solución a estos retos " +
                        "implica poner en juego el pensamiento lógico, clasificar las formas estableciendo relaciones entre sus " +
                        "propiedades. Así como desplegar procesos de representación desde diversas perspectivas usando " +
                        "sistemas de referencia, modelos, lenguaje geométrico, estrategias y diversos recursos. Esta " +
                        "competencia implica la combinación de las siguientes capacidades: " +
                        "En el ciclo II del nivel de Educación Inicial esta competencia se denomina Establece relaciones " +
                        "espaciales. Los niños y las niñas desarrollan esta competencia a partir de la estructuración de " +
                        "nociones espaciales, de forma y medida. En este nivel se espera que resuelvan problemas en " +
                        "situaciones en las que requiere reconocer la ubicación, la posición de los objetos, construir formas " +
                        "bidimensionales y tridimensionales, comparar la medida de dos objetos o realizar desplazamientos; " +
                        "usando sus propias estrategias y comunicando sus ideas sobre las relaciones que establece.");
        competencias.add(comp_13);
        area_6.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_6);
        ciclo_ii.getCompetencias().add(area_6.getCompetencias().get(0));
        ciclo_ii.getCompetencias().add(area_6.getCompetencias().get(1));
        cicloRepository.save(ciclo_ii);

        competencias.clear();

        /**
         * Area_7: Ciencia y Tecnología
         */
        Area area_7 = areaRepository.findByCode(7);
        Competencia comp_14 = new Competencia(14, "Indaga mediante métodos científicos para construir sus conocimientos",
                "El estudiante es capaz de construir conocimiento acerca del funcionamiento y estructura del mundo " +
                        "que le rodea a través de procedimientos propios de la ciencia, desarrollando habilidades científicas, " +
                        "reflexionando acerca de lo que sabe y del cómo ha llegado a saberlo poniendo en juego actitudes " +
                        "como la curiosidad, asombro, escepticismo, entre otros.");
        competencias.add(comp_14);
        area_7.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_7);
        ciclo_ii.getCompetencias().add(area_7.getCompetencias().get(0));
        cicloRepository.save(ciclo_ii);

        ciclos.clear();
        competencias.clear();

        //===========PRIMARIA===========
        ciclos.add(ciclo_iii);
        ciclos.add(ciclo_iv);
        ciclos.add(ciclo_v);

        /**
         * Area_8: "Mate prim"
         */
        Area area_8 = areaRepository.findByCode(8);
        competencias.add(new Competencia(15, "Resuelve problemas de cantidad",
                "Consiste en que el estudiante solucione problemas o plantee nuevos que le demanden construir y comprender las nociones de número," +
                        "de sistemas numéricos, sus operaciones y propiedades. Además dotar de significado a estos" +
                        "conocimientos en la situación y usarlos para representar o reproducir las relaciones entre sus" +
                        "datos y condiciones. Implica también discernir si la solución buscada requiere darse como una" +
                        "estimación o cálculo exacto, y para esto selecciona estrategias, procedimientos, unidades de" +
                        "medida y diversos recursos. El razonamiento lógico en esta competencia es usado cuando el" +
                        "estudiante hace comparaciones, explica a través de analogías, induce propiedades a partir de" +
                        "casos particulares o ejemplos, en el proceso de resolución del problema"));
        competencias.add(new Competencia(16, "Resuelve problemas de regularidad, equivalencia y cambio",
                "Consiste en que el estudiante logre caracterizar equivalencias y generalizar regularidades y el " +
                        "cambio de una magnitud con respecto de otra, a través de reglas generales que le permitan " +
                        "encontrar valores desconocidos, determinar restricciones y hacer predicciones sobre el " +
                        "comportamiento de un fenómeno. Para esto plantea ecuaciones, inecuaciones y funciones, y " +
                        "usa estrategias, procedimientos y propiedades para resolverlas, graficarlas o manipular " +
                        "expresiones simbólicas. Así también razona de manera inductiva y deductiva, para determinar " +
                        "leyes generales mediante varios ejemplos, propiedades y contraejemplos. "));
        competencias.add(new Competencia(17, "Resuelve problemas de movimiento, forma y localización",
                "Consiste en que el estudiante se oriente y describa la posición y el movimiento de objetos y de sí mismo en " +
                        "el espacio, visualizando, interpretando y relacionando las características de los objetos con " +
                        "formas geométricas bidimensionales y tridimensionales. Implica que realice mediciones directas " +
                        "o indirectas de la superficie, del perímetro, del volumen y de la capacidad de los objetos, y que " +
                        "logre construir representaciones de las formas geométricas para diseñar objetos, planos y " +
                        "maquetas, usando instrumentos, estrategias y procedimientos de construcción y medida. " +
                        "Además describa trayectorias y rutas, usando sistemas de referencia y lenguaje geométrico."));
        competencias.add(new Competencia(18, "Resuelve problemas de gestión de datos e incertidumbre",
                "Consiste en que el estudiante analice datos sobre un tema de interés o estudio o de situaciones aleatorias, " +
                        "que le permita tomar decisiones, elaborar predicciones razonables y conclusiones respaldadas " +
                        "en la información producida. Para ello, el estudiante recopila, organiza y representa datos que " +
                        "le dan insumos para el análisis, interpretación e inferencia del comportamiento determinista o " +
                        "aleatorio de los mismos usando medidas estadísticas y probabilísticas."));
        area_8.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_8);
        ciclo_iii.getCompetencias().addAll(area_8.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_8.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_8.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_9: "Comu prim"
 */
        Area area_9 = areaRepository.findByCode(9);
        competencias.add(new Competencia(19, "Se comunica oralmente",
                "Se define como una interacción dinámica entre uno " +
                        "o más interlocutores para expresar y comprender ideas y emociones. Supone un proceso activo " +
                        "de construcción del sentido de los diversos tipos de textos orales ya que el estudiante alterna los " +
                        "roles de hablante y oyente con el fin de lograr su propósito comunicativo. " +
                        "Esta competencia se asume como una práctica social donde el estudiante interactúa con distintos " +
                        "individuos o comunidades socioculturales, ya sea de forma presencial o virtual. Al hacerlo, tiene " +
                        "la posibilidad de usar el lenguaje oral de manera creativa y responsable, considerando la " +
                        "repercusión de lo expresado o escuchado, y estableciendo una posición crítica con los medios de " +
                        "comunicación audiovisuales. La comunicación oral es una herramienta fundamental para la " +
                        "constitución de las identidades y el desarrollo personal."));
        competencias.add(new Competencia(20, "Lee diversos tipos de textos escritos",
                "Esta competencia se define como una " +
                        "interacción dinámica entre el lector, el texto y los contextos socioculturales que enmarcan la " +
                        "lectura. Supone para el estudiante un proceso activo de construcción del sentido, ya que el " +
                        "estudiante no solo decodifica o comprende la información explícita de los textos que lee sino " +
                        "que es capaz de interpretarlos y establecer una posición sobre ellos. " +
                        "Cuando el estudiante pone en juego está competencia utiliza saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia " +
                        "de la diversidad de propósitos que tiene la lectura, del uso que se hace de esta en distintos " +
                        "ámbitos de la vida, del papel de la experiencia literaria en la formación de lectores y de las " +
                        "relaciones intertextuales que se establecen entre los textos leídos. Esto es crucial en un mundo " +
                        "donde las nuevas tecnologías y la multimodalidad han transformado los modos de leer. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una " +
                        "práctica social situada en distintos grupos o comunidades de lectores. Al involucrarse con la " +
                        "lectura, el estudiante contribuye con su desarrollo personal, así como el de su propia comunidad, " +
                        "además de conocer e interactuar con contextos socioculturales distintos al suyo."));
        competencias.add(new Competencia(21, "Escribe diversos tipos de textos",
                "Esta competencia se define como el uso del " +
                        "lenguaje escrito para construir sentidos en el texto y comunicarlos a otros. Se trata de un " +
                        "proceso reflexivo porque supone la adecuación y organización de los textos considerando los " +
                        "contextos y el propósito comunicativo, así como la revisión permanente de lo escrito con la " +
                        "finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el " +
                        "sistema alfabético y un conjunto de convenciones de la escritura, así como diferentes estrategias " +
                        "para ampliar ideas, enfatizar o matizar significados en los textos que escribe. Con ello, toma " +
                        "conciencia de las posibilidades y limitaciones que ofrece el lenguaje, la comunicación y el " +
                        "sentido. Esto es crucial en una época dominada por nuevas tecnologías que han transformado " +
                        "la naturaleza de la comunicación escrita. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como " +
                        "una práctica social que permite participar en distintos grupos o comunidades socioculturales. " +
                        "Además de participar en la vida social, esta competencia supone otros propósitos, como la " +
                        "construcción de conocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se " +
                        "ofrece la posibilidad de interactuar con otras personas empleando el lenguaje escrito de manera " +
                        "creativa y responsable, teniendo en cuenta su repercusión en los demás."));
        area_9.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_9);
        ciclo_iii.getCompetencias().addAll(area_9.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_9.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_9.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_10: "Ingles prim"
 */
        Area area_10 = areaRepository.findByCode(10);
        competencias.add(new Competencia(22, "Se comunica oralmente en inglés como lengua extranjera",
                "se define como una interacción dinámica " +
                        "entre uno o más interlocutores para expresar y comprender ideas y emociones. Supone un " +
                        "proceso activo de construcción del sentido de los diversos tipos de textos orales ya que el " +
                        "estudiante alterna los roles de hablante y oyente con el fin de lograr su propósito comunicativo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes del lenguaje oral y del mundo que lo rodea. Esto significa considerar los modos de " +
                        "cortesía de acuerdo al contexto sociocultural, así como los recursos no verbales y paraverbales " +
                        "y las diversas estrategias de manera pertinente para expresarse, intercambiar información, " +
                        "persuadir, consensuar, entre otros fines. De igual forma, supone tomar conciencia del impacto " +
                        "de las nuevas tecnologías en la oralidad. " +
                        "La comunicación oral es una herramienta fundamental para la constitución de las identidades y " +
                        "el desarrollo personal. Esta competencia se asume como una práctica social donde el estudiante " +
                        "interactúa con distintos individuos o comunidades socioculturales, ya sea de forma presencial o " +
                        "virtual. Al hacerlo, tiene la posibilidad de usar el lenguaje oral de manera creativa y responsable, " +
                        "considerando la repercusión de lo expresado o escuchado, y estableciendo una posición crítica " +
                        "con los medios de comunicación audiovisuales."));
        competencias.add(new Competencia(23, "Lee diversos tipos de textos en inglés como lengua extranjera",
                "se define como una interacción " +
                        "dinámica entre el lector, el texto y los contextos socioculturales que enmarcan la lectura. Supone " +
                        "un proceso activo de construcción del sentido ya que el estudiante no solo decodifica o " +
                        "comprende la información explícita de los textos que lee sino que es capaz de interpretarlos y " +
                        "establecer una posición sobre ellos. " +
                        "En esta competencia el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia " +
                        "de la diversidad de propósitos que tiene la lectura, del uso que se hace de esta en distintos " +
                        "ámbitos de la vida. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una " +
                        "práctica social situada en distintos grupos o comunidades de lectores. Al involucrarse con la " +
                        "lectura, el estudiante contribuye con su desarrollo personal, así como el de su propia " +
                        "comunidad, además de conocer e interactuar con contextos socioculturales distintos al suyo."));
        competencias.add(new Competencia(24, "Escribe diversos tipos de textos inglés como lengua extranjera",
                "se define como el uso del " +
                        "lenguaje escrito para construir sentidos en el texto y comunicarlos a otros. Se trata de un " +
                        "proceso reflexivo porque supone la adecuación y organización de los textos considerando los " +
                        "contextos y el propósito comunicativo, así como la revisión permanente de lo escrito con la " +
                        "finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el " +
                        "sistema alfabético y un conjunto de convenciones de la escritura, así como diferentes estrategias " +
                        "para ampliar ideas, enfatizar o matizar significados en los textos que escribe. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como " +
                        "una práctica social que permite participar en distintos grupos o comunidades socioculturales. " +
                        "Además de participar en la vida social, esta competencia supone otros propósitos, como la " +
                        "construcción de conocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se " +
                        "ofrece la posibilidad de interactuar con otras personas empleando el lenguaje escrito de manera " +
                        "creativa y responsable, teniendo en cuenta su repercusión en los demás."));
        area_10.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_10);
        ciclo_iii.getCompetencias().addAll(area_10.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_10.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_10.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_11: "Personal Social prim"
 */
        Area area_11 = areaRepository.findByCode(11);
        competencias.add(new Competencia(25, "Construye su identidad",
                "El estudiante conoce y valora su cuerpo, su forma de " +
                        "sentir, de pensar y de actuar desde el reconocimiento de las distintas identidades que lo definen " +
                        "(histórica, étnica, social, sexual, cultural, de género, entre otras) como producto de las " +
                        "interacciones continuas entre los individuos y los diversos contextos en los que se desenvuelven " +
                        "(familia, escuela, comunidad). No se trata que los estudiantes construyan una identidad “ideal”, " +
                        "sino que cada estudiante pueda –a su propio ritmo y criterio– ser consciente de las características " +
                        "que lo hacen único y de aquellas que lo hacen semejantes a otros."));
        competencias.add(new Competencia(26, "Convive y participa democráticamente",
                "El estudiante actúa en la sociedad " +
                        "relacionándose con los demás de manera justa y equitativa, reconociendo que todas las personas " +
                        "tienen los mismos derechos y responsabilidades. Muestra disposición por conocer, comprender " +
                        "y enriquecerse con los aportes de las diversas culturas, respetando las diferencias. De igual forma, " +
                        "toma posición frente a aquellos asuntos que lo involucra como ciudadano y contribuye en la " +
                        "construcción del bienestar general, en la consolidación de los procesos democráticos y en la " +
                        "promoción de los derechos humanos."));
        competencias.add(new Competencia(27, "Construye interpretaciones históricas",
                "Sustenta una posición crítica sobre " +
                        "hechos y procesos históricos que ayuden a comprender el siglo XXI y sus desafíos, articulando el " +
                        "uso de distintas fuentes, la comprensión de los cambios, permanencias, simultaneidades y " +
                        "secuencias temporales y la explicación de las múltiples causas y consecuencias de estos. Supone " +
                        "reconocerse como sujeto histórico, es decir, como protagonista de los procesos históricos y, " +
                        "como tal, producto de un pasado, pero que, a la vez, está construyendo su futuro."));
        competencias.add(new Competencia(28, "Gestiona responsablemente el ambiente y el espacio",
                "El estudiante toma " +
                        "decisiones que contribuyen a la satisfacción de las necesidades desde una posición crítica y una " +
                        "perspectiva de desarrollo sostenible -es decir, sin poner en riesgo a las generaciones futuras-, y " +
                        "participa en acciones que disminuyen la vulnerabilidad de la sociedad frente a distintos desastres. " +
                        "Supone comprender que el espacio es una construcción social dinámica, es decir, un espacio de " +
                        "interacción entre elementos naturales y sociales que se va transformando a lo largo del tiempo y " +
                        "donde el ser humano cumple un rol fundamental."));
        competencias.add(new Competencia(29, "Gestiona responsablemente los recursos económicos",
                "El estudiante es " +
                        "capaz de administrar los recursos, tanto personales como familiares, a partir de asumir una " +
                        "postura crítica sobre el manejo de estos, de manera informada y responsable. Esto supone " +
                        "reconocerse como agente económico, comprender la función de los recursos económicos en la " +
                        "satisfacción de las necesidades, y el funcionamiento del sistema económico y financiero."));
        area_11.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_11);
        ciclo_iii.getCompetencias().addAll(area_11.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_11.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_11.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_12: "Arte y cultura prim"
 */
        Area area_12 = areaRepository.findByCode(12);
        competencias.add(new Competencia(30, "Aprecia de manera crítica manifestaciones artístico-culturales",
                "Se define como la interacción entre el estudiante y manifestaciones artístico-culturales para que " +
                        "puedan observarlas, investigarlas, comprenderlas y reflexionar sobre ellas. Permite al estudiante " +
                        "desarrollar habilidades para percibir, describir y analizar sus cualidades estéticas, para ayudarlo " +
                        "a “leer” y entender el arte que observa y experimenta. Supone comprender y apreciar los " +
                        "contextos específicos en que se originan estas manifestaciones, y entender que tener " +
                        "conocimiento sobre estos contextos mejora nuestra capacidad de apreciar, producir y " +
                        "entendernos a nosotros mismos, a otros y al entorno. También implica emitir juicios de valor " +
                        "cada vez más informados, basándose en los conocimientos obtenidos en el proceso de " +
                        "apreciación crítica."));
        competencias.add(new Competencia(31, "Crea proyectos desde los lenguajes artísticos",
                "El estudiante usa los " +
                        "diversos lenguajes artísticos (artes visuales, música, danza, teatro, artes interdisciplinares y otros) " +
                        "para expresar o comunicar mensajes, ideas y sentimientos. En la que pone en práctica habilidades " +
                        "imaginativas, creativas y reflexivas para generar ideas, planificar, concretar propuestas y " +
                        "evaluarlas de manera continua. Para lo cual hace uso de recursos y conocimientos que ha " +
                        "desarrollado en su interacción con el entorno, con manifestaciones artístico-culturales diversas y " +
                        "con los diversos lenguajes artísticos. Experimenta, investiga y aplica los diferentes materiales, " +
                        "técnicas y elementos del arte con una intención específica. Así mismo, reflexiona sobre sus " +
                        "procesos y creaciones y los socializa con otros, con el fin de seguir desarrollando sus capacidades " +
                        "críticas y creativas."));
        area_12.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_12);
        ciclo_iii.getCompetencias().addAll(area_12.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_12.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_12.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_13: "Ciencia y Tecnología prim"
 */
        Area area_13 = areaRepository.findByCode(13);
        competencias.add(new Competencia(32, "Indaga mediante métodos científicos",
                "El estudiante es capaz de construir su conocimiento acerca del " +
                        "funcionamiento y estructura del mundo natural y artificial que le rodea, a través de " +
                        "procedimientos propios de la ciencia, reflexionando acerca de lo que sabe y de cómo ha llegado " +
                        "a saberlo poniendo en juego actitudes como la curiosidad, asombro, escepticismo, entre otras."));
        competencias.add(new Competencia(33, "Explica el mundo físico basándose en conocimientos sobre los seres vivos; materia y energía; biodiversidad, Tierra y universo",
                "El estudiante es capaz de " +
                        "comprender conocimientos científicos relacionados a hechos o fenómenos naturales, sus causas " +
                        "y relaciones con otros fenómenos, construyendo representaciones del mundo natural y artificial. " +
                        "Esta representación del mundo, le permite evaluar situaciones donde la aplicación de la ciencia " +
                        "y la tecnología se encuentran en debate, para construir argumentos que le llevan a participar, " +
                        "deliberar y tomar decisiones en asuntos personales y públicos, mejorando su calidad de vida, así " +
                        "como conservar el ambiente."));
        competencias.add(new Competencia(34, "Diseña y construye soluciones tecnológicas para resolver problemas",
                "tecnológicas para resolver problemas de su " +
                        "entorno. El estudiante es capaz de construir objetos, procesos o sistemas tecnológicos, basados " +
                        "en conocimientos científicos, tecnológicos y de diversas prácticas locales, para dar respuesta a " +
                        "problemas del contexto, ligados a las necesidades sociales, poniendo en juego la creatividad y " +
                        "perseverancia."));
        area_13.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_13);
        ciclo_iii.getCompetencias().addAll(area_13.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_13.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_13.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_14: "Educación Física prim"
 */
        Area area_14 = areaRepository.findByCode(14);
        competencias.add(new Competencia(35, "Se desenvuelve de manera autónoma a través de su motricidad",
                "El estudiante comprende y toma conciencia de sí mismo en interacción con el espacio y las " +
                        "personas de su entorno, lo que le permite construir su identidad y autoestima. Interioriza y " +
                        "organiza sus movimientos eficazmente según sus posibilidades, en la práctica de actividades " +
                        "físicas como el juego, el deporte y aquellas que se desarrollan en la vida cotidiana. Asimismo, " +
                        "es capaz de expresar y comunicar a través de su cuerpo manifestando ideas, emociones y " +
                        "sentimientos con gestos, posturas, tono muscular, entre otros."));
        competencias.add(new Competencia(36, "Asume una vida saludable",
                "El estudiante tiene conciencia reflexiva hacia el " +
                        "logro del bienestar común incorporando prácticas autónomas que conllevan a una mejora de su " +
                        "calidad de vida. Esto supone la comprensión y aplicación de la actividad física para la salud y de " +
                        "los conocimientos relacionados con posturas adecuadas, alimentación e higiene corporal " +
                        "saludables según sus recursos y entorno."));
        competencias.add(new Competencia(37, "Interactúa a través de sus habilidades sociomotrices",
                "En la práctica de " +
                        "diferentes actividades físicas (juegos, deportes, actividades predeportivas, etc). Implica poner " +
                        "en juego los recursos personales para una apropiada interacción social, inclusión y convivencia, " +
                        "insertándose adecuadamente en el grupo y resolviendo conflictos de manera asertiva, empática " +
                        "y pertinente a cada situación. De igual manera, aplica estrategias y tácticas para el logro de un " +
                        "objetivo común en la práctica de diferentes actividades físicas, mostrando una actitud proactiva " +
                        "en la organización de eventos lúdicos y deportivos."));
        area_14.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_14);
        ciclo_iii.getCompetencias().addAll(area_14.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_14.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_14.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_15: "Educación Religiosa prim"
 */
        Area area_15 = areaRepository.findByCode(15);
        competencias.add(new Competencia(38, "Construye su identidad como persona humana, amada por Dios, digna, libre y trascendente"));
        competencias.add(new Competencia(39, "Asume la experiencia el encuentro personal y comunitario con Dios"));
        area_15.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_15);
        ciclo_iii.getCompetencias().addAll(area_15.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_15.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_15.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        competencias.clear();

/**
 * Area_16: "Castellano como segunda lengua prim"
 */
        Area area_16 = areaRepository.findByCode(16);
        competencias.add(new Competencia(40, "Se comunica oralmente en castellano como segunda lengua",
                " Se define como una interacción dinámica entre uno o más interlocutores para expresar y " +
                        "comprender ideas y emociones. Supone un proceso activo de construcción del sentido de los " +
                        "diversos tipos de textos orales ya que el estudiante alterna los roles de hablante y oyente con el " +
                        "fin de lograr su propósito comunicativo. El estudiante pone en juego saberes de distinto tipo y " +
                        "recursos provenientes del lenguaje oral y del mundo que lo rodea. Esto significa considerar los " +
                        "modos de cortesía de acuerdo al contexto sociocultural, así como los recursos no verbales y " +
                        "paraverbales y las diversas estrategias de manera pertinente para expresarse, intercambiar " +
                        "información, persuadir, consensuar, entre otros fines. De igual forma, supone tomar conciencia " +
                        "del impacto de las nuevas tecnologías en la oralidad. " +
                        "La comunicación oral es una herramienta fundamental para la constitución de las identidades y " +
                        "el desarrollo personal. Esta competencia se asume como una práctica social donde el estudiante " +
                        "interactúa con distintos individuos o comunidades socioculturales, ya sea de forma presencial o " +
                        "virtual. Al hacerlo, tiene la posibilidad de usar el lenguaje oral de manera creativa y responsable, " +
                        "considerando la repercusión de lo expresado o escuchado, y estableciendo una posición crítica " +
                        "con los medios de comunicación audiovisuales."));
        competencias.add(new Competencia(41, "Lee diversos tipos de textos escritos en castellano como segunda lengua",
                "Esta competencia se define como una interacción dinámica entre el lector, el texto y los contextos " +
                        "socioculturales que enmarcan la lectura. Supone un proceso activo de construcción del sentido, " +
                        "ya que el estudiante no solo decodifica o comprende la información explícita de los textos que " +
                        "lee sino que es capaz de interpretarlos y establecer una posición sobre ellos. " +
                        "En esta competencia el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia " +
                        "de la diversidad de propósitos que tiene la lectura, del uso que se hace de esta en distintos " +
                        "ámbitos de la vida, del papel de la experiencia literaria en la formación de lectores y de las " +
                        "relaciones intertextuales que se establecen entre los textos leídos. Esto es crucial en un mundo " +
                        "donde las nuevas tecnologías y la multimodalidad han transformado los modos de leer. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una " +
                        "práctica social situada en distintos grupos o comunidades de lectores. Al involucrarse con la " +
                        "lectura, el estudiante contribuye con su desarrollo personal, así como el de su propia " +
                        "comunidad, además de conocer e interactuar con contextos socioculturales distintos al suyo."));
        competencias.add(new Competencia(42, "Escribe diversos tipos de textos castellano como segunda lengua",
                "Esta competencia se define como el uso del lenguaje escrito para construir sentidos en el texto y " +
                        "comunicarlos a otros. Se trata de un proceso reflexivo porque supone la adecuación y " +
                        "organización de los textos considerando los contextos y el propósito comunicativo, así como la " +
                        "revisión permanente de lo escrito con la finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos provenientes " +
                        "de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el sistema alfabético y un " +
                        "conjunto de convenciones de la escritura, así como diferentes estrategias para ampliar ideas, " +
                        "enfatizar o matizar significados en los textos que escribe. Con ello, toma conciencia de las " +
                        "posibilidades y limitaciones que ofrece el lenguaje, la comunicación y el sentido. Esto es crucial en " +
                        "una época dominada por nuevas tecnologías que han transformado la naturaleza de la comunicación " +
                        "escrita. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como una " +
                        "práctica social que permite participar en distintos grupos o comunidades socioculturales. Además " +
                        "de participar en la vida social, esta competencia supone otros propósitos, como la construcción de " +
                        "conocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se ofrece la posibilidad " +
                        "de interactuar con otras personas empleando el lenguaje escrito de manera creativa y responsable, " +
                        "teniendo en cuenta su repercusión en los demás."));
        area_16.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_16);
        ciclo_iii.getCompetencias().addAll(area_16.getCompetencias());
        ciclo_iv.getCompetencias().addAll(area_16.getCompetencias());
        ciclo_v.getCompetencias().addAll(area_16.getCompetencias());
        cicloRepository.save(ciclo_iii);
        cicloRepository.save(ciclo_iv);
        cicloRepository.save(ciclo_v);

        ciclos.clear();
        competencias.clear();

        //==============SECUNDARIA==============
        ciclos.add(ciclo_vi);
        ciclos.add(ciclo_vii);

/**
 * Area_17: "Matemática sec"
 */
        Area area_17 = areaRepository.findByCode(17);
        competencias.add(new Competencia(43, "Resuelve problemas de cantidad",
                "Consiste en que el estudiante solucione " +
                        "problemas o plantee nuevos que le demanden construir y comprender las nociones de número, de " +
                        "sistemas numéricos, sus operaciones y propiedades. Además dotar de significado a estos " +
                        "conocimientos en la situación y usarlos para representar o reproducir las relaciones entre sus datos " +
                        "y condiciones. Implica también discernir si la solución buscada requiere darse como una estimación " +
                        "o cálculo exacto, y para esto selecciona estrategias, procedimientos, unidades de medida y diversos " +
                        "recursos. El razonamiento lógico en esta competencia es usado cuando el estudiante hace " +
                        "comparaciones, explica a través de analogías, induce propiedades a partir de casos particulares o " +
                        "ejemplos, en el proceso de resolución del problema."));
        competencias.add(new Competencia(44, "Resuelve problemas de regularidad, equivalencia y cambio",
                "Consiste en que el estudiante logre caracterizar equivalencias y generalizar regularidades y el " +
                        "cambio de una magnitud con respecto de otra, a través de reglas generales que le permitan " +
                        "encontrar valores desconocidos, determinar restricciones y hacer predicciones sobre el " +
                        "comportamiento de un fenómeno. Para esto plantea ecuaciones, inecuaciones y funciones, y usa " +
                        "estrategias, procedimientos y propiedades para resolverlas, graficarlas o manipular expresiones " +
                        "simbólicas. Así también razona de manera inductiva y deductiva, para determinar leyes generales " +
                        "mediante varios ejemplos, propiedades y contraejemplos."));
        competencias.add(new Competencia(45, "Resuelve problemas de movimiento, forma y localización",
                "Consiste en que el estudiante se oriente y describa la posición y el movimiento de objetos y de sí mismo en el " +
                        "espacio, visualizando, interpretando y relacionando las características de los objetos con formas " +
                        "geométricas bidimensionales y tridimensionales. Implica que realice mediciones directas o " +
                        "indirectas de la superficie, del perímetro, del volumen y de la capacidad de los objetos, y que logre " +
                        "construir representaciones de las formas geométricas para diseñar objetos, planos y maquetas, " +
                        "usando instrumentos, estrategias y procedimientos de construcción y medida. Además describa " +
                        "trayectorias y rutas, usando sistemas de referencia y lenguaje geométrico."));
        competencias.add(new Competencia(46, "Resuelve problemas de gestión de datos e incertidumbre",
                "Consiste en que " +
                        "el estudiante analice datos sobre un tema de interés o estudio o de situaciones aleatorias, que le " +
                        "permita tomar decisiones, elaborar predicciones razonables y conclusiones respaldadas en la " +
                        "información producida. Para ello, el estudiante recopila, organiza y representa datos que le dan " +
                        "insumos para el análisis, interpretación e inferencia del comportamiento determinista o aleatorio " +
                        "de los mismos usando medidas estadísticas y probabilísticas."));
        area_17.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_17);
        ciclo_vi.getCompetencias().addAll(area_17.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_17.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_18: "Comunicación sec"
 */
        Area area_18 = areaRepository.findByCode(18);
        competencias.add(new Competencia(47, "Se comunica oralmente en lengua materna",
                " Se define como una interacción dinámica entre uno o más interlocutores para expresar y comprender ideas y " +
                        "emociones. Supone un proceso activo de construcción del sentido de los diversos tipos de textos " +
                        "orales ya que el estudiante alterna los roles de hablante y oyente con el fin de lograr su propósito " +
                        "comunicativo. " +
                        "Esta competencia se asume como una práctica social donde el estudiante interactúa con distintos " +
                        "individuos o comunidades socioculturales, ya sea de forma presencial o virtual. Al hacerlo, tiene " +
                        "la posibilidad de usar el lenguaje oral de manera creativa y responsable, considerando la " +
                        "repercusión de lo expresado o escuchado, y estableciendo una posición crítica con los medios de " +
                        "comunicación audiovisuales. La comunicación oral es una herramienta fundamental para la " +
                        "constitución de las identidades y el desarrollo personal."));
        competencias.add(new Competencia(48, "Lee diversos tipos de textos escritos",
                "Esta competencia se define como una interacción dinámica entre el lector, el texto y los contextos " +
                        "socioculturales que enmarcan la lectura. Supone para el estudiante un proceso activo de " +
                        "construcción del sentido, ya que el estudiante no solo decodifica o comprende la información " +
                        "explícita de los textos que lee sino que es capaz de interpretarlos y establecer una posición sobre " +
                        "ellos. Cuando el estudiante pone en juego está competencia utiliza saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia " +
                        "de la diversidad de propósitos que tiene la lectura, del uso que se hace de esta en distintos " +
                        "ámbitos de la vida, del papel de la experiencia literaria en la formación de lectores y de las " +
                        "relaciones intertextuales que se establecen entre los textos leídos. Esto es crucial en un mundo " +
                        "donde las nuevas tecnologías y la multimodalidad han transformado los modos de leer. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una " +
                        "práctica social situada en distintos grupos o comunidades de lectores. Al involucrarse con la " +
                        "lectura, el estudiante contribuye con su desarrollo personal, así como el de su propia comunidad, " +
                        "además de conocer e interactuar con contextos socioculturales distintos al suyo."));
        competencias.add(new Competencia(49, "Escribe diversos tipos de textos",
                "Esta competencia se define como el uso del " +
                        "lenguaje escrito para construir sentidos en el texto y comunicarlos a otros. Se trata de un " +
                        "proceso reflexivo porque supone la adecuación y organización de los textos considerando los " +
                        "contextos y el propósito comunicativo, así como la revisión permanente de lo escrito con la " +
                        "finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el " +
                        "sistema alfabético y un conjunto de convenciones de la escritura, así como diferentes estrategias " +
                        "para ampliar ideas, enfatizar o matizar significados en los textos que escribe. Con ello, toma " +
                        "conciencia de las posibilidades y limitaciones que ofrece el lenguaje, la comunicación y el " +
                        "sentido. Esto es crucial en una época dominada por nuevas tecnologías que han transformado " +
                        "la naturaleza de la comunicación escrita. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como " +
                        "una práctica social que permite participar en distintos grupos o comunidades socioculturales. " +
                        "Además de participar en la vida social, esta competencia supone otros propósitos, como la " +
                        "construcción de conocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se " +
                        "ofrece la posibilidad de interactuar con otras personas empleando el lenguaje escrito de manera " +
                        "creativa y responsable, teniendo en cuenta su repercusión en los demás."));
        area_18.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_18);
        ciclo_vi.getCompetencias().addAll(area_18.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_18.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_19: "Inglés sec"
 */

        Area area_19 = areaRepository.findByCode(19);
        competencias.add(new Competencia(50, "Se comunica oralmente en inglés como lengua extranjera",
                "se define como una interacción dinámica " +
                        "entre uno o más interlocutores para expresar y comprender ideas y emociones. Supone un " +
                        "proceso activo de construcción del sentido de los diversos tipos de textos orales ya que el " +
                        "estudiante alterna los roles de hablante y oyente con el fin de lograr su propósito comunicativo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes del lenguaje oral y del mundo que lo rodea. Esto significa considerar los modos de " +
                        "cortesía de acuerdo al contexto sociocultural, así como los recursos no verbales y paraverbales " +
                        "y las diversas estrategias de manera pertinente para expresarse, intercambiar información, " +
                        "persuadir, consensuar, entre otros fines. De igual forma, supone tomar conciencia del impacto " +
                        "de las nuevas tecnologías en la oralidad."));
        competencias.add(new Competencia(51, "Lee diversos tipos de textos en inglés como lengua extranjera",
                "se define como una interacción " +
                        "dinámica entre el lector, el texto y los contextos socioculturales que enmarcan la lectura. Supone " +
                        "un proceso activo de construcción del sentido ya que el estudiante no solo decodifica o " +
                        "comprende la información explícita de los textos que lee sino que es capaz de interpretarlos y " +
                        "establecer una posición sobre ellos. " +
                        "En esta competencia el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia " +
                        "de la diversidad de propósitos que tiene la lectura, del uso que se hace de esta en distintos " +
                        "ámbitos de la vida. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una " +
                        "práctica social situada en distintos grupos o comunidades de lectores. Al involucrarse con la " +
                        "lectura, el estudiante contribuye con su desarrollo personal, así como el de su propia " +
                        "comunidad, además de conocer e interactuar con contextos socioculturales distintos al suyo."));
        competencias.add(new Competencia(52, "Escribe diversos tipos de textos inglés como lengua extranjera",
                "se define como el uso del " +
                        "lenguaje escrito para construir sentidos en el texto y comunicarlos a otros. Se trata de un " +
                        "proceso reflexivo porque supone la adecuación y organización de los textos considerando los " +
                        "contextos y el propósito comunicativo, así como la revisión permanente de lo escrito con la " +
                        "finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el " +
                        "sistema alfabético y un conjunto de convenciones de la escritura, así como diferentes estrategias " +
                        "para ampliar ideas, enfatizar o matizar significados en los textos que escribe. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como " +
                        "una práctica social que permite participar en distintos grupos o comunidades socioculturales. " +
                        "Además de participar en la vida social, esta competencia supone otros propósitos, como la " +
                        "construcción de conocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se " +
                        "ofrece la posibilidad de interactuar con otras personas empleando el lenguaje escrito de manera " +
                        "creativa y responsable, teniendo en cuenta su repercusión en los demás."));
        area_19.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_19);
        ciclo_vi.getCompetencias().addAll(area_19.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_19.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_20: "Arte y Cultura SEC"
 */

        Area area_20 = areaRepository.findByCode(20);
        competencias.add(new Competencia(53, "Aprecia de manera crítica manifestaciones artístico-culturales",
                "Se define como la interacción entre el estudiante y manifestaciones artístico-culturales para que " +
                        "puedan observarlas, investigarlas, comprenderlas y reflexionar sobre ellas. Permite al estudiante " +
                        "desarrollar habilidades para percibir, describir y analizar sus cualidades estéticas, para ayudarlo " +
                        "a “leer” y entender el arte que observa y experimenta. Supone comprender y apreciar los " +
                        "contextos específicos en que se originan estas manifestaciones, y entender que tener " +
                        "conocimiento sobre estos contextos mejora nuestra capacidad de apreciar, producir y " +
                        "entendernos a nosotros mismos, a otros y al entorno. También implica emitir juicios de valor " +
                        "cada vez más informados, basándose en los conocimientos obtenidos en el proceso de " +
                        "apreciación crítica."));
        competencias.add(new Competencia(54, "Crea proyectos desde los lenguajes artísticos",
                "El estudiante usa los diversos lenguajes artísticos (artes visuales, música, danza, teatro, artes interdisciplinares y otros) " +
                        "para expresar o comunicar mensajes, ideas y sentimientos. En la que pone en práctica habilidades " +
                        "imaginativas, creativas y reflexivas para generar ideas, planificar, concretar propuestas y " +
                        "evaluarlas de manera continua. Para lo cual hace uso de recursos y conocimientos que ha " +
                        "desarrollado en su interacción con el entorno, con manifestaciones artístico-culturales diversas y " +
                        "con los diversos lenguajes artísticos. Experimenta, investiga y aplica los diferentes materiales, " +
                        "técnicas y elementos del arte con una intención específica. Así mismo, reflexiona sobre sus " +
                        "procesos y creaciones y los socializa con otros, con el fin de seguir desarrollando sus capacidades " +
                        "críticas y creativas."));
        area_20.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_20);
        ciclo_vi.getCompetencias().addAll(area_20.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_20.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_21: "Ciencias Sociales SEC"
 */

        Area area_21 = areaRepository.findByCode(21);
        competencias.add(new Competencia(55, "Construye interpretaciones históricas",
                "El estudiante sustenta una posición " +
                        "crítica sobre hechos y procesos históricos que ayuden a comprender el siglo XXI y sus desafíos, " +
                        "articulando el uso de distintas fuentes, la comprensión de los cambios, permanencias, " +
                        "simultaneidades y secuencias temporales y la explicación de las múltiples causas y consecuencias " +
                        "de estos. Supone reconocerse como sujeto histórico, es decir, como protagonista de los procesos " +
                        "históricos y, como tal, producto de un pasado, pero que, a la vez, está construyendo su futuro."));
        competencias.add(new Competencia(56, "Gestiona responsablemente el ambiente y el espacio",
                "El estudiante toma " +
                        "decisiones que contribuyen a la satisfacción de las necesidades desde una posición crítica y una " +
                        "perspectiva de desarrollo sostenible -es decir, sin poner en riesgo a las generaciones futuras-, y " +
                        "participa en acciones que disminuyen la vulnerabilidad de la sociedad frente a distintos desastres. " +
                        "Supone comprender que el espacio es una construcción social dinámica, es decir, un espacio de " +
                        "interacción entre elementos naturales y sociales que se va transformando a lo largo del tiempo y " +
                        "donde el ser humano cumple un rol fundamental."));
        competencias.add(new Competencia(57, "Gestiona responsablemente los recursos económicos",
                "El estudiante es " +
                        "capaz de administrar los recursos, tanto personales como familiares, a partir de asumir una " +
                        "postura crítica sobre el manejo de estos, de manera informada y responsable. Esto supone " +
                        "reconocerse como agente económico, comprender la función de los recursos económicos en la " +
                        "satisfacción de las necesidades, y el funcionamiento del sistema económico y financiero."));
        area_21.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_21);
        ciclo_vi.getCompetencias().addAll(area_21.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_21.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_22: "Desarrollo personal, ciudadanía y cívica SEC"
 */

        Area area_22 = areaRepository.findByCode(22);
        competencias.add(new Competencia(58, "Construye su identidad",
                "El estudiante conoce y valora su cuerpo, su forma " +
                        "de sentir, de pensar y de actuar desde el reconocimiento de las distintas identidades que lo " +
                        "definen (histórica, étnica, social, sexual, cultural, de género, entre otras) como producto de las " +
                        "interacciones continuas entre los individuos y los diversos contextos en los que se " +
                        "desenvuelven (familia, escuela, comunidad). No se trata que los estudiantes construyan una " +
                        "identidad “ideal”, sino que cada estudiante pueda –a su propio ritmo y criterio– ser consciente " +
                        "de las características que lo hacen único y de aquellas que lo hacen semejantes a otros."));
        competencias.add(new Competencia(59, "Convive y participa democráticamente",
                "El estudiante actúa en la sociedad " +
                        "relacionándose con los demás de manera justa y equitativa, reconociendo que todas las " +
                        "personas tienen los mismos derechos y responsabilidades. Muestra disposición por conocer, " +
                        "comprender y enriquecerse con los aportes de las diversas culturas, respetando las diferencias. " +
                        "De igual forma, toma posición frente a aquellos asuntos que lo involucra como ciudadano y " +
                        "contribuye en la construcción del bienestar general, en la consolidación de los procesos " +
                        "democráticos y en la promoción de los derechos humanos."));
        area_22.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_22);
        ciclo_vi.getCompetencias().addAll(area_22.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_22.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_23: "Educación Física SEC"
 */

        Area area_23 = areaRepository.findByCode(23);
        competencias.add(new Competencia(60, "Se desenvuelve de manera autónoma a través de su motricidad",
                "El estudiante comprende y toma conciencia de sí mismo en interacción con el espacio y las " +
                        "personas de su entorno, lo que le permite construir su identidad y autoestima. Interioriza y " +
                        "organiza sus movimientos eficazmente según sus posibilidades, en la práctica de actividades " +
                        "físicas como el juego, el deporte y aquellas que se desarrollan en la vida cotidiana. Asimismo, " +
                        "es capaz de expresar y comunicar a través de su cuerpo manifestando ideas, emociones y " +
                        "sentimientos con gestos, posturas, tono muscular, entre otros."));
        competencias.add(new Competencia(61, "Asume una vida saludable",
                "El estudiante tiene conciencia reflexiva hacia el " +
                        "logro del bienestar común incorporando prácticas autónomas que conllevan a una mejora de su " +
                        "calidad de vida. Esto supone la comprensión y aplicación de la actividad física para la salud y de " +
                        "los conocimientos relacionados con posturas adecuadas, alimentación e higiene corporal " +
                        "saludables según sus recursos y entorno."));
        competencias.add(new Competencia(62, "Interactúa a través de sus habilidades sociomotrices",
                "en la práctica de " +
                        "diferentes actividades físicas (juegos, deportes, actividades predeportivas, etc). Implica poner " +
                        "en juego los recursos personales para una apropiada interacción social, inclusión y convivencia, " +
                        "insertándose adecuadamente en el grupo y resolviendo conflictos de manera asertiva, empática " +
                        "y pertinente a cada situación. De igual manera, aplica estrategias y tácticas para el logro de un " +
                        "objetivo común en la práctica de diferentes actividades físicas, mostrando una actitud proactiva " +
                        "en la organización de eventos lúdicos y deportivos."));
        area_23.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_23);
        ciclo_vi.getCompetencias().addAll(area_23.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_23.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_24: "Educación Religiosa SEC"
 */

        Area area_24 = areaRepository.findByCode(24);
        competencias.add(new Competencia(63, "Construye su identidad como persona humana, amada por Dios, digna, libre y trascendente, comprendiendo la doctrina de su propia religión , abierto al dialogo con las que le son más cercanas",
                "El estudiante descubre y asume " +
                        "que existe una verdad trascendente, que le da una identidad y una dignidad humana, toma " +
                        "conciencia de que es hijo de Dios creado a imagen y semejanza, reconoce la acción providente " +
                        "de Dios en su vida, en su comunidad y en la historia humana que le da sentido a los " +
                        "acontecimientos. Desde esta conciencia, los estudiantes aprenderán a relacionarse con Dios, " +
                        "como origen y fin último de todos los valores; consigo mismos por ser parte de la creación; con " +
                        "los demás, como un llamado a vivir la comunión, la corresponsabilidad y la reconciliación, y con " +
                        "la naturaleza para descubrir el sentido de todo lo creado. " +
                        "La educación religiosa, desde el conocimiento de Dios, lleva al estudiante a realizar un diálogo " +
                        "interdisciplinar: fe y cultura, fe y ciencia, fe y vida, para actuar con libertad, autonomía y " +
                        "responsabilidad frente a la vida. Le capacita para el respeto y diálogo con otras creencias " +
                        "presentes en nuestra sociedad pluralista, posibilita el desarrollo espiritual, psicológico y cultural " +
                        "del estudiante, en su propio contexto histórico y ambiental, ayuda a comprender el patrimonio " +
                        "cultural y artístico peruano y le permite estructurar y sistematizar los contenidos de su fe."));
        competencias.add(new Competencia(64, "Asume la experiencia el encuentro personal y comunitario con Dios en su proyecto de vida en coherencia con su creencia religiosa",
                "el estudiante valora " +
                        "a Jesús como modelo, desarrollando valores y virtudes personales que configuran su " +
                        "personalidad libre y responsable propia de quien vive los ideales del Evangelio a través de la " +
                        "experiencia del discipulado. Conoce a Jesucristo como El Salvador, asume sus actitudes y las " +
                        "evidencia en el diario vivir, dando testimonio de su fe; acepta su proyecto de vida confrontando " +
                        "los modelos y paradigmas de hombre que presenta la sociedad con la persona de Jesucristo, lo " +
                        "cual le permite seleccionar y optar por el que responda a sus expectativas de búsqueda personal, " +
                        "al modelo ideal. " +
                        "Desarrolla una cosmovisión cristiana de la realidad interpretando críticamente la cultura."));
        area_24.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_24);
        ciclo_vi.getCompetencias().addAll(area_24.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_24.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_25: "Ciencia y tecnología SEC"
 */

        Area area_25 = areaRepository.findByCode(25);
        competencias.add(new Competencia(65, "Indaga mediante métodos científicos",
                "El estudiante es capaz de construir su conocimiento acerca del funcionamiento y " +
                        "estructura del mundo natural y artificial que le rodea, a través de procedimientos propios de la " +
                        "ciencia, reflexionando acerca de lo que sabe y de cómo ha llegado a saberlo poniendo en juego " +
                        "actitudes como la curiosidad, asombro, escepticismo, entre otras."));
        competencias.add(new Competencia(66, "Explica el mundo físico basándose en conocimientos sobre los seres vivos; materia y energía; biodiversidad, Tierra y universo",
                "El estudiante es capaz de " +
                        "comprender conocimientos científicos relacionados a hechos o fenómenos naturales, sus causas y " +
                        "relaciones con otros fenómenos, construyendo representaciones del mundo natural y artificial. Esta " +
                        "representación del mundo, le permite evaluar situaciones donde la aplicación de la ciencia y la " +
                        "tecnología se encuentran en debate, para construir argumentos que le llevan a participar, deliberar " +
                        "y tomar decisiones en asuntos personales y públicos, mejorando su calidad de vida, así como " +
                        "conservar el ambiente."));
        competencias.add(new Competencia(67, "Diseña y construye soluciones tecnológicas para resolver problemas",
                "El estudiante es capaz de construir objetos, procesos o sistemas tecnológicos, basados en " +
                        "conocimientos científicos, tecnológicos y de diversas prácticas locales, para dar respuesta a " +
                        "problemas del contexto, ligados a las necesidades sociales, poniendo en juego la creatividad y " +
                        "perseverancia."));
        area_25.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_25);
        ciclo_vi.getCompetencias().addAll(area_25.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_25.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_26: "Educación para el Trabajo SEC"
 */

        Area area_26 = areaRepository.findByCode(26);
        competencias.add(new Competencia(68, "Gestiona proyectos de emprendimiento económico y social",
                "Es cuando el " +
                        "estudiante lleva a la acción una idea creativa movilizando con eficiencia y eficacia los recursos, " +
                        "tareas, y técnicas necesarias para alcanzar objetivos y metas individuales o colectivas en atención " +
                        "de resolver una necesidad no satisfecha o un problema económico o social. Comprende que el " +
                        "estudiante trabaje cooperativamente para crear una alternativa de solución a una necesidad o " +
                        "problema de su entorno, a través de un bien o servicio, valide sus ideas con posibles usuarios y " +
                        "seleccione, en función de la pertinencia y viabilidad, una de ellas ; diseñe la estrategia que le permita " +
                        "implementarla definiendo los recursos y tareas necesarios, aplica habilidades técnicas para producir " +
                        "o prestar el bien o servicio ideado y evalúa los procesos y resultados con el fin de tomar decisiones " +
                        "para mejorar o innovar. Actuando permanentemente con ética, iniciativa, adaptabilidad y " +
                        "perseverancia."));
        area_26.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_26);
        ciclo_vi.getCompetencias().addAll(area_26.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_26.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);

        competencias.clear();

/**
 * Area_27: "Castellano como segunda lengua SEC"
 */

        Area area_27 = areaRepository.findByCode(27);
        competencias.add(new Competencia(69, "Se comunica oralmente en castellano como segunda lengua",
                "Se define como una interacción dinámica entre uno o más interlocutores para expresar y " +
                        "comprender ideas y emociones. Supone un proceso activo de construcción del sentido de los " +
                        "diversos tipos de textos orales ya que el estudiante alterna los roles de hablante y oyente con el " +
                        "fin de lograr su propósito comunicativo. El estudiante pone en juego saberes de distinto tipo y " +
                        "recursos provenientes del lenguaje oral y del mundo que lo rodea. Esto significa considerar los " +
                        "modos de cortesía de acuerdo al contexto sociocultural, así como los recursos no verbales y " +
                        "paraverbales y las diversas estrategias de manera pertinente para expresarse, intercambiar " +
                        "información, persuadir, consensuar, entre otros fines. De igual forma, supone tomar conciencia " +
                        "del impacto de las nuevas tecnologías en la oralidad. " +
                        "La comunicación oral es una herramienta fundamental para la constitución de las identidades y " +
                        "el desarrollo personal. Esta competencia se asume como una práctica social donde el estudiante " +
                        "interactúa con distintos individuos o comunidades socioculturales, ya sea de forma presencial o " +
                        "virtual. Al hacerlo, tiene la posibilidad de usar el lenguaje oral de manera creativa y responsable, " +
                        "considerando la repercusión de lo expresado o escuchado, y estableciendo una posición crítica " +
                        "con los medios de comunicación audiovisuales."));
        competencias.add(new Competencia(70, "Lee diversos tipos de textos escritos en castellano como segunda lengua",
                "Esta competencia se define como una interacción dinámica entre el lector, el texto y los contextos " +
                        "socioculturales que enmarcan la lectura. Supone un proceso activo de construcción del sentido, " +
                        "ya que el estudiante no solo decodifica o comprende la información explícita de los textos que " +
                        "lee sino que es capaz de interpretarlos y establecer una posición sobre ellos. " +
                        "En esta competencia el estudiante pone en juego saberes de distinto tipo y recursos " +
                        "provenientes de su experiencia lectora y del mundo que lo rodea. Ello implica tomar conciencia " +
                        "de la diversidad de propósitos que tiene la lectura, del uso que se hace de esta en distintos " +
                        "ámbitos de la vida, del papel de la experiencia literaria en la formación de lectores y de las " +
                        "relaciones intertextuales que se establecen entre los textos leídos. Esto es crucial en un mundo " +
                        "donde las nuevas tecnologías y la multimodalidad han transformado los modos de leer. " +
                        "Para construir el sentido de los textos que lee, es indispensable asumir la lectura como una " +
                        "práctica social situada en distintos grupos o comunidades de lectores. Al involucrarse con la " +
                        "lectura, el estudiante contribuye con su desarrollo personal, así como el de su propia " +
                        "comunidad, además de conocer e interactuar con contextos socioculturales distintos al suyo."));
        competencias.add(new Competencia(71, "Escribe diversos tipos de textos castellano como segunda lengua",
                "Esta competencia se define como el uso del lenguaje escrito para construir sentidos en el texto y " +
                        "comunicarlos a otros. Se trata de un proceso reflexivo porque supone la adecuación y " +
                        "organización de los textos considerando los contextos y el propósito comunicativo, así como la " +
                        "revisión permanente de lo escrito con la finalidad de mejorarlo. " +
                        "En esta competencia, el estudiante pone en juego saberes de distinto tipo y recursos provenientes " +
                        "de su experiencia con el lenguaje escrito y del mundo que lo rodea. Utiliza el sistema alfabético yconjunto de convenciones de la escritura, así como diferentes estrategias para ampliar ideas, " +
                        "enfatizar o matizar significados en los textos que escribe. Con ello, toma conciencia deposibilidades y limitaciones que ofrece el lenguaje, la comunicación y el sentido. Esto es crucialuna época dominada por nuevas tecnologías que han transformado la naturaleza de la comunicación " +
                        "escrita. " +
                        "Para construir el sentido de los textos que escribe, es indispensable asumir la escritura como una " +
                        "práctica social que permite participar en distintos grupos o comunidades socioculturales. Además " +
                        "de participar en la vida social, esta competencia supone otros propósitos, como la construcciónconocimientos o el uso estético el lenguaje. Al involucrarse con la escritura, se ofrece la posibilidad " +
                        "de interactuar con otras personas empleando el lenguaje escrito de manera creativa y responsable, " +
                        "teniendo en cuenta su repercusión en los demás."));
        area_27.getCompetencias().addAll(competenciaRepository.saveAll(competencias));
        areaRepository.save(area_27);
        ciclo_vi.getCompetencias().addAll(area_27.getCompetencias());
        ciclo_vii.getCompetencias().addAll(area_27.getCompetencias());
        cicloRepository.save(ciclo_vi);
        cicloRepository.save(ciclo_vii);
    }

    private void initMatrixCapacidad() {
        List<Capacidad> capacidades = new ArrayList<>();

        Competencia comp_1 = competenciaRepository.findByCode(1);
        capacidades.add(new Capacidad("Se valora a sí mismo"));
        capacidades.add(new Capacidad("Autoregula sus emociones"));
        comp_1.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_1);

        capacidades.clear();

        Competencia comp_2 = competenciaRepository.findByCode(2);
        capacidades.add(new Capacidad("Interactúa con todas las personas"));
        capacidades.add(new Capacidad("Construye normas, y asume acuerdos y leyes"));
        capacidades.add(new Capacidad("Participa en acciones que promueven el bienestar común"));
        comp_2.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_2);

        capacidades.clear();

        Competencia comp_3 = competenciaRepository.findByCode(3);
        capacidades.add(new Capacidad("Comprende su cuerpo"));
        capacidades.add(new Capacidad("Se expresa corporalmente"));
        comp_3.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_3);

        capacidades.clear();

        Competencia comp_4 = competenciaRepository.findByCode(4);
        capacidades.add(new Capacidad("Obtiene información del texto oral"));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral"));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto de forma coherente y cohesionada"));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica"));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores"));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral"));
        comp_4.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_4);

        capacidades.clear();

        Competencia comp_5 = competenciaRepository.findByCode(5);
        capacidades.add(new Capacidad("Genera y registra datos o información"));
        comp_5.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_5);

        capacidades.clear();

        Competencia comp_6 = competenciaRepository.findByCode(6);
        capacidades.add(new Capacidad("Traduce cantidades a expresiones numéricas"));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos de estimación y cálculo"));
        comp_6.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_6);

        capacidades.clear();

        Competencia comp_7 = competenciaRepository.findByCode(7);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones"));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para orientarse en el espacio"));
        comp_7.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_7);

        capacidades.clear();

        Competencia comp_8 = competenciaRepository.findByCode(8);
        capacidades.add(new Capacidad("Se valora a sí mismo"));
        capacidades.add(new Capacidad("Autoregula sus emociones"));
        comp_8.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_8);

        capacidades.clear();

        Competencia comp_9 = competenciaRepository.findByCode(9);
        capacidades.add(new Capacidad("Interactúa con todas las personas"));
        capacidades.add(new Capacidad("Construye normas, y asume acuerdos y leyes"));
        capacidades.add(new Capacidad("Participa en acciones que promueven el bienestar común"));
        comp_9.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_9);

        capacidades.clear();


        Competencia comp_10 = competenciaRepository.findByCode(10);
        capacidades.add(new Capacidad("Conoce a Dios y asume su identidad religiosa y espiritual como persona digna, libre y trascendente"));
        capacidades.add(new Capacidad("Cultiva y valora las manifestaciones religiosas de su entorno argumentando su fe de manera comprensible y respetuosa"));
        comp_10.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_10);

        capacidades.clear();

        Competencia comp_11 = competenciaRepository.findByCode(11);
        capacidades.add(new Capacidad("Comprende su cuerpo"));
        capacidades.add(new Capacidad("Se expresa corporalmente"));
        comp_11.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_11);

        capacidades.clear();

        Competencia comp_12 = competenciaRepository.findByCode(12);
        capacidades.add(new Capacidad("Obtiene información del texto oral"));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral"));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto de forma coherente y cohesionada"));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica"));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores"));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral"));
        comp_12.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_12);

        capacidades.clear();

        Competencia comp_13 = competenciaRepository.findByCode(13);
        capacidades.add(new Capacidad("Obtiene información del texto escrito"));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto escrito"));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito"));
        comp_13.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_13);

        capacidades.clear();

        Competencia comp_14 = competenciaRepository.findByCode(14);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa"));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada"));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente"));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito"));
        comp_14.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_14);

        capacidades.clear();

        Competencia comp_15 = competenciaRepository.findByCode(15);
        capacidades.add(new Capacidad("Explora y experimenta los lenguajes del arte"));
        capacidades.add(new Capacidad("Aplica procesos creativos"));
        capacidades.add(new Capacidad("Socializa sus procesos y proyectos"));
        comp_15.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_15);

        capacidades.clear();

        Competencia comp_16 = competenciaRepository.findByCode(16);
        capacidades.add(new Capacidad("Obtiene información del texto oral"));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral"));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto de forma coherente y cohesionada"));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverba les de forma estratégica"));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores"));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral"));
        comp_16.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_16);

        capacidades.clear();

        Competencia comp_17 = competenciaRepository.findByCode(17);
        capacidades.add(new Capacidad("Traduce cantidades a expresiones numéricas"));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos de estimación y cálculo"));
        comp_17.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_17);

        capacidades.clear();

        Competencia comp_18 = competenciaRepository.findByCode(18);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones"));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para orientarse en el espacio"));
        comp_18.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_18);

        capacidades.clear();

        Competencia comp_19 = competenciaRepository.findByCode(19);
        capacidades.add(new Capacidad("Problematiza situaciones para hacer indagación"));
        capacidades.add(new Capacidad("Diseña estrategias para hacer indagación"));
        capacidades.add(new Capacidad("Genera y registra datos o información"));
        capacidades.add(new Capacidad("Analiza datos e información"));
        capacidades.add(new Capacidad("Evalúa y comunica el proceso y resultado de su indagación"));
        comp_19.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_19);

        capacidades.clear();

        Competencia comp_20 = competenciaRepository.findByCode(20);
        capacidades.add(new Capacidad("Traduce cantidades a expresiones numéricas",
                "Es transformar las relaciones entre los datos y condiciones de un problema, a una expresión" +
                        "numérica (modelo) que reproduzca las relaciones entre estos; esta expresión se" +
                        "comporta como un sistema compuesto por números, operaciones y sus" +
                        "propiedades. Es plantear problemas a partir de una situación o una expresión" +
                        "numérica dada. También implica evaluar si el resultado obtenido o la expresión" +
                        "numérica formulada (modelo), cumplen las condiciones iniciales del problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones",
                "Es expresar la comprensión de los conceptos numéricos, las operaciones y" +
                        "propiedades, las unidades de medida, las relaciones que establece entre ellos;" +
                        "usando lenguaje numérico y diversas representaciones; así como leer sus" +
                        "representaciones e información con contenido numérico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos de estimación y cálculo",
                "Es seleccionar, adaptar, combinar o crear una variedad de estrategias," +
                        "procedimientos como el cálculo mental y escrito, la estimación, la aproximación" +
                        "y medición, comparar cantidades; y emplear diversos recursos."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre las relaciones numéricas y las operaciones",
                "Es elaborar afirmaciones sobre las posibles relaciones entre" +
                        "números naturales, enteros, racionales, reales, sus operaciones y propiedades;" +
                        "en base a comparaciones y experiencias en las que induce propiedades a partir" +
                        "de casos particulares; así como explicarlas con analogías, justificarlas, validarlas" +
                        "o refutarlas con ejemplos y contraejemplos."));
        comp_20.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_20);

        capacidades.clear();

        Competencia comp_21 = competenciaRepository.findByCode(21);
        capacidades.add(new Capacidad("Traduce datos y condiciones a expresiones algebraicas",
                "Es transformar los datos, valores desconocidos, variables y relaciones de un " +
                        "problema a una expresión gráfica o algebraica (modelo) que generalice la " +
                        "interacción entre estos. Implica también evaluar el resultado o la " +
                        "expresión formulada, con respecto a las condiciones de la situación; y " +
                        "formular preguntas o problemas a partir de una situación o una expresión. "));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las relaciones algebraicas",
                "Es expresar su comprensión de la noción, concepto o propiedades de los " +
                        "patrones, funciones, ecuaciones e inecuaciones estableciendo relaciones " +
                        "entre estas; usando lenguaje algebraico y diversas representaciones. Así " +
                        "como interpretar información que presente contenido algebraico. "));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para encontrar reglas generales",
                "Es seleccionar, adaptar, combinar o crear, procedimientos, " +
                        "estrategias y algunas propiedades para simplificar o transformar " +
                        "ecuaciones, inecuaciones y expresiones simbólicas que le permitan " +
                        "resolver ecuaciones, determinar dominios y rangos, representar rectas, " +
                        "parábolas, y diversas funciones."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones de cambio y equivalencia",
                "Es elaborar afirmaciones sobre variables, reglas " +
                        "algebraicas y propiedades algebraicas, razonando de manera inductiva " +
                        "para generalizar una regla y de manera deductiva probando y " +
                        "comprobando propiedades y nuevas relaciones."));
        comp_21.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_21);

        capacidades.clear();

        Competencia comp_22 = competenciaRepository.findByCode(22);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones",
                "Es construir un modelo que reproduzca las características de los objetos, su " +
                        "localización y movimiento, mediante formas geométricas, sus elementos y " +
                        "propiedades; la ubicación y transformaciones en el plano. Es también " +
                        "evaluar si el modelo cumple con las condiciones dadas en el problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas",
                "Es comunicar su comprensión de las propiedades de las formas " +
                        "geométricas, sus transformaciones y la ubicación en un sistema de " +
                        "referencia; es también establecer relaciones entre estas formas, usando " +
                        "lenguaje geométrico y representaciones gráficas o simbólicas."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para orientarse en el espacio",
                "Es seleccionar, adaptar, combinar o crear, una variedad de estrategias, " +
                        "procedimientos y recursos para construir formas geométricas, trazar rutas, " +
                        "medir o estimar distancias y superficies, y transformar las formas " +
                        "bidimensionales y tridimensionales."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones geométricas",
                "Es elaborar afirmaciones sobre las posibles relaciones entre los elementos y las " +
                        "propiedades de las formas geométricas; en base a su exploración o " +
                        "visualización. Asimismo, justificarlas, validarlas o refutarlas, en base a su " +
                        "experiencia, ejemplos o contraejemplos, y conocimientos sobre " +
                        "propiedades geométricas; usando el razonamiento inductivo o deductivo."));
        comp_22.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_22);

        capacidades.clear();

        Competencia comp_23 = competenciaRepository.findByCode(23);
        capacidades.add(new Capacidad("Representa datos con gráficos y medidas estadísticas o probabilísticas",
                "Es representar el comportamiento de un conjunto " +
                        "de datos, seleccionando tablas o gráficos estadísticos, medidas de " +
                        "tendencia central, de localización o dispersión. Reconocer variables " +
                        "de la población o la muestra al plantear un tema de estudio. Así " +
                        "también implica el análisis de situaciones aleatorias y representar " +
                        "la ocurrencia de sucesos mediante el valor de la probabilidad."));
        capacidades.add(new Capacidad("Comunica la comprensión de los conceptos estadísticos y probabilísticos",
                "Es comunicar su comprensión de conceptos " +
                        "estadísticos y probabilísticos en relación a la situación. Leer, " +
                        "describir e interpretar información estadística contenida en " +
                        "gráficos o tablas provenientes de diferentes fuentes."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para recopilar y procesar datos",
                "Es seleccionar, adaptar, combinar o crear una variedad de " +
                        "procedimientos, estrategias y recursos para recopilar, procesar y " +
                        "analizar datos, así como el uso de técnicas de muestreo y el cálculo " +
                        "de las medidas estadísticas y probabilísticas."));
        capacidades.add(new Capacidad("Sustenta conclusiones o decisiones en base a información obtenida",
                "Es tomar decisiones, hacer predicciones o elaborar " +
                        "conclusiones, y sustentarlas en base a la información obtenida del " +
                        "procesamiento y análisis de datos, y de la revisión o valoración de " +
                        "los procesos."));
        comp_23.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_23);

        capacidades.clear();

        Competencia comp_24 = competenciaRepository.findByCode(24);
        capacidades.add(new Capacidad("Obtiene información del texto oral",
                "El estudiante recupera y extrae información explícita " +
                        "expresada por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral",
                "El estudiante construye el sentido del texto. " +
                        "Para ello, infiere estableciendo diversas relaciones entre la información explícita e implícita " +
                        "con el fin de deducir nueva información y completar los vacíos del texto oral. A partir de " +
                        "estas inferencias, el estudiante interpreta integrando la información explícita e implícita, los " +
                        "recursos verbales, no verbales y paraverbales para construir el sentido global y profundo del " +
                        "texto oral, y explicar el propósito, el uso estético del lenguaje, las intenciones e ideologías de " +
                        "los interlocutores, así como su relación con el contexto sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El estudiante " +
                        "expresa sus ideas adaptándose al propósito, destinatario, características del tipo de texto, " +
                        "género discursivo y registro, considerando las normas y modos de cortesía, así como los " +
                        "contextos socioculturales que enmarcan la comunicación. Asimismo, expresa las ideas en " +
                        "torno a un tema de forma lógica, relacionándolas mediante diversos recursos cohesivos para " +
                        "construir el sentido de distintos tipos de textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante emplea " +
                        "variados recursos no verbales (como gestos o movimientos corporales) o paraverbales (como " +
                        "el tono de la voz o silencios) según la situación comunicativa para enfatizar o matizar " +
                        "significados y producir determinados efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores",
                "El estudiante intercambia los " +
                        "roles de hablante y oyente alternada y dinámicamente, participando de forma pertinente, " +
                        "oportuna y relevante para lograr su propósito comunicativo."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral",
                "Los procesos de " +
                        "reflexión y evaluación están relacionados porque ambos suponen que el estudiante se " +
                        "distancie de los textos orales en los que participa. Para ello, reflexiona como oyente y " +
                        "hablante, que supone distanciarse de los textos orales en que participa de forma presencial " +
                        "o a través de medios audiovisuales, comparando y contrastando aspectos formales y de " +
                        "contenido, con la experiencia, el contexto, el conocimiento formal y diversas fuentes de " +
                        "información. Asimismo, evalúa, que implica analizar y valorar los textos orales producidos " +
                        "para construir una opinión personal o un juicio crítico sobre sus aspectos formales, " +
                        "contenidos e ideologías, y su relación con el contexto sociocultural, considerando los efectos " +
                        "que producen en los interlocutores."));
        comp_24.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_24);

        capacidades.clear();

        Competencia comp_25 = competenciaRepository.findByCode(25);
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "El estudiante localiza y selecciona " +
                        "información explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto",
                "El estudiante construye el sentido " +
                        "del texto. Para ello, infiere estableciendo diversas relaciones entre la " +
                        "información explícita e implícita con el fin de deducir nueva información y " +
                        "completar los vacíos del texto. A partir de estas inferencias, el estudiante " +
                        "interpreta integrando la información explícita e implícita, así como los recursos " +
                        "textuales, para construir el sentido global y profundo del texto, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones del autor, las ideologías " +
                        "de los textos así como su relación con el contexto sociocultural del lector y del " +
                        "texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares " +
                        "distintos, y que son presentados en diferentes soportes y formatos. Reflexionar " +
                        "implica comparar y contrastar aspectos formales y de contenido del texto con la " +
                        "experiencia, el conocimiento formal del lector y diversas fuentes de " +
                        "información. Evaluar implica analizar y valorar los textos escritos para construir " +
                        "una opinión personal o un juicio crítico sobre aspectos formales, estéticos, " +
                        "contenidos e ideologías de los textos considerando los efectos que producen, la " +
                        "relación con otros textos, y el contexto sociocultural del texto y del lector."));
        comp_25.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_25);

        capacidades.clear();

        Competencia comp_26 = competenciaRepository.findByCode(26);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "El estudiante considera el " +
                        "propósito, destinatario, tipo de texto, género discursivo y registro que utilizará al " +
                        "escribir los textos, así como los contextos socioculturales que enmarcan la " +
                        "comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El " +
                        "estudiante ordena lógicamente las ideas en torno a un tema, ampliándolas y " +
                        "complementándolas, estableciendo relaciones de cohesión entre ellas y utilizando " +
                        "un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente",
                "El estudiante usa de forma apropiada recursos textuales para garantizar la claridad, " +
                        "el uso estético del lenguaje y el sentido del texto escrito. "));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito",
                "El estudiante se distancia del texto que ha escrito para revisar de manera " +
                        "permanente el contenido, la coherencia, cohesión y adecuación a la situación " +
                        "comunicativa con la finalidad de mejorarlo. También implica analizar, comparar y " +
                        "contrastar las características de los usos del lenguaje escrito y sus posibilidades, así " +
                        "como su repercusión en otras personas o su relación con otros textos según el " +
                        "contexto sociocultural."));
        comp_26.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_26);

        capacidades.clear();

        Competencia comp_27 = competenciaRepository.findByCode(27);
        capacidades.add(new Capacidad("Obtiene información del texto oral en inglés",
                "El estudiante recupera y extrae información explícita " +
                        "expresada por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral en inglés",
                "El estudiante construye el sentido del texto. " +
                        "Para ello, infiere estableciendo diversas relaciones entre la información explícita e implícita con el " +
                        "fin de deducir nueva información y completar los vacíos del texto oral. A partir de estas inferencias, " +
                        "el estudiante interpreta integrando la información explícita e implícita, los recursos verbales, no " +
                        "verbales y paraverbales para construir el sentido global y profundo del texto oral, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones e ideologías de los interlocutores, así como " +
                        "su relación con el contexto sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto en inglés de forma coherente y cohesionada",
                "El estudiante " +
                        "expresa sus ideas adaptándose al propósito, destinatario, características del tipo de texto, género " +
                        "discursivo y registro, considerando las normas y modos de cortesía, así como los contextos " +
                        "socioculturales que enmarcan la comunicación. Asimismo, expresa las ideas en torno a un tema de " +
                        "forma lógica, relacionándolas mediante diversos recursos cohesivos para construir el sentido de " +
                        "distintos tipos de textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante emplea variados " +
                        "recursos no verbales (como gestos o movimientos corporales) o paraverbales (como el tono de la " +
                        "voz o silencios) según la situación comunicativa para enfatizar o matizar significados y producir " +
                        "determinados efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente en inglés con distintos interlocutores",
                "El estudiante intercambia los " +
                        "roles de hablante y oyente alternada y dinámicamente, participando de forma pertinente, oportuna " +
                        "y relevante para lograr su propósito comunicativo."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral en inglés",
                "Los procesos de " +
                        "reflexión y evaluación están relacionados porque ambos suponen que el estudiante se distancie de " +
                        "los textos orales en los que participa. Para ello, reflexiona como oyente y hablante, que supone " +
                        "distanciarse de los textos orales en que participa de forma presencial o a través de medios " +
                        "audiovisuales, comparando y contrastando aspectos formales y de contenido, con la experiencia, el " +
                        "contexto, el conocimiento formal y diversas fuentes de información. Asimismo, evalúa, que implica " +
                        "analizar y valorar los textos orales producidos para construir una opinión personal o un juicio crítico " +
                        "sobre sus aspectos formales, contenidos e ideologías, y su relación con el contexto sociocultural, " +
                        "considerando los efectos que producen en los interlocutores."));
        comp_27.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_27);

        capacidades.clear();

        Competencia comp_28 = competenciaRepository.findByCode(28);
        capacidades.add(new Capacidad("Obtiene información del texto escrito en inglés",
                "El estudiante localiza y selecciona " +
                        "información explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto escrito en inglés",
                "El estudiante construye el " +
                        "sentido del texto. Para ello, infiere estableciendo diversas relaciones entre la información " +
                        "explícita e implícita con el fin de deducir nueva información y completar los vacíos del texto. " +
                        "A partir de estas inferencias, el estudiante interpreta integrando la información explícita e " +
                        "implícita, así como los recursos textuales, para construir el sentido global y profundo del " +
                        "texto, y explicar el propósito, las intenciones del autor, así como su relación con el contexto " +
                        "sociocultural del lector y del texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito en inglés",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares distintos, y que " +
                        "son presentados en diferentes soportes y formatos. Reflexionar implica comparar y " +
                        "contrastar aspectos formales y de contenido del texto con la experiencia, el conocimiento " +
                        "formal del lector y diversas fuentes de información. Evaluar implica analizar y valorar los " +
                        "textos escritos para construir una opinión personal o un juicio crítico sobre aspectos " +
                        "formales, contenidos de los textos considerando los efectos que producen, la relación con " +
                        "otros textos, y el contexto sociocultural del texto y del lector."));
        comp_28.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_28);

        capacidades.clear();

        Competencia comp_29 = competenciaRepository.findByCode(29);
        capacidades.add(new Capacidad("Adecúa el texto en inglés a la situación comunicativa",
                "El estudiante considera el propósito, " +
                        "destinatario, tipo de texto, género discursivo y registro que utilizará al escribir los textos, así " +
                        "como los contextos socioculturales que enmarcan la comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas en inglés de forma coherente y cohesionada",
                "El estudiante " +
                        "ordena lógicamente las ideas en torno a un tema, ampliándolas y complementándolas, " +
                        "estableciendo relaciones de cohesión entre ellas y utilizando un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito en inglés de forma pertinente",
                "El estudiante usa " +
                        "de forma apropiada recursos textuales para garantizar la claridad, el uso estético del lenguaje " +
                        "y el sentido del texto escrito."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito en inglés",
                "El estudiante se distancia del texto que ha escrito para revisar de manera permanente el " +
                        "contenido, la coherencia, cohesión y adecuación a la situación comunicativa con la finalidad " +
                        "de mejorarlo. También implica analizar, comparar y contrastar las características de los usos " +
                        "del lenguaje escrito y sus posibilidades, así como su repercusión en otras personas o su " +
                        "relación con otros textos según el contexto sociocultural."));
        comp_29.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_29);


        capacidades.clear();

        Competencia comp_30 = competenciaRepository.findByCode(30);
        capacidades.add(new Capacidad("Se valora a sí mismo",
                "Es decir, el estudiante reconoce sus características, cualidades, " +
                        "limitaciones y potencialidades que lo hacen ser quien es, que le permiten aceptarse, " +
                        "sentirse bien consigo mismo y ser capaz de asumir retos y alcanzar sus metas. Además, se " +
                        "reconoce como integrante de una colectividad sociocultural específica y tiene sentido de " +
                        "pertenencia a su familia, escuela, comunidad, país y mundo."));
        capacidades.add(new Capacidad("Autorregula sus emociones",
                "Es que el estudiante reconoce y toma conciencia de sus " +
                        "emociones, a fin de poder expresarlas de manera adecuada según el contexto, los patrones " +
                        "culturales diversos y las consecuencias que estas tienen para sí mismo y para los demás. " +
                        "Ello le permite regular su comportamiento, en favor de su bienestar y el de los demás."));
        capacidades.add(new Capacidad("Reflexiona y argumenta éticamente",
                "Es que el estudiante analice situaciones cotidianas para " +
                        "identificar los valores que están presentes en ellas y asumir una posición, sustentada en " +
                        "argumentos razonados y en principios éticos. Implica también tomar conciencia de las " +
                        "propias decisiones y acciones, a partir de reflexionar sobre si estas responden a los " +
                        "principios éticos asumidos, y cómo los resultados y consecuencias influyen en sí mismos y " +
                        "en los demás."));
        capacidades.add(new Capacidad("Vive su sexualidad de manera plena y responsable",
                "Es tomar conciencia de sí mismo como " +
                        "hombre o mujer, a partir del desarrollo de su imagen corporal, de su identidad sexual y de " +
                        "género, y mediante la exploración y valoración de su cuerpo. Supone establecer relaciones " +
                        "de igualdad entre mujeres y hombres, así como relaciones afectivas armoniosas y libres de " +
                        "violencia. También implica identificar y poner en práctica conductas de autocuidado frente " +
                        "a situaciones que ponen en riesgo su bienestar o que vulneran sus derechos sexuales y " +
                        "reproductivos."));
        comp_30.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_30);

        capacidades.clear();

        Competencia comp_31 = competenciaRepository.findByCode(31);
        capacidades.add(new Capacidad("Interactúa con todas las personas",
                "Es decir, reconoce a todos como personas valiosas y con " +
                        "derechos, muestra preocupación por el otro, respeta las diferencias y se enriquecerse de " +
                        "ellas. Actúa frente a las distintas formas de discriminación (por género, fenotipo, origen " +
                        "étnico, lengua, discapacidad, orientación sexual, edad, nivel socioeconómico, entre otras), " +
                        "y reflexiona sobre las diversas situaciones que vulneran la convivencia democrática."));
        capacidades.add(new Capacidad("Construye normas y asume acuerdos y leyes",
                "Es que el estudiante participe en la " +
                        "construcción de normas, las respete y evalúe en relación a los principios que las sustentan, " +
                        "así como, cumple con los acuerdos y las leyes, reconociendo la importancia de estas para " +
                        "la convivencia. Para lo cual, maneja información y conceptos relacionados con la " +
                        "convivencia (como la equidad, el respeto y la libertad) y hace suyo los principios " +
                        "democráticos (la autofundación, la secularidad, la incertidumbre, la ética, la complejidad y " +
                        "lo público)."));
        capacidades.add(new Capacidad("Maneja conflictos de manera constructiva",
                "Es que actúe con empatía y asertividad frente a " +
                        "ellos, y ponga en práctica pautas y estrategias para resolverlos de manera pacífica y " +
                        "creativa, contribuyendo a construir comunidades democráticas. Para lo cual parte de " +
                        "comprender el conflicto como inherente a las relaciones humanas, así como desarrollar " +
                        "criterios para evaluar situaciones en las que estos ocurren."));
        capacidades.add(new Capacidad("Delibera sobre asuntos públicos",
                "Es que participe en un proceso de reflexión y diálogo sobre " +
                        "asuntos que involucran a todos, donde se plantean diversos puntos de vista y se busca " +
                        "llegar a consensos orientados al bien común. Supone construir una posición propia sobre " +
                        "dichos asuntos basándose en argumentos razonados, la institucionalidad, el Estado de " +
                        "derecho y los principios democráticos, así como valorar y contraponer las diversas " +
                        "posiciones."));
        capacidades.add(new Capacidad("Participa en acciones que promueven el bienestar común",
                "Es que proponga y gestione " +
                        "iniciativas vinculadas con el interés común y con la promoción y defensa de los derechos " +
                        "humanos, tanto en la escuela como en la comunidad. Para ello, se apropia y utiliza canales " +
                        "y mecanismos de participación democrática."));
        comp_31.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_31);

        capacidades.clear();

        Competencia comp_32 = competenciaRepository.findByCode(32);
        capacidades.add(new Capacidad("Interpreta críticamente fuentes diversas",
                "Implica reconocer la diversidad de fuentes y su " +
                        "diferente utilidad para abordar un hecho o proceso histórico. Supone ubicarlas en su " +
                        "contexto y comprender, de manera crítica, que estas reflejan una perspectiva particular y " +
                        "tienen diferentes grados de fiabilidad. También implica recurrir a múltiples fuentes."));
        capacidades.add(new Capacidad("Comprende el tiempo histórico",
                "Supone usar las nociones relativas al tiempo de manera " +
                        "pertinente, reconociendo que los sistemas de medición temporal son convenciones que " +
                        "dependen de distintas tradiciones culturales y que el tiempo histórico tiene diferentes " +
                        "duraciones. Asimismo, implica ordenar los hechos y procesos históricos cronológicamente " +
                        "y explicar los cambios y permanencias que se dan en ellos."));
        capacidades.add(new Capacidad("Elabora explicaciones sobre procesos históricos",
                "Implica jerarquizar las causas de los " +
                        "procesos históricos relacionando las motivaciones de sus protagonistas con su " +
                        "cosmovisión y la época en la que vivieron. También es establecer las múltiples " +
                        "consecuencias de los procesos del pasado y sus implicancias en el presente, así como " +
                        "reconocer que este va construyendo nuestro futuro."));
        comp_32.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_32);

        capacidades.clear();

        Competencia comp_33 = competenciaRepository.findByCode(33);
        capacidades.add(new Capacidad("Comprende las relaciones entre los elementos naturales y sociales",
                "Es explicar las dinámicas " +
                        "y transformaciones del espacio geográfico, a partir del reconocimiento de sus elementos " +
                        "naturales y sociales que los componen, así como de las interacciones que se dan entre " +
                        "ambos a escala local, nacional o global."));
        capacidades.add(new Capacidad("Maneja fuentes de información para comprender el espacio geográfico",
                "Es usar distintas " +
                        "fuentes: cartográficas, fotográficas e imágenes diversas, cuadros y gráficos estadísticos, " +
                        "entre otros, para analizar el espacio geográfico, orientarse y desplazarse en él."));
        capacidades.add(new Capacidad("Genera acciones para preservar el ambiente",
                "Es proponer y poner en práctica acciones " +
                        "orientadas al cuidado del ambiente y a contribuir a la prevención de situaciones de riesgo " +
                        "de desastre. Esto supone analizar el impacto de las problemáticas ambientales y " +
                        "territoriales en la vida de las personas."));
        comp_33.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_33);

        capacidades.clear();

        Competencia comp_34 = competenciaRepository.findByCode(34);
        capacidades.add(new Capacidad("Comprende el funcionamiento del sistema económico y financiero",
                "Supone identificar los " +
                        "roles de los diversos agentes que intervienen en el sistema, analizar las interacciones entre " +
                        "ellos y comprender el rol del Estado en dichas interrelaciones."));
        capacidades.add(new Capacidad("Toma decisiones económicas y financieras",
                "Supone planificar el uso de sus recursos " +
                        "económicos de manera sostenible, en función a sus necesidades y posibilidades. También " +
                        "implica asumir una posición crítica frente a los sistemas de producción y de consumo, así " +
                        "como ejercer sus derechos y responsabilidades como consumidor informado."));
        comp_34.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_34);

        capacidades.clear();

        Competencia comp_35 = competenciaRepository.findByCode(35);
        capacidades.add(new Capacidad("Percibe manifestaciones artístico-culturales",
                "Consiste en usar los sentidos para " +
                        "observar, escuchar, describir y analizar las cualidades visuales, táctiles, sonoras y " +
                        "kinestésicas de diversas manifestaciones artístico-culturales."));
        capacidades.add(new Capacidad("Contextualiza las manifestaciones culturales",
                "Es informarse acerca de la cultura en que " +
                        "se origina una manifestación artística para entender cómo el contexto social, cultural e " +
                        "histórico de esta influye en su creación y la manera en que transmite sus significados."));
        capacidades.add(new Capacidad("Reflexiona creativa y críticamente",
                "Supone interpretar las intenciones y significados de " +
                        "manifestaciones artístico-culturales que hayan visto o experimentado y emitir juicios de " +
                        "valor, entrelazando información obtenida a través de la percepción, el análisis y la " +
                        "comprensión de los contextos."));
        comp_35.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_35);

        capacidades.clear();

        Competencia comp_36 = competenciaRepository.findByCode(36);
        capacidades.add(new Capacidad("Explora y experimenta los lenguajes del arte",
                "Significa experimentar, improvisar y desarrollar " +
                        "habilidades en el uso de los medios, materiales, herramientas y técnicas de los diversos " +
                        "lenguajes del arte."));
        capacidades.add(new Capacidad("Aplica procesos creativos",
                "Supone generar ideas, investigar, tomar decisiones y poner en " +
                        "práctica sus conocimientos para elaborar un proyecto artístico individual o colaborativo en " +
                        "relación a una intención específica."));
        capacidades.add(new Capacidad("Evalúa y socializa sus procesos y proyectos",
                "Significa registrar sus experiencias, comunicar " +
                        "sus descubrimientos y compartir sus creaciones con otros, para profundizar en ellos y " +
                        "reflexionar sobre sus ideas y experiencias."));
        comp_36.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_36);

        capacidades.clear();

        Competencia comp_37 = competenciaRepository.findByCode(37);
        capacidades.add(new Capacidad("Problematiza situaciones para hacer indagación",
                "Es plantear preguntas " +
                        "sobre hechos y fenómenos naturales, interpretar situaciones y formular " +
                        "hipótesis."));
        capacidades.add(new Capacidad("Diseña estrategias para hacer indagación",
                "Es proponer actividades que " +
                        "permitan construir un procedimiento, seleccionar materiales, instrumentos e " +
                        "información para comprobar o refutar la hipótesis."));
        capacidades.add(new Capacidad("Genera y registra datos o información",
                "Es obtener, organizar y registrar " +
                        "datos fiables en función de las variables, utilizando instrumentos y diversas " +
                        "técnicas, que permitan comprobar o refutar la hipótesis."));
        capacidades.add(new Capacidad("Analiza datos e información",
                "Es interpretar los datos obtenidos en la " +
                        "indagación, contrastarlos con las hipótesis e información relacionada al " +
                        "problema para elaborar conclusiones, que comprueban o refutan la hipótesis."));
        capacidades.add(new Capacidad("Evalúa y comunica el proceso y resultados de su indagación",
                "Es identificar " +
                        "y dar a conocer las dificultades técnicas y los conocimientos logrados para " +
                        "cuestionar el grado de satisfacción que la respuesta da a la pregunta de " +
                        "indagación."));
        comp_37.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_37);

        capacidades.clear();

        Competencia comp_38 = competenciaRepository.findByCode(38);
        capacidades.add(new Capacidad("Comprende y usa conocimientos sobre los seres vivos, materia y energía, biodiversidad, Tierra y universo",
                "Cuando es capaz de tener desempeños " +
                        "flexibles, es decir, establece relaciones entre varios conceptos y los transfiere " +
                        "a nuevas situaciones. Esto le permite construir representaciones del mundo " +
                        "natural y artificial, que se evidencian cuando el estudiante explica, ejemplifica, " +
                        "aplica, justifica, compara, contextualiza y generaliza sus conocimientos."));
        capacidades.add(new Capacidad("Evalúa las implicancias del saber y del quehacer científico y tecnológico",
                "Cuando identifica los cambios generados en la sociedad por el conocimiento " +
                        "científico o desarrollo tecnológico, con el fin de asumir una postura crítica o " +
                        "tomar decisiones, considerando saberes locales, evidencia empírica y " +
                        "científica, con la finalidad de mejorar su calidad de vida y conservar el " +
                        "ambiente."));
        comp_38.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_38);

        capacidades.clear();

        Competencia comp_39 = competenciaRepository.findByCode(39);
        capacidades.add(new Capacidad("Determina una alternativa de solución tecnológica",
                "al detectar un problema " +
                        "y propone alternativas de solución creativas basadas en conocimientos " +
                        "científico, tecnológico y prácticas locales, evaluando su pertinencia para " +
                        "seleccionar una de ellas."));
        capacidades.add(new Capacidad("Diseña la alternativa de solución tecnológica",
                "Es representar de manera " +
                        "gráfica o esquemática la estructura y funcionamiento de la solución " +
                        "tecnológica (especificaciones de diseño), usando conocimiento científico, " +
                        "tecnológico y prácticas locales, teniendo en cuenta los requerimientos del " +
                        "problema y los recursos disponibles."));
        capacidades.add(new Capacidad("Implementa la alternativa de solución tecnológica",
                "Es llevar a cabo la " +
                        "alternativa de solución, verificando y poniendo a prueba el cumplimiento de " +
                        "las especificaciones de diseño y el funcionamiento de sus partes o etapas."));
        capacidades.add(new Capacidad("Evalúa y comunica el funcionamiento de su alternativa de solución tecnológica",
                "Es determinar qué tan bien la solución tecnológica logró " +
                        "responder a los requerimientos del problema, comunicar su funcionamiento y " +
                        "analizar sus posibles impactos, en el ambiente y la sociedad, tanto en su " +
                        "proceso de elaboración como de uso."));
        comp_39.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_39);

        capacidades.clear();

        Competencia comp_40 = competenciaRepository.findByCode(40);
        capacidades.add(new Capacidad("Comprende su cuerpo",
                "Es decir interioriza su cuerpo en estado estático o en " +
                        "movimiento en relación al espacio, el tiempo, los objetos y demás personas de su " +
                        "entorno, representando mentalmente su cuerpo y desarrollando su identidad."));
        capacidades.add(new Capacidad("Se expresa corporalmente",
                "Usa el lenguaje corporal para comunicar emociones, " +
                        "sentimientos y pensamientos. Implica utilizar el tono, los gestos, mímicas, posturas y " +
                        "movimientos para expresarse, desarrollando la creatividad al usar todos los recursos " +
                        "que ofrece el cuerpo y el movimiento."));
        comp_40.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_40);

        capacidades.clear();

        Competencia comp_41 = competenciaRepository.findByCode(41);
        capacidades.add(new Capacidad("Comprende las relaciones entre la actividad física, alimentación, postura e higiene corporal y la salud",
                "Es analizar y comprender los procesos vinculados con la " +
                        "alimentación, la postura, la higiene corporal y la práctica de actividad física y cómo estos " +
                        "influyen en las diferentes actividades físicas o de la vida cotidiana, para el logro de un " +
                        "estado de bienestar integral (físico, psicológico y emocional), según sus recursos y " +
                        "entorno."));
        capacidades.add(new Capacidad("Incorpora prácticas que mejoran su calidad de vida",
                "Es asumir una actitud crítica sobre " +
                        "la importancia de hábitos saludables y sus beneficios vinculados con la mejora de la " +
                        "calidad de vida. Esto supone la planificación de rutinas, dietas o planes que pongan en " +
                        "práctica sus conocimientos sobre alimentación, higiene corporal, posturas y actividad " +
                        "física para la salud según sus propias necesidades, recursos y entorno."));
        comp_41.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_41);

        capacidades.clear();

        Competencia comp_42 = competenciaRepository.findByCode(42);
        capacidades.add(new Capacidad("Se relaciona utilizando sus habilidades sociomotrices",
                "supone interactuar de manera " +
                        "asertiva con los demás en la práctica lúdica y deportiva experimentando el placer y " +
                        "disfrute que ella representa. Por otro lado desarrolla habilidades como el respeto a las " +
                        "normas de juego, liderazgo, tolerancia, actitud proactiva, la resolución de conflictos " +
                        "interpersonales, la pertenencia positiva a un grupo, entre otras."));
        capacidades.add(new Capacidad("Crea y aplica estrategias y tácticas de juego",
                "supone emplear los recursos personales y " +
                        "las potencialidades de cada miembro del equipo para el logro de un objetivo común, " +
                        "desarrollando y aplicando reglas y soluciones tácticas de juego en actividades físicas de " +
                        "colaboración, cooperación y oposición."));
        comp_42.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_42);

//        capacidades.clear();

        Competencia comp_43 = competenciaRepository.findByCode(43);
//        capacidades.add(new Capacidad(comp_43, "",
//                ""));
//        capacidades.add(new Capacidad(comp_43, "",
//                ""));
//
//        capacidades.clear();

        Competencia comp_44 = competenciaRepository.findByCode(44);
//        capacidades.add(new Capacidad(comp_44, "",
//                ""));
//        capacidades.add(new Capacidad(comp_44, "",
//                ""));

        capacidades.clear();

        Competencia comp_45 = competenciaRepository.findByCode(45);
        capacidades.add(new Capacidad("Obtiene información del texto oral",
                "El estudiante recupera y extrae información explícita expresada " +
                        "por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral",
                "El estudiante construye el sentido del texto. Para ello, " +
                        "infiere estableciendo diversas relaciones entre la información explícita e implícita con el fin de deducir " +
                        "nueva información y completar los vacíos del texto oral. A partir de estas inferencias, el estudiante " +
                        "interpreta integrando la información explícita e implícita, los recursos verbales, no verbales y paraverbales " +
                        "para construir el sentido global y profundo del texto oral, y explicar el propósito, el uso estético del " +
                        "lenguaje, las intenciones e ideologías de los interlocutores, así como su relación con el contexto " +
                        "sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto de forma coherente y cohesionada",
                "El estudiante expresa sus " +
                        "ideas adaptándose al propósito, destinatario, características del tipo de texto, género discursivo y registro, " +
                        "considerando las normas y modos de cortesía, así como los contextos socioculturales que enmarcan la " +
                        "comunicación. Asimismo, expresa las ideas en torno a un tema de forma lógica, relacionándolas mediante " +
                        "diversos recursos cohesivos para construir el sentido de distintos tipos de textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante emplea variados " +
                        "recursos no verbales (como gestos o movimientos corporales) o paraverbales (como el tono de la voz o " +
                        "silencios) según la situación comunicativa para enfatizar o matizar significados y producir determinados " +
                        "efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores",
                "El estudiante intercambia los roles de " +
                        "hablante y oyente alternada y dinámicamente, participando de forma pertinente, oportuna y relevante " +
                        "para lograr su propósito comunicativo."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral",
                "Los procesos de reflexión y " +
                        "evaluación están relacionados porque ambos suponen que el estudiante se distancie de los textos orales " +
                        "en los que participa. Para ello, reflexiona como oyente y hablante, que supone distanciarse de los textos " +
                        "orales en que participa de forma presencial o a través de medios audiovisuales, comparando y " +
                        "contrastando aspectos formales y de contenido, con la experiencia, el contexto, el conocimiento formal y " +
                        "diversas fuentes de información. Asimismo, evalúa, que implica analizar y valorar los textos orales " +
                        "producidos para construir una opinión personal o un juicio crítico sobre sus aspectos formales, contenidos " +
                        "e ideologías, y su relación con el contexto sociocultural, considerando los efectos que producen en los " +
                        "interlocutores."));
        comp_45.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_45);

        capacidades.clear();

        Competencia comp_46 = competenciaRepository.findByCode(46);
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "El estudiante localiza y selecciona " +
                        "información explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto",
                "El estudiante construye el sentido " +
                        "del texto. Para ello, infiere estableciendo diversas relaciones entre la " +
                        "información explícita e implícita con el fin de deducir nueva información y " +
                        "completar los vacíos del texto. A partir de estas inferencias, el estudiante " +
                        "interpreta integrando la información explícita e implícita, así como los recursos " +
                        "textuales, para construir el sentido global y profundo del texto, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones del autor, las ideologías " +
                        "de los textos así como su relación con el contexto sociocultural del lector y del " +
                        "texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares " +
                        "distintos, y que son presentados en diferentes soportes y formatos. Reflexionar " +
                        "implica comparar y contrastar aspectos formales y de contenido del texto con la " +
                        "experiencia, el conocimiento formal del lector y diversas fuentes de " +
                        "información. Evaluar implica analizar y valorar los textos escritos para construir " +
                        "una opinión personal o un juicio crítico sobre aspectos formales, estéticos, " +
                        "contenidos e ideologías de los textos considerando los efectos que producen, la " +
                        "relación con otros textos, y el contexto sociocultural del texto y del lector."));
        comp_46.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_46);

        capacidades.clear();

        Competencia comp_47 = competenciaRepository.findByCode(47);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "El estudiante considera el " +
                        "propósito, destinatario, tipo de texto, género discursivo y registro que utilizará al " +
                        "escribir los textos, así como los contextos socioculturales que enmarcan la " +
                        "comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El estudiante ordena lógicamente las ideas en torno a un tema, ampliándolas y " +
                        "complementándolas, estableciendo relaciones de cohesión entre ellas y utilizando " +
                        "un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente",
                "El estudiante usa de forma apropiada recursos textuales para garantizar la claridad, " +
                        "el uso estético del lenguaje y el sentido del texto escrito."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito",
                "El estudiante se distancia del texto que ha escrito para revisar de manera " +
                        "permanente el contenido, la coherencia, cohesión y adecuación a la situación " +
                        "comunicativa con la finalidad de mejorarlo. También implica analizar, comparar y " +
                        "contrastar las características de los usos del lenguaje escrito y sus posibilidades, así " +
                        "como su repercusión en otras personas o su relación con otros textos según el " +
                        "contexto sociocultural."));
        comp_47.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_47);

        capacidades.clear();

        Competencia comp_48 = competenciaRepository.findByCode(48);
        capacidades.add(new Capacidad("Traduce cantidades a expresiones numéricas",
                "Es transformar las relaciones " +
                        "entre los datos y condiciones de un problema, a una expresión numérica (modelo) " +
                        "que reproduzca las relaciones entre estos; esta expresión se comporta como un " +
                        "sistema compuesto por números, operaciones y sus propiedades. Es plantear " +
                        "problemas a partir de una situación o una expresión numérica dada. También " +
                        "implica evaluar si el resultado obtenido o la expresión numérica formulada " +
                        "(modelo), cumplen las condiciones iniciales del problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones",
                "Es " +
                        "expresar la comprensión de los conceptos numéricos, las operaciones y " +
                        "propiedades, las unidades de medida, las relaciones que establece entre ellos; " +
                        "usando lenguaje numérico y diversas representaciones; así como leer sus " +
                        "representaciones e información con contenido numérico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos de estimación y cálculo",
                "Es seleccionar, " +
                        "adaptar, combinar o crear una variedad de estrategias, procedimientos como el " +
                        "cálculo mental y escrito, la estimación, la aproximación y medición, comparar " +
                        "cantidades; y emplear diversos recursos."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre las relaciones numéricas y las operaciones",
                "Es elaborar afirmaciones sobre las posibles relaciones entre números naturales, " +
                        "enteros, racionales, reales, sus operaciones y propiedades; en " +
                        " base a " +
                        "comparaciones y experiencias en las que induce propiedades a partir de casos " +
                        "particulares; así como explicarlas con analogías, justificarlas, validarlas o refutarlas " +
                        "con ejemplos y contraejemplos."));
        comp_48.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_48);

        capacidades.clear();

        Competencia comp_49 = competenciaRepository.findByCode(49);
        capacidades.add(new Capacidad("Traduce datos y condiciones a expresiones algebraicas",
                "Es transformar los datos, valores desconocidos, variables y relaciones de un " +
                        "problema a una expresión gráfica o algebraica (modelo) que generalice la " +
                        "interacción entre estos. Implica también evaluar el resultado o la expresión " +
                        "formulada, con respecto a las condiciones de la situación; y formular " +
                        "preguntas o problemas a partir de una situación o una expresión. "));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las relaciones algebraicas",
                "Es expresar su comprensión de la noción, concepto o propiedades de los " +
                        "patrones, funciones, ecuaciones e inecuaciones estableciendo relaciones " +
                        "entre estas; usando lenguaje algebraico y diversas representaciones. Así " +
                        "como interpretar información que presente contenido algebraico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para encontrar reglas generales",
                "Es seleccionar, adaptar, combinar o crear, procedimientos, estrategias y " +
                        "algunas propiedades para simplificar o transformar ecuaciones, inecuaciones " +
                        "y expresiones simbólicas que le permitan resolver ecuaciones, determinar " +
                        "dominios y rangos, representar rectas, parábolas, y diversas funciones."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones de cambio y equivalencia",
                "Es elaborar afirmaciones sobre variables, reglas algebraicas y propiedades " +
                        "algebraicas, razonando de manera inductiva para generalizar una regla y de " +
                        "manera deductiva probando y comprobando propiedades y nuevas " +
                        "relaciones."));
        comp_49.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_49);

        capacidades.clear();

        Competencia comp_50 = competenciaRepository.findByCode(50);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones",
                "Es construir un modelo que reproduzca las características de los objetos, su " +
                        "localización y movimiento, mediante formas geométricas, sus elementos y " +
                        "propiedades; la ubicación y transformaciones en el plano. Es también evaluar " +
                        "si el modelo cumple con las condiciones dadas en el problema. "));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas",
                "Es comunicar su comprensión de las propiedades de las formas geométricas, sus " +
                        "transformaciones y la ubicación en un sistema de referencia; es también " +
                        "establecer relaciones entre estas formas, usando lenguaje geométrico y " +
                        "representaciones gráficas o simbólicas."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para orientarse en el espacio",
                "Es seleccionar, adaptar, combinar o crear, una variedad de " +
                        " estrategias, procedimientos y recursos para construir formas geométricas, " +
                        " trazar rutas, medir o estimar distancias y superficies, y transformar " +
                        " las formas bidimensionales y tridimensionales."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones geométricas",
                "Es elaborar afirmaciones sobre las posibles relaciones entre los elementos y las " +
                        "propiedades de las formas geométricas; en base a su exploración o " +
                        "visualización. Asimismo, justificarlas, validarlas o refutarlas, en base a su " +
                        "experiencia, ejemplos o contraejemplos, y conocimientos sobre propiedades " +
                        "geométricas; usando el razonamiento inductivo o deductivo."));
        comp_50.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_50);

        capacidades.clear();

        Competencia comp_51 = competenciaRepository.findByCode(51);
        capacidades.add(new Capacidad("Representa datos con gráficos y medidas estadísticas o probabilísticas",
                "Es representar el comportamiento de un conjunto de " +
                        "datos, seleccionando tablas o gráficos estadísticos, medidas de " +
                        "tendencia central, de localización o dispersión. Reconocer variables de " +
                        "la población o la muestra al plantear un tema de estudio. Así también " +
                        "implica el análisis de situaciones aleatorias y representar la ocurrencia " +
                        "de sucesos mediante el valor de la probabilidad."));
        capacidades.add(new Capacidad("Comunica la comprensión de los conceptos estadísticos y probabilísticos",
                "Es comunicar su comprensión de conceptos " +
                        "estadísticos y probabilísticos en relación a la situación. Leer, describir " +
                        "e interpretar información estadística contenida en gráficos o tablas " +
                        "provenientes de diferentes fuentes."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para recopilar y procesar datos",
                "Es seleccionar, adaptar, combinar o crear una variedad de " +
                        "procedimientos, estrategias y recursos para recopilar, procesar y " +
                        "analizar datos, así como el uso de técnicas de muestreo y el cálculo de " +
                        "las medidas estadísticas y probabilísticas."));
        capacidades.add(new Capacidad("Sustenta conclusiones o decisiones en base a información obtenida",
                "Es tomar decisiones, hacer predicciones o elaborar conclusiones, y " +
                        "sustentarlas en base a la información obtenida del procesamiento y " +
                        "análisis de datos, y de la revisión o valoración de los procesos."));
        comp_51.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_51);

        capacidades.clear();

        Competencia comp_52 = competenciaRepository.findByCode(52);
        capacidades.add(new Capacidad("Obtiene información del texto oral",
                "El estudiante recupera y extrae información explícita " +
                        "expresada por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral",
                "El estudiante construye el sentido del texto. " +
                        "Para ello, infiere estableciendo diversas relaciones entre la información explícita e implícita " +
                        "con el fin de deducir nueva información y completar los vacíos del texto oral. A partir de " +
                        "estas inferencias, el estudiante interpreta integrando la información explícita e implícita, los " +
                        "recursos verbales, no verbales y paraverbales para construir el sentido global y profundo del " +
                        "texto oral, y explicar el propósito, el uso estético del lenguaje, las intenciones e ideologías de " +
                        "los interlocutores, así como su relación con el contexto sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El estudiante " +
                        "expresa sus ideas adaptándose al propósito, destinatario, características del tipo de texto, " +
                        "género discursivo y registro, considerando las normas y modos de cortesía, así como los " +
                        "contextos socioculturales que enmarcan la comunicación. Asimismo, expresa las ideas en " +
                        "torno a un tema de forma lógica, relacionándolas mediante diversos recursos cohesivos para " +
                        "construir el sentido de distintos tipos de textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante emplea " +
                        "variados recursos no verbales (como gestos o movimientos corporales) o paraverbales (como " +
                        "el tono de la voz o silencios) según la situación comunicativa para enfatizar o matizar " +
                        "significados y producir determinados efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores",
                "El estudiante intercambia los " +
                        "roles de hablante y oyente alternada y dinámicamente, participando de forma pertinente, " +
                        "oportuna y relevante para lograr su propósito comunicativo."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el estudiante se " +
                        "distancie de los textos orales en los que participa. Para ello, reflexiona como oyente y " +
                        "hablante, que supone distanciarse de los textos orales en que participa de forma presencial " +
                        "o a través de medios audiovisuales, comparando y contrastando aspectos formales y de " +
                        "contenido, con la experiencia, el contexto, el conocimiento formal y diversas fuentes de " +
                        "información. Asimismo, evalúa, que implica analizar y valorar los textos orales producidos " +
                        "para construir una opinión personal o un juicio crítico sobre sus aspectos formales, " +
                        "contenidos e ideologías, y su relación con el contexto sociocultural, considerando los efectos " +
                        "que producen en los interlocutores."));
        comp_52.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_52);

        capacidades.clear();

        Competencia comp_53 = competenciaRepository.findByCode(53);
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "El estudiante localiza y selecciona " +
                        "información explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto",
                "El estudiante construye el sentido " +
                        "del texto. Para ello, infiere estableciendo diversas relaciones entre la " +
                        "información explícita e implícita con el fin de deducir nueva información y " +
                        "completar los vacíos del texto. A partir de estas inferencias, el estudiante " +
                        "interpreta integrando la información explícita e implícita, así como los recursos " +
                        "textuales, para construir el sentido global y profundo del texto, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones del autor, las ideologías " +
                        "de los textos así como su relación con el contexto sociocultural del lector y del " +
                        "texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares " +
                        "distintos, y que son presentados en diferentes soportes y formatos. Reflexionar " +
                        "implica comparar y contrastar aspectos formales y de contenido del texto con la " +
                        "experiencia, el conocimiento formal del lector y diversas fuentes de " +
                        "información. Evaluar implica analizar y valorar los textos escritos para construir " +
                        "una opinión personal o un juicio crítico sobre aspectos formales, estéticos, " +
                        "contenidos e ideologías de los textos considerando los efectos que producen, la " +
                        "relación con otros textos, y el contexto sociocultural del texto y del lector."));
        comp_53.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_53);

        capacidades.clear();

        Competencia comp_54 = competenciaRepository.findByCode(54);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "El estudiante considera el " +
                        "propósito, destinatario, tipo de texto, género discursivo y registro que utilizará al " +
                        "escribir los textos, así como los contextos socioculturales que enmarcan la " +
                        "comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El estudiante ordena lógicamente las ideas en torno a un tema, ampliándolas y " +
                        "complementándolas, estableciendo relaciones de cohesión entre ellas y utilizando " +
                        "un vocabulario pertinente. "));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente",
                "El estudiante usa de forma apropiada recursos textuales para garantizar la claridad, " +
                        "el uso estético del lenguaje y el sentido del texto escrito. "));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito",
                "El estudiante se distancia del texto que ha escrito para revisar de manera " +
                        "permanente el contenido, la coherencia, cohesión y adecuación a la situación " +
                        "comunicativa con la finalidad de mejorarlo. También implica analizar, comparar y " +
                        "contrastar las características de los usos del lenguaje escrito y sus posibilidades, así " +
                        "como su repercusión en otras personas o su relación con otros textos según el " +
                        "contexto sociocultural."));
        comp_54.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_54);

        capacidades.clear();

        Competencia comp_55 = competenciaRepository.findByCode(55);
        capacidades.add(new Capacidad("Obtiene información del texto oral en inglés",
                "El estudiante recupera y extrae información explícita " +
                        "expresada por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral en inglés",
                "El estudiante construye el sentido del texto. " +
                        "Para ello, infiere estableciendo diversas relaciones entre la información explícita e implícita con el " +
                        "fin de deducir nueva información y completar los vacíos del texto oral. A partir de estas inferencias, " +
                        "el estudiante interpreta integrando la información explícita e implícita, los recursos verbales, no " +
                        "verbales y paraverbales para construir el sentido global y profundo del texto oral, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones e ideologías de los interlocutores, así como " +
                        "su relación con el contexto sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto en inglés de forma coherente y cohesionada",
                "El estudiante expresa sus ideas adaptándose al propósito, destinatario, características del tipo de texto, género " +
                        "discursivo y registro, considerando las normas y modos de cortesía, así como los contextos " +
                        "socioculturales que enmarcan la comunicación. Asimismo, expresa las ideas en torno a un tema de " +
                        "forma lógica, relacionándolas mediante diversos recursos cohesivos para construir el sentido de " +
                        "distintos tipos de textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante emplea variados " +
                        "recursos no verbales (como gestos o movimientos corporales) o paraverbales (como el tono de la " +
                        "voz o silencios) según la situación comunicativa para enfatizar o matizar significados y producir " +
                        "determinados efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente en inglés con distintos interlocutores",
                "El estudiante intercambia los " +
                        "roles de hablante y oyente alternada y dinámicamente, participando de forma pertinente, oportuna " +
                        "y relevante para lograr su propósito comunicativo. "));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral en inglés",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el estudiante se distancie de " +
                        "los textos orales en los que participa. Para ello, reflexiona como oyente y hablante, que supone " +
                        "distanciarse de los textos orales en que participa de forma presencial o a través de medios " +
                        "audiovisuales, comparando y contrastando aspectos formales y de contenido, con la experiencia, el " +
                        "contexto, el conocimiento formal y diversas fuentes de información. Asimismo, evalúa, que implica " +
                        "analizar y valorar los textos orales producidos para construir una opinión personal o un juicio crítico " +
                        "sobre sus aspectos formales, contenidos e ideologías, y su relación con el contexto sociocultural, " +
                        "considerando los efectos que producen en los interlocutores."));
        comp_55.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_55);

        capacidades.clear();

        Competencia comp_56 = competenciaRepository.findByCode(56);
        capacidades.add(new Capacidad("Obtiene información del texto escrito en inglés",
                "El estudiante localiza y selecciona " +
                        "información explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto escrito en inglés",
                "El estudiante construye el " +
                        "sentido del texto. Para ello, infiere estableciendo diversas relaciones entre la información " +
                        "explícita e implícita con el fin de deducir nueva información y completar los vacíos del texto. " +
                        "A partir de estas inferencias, el estudiante interpreta integrando la información explícita e " +
                        "implícita, así como los recursos textuales, para construir el sentido global y profundo del " +
                        "texto, y explicar el propósito, las intenciones del autor, así como su relación con el contexto " +
                        "sociocultural del lector y del texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito en inglés",
                "Los procesos de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares distintos, y que " +
                        "son presentados en diferentes soportes y formatos. Reflexionar implica comparar y " +
                        "contrastar aspectos formales y de contenido del texto con la experiencia, el conocimiento " +
                        "formal del lector y diversas fuentes de información. Evaluar implica analizar y valorar los " +
                        "textos escritos para construir una opinión personal o un juicio crítico sobre aspectos " +
                        "formales, contenidos de los textos considerando los efectos que producen, la relación con " +
                        "otros textos, y el contexto sociocultural del texto y del lector."));
        comp_56.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_56);

        capacidades.clear();

        Competencia comp_57 = competenciaRepository.findByCode(57);
        capacidades.add(new Capacidad("Adecúa el texto en inglés a la situación comunicativa",
                "El estudiante considera el propósito, " +
                        "destinatario, tipo de texto, género discursivo y registro que utilizará al escribir los textos, así " +
                        "como los contextos socioculturales que enmarcan la comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas en inglés de forma coherente y cohesionada",
                "El estudiante " +
                        "ordena lógicamente las ideas en torno a un tema, ampliándolas y complementándolas, " +
                        "estableciendo relaciones de cohesión entre ellas y utilizando un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito en inglés de forma pertinente",
                "El estudiante usa de forma apropiada recursos textuales para garantizar la claridad, el uso estético del lenguaje " +
                        "y el sentido del texto escrito."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito en inglés",
                "El estudiante se distancia del texto que ha escrito para revisar de manera permanente el " +
                        "contenido, la coherencia, cohesión y adecuación a la situación comunicativa con la finalidad " +
                        "de mejorarlo. También implica analizar, comparar y contrastar las características de los usos " +
                        "del lenguaje escrito y sus posibilidades, así como su repercusión en otras personas o su " +
                        "relación con otros textos según el contexto sociocultural."));
        comp_57.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_57);

        capacidades.clear();

        Competencia comp_58 = competenciaRepository.findByCode(58);
        capacidades.add(new Capacidad("Percibe manifestaciones artístico-culturales",
                "Consiste en usar los sentidos para " +
                        "observar, escuchar, describir y analizar las cualidades visuales, táctiles, sonoras y " +
                        "kinestésicas de diversas manifestaciones artístico-culturales."));
        capacidades.add(new Capacidad("Contextualiza las manifestaciones culturales",
                "Es informarse acerca de la cultura en que " +
                        "se origina una manifestación artística para entender cómo el contexto social, cultural e " +
                        "histórico de esta influye en su creación y la manera en que transmite sus significados."));
        capacidades.add(new Capacidad("Reflexiona creativa y críticamente",
                "Supone interpretar las intenciones y significados de " +
                        "manifestaciones artístico-culturales que hayan visto o experimentado y emitir juicios de " +
                        "valor, entrelazando información obtenida a través de la percepción, el análisis y la " +
                        "comprensión de los contextos."));
        comp_58.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_58);

        capacidades.clear();

        Competencia comp_59 = competenciaRepository.findByCode(59);
        capacidades.add(new Capacidad("Explora y experimenta los lenguajes del arte",
                "Significa experimentar, improvisar y desarrollar " +
                        "habilidades en el uso de los medios, materiales, herramientas y técnicas de los diversos " +
                        "lenguajes del arte."));
        capacidades.add(new Capacidad("Aplica procesos creativos",
                "Supone generar ideas, investigar, tomar decisiones y poner en " +
                        "práctica sus conocimientos para elaborar un proyecto artístico individual o colaborativo en " +
                        "relación a una intención específica."));
        capacidades.add(new Capacidad("Evalúa y socializa sus procesos y proyectos",
                "Significa registrar sus experiencias, comunicar " +
                        "sus descubrimientos y compartir sus creaciones con otros, para profundizar en ellos y " +
                        "reflexionar sobre sus ideas y experiencias."));
        comp_59.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_59);

        capacidades.clear();

        Competencia comp_60 = competenciaRepository.findByCode(60);
        capacidades.add(new Capacidad("Interpreta críticamente fuentes diversas",
                "Es reconocer la diversidad de fuentes y su " +
                        "diferente utilidad para abordar un hecho o proceso histórico. Supone ubicarlas en su " +
                        "contexto y comprender, de manera crítica, que estas reflejan una perspectiva particular y " +
                        "tienen diferentes grados de fiabilidad. También implica recurrir a múltiples fuentes."));
        capacidades.add(new Capacidad("Comprende el tiempo histórico",
                "Es usar las nociones relativas al tiempo de manera " +
                        "pertinente, reconociendo que los sistemas de medición temporal son convenciones que " +
                        "dependen de distintas tradiciones culturales y que el tiempo histórico tiene diferentes " +
                        "duraciones. Asimismo, implica ordenar los hechos y procesos históricos cronológicamente " +
                        "y explicar los cambios y permanencias que se dan en ellos."));
        capacidades.add(new Capacidad("Elabora explicaciones sobre procesos históricos",
                "Es jerarquizar las causas de los procesos " +
                        "históricos relacionando las motivaciones de sus protagonistas con su cosmovisión y la " +
                        "época en la que vivieron. También es establecer las múltiples consecuencias de los " +
                        "procesos del pasado y sus implicancias en el presente, así como reconocer que este va " +
                        "construyendo nuestro futuro."));
        comp_60.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_60);

        capacidades.clear();

        Competencia comp_61 = competenciaRepository.findByCode(61);
        capacidades.add(new Capacidad("Comprende las relaciones entre los elementos naturales y sociales",
                "Es explicar las dinámicas " +
                        "y transformaciones del espacio geográfico, a partir del reconocimiento de sus elementos " +
                        "naturales y sociales que los componen, así como de las interacciones que se dan entre " +
                        "ambos a escala local, nacional o global."));
        capacidades.add(new Capacidad("Maneja fuentes de información para comprender el espacio geográfico",
                "Es usar distintas " +
                        "fuentes: cartográficas, fotográficas e imágenes diversas, cuadros y gráficos estadísticos, " +
                        "entre otros, para analizar el espacio geográfico, orientarse y desplazarse en él."));
        capacidades.add(new Capacidad("Genera acciones para preservar el ambiente",
                "Es proponer y poner en práctica acciones " +
                        "orientadas al cuidado del ambiente y a contribuir a la prevención de situaciones de riesgo " +
                        "de desastre. Esto supone analizar el impacto de las problemáticas ambientales y " +
                        "territoriales en la vida de las personas."));
        comp_61.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_61);

        capacidades.clear();

        Competencia comp_62 = competenciaRepository.findByCode(62);
        capacidades.add(new Capacidad("Comprende el funcionamiento del sistema económico y financiero",
                "Supone identificar los " +
                        "roles de los diversos agentes que intervienen en el sistema, analizar las interacciones entre " +
                        "ellos y comprender el rol del Estado en dichas interrelaciones."));
        capacidades.add(new Capacidad("Toma decisiones económicas y financieras",
                "Supone planificar el uso de sus recursos " +
                        "económicos de manera sostenible, en función a sus necesidades y posibilidades. También " +
                        "implica asumir una posición crítica frente a los sistemas de producción y de consumo, así " +
                        "como ejercer sus derechos y responsabilidades como consumidor informado."));
        comp_62.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_62);

        capacidades.clear();

        Competencia comp_63 = competenciaRepository.findByCode(63);
        capacidades.add(new Capacidad("Se valora a sí mismo",
                "es decir, el estudiante reconoce sus características, cualidades, " +
                        "limitaciones y potencialidades que lo hacen ser quien es, que le permiten aceptarse, " +
                        "sentirse bien consigo mismo y ser capaz de asumir retos y alcanzar sus metas. Además, se " +
                        "reconoce como integrante de una colectividad sociocultural específica y tiene sentido de " +
                        "pertenencia a su familia, escuela, comunidad, país y mundo."));
        capacidades.add(new Capacidad("Autorregula sus emociones",
                "es que el estudiante reconoce y toma conciencia de sus " +
                        "emociones, a fin de poder expresarlas de manera adecuada según el contexto, los " +
                        "patrones culturales diversos y las consecuencias que estas tienen para sí mismo y para los " +
                        "demás. Ello le permite regular su comportamiento, en favor de su bienestar y el de los " +
                        "demás."));
        capacidades.add(new Capacidad("Reflexiona y argumenta éticamente",
                "es que el estudiante analice situaciones cotidianas " +
                        "para identificar los valores que están presentes en ellas y asumir una posición, sustentada " +
                        "en argumentos razonados y en principios éticos. Implica también tomar conciencia de las " +
                        "propias decisiones y acciones, a partir de reflexionar sobre si estas responden a los " +
                        "principios éticos asumidos, y cómo los resultados y consecuencias influyen en sí mismos " +
                        "y en los demás."));
        capacidades.add(new Capacidad("Vive su sexualidad de manera plena y responsable",
                "es tomar conciencia de sí mismo como " +
                        "hombre o mujer, a partir del desarrollo de su imagen corporal, de su identidad sexual y " +
                        "de género, y mediante la exploración y valoración de su cuerpo. Supone establecer " +
                        "relaciones de igualdad entre mujeres y hombres, así como relaciones afectivas " +
                        "armoniosas y libres de violencia. También implica identificar y poner en práctica " +
                        "conductas de autocuidado frente a situaciones que ponen en riesgo su bienestar o que " +
                        "vulneran sus derechos sexuales y reproductivos."));
        comp_63.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_63);

        capacidades.clear();

        Competencia comp_64 = competenciaRepository.findByCode(64);
        capacidades.add(new Capacidad("Interactúa con todas las personas",
                "es decir, reconoce a todos como personas valiosas y " +
                        "con derechos, muestra preocupación por el otro, respeta las diferencias y se enriquecerse " +
                        "de ellas. Actúa frente a las distintas formas de discriminación (por género, fenotipo, origen " +
                        "étnico, lengua, discapacidad, orientación sexual, edad, nivel socioeconómico, entre " +
                        "otras), y reflexiona sobre las diversas situaciones que vulneran la convivencia " +
                        "democrática."));
        capacidades.add(new Capacidad("Construye normas y asume acuerdos y leyes",
                "es que el estudiante participe en la " +
                        "construcción de normas, las respete y evalúe en relación a los principios que las sustentan, " +
                        "así como, cumple con los acuerdos y las leyes, reconociendo la importancia de estas para " +
                        "la convivencia. Para lo cual, maneja información y conceptos relacionados con la " +
                        "convivencia (como la equidad, el respeto y la libertad) y hace suyo los principios " +
                        "democráticos (la autofundación, la secularidad, la incertidumbre, la ética, la complejidad " +
                        "y lo público)."));
        capacidades.add(new Capacidad("Maneja conflictos de manera constructiva",
                "es que actúe con empatía y asertividad frente " +
                        "a ellos, y ponga en práctica pautas y estrategias para resolverlos de manera pacífica y " +
                        "creativa, contribuyendo a construir comunidades democráticas. Para lo cual parte de " +
                        "comprender el conflicto como inherente a las relaciones humanas, así como desarrollar " +
                        "criterios para evaluar situaciones en las que estos ocurren."));
        capacidades.add(new Capacidad("Delibera sobre asuntos públicos",
                "es que participe en un proceso de reflexión y diálogo " +
                        "sobre asuntos que involucran a todos, donde se plantean diversos puntos de vista y se " +
                        "busca llegar a consensos orientados al bien común. Supone construir una posición propia " +
                        "sobre dichos asuntos basándose en argumentos razonados, la institucionalidad, el Estado " +
                        "de derecho y los principios democráticos, así como valorar y contraponer las diversas " +
                        "posiciones."));
        capacidades.add(new Capacidad("Participa en acciones que promueven el bienestar común",
                "es que proponga y gestione " +
                        "iniciativas vinculadas con el interés común y con la promoción y defensa de los derechos " +
                        "humanos, tanto en la escuela como en la comunidad. Para ello, se apropia y utiliza canales " +
                        "y mecanismos de participación democrática."));
        comp_64.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_64);

        capacidades.clear();

        Competencia comp_65 = competenciaRepository.findByCode(65);
        capacidades.add(new Capacidad("Comprende su cuerpo",
                "es decir interioriza su cuerpo en estado estático o en " +
                        "movimiento en relación al espacio, el tiempo, los objetos y demás personas de su " +
                        "entorno, representando mentalmente su cuerpo y desarrollando su identidad."));
        capacidades.add(new Capacidad("Se expresa corporalmente",
                "usa el lenguaje corporal para comunicar emociones, " +
                        "sentimientos y pensamientos. Implica utilizar el tono, los gestos, mímicas, posturas y " +
                        "movimientos para expresarse, desarrollando la creatividad al usar todos los recursos " +
                        "que ofrece el cuerpo y el movimiento."));
        comp_65.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_65);

        capacidades.clear();

        Competencia comp_66 = competenciaRepository.findByCode(66);
        capacidades.add(new Capacidad("Comprende las relaciones entre la actividad física, alimentación, postura e higiene corporal y la salud",
                "es analizar y comprender los procesos vinculados con la " +
                        "alimentación, la postura, la higiene corporal y la práctica de actividad física y cómo estos " +
                        "influyen en las diferentes actividades físicas o de la vida cotidiana, para el logro de un " +
                        "estado de bienestar integral (físico, psicológico y emocional), según sus recursos y " +
                        "entorno."));
        capacidades.add(new Capacidad("Incorpora prácticas que mejoran su calidad de vida",
                "es asumir una actitud crítica sobre " +
                        "la importancia de hábitos saludables y sus beneficios vinculados con la mejora de la " +
                        "calidad de vida. Esto supone la planificación de rutinas, dietas o planes que pongan en " +
                        "práctica sus conocimientos sobre alimentación, higiene corporal, posturas y actividad " +
                        "física para la salud según sus propias necesidades, recursos y entorno."));
        comp_66.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_66);

        capacidades.clear();

        Competencia comp_67 = competenciaRepository.findByCode(67);
        capacidades.add(new Capacidad("Se relaciona utilizando sus habilidades sociomotrices",
                "supone interactuar de manera " +
                        "asertiva con los demás en la práctica lúdica y deportiva experimentando el placer y " +
                        "disfrute que ella representa. Por otro lado desarrolla habilidades como el respeto a las " +
                        "normas de juego, liderazgo, tolerancia, actitud proactiva, la resolución de conflictos " +
                        "interpersonales, la pertenencia positiva a un grupo, entre otras."));
        capacidades.add(new Capacidad("Crea y aplica estrategias y tácticas de juego",
                "supone emplear los recursos personales y " +
                        "las potencialidades de cada miembro del equipo para el logro de un objetivo común, " +
                        "desarrollando y aplicando reglas y soluciones tácticas de juego en actividades físicas de " +
                        "colaboración, cooperación y oposición."));
        comp_67.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_67);

        capacidades.clear();

        Competencia comp_68 = competenciaRepository.findByCode(68);
        capacidades.add(new Capacidad("Conoce a Dios y asume su identidad religiosa como persona digna, libre y trascendente",
                "El estudiante entiende y experimenta que Dios es Amor, comprende que Dios es su Padre y " +
                        "creador, que lo ama y le ha dado la vida para ser feliz. "));
        capacidades.add(new Capacidad("Cultiva y valora las manifestaciones religiosas de su entorno argumentando su fe de manera comprensible y respetuosa",
                "El estudiante comprende el mensaje cristiano en " +
                        "relación con los problemas existenciales comunes a las religiones y característicos de todo " +
                        "ser humano, con las concepciones de la vida presentes en la cultura, y con los problemas " +
                        "morales fundamentales en los que hoy se ve envuelta la humanidad. También expresa con " +
                        "libertad su fe respetando las diversas creencias y expresiones religiosas de los demás."));
        comp_68.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_68);

        capacidades.clear();

        Competencia comp_69 = competenciaRepository.findByCode(69);
        capacidades.add(new Capacidad("Transforma su entorno desde el encuentro personal y comunitario con Dios y desde la fe que profesa",
                "Los estudiantes deben asumir con renovado entusiasmo y decisión, el reto de " +
                        "contribuir a la gestación de una nueva sociedad, más justa, más solidaria, más fraterna y " +
                        "más cristiana, de acuerdo con los valores de la civilización del amor."));
        capacidades.add(new Capacidad("Actúa coherentemente en razón de su fe según los principios de su conciencia moral en situaciones concretas de la vida",
                "Los estudiantes deben actuar según los principios de la " +
                        "conciencia moral cristiana: verdad, bondad y misericordia en situaciones concretas de la " +
                        "convivencia humana. Toman decisiones razonables en coherencia con los principios " +
                        "evangélicos y su escala de valores morales."));
        comp_69.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_69);

        capacidades.clear();

        Competencia comp_70 = competenciaRepository.findByCode(70);
        capacidades.add(new Capacidad("Problematiza situaciones para hacer indagación",
                "Es plantear preguntas sobre " +
                        "hechos y fenómenos naturales, interpretar situaciones y formular hipótesis."));
        capacidades.add(new Capacidad("Diseña estrategias para hacer indagación",
                "Es proponer actividades que " +
                        "permitan construir un procedimiento, seleccionar materiales, instrumentos e " +
                        "información para comprobar o refutar la hipótesis."));
        capacidades.add(new Capacidad("Genera y registra datos o información",
                "Es obtener, organizar y registrar datos " +
                        "fiables en función de las variables, utilizando instrumentos y diversas técnicas, que " +
                        "permitan comprobar o refutar la hipótesis."));
        capacidades.add(new Capacidad("Analiza datos e información",
                "Es interpretar los datos obtenidos en la " +
                        "indagación, contrastarlos con las hipótesis e información relacionada al problema " +
                        "para elaborar conclusiones, que comprueban o refutan la hipótesis."));
        capacidades.add(new Capacidad("Evalúa y comunica el proceso y resultados de su indagación",
                "Es identificar y " +
                        "dar a conocer las dificultades técnicas y los conocimientos logrados para cuestionar " +
                        "el grado de satisfacción que la respuesta da a la pregunta de indagación."));
        comp_70.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_70);

        capacidades.clear();

        Competencia comp_71 = competenciaRepository.findByCode(71);
        capacidades.add(new Capacidad("Comprende y usa conocimientos sobre los seres vivos, materia y energía, biodiversidad, Tierra y universo",
                "Cuando es capaz de tener desempeños flexibles, " +
                        "es decir, establece relaciones entre varios conceptos y los transfiere a nuevas " +
                        "situaciones. Esto le permite construir representaciones del mundo natural y " +
                        "artificial, que se evidencian cuando el estudiante explica, ejemplifica, aplica, " +
                        "justifica, compara, contextualiza y generaliza sus conocimientos."));
        capacidades.add(new Capacidad("Evalúa las implicancias del saber y del quehacer científico y tecnológico",
                "Cuando " +
                        "identifica los cambios generados en la sociedad por el conocimiento científico o " +
                        "desarrollo tecnológico, con el fin de asumir una postura crítica o tomar decisiones, " +
                        "considerando saberes locales, evidencia empírica y científica, con la finalidad de " +
                        "mejorar su calidad de vida y conservar el ambiente."));
        comp_71.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_71);

        capacidades.clear();

        Competencia comp_72 = competenciaRepository.findByCode(72);
        capacidades.add(new Capacidad("Determina una alternativa de solución tecnológica",
                "al detectar un problema y " +
                        "propone alternativas de solución creativas basadas en conocimientos científico, " +
                        "tecnológico y prácticas locales, evaluando su pertinencia para seleccionar una de " +
                        "ellas."));
        capacidades.add(new Capacidad("Diseña la alternativa de solución tecnológica",
                "es representar de manera gráfica o " +
                        "esquemática la estructura y funcionamiento de la solución tecnológica " +
                        "(especificaciones de diseño), usando conocimiento científico, tecnológico y " +
                        "prácticas locales, teniendo en cuenta los requerimientos del problema y los " +
                        "recursos disponibles."));
        capacidades.add(new Capacidad("Implementa la alternativa de solución tecnológica",
                "es llevar a cabo la alternativa " +
                        "de solución, verificando y poniendo a prueba el cumplimiento de las " +
                        "especificaciones de diseño y el funcionamiento de sus partes o etapas."));
        capacidades.add(new Capacidad("Evalúa y comunica el funcionamiento de su alternativa de solución tecnológica",
                "es determinar qué tan bien la solución tecnológica logró responder a los " +
                        "requerimientos del problema, comunicar su funcionamiento y analizar sus " +
                        "posibles impactos, en el ambiente y la sociedad, tanto en su proceso de " +
                        "elaboración como de uso."));
        comp_72.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_72);

        capacidades.clear();

        Competencia comp_73 = competenciaRepository.findByCode(73);
        capacidades.add(new Capacidad("Crea propuestas de valor",
                "Genera alternativas de solución creativas e innovadoras a " +
                        "través de un bien o servicio que resuelva una necesidad no satisfecha o un problema " +
                        "social que investiga en su entorno; evalúa la pertinencia de sus alternativas de solución " +
                        "validando sus ideas con las personas que busca beneficiar o impactar, y la viabilidad de " +
                        "las alternativas de solución en base a criterios para seleccionar una de ellas y diseña una " +
                        "estrategia que le permita poner en marcha su idea definiendo objetivos y metas y " +
                        "dimensionando los recursos y tareas."));
        capacidades.add(new Capacidad("Aplica habilidades técnicas",
                "Es operar herramientas, máquinas o programas de " +
                        "software, y desarrollar métodos y estrategias para ejecutar los procesos de producción " +
                        "de un bien o la prestación de un servicio aplicando principios técnicos; implica " +
                        "seleccionar o combinar aquellas herramientas, métodos o técnicas en función de " +
                        "requerimientos específicos aplicando criterios de calidad y eficiencia."));
        capacidades.add(new Capacidad("Trabaja cooperativamente para lograr objetivos y metas",
                "Es integrar esfuerzos " +
                        "individuales para el logro de un objetivo en común, organizar el trabajo en equipo en " +
                        "función de las habilidades diferentes que puede aportar cada miembro, asumir con " +
                        "responsabilidad su rol y las tareas que implica desempeñándose con eficacia y " +
                        "eficiencia. Es también reflexionar sobre su experiencia de trabajo y la de los miembros " +
                        "del equipo para generar un clima favorable, mostrando tolerancia a la frustración, " +
                        "aceptando distintos puntos de vista y consensuando ideas."));
        capacidades.add(new Capacidad("Evalúa los resultados del proyecto de emprendimiento",
                "Es determinar en qué medida " +
                        "los resultados parciales o finales generaron los cambios esperados en la atención del " +
                        "problema o necesidad identificada; emplea la información para tomar decisiones e " +
                        "incorporar mejoras al diseño del proyecto. Es además analizar los posibles impactos en " +
                        "el ambiente y la sociedad, y formular estrategias que permitan la sostenibilidad del " +
                        "proyecto en el tiempo."));
        comp_73.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_73);

        capacidades.clear();

        Competencia comp_74 = competenciaRepository.findByCode(74);
        capacidades.add(new Capacidad("Obtiene información del texto oral",
                "El estudiante recupera y extrae información explícita expresada " +
                        "por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral",
                "El estudiante construye el sentido del texto. Para ello, " +
                        "infiere estableciendo diversas relaciones entre la información explícita e implícita con el fin de deducir " +
                        "nueva información y completar los vacíos del texto oral. A partir de estas inferencias, el estudiante " +
                        "interpreta integrando la información explícita e implícita, los recursos verbales, no verbales y paraverbales " +
                        "para construir el sentido global y profundo del texto oral, y explicar el propósito, el uso estético del " +
                        "lenguaje, las intenciones e ideologías de los interlocutores, así como su relación con el contexto " +
                        "sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto de forma coherente y cohesionada",
                "El estudiante expresa sus " +
                        "ideas adaptándose al propósito, destinatario, características del tipo de texto, género discursivo y registro, " +
                        "considerando las normas y modos de cortesía, así como los contextos socioculturales que enmarcan la " +
                        "comunicación. Asimismo, expresa las ideas en torno a un tema de forma lógica, relacionándolas mediante " +
                        "diversos recursos cohesivos para construir el sentido de distintos tipos de textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante emplea variados " +
                        "recursos no verbales (como gestos o movimientos corporales) o paraverbales (como el tono de la voz o " +
                        "silencios) según la situación comunicativa para enfatizar o matizar significados y producir determinados " +
                        "efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores",
                "El estudiante intercambia los roles de " +
                        "hablante y oyente alternada y dinámicamente, participando de forma pertinente, oportuna y relevante " +
                        "para lograr su propósito comunicativo."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral",
                "Los procesos de reflexión y " +
                        "evaluación están relacionados porque ambos suponen que el estudiante se distancie de los textos orales " +
                        "en los que participa. Para ello, reflexiona como oyente y hablante, que supone distanciarse de los textos " +
                        "orales en que participa de forma presencial o a través de medios audiovisuales, comparando y " +
                        "contrastando aspectos formales y de contenido, con la experiencia, el contexto, el conocimiento formal y " +
                        "diversas fuentes de información. Asimismo, evalúa, que implica analizar y valorar los textos orales " +
                        "producidos para construir una opinión personal o un juicio crítico sobre sus aspectos formales, contenidos " +
                        "e ideologías, y su relación con el contexto sociocultural, considerando los efectos que producen en los " +
                        "interlocutores."));
        comp_74.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_74);

        capacidades.clear();

        Competencia comp_75 = competenciaRepository.findByCode(75);
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "El estudiante localiza y selecciona " +
                        "información explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto",
                "El estudiante construye el sentido " +
                        "del texto. Para ello, infiere estableciendo diversas relaciones entre la " +
                        "información explícita e implícita con el fin de deducir nueva información y " +
                        "completar los vacíos del texto. A partir de estas inferencias, el estudiante " +
                        "interpreta integrando la información explícita e implícita, así como los recursos " +
                        "textuales, para construir el sentido global y profundo del texto, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones del autor, las ideologías " +
                        "de los textos así como su relación con el contexto sociocultural del lector y del " +
                        "texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto",
                "Los procesos " +
                        "de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares " +
                        "distintos, y que son presentados en diferentes soportes y formatos. Reflexionar " +
                        "implica comparar y contrastar aspectos formales y de contenido del texto con la " +
                        "experiencia, el conocimiento formal del lector y diversas fuentes de " +
                        "información. Evaluar implica analizar y valorar los textos escritos para construir " +
                        "una opinión personal o un juicio crítico sobre aspectos formales, estéticos, " +
                        "contenidos e ideologías de los textos considerando los efectos que producen, la " +
                        "relación con otros textos, y el contexto sociocultural del texto y del lector."));
        comp_75.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_75);

        capacidades.clear();

        Competencia comp_76 = competenciaRepository.findByCode(76);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "El estudiante considera el " +
                        "propósito, destinatario, tipo de texto, género discursivo y registro que utilizará al " +
                        "escribir los textos, así como los contextos socioculturales que enmarcan la " +
                        "comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El estudiante ordena lógicamente las ideas en torno a un tema, ampliándolas y " +
                        "complementándolas, estableciendo relaciones de cohesión entre ellas y utilizando " +
                        "un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente",
                "El estudiante usa de forma apropiada recursos textuales para garantizar la claridad, " +
                        "el uso estético del lenguaje y el sentido del texto escrito."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito",
                "El estudiante se distancia del texto que ha escrito para revisar de manera " +
                        "permanente el contenido, la coherencia, cohesión y adecuación a la situación " +
                        "comunicativa con la finalidad de mejorarlo. También implica analizar, comparar y " +
                        "contrastar las características de los usos del lenguaje escrito y sus posibilidades, así " +
                        "como su repercusión en otras personas o su relación con otros textos según el " +
                        "contexto sociocultural."));
        comp_76.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_76);

    }

    private void initMatrixDesempeyos() {
        List<Desempenyo> desempenyos = new ArrayList<>();


    }

}
