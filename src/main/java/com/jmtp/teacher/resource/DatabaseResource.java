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
    private DesempenyoRepository desempenyoRepository;
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
                            DesempenyoRepository desempenyoRepository,
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
        this.desempenyoRepository = desempenyoRepository;
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
        initMatrixCapacidad();

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
        areas_secundaria.add(new Area(27,"Castellano como segunda lengua"));
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
        grados.add( new GradoBase(1,"2 años") );
        ciclo_i.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_i);

        grados.clear();

        Ciclo ciclo_ii = cicloRepository.findByName("II");
        grados.add( new GradoBase(2,"3 años") );
        grados.add( new GradoBase(3,"4 años") );
        grados.add( new GradoBase(4,"5 años") );
        ciclo_ii.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_ii);

        grados.clear();

        Ciclo ciclo_iii = cicloRepository.findByName("III");
        grados.add( new GradoBase(5,"1ro") );
        grados.add( new GradoBase(6,"2do") );
        ciclo_iii.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_iii);

        grados.clear();

        Ciclo ciclo_iv = cicloRepository.findByName("IV");
        grados.add( new GradoBase(7,"3ro") );
        grados.add( new GradoBase(8,"4to") );
        ciclo_iv.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_iv);

        grados.clear();

        Ciclo ciclo_v = cicloRepository.findByName("V");
        grados.add( new GradoBase(9,"5to") );
        grados.add( new GradoBase(10,"6to") );
        ciclo_v.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_v);

        grados.clear();

        Ciclo ciclo_vi = cicloRepository.findByName("VI");
        grados.add( new GradoBase(11,"1ro") );
        grados.add( new GradoBase(12,"2do") );
        ciclo_vi.getGrados().addAll(gradoBaseRepository.saveAll(grados));
        cicloRepository.save(ciclo_vi);

        grados.clear();

        Ciclo ciclo_vii = cicloRepository.findByName("VII");
        grados.add( new GradoBase(13,"3ro") );
        grados.add( new GradoBase(14,"4to") );
        grados.add( new GradoBase(15,"5to") );
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
        capacidades.add(new Capacidad("Se valora a sí mismo",
                "Parte por reconocer las características, cualidades, " +
                        "limitaciones y potencialidades que lo hacen ser quien es, que le permiten " +
                        "aceptarse, sentirse bien consigo mismo y ser capaz de asumir retos y alcanzar sus " +
                        "metas. Además, se reconoce como integrante de una colectividad sociocultural " +
                        "específica, desarrollando un sentido de pertenencia a su familia, escuela, " +
                        "comunidad, país y mundo."));
        capacidades.add(new Capacidad("Autoregula sus emociones",
                "Es reconocer y tomar conciencia de sus emociones, " +
                        "a fin de poder expresarlas de manera adecuada, considerando su etapa de " +
                        "desarrollo, el contexto, los patrones culturales diversos y las consecuencias que " +
                        "estas tienen para sí mismo y para los demás. Ello le permite regular su " +
                        "comportamiento, en favor de su bienestar y el de los demás."));
        comp_1.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_1);

        capacidades.clear();

        Competencia comp_2 = competenciaRepository.findByCode(2);
        capacidades.add(new Capacidad("Interactúa con todas las personas reconociendo que todos tenemos derechos",
                "Es establecer relaciones en las que se respetan las diferencias, se reconoce a todos " +
                        "como personas valiosas, y se demuestra disposición a preocuparse por el otro y a " +
                        "enriquecerse mutuamente. Implica actuar frente a las distintas formas de " +
                        "discriminación (por género, fenotipo, origen étnico, lengua, discapacidad, " +
                        "orientación sexual, edad, nivel socioeconómico, entre otras), así como reflexionar " +
                        "sobre las diversas situaciones que vulneran la convivencia democrática."));
        capacidades.add(new Capacidad("Construye normas, y asume acuerdos y leyes",
                "Es producir, respetar y evaluar las normas en " +
                        "relación a los principios que las sustentan, así como cumplir con las leyes, " +
                        "reconociendo la importancia de estas para la convivencia. Supone manejar " +
                        "información y conceptos relacionados con la convivencia (como la equidad, el " +
                        "respeto y la libertad) y hacer suyos los principios democráticos (la autofundación, " +
                        "la secularidad, la incertidumbre, la ética, la complejidad y lo público)."));
        capacidades.add(new Capacidad("Participa en acciones que promueven el bienestar común",
                "Es proponer y gestionar iniciativas vinculadas con el interés común y con la promoción y defensa " +
                        "de los derechos humanos, tanto en la escuela como en la comunidad. Para ello, " +
                        "se apropia y utiliza canales y mecanismos de participación democrática."));
        comp_2.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_2);

        capacidades.clear();

        Competencia comp_3 = competenciaRepository.findByCode(3);
        capacidades.add(new Capacidad("Comprende su cuerpo",
                "Es la interiorización progresiva que los estudiantes tienen " +
                        "de su cuerpo en estado estático o en movimiento y con relación al espacio, el " +
                        "tiempo, los otros y los objetos de su entorno, permitiéndoles el desarrollo de su " +
                        "personalidad y la representación mental de su cuerpo."));
        capacidades.add(new Capacidad("Se expresa corporalmente",
                "Es el uso del lenguaje corporal para comunicar " +
                        "emociones, sentimientos y pensamientos. Implica utilizar el tono, los gestos, " +
                        "mímicas, posturas y movimientos para expresarse, desarrollando la creatividad al " +
                        "usar todos los recursos que ofrece el cuerpo y el movimiento."));
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
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "El estudiante localiza y selecciona información explícita " +
                        "en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto",
                "El estudiante construye el sentido del texto. Para " +
                        "ello, infiere estableciendo diversas relaciones entre la información explícita e implícita con el fin " +
                        "de deducir nueva información y completar los vacíos del texto. A partir de estas inferencias, el " +
                        "estudiante interpreta integrando la información explícita e implícita, así como los recursos " +
                        "textuales, para construir el sentido global y profundo del texto, y explicar el propósito, el uso " +
                        "estético del lenguaje, las intenciones del autor, las ideologías de los textos así como su relación " +
                        "con el contexto sociocultural del lector y del texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto",
                "Los procesos de reflexión y " +
                        "evaluación están relacionados porque ambos suponen que el estudiante se distancie de los " +
                        "textos escritos situados en épocas y lugares distintos, y que son presentados en diferentes " +
                        "soportes y formatos. Reflexionar implica comparar y contrastar aspectos formales y de " +
                        "contenido del texto con la experiencia, el conocimiento formal del lector y diversas fuentes de " +
                        "información. Evaluar implica analizar y valorar los textos escritos para construir una opinión " +
                        "personal o un juicio crítico sobre aspectos formales, estéticos, contenidos e ideologías de los " +
                        "textos considerando los efectos que producen, la relación con otros textos, y el contexto " +
                        "sociocultural del texto y del lector."));
        comp_5.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_5);

        capacidades.clear();

        Competencia comp_6 = competenciaRepository.findByCode(6);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "El estudiante considera el propósito, " +
                        "destinatario, tipo de texto, género discursivo y registro que utilizará al escribir los " +
                        "textos, así como los contextos socioculturales que enmarcan la comunicación " +
                        "escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "El estudiante " +
                        "ordena lógicamente las ideas en torno a un tema, ampliándolas y " +
                        "complementándolas, estableciendo relaciones de cohesión entre ellas y " +
                        "utilizando un vocabulario pertinente."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito",
                "El estudiante se distancia del texto que ha escrito para revisar de manera " +
                        "permanente el contenido, la coherencia, cohesión y adecuación a la situación " +
                        "comunicativa con la finalidad de mejorarlo. También implica analizar, comparar y " +
                        "contrastar las características de los usos del lenguaje escrito y sus posibilidades, " +
                        "así como su repercusión en otras personas o su relación con otros textos según el " +
                        "contexto sociocultural."));
        comp_6.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_6);

        capacidades.clear();

        Competencia comp_7 = competenciaRepository.findByCode(7);
        capacidades.add(new Capacidad("Explora el lenguaje de las artes",
                "Significa experimentar, descubrir y aprender sobre " +
                        "los elementos, códigos y formas del arte, así como las diferentes técnicas y diversos " +
                        "materiales como medios, para relacionar sus sensaciones con sus ideas."));
        capacidades.add(new Capacidad("Desarrolla procesos de creación",
                "Significa planificar y realizar proyectos artísticos, a " +
                        "través de un proceso creativo, motivado por un impulso expresivo, un estímulo externo, una " +
                        "intención o idea."));
        capacidades.add(new Capacidad("Socializa sus experiencias y descubrimientos",
                "Significa compartir los hallazgos y " +
                        "resultados de sus procesos y proyectos artístico-culturales, lo que le permite vincularse con " +
                        "diversos agentes de su comunidad para dar y recibir retroalimentación de ellos."));
        comp_7.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_7);

        capacidades.clear();

        Competencia comp_8 = competenciaRepository.findByCode(8);
        capacidades.add(new Capacidad("Obtiene información del texto oral",
                "El estudiante recupera y extrae información " +
                        "explícita expresada por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral",
                "El estudiante construye el sentido del " +
                        "texto. Para ello, infiere estableciendo diversas relaciones entre la información explícita " +
                        "e implícita con el fin de deducir nueva información y completar los vacíos del texto " +
                        "oral. A partir de estas inferencias, el estudiante interpreta integrando la información " +
                        "explícita e implícita, los recursos verbales, no verbales y paraverbales para construir el " +
                        "sentido global y profundo del texto oral, y explicar el propósito, el uso estético del " +
                        "lenguaje, las intenciones e ideologías de los interlocutores, así como su relación con el " +
                        "contexto sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla el texto de forma coherente y cohesionada",
                "El estudiante expresa sus ideas adaptándose al propósito, destinatario, características " +
                        "del tipo de texto, género discursivo y registro, considerando las normas y modos de " +
                        "cortesía, así como los contextos socioculturales que enmarcan la comunicación. " +
                        "Asimismo, expresa las ideas en torno a un tema de forma lógica, relacionándolas " +
                        "mediante diversos recursos cohesivos para construir el sentido de distintos tipos de " +
                        "textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "El estudiante " +
                        "emplea variados recursos no verbales (como gestos o movimientos corporales) o " +
                        "paraverbales (como el tono de la voz o silencios) según la situación comunicativa para " +
                        "enfatizar o matizar significados y producir determinados efectos en los interlocutores."));
        capacidades.add(new Capacidad("Interactúa estratégicamente con distintos interlocutores",
                "El estudiante intercambia " +
                        "los roles de hablante y oyente alternada y dinámicamente, participando de forma " +
                        "pertinente, oportuna y relevante para lograr su propósito comunicativo."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto oral",
                "Los procesos de " +
                        "reflexión y evaluación están relacionados porque ambos suponen que el estudiante se " +
                        "distancie de los textos orales en los que participa. Para ello, reflexiona como oyente y " +
                        "hablante, que supone distanciarse de los textos orales en que participa de forma " +
                        "presencial o a través de medios audiovisuales, comparando y contrastando aspectos " +
                        "formales y de contenido, con la experiencia, el contexto, el conocimiento formal y " +
                        "diversas fuentes de información. Asimismo, evalúa, que implica analizar y valorar los " +
                        "textos orales producidos para construir una opinión personal o un juicio crítico sobre " +
                        "sus aspectos formales, contenidos e ideologías, y su relación con el contexto " +
                        "sociocultural, considerando los efectos que producen en los interlocutores."));
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
        capacidades.add(new Capacidad("Genera y registra datos o información"));
        comp_10.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_10);

        capacidades.clear();

        Competencia comp_11 = competenciaRepository.findByCode(11);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones"));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para orientarse en el espacio"));
        comp_11.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_11);

        capacidades.clear();

        Competencia comp_12 = competenciaRepository.findByCode(12);
        capacidades.add(new Capacidad("Traduce cantidades a expresiones numéricas",
                "Es transformar las relaciones " +
                        "entre los datos y condiciones de un problema, a una expresión numérica (modelo) " +
                        "que reproduzca las relaciones entre estos; esta expresión se comporta como un " +
                        "sistema compuesto por números, operaciones y sus propiedades. Es plantear " +
                        "problemas a partir de una situación o una expresión numérica dada. También implica " +
                        "evaluar si el resultado obtenido o la expresión numérica formulada (modelo), " +
                        "cumplen las condiciones iniciales del problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones",
                "Es expresar " +
                        "la comprensión de los conceptos numéricos, las operaciones y propiedades, las " +
                        "unidades de medida, las relaciones que establece entre ellos; usando lenguaje " +
                        "numérico y diversas representaciones, así como leer sus representaciones e " +
                        "información con contenido numérico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos de estimación y calculo",
                "Es seleccionar, " +
                        "adaptar, combinar o crear una variedad de estrategias, procedimientos como el " +
                        "cálculo mental y escrito, la estimación, la aproximación y medición, comparar " +
                        "cantidades; y emplear diversos recursos."));
        comp_12.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_12);

        capacidades.clear();

        Competencia comp_13 = competenciaRepository.findByCode(13);
        capacidades.add(new Capacidad("Traduce datos y condiciones a expresiones algebraicas",
                "Es transformar los " +
                        "datos, valores desconocidos, variables y relaciones de un problema, a una " +
                        "expresión gráfica o algebraica (modelo) que generalice la interacción entre " +
                        "estos. Implica también evaluar el resultado o la expresión formulada, con " +
                        "respecto a las condiciones de la situación; y formular preguntas o problemas " +
                        "a partir de una situación o una expresión."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las relaciones algebraicas",
                "Es expresar su " +
                        "comprensión de la noción, concepto o propiedades de los patrones, " +
                        "funciones, ecuaciones e inecuaciones estableciendo relaciones entre estas; " +
                        "usando lenguaje algebraico y diversas representaciones. Así como " +
                        "interpretar información que presente contenido algebraico"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para encontrar reglas generales",
                "Es seleccionar, adaptar, combinar o crear, procedimientos, estrategias y " +
                        "algunas propiedades como las de las ecuaciones e inecuaciones, y reglas de " +
                        "manipulación de expresiones simbólicas que permitan resolver ecuaciones, " +
                        "determinar dominios y rangos, representar rectas, parábolas, y diversas " +
                        "funciones."));
        comp_13.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_13);

        capacidades.clear();

        Competencia comp_14 = competenciaRepository.findByCode(14);
        capacidades.add(new Capacidad("Problematiza situaciones para hacer indagación",
                "Cuando plantea preguntas " +
                        "sobre hechos y fenómenos naturales, interpreta situaciones y formula " +
                        "hipótesis."));
        capacidades.add(new Capacidad("Diseña estrategias para hacer indagación",
                "Cuando propone actividades que " +
                        "permitan construir un procedimiento, selecciona materiales, instrumentos e " +
                        "información para comprobar o refutar la hipótesis."));
        capacidades.add(new Capacidad("Genera y registra datos o información",
                "Cuando aplica el procedimiento, utiliza " +
                        "instrumentos y diversas técnicas para obtener y organizar datos fiables, en " +
                        "función de las variables, que permitan comprobar o refutar la hipótesis."));
        capacidades.add(new Capacidad("Analiza datos e información",
                "Cuando interpreta los datos obtenidos en la " +
                        "indagación, los contrasta con las hipótesis e información relacionada al " +
                        "problema para elaborar conclusiones, que comprueban o refutan la hipótesis."));
        capacidades.add(new Capacidad("Evalúa y comunica el proceso y resultados de su indagación",
                "Cuando identifica y da a conocer las dificultades técnicas y los conocimientos logrados " +
                        "para cuestionar el grado de satisfacción que la respuesta da a la pregunta de " +
                        "indagación."));
        comp_14.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_14);

        capacidades.clear();

        Competencia comp_15 = competenciaRepository.findByCode(15);
        capacidades.add(new Capacidad("Traduce cantidades a expresiones numéricas",
                "Es transformar las " +
                        "relaciones entre los datos y condiciones de un problema, a una expresión " +
                        "numérica (modelo) que reproduzca las relaciones entre estos; esta expresión se " +
                        "comporta como un sistema compuesto por números, operaciones y sus " +
                        "propiedades. Es plantear problemas a partir de una situación o una expresión " +
                        "numérica dada. También implica evaluar si el resultado obtenido o la expresión " +
                        "numérica formulada (modelo), cumplen las condiciones iniciales del problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones",
                "Es expresar la comprensión de los conceptos numéricos, las operaciones y " +
                        "propiedades, las unidades de medida, las relaciones que establece entre ellos; " +
                        "usando lenguaje numérico y diversas representaciones; así como leer sus " +
                        "representaciones e información con contenido numérico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos de estimación y cálculo",
                "Es seleccionar, adaptar, combinar o crear una variedad de estrategias, " +
                        "procedimientos como el cálculo mental y escrito, la estimación, la aproximación " +
                        "y medición, comparar cantidades; y emplear diversos recursos."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre las relaciones numéricas y las operaciones",
                "Es elaborar afirmaciones sobre las posibles relaciones entre " +
                        "números naturales, enteros, racionales, reales, sus operaciones y propiedades; " +
                        "en base a comparaciones y experiencias en las que induce propiedades a partir " +
                        "de casos particulares; así como explicarlas con analogías, justificarlas, validarlas " +
                        "o refutarlas con ejemplos y contraejemplos."));
        comp_15.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_15);

        capacidades.clear();

        Competencia comp_16 = competenciaRepository.findByCode(16);
        capacidades.add(new Capacidad("Traduce datos y condiciones a expresiones algebraicas",
                "Es transformar los datos, valores desconocidos, variables y relaciones de un " +
                        "problema a una expresión gráfica o algebraica (modelo) que generalice la " +
                        "interacción entre estos. Implica también evaluar el resultado o la " +
                        "expresión formulada, con respecto a las condiciones de la situación; y " +
                        "formular preguntas o problemas a partir de una situación o una expresión."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las relaciones algebraicas",
                "Es expresar su comprensión de la noción, concepto o propiedades de los " +
                        "patrones, funciones, ecuaciones e inecuaciones estableciendo relaciones " +
                        "entre estas; usando lenguaje algebraico y diversas representaciones. Así " +
                        "como interpretar información que presente contenido algebraico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para encontrar reglas generales",
                "Es seleccionar, adaptar, combinar o crear, procedimientos, " +
                        "estrategias y algunas propiedades para simplificar o transformar " +
                        "ecuaciones, inecuaciones y expresiones simbólicas que le permitan " +
                        "resolver ecuaciones, determinar"));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones de cambio y equivalencia",
                "Es elaborar afirmaciones sobre variables, reglas " +
                        "algebraicas y propiedades algebraicas, razonando de manera inductiva " +
                        "para generalizar una regla y de manera deductiva probando y " +
                        "comprobando propiedades y nuevas relaciones."));
        comp_16.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_16);

        capacidades.clear();

        Competencia comp_17 = competenciaRepository.findByCode(17);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones",
                "Es construir un modelo que reproduzca las características de los objetos, su " +
                        "localización y movimiento, mediante formas geométricas, sus elementos y " +
                        "propiedades; la ubicación y transformaciones en el plano. Es también " +
                        "evaluar si el modelo cumple con las condiciones dadas en el problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas",
                "Es comunicar su comprensión de las propiedades de las formas " +
                        "geométricas, sus transformaciones y la ubicación en un sistema de " +
                        "referencia; es también establecer relaciones entre estas formas, usando " +
                        "lenguaje geométrico y representaciones gráficas o simbólicas"));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para orientarse en el espacio",
                "Es seleccionar, adaptar, combinar o crear, una variedad de estrategias, " +
                        "procedimientos y recursos para construir formas geométricas, trazar rutas, " +
                        "medir o estimar distancias y superficies, y transformar las formas " +
                        "bidimensionales y tridimensionales."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones geométricas",
                "Es elaborar " +
                        "afirmaciones sobre las posibles relaciones entre los elementos y las " +
                        "propiedades de las formas geométricas; en base a su exploración o " +
                        "visualización. Asimismo, justificarlas, validarlas o refutarlas, en base a su " +
                        "experiencia, ejemplos o contraejemplos, y conocimientos sobre " +
                        "propiedades geométricas; usando el razonamiento inductivo o deductivo."));
        comp_17.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_17);

        capacidades.clear();

        Competencia comp_18 = competenciaRepository.findByCode(18);
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
        comp_18.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_18);

        capacidades.clear();

        Competencia comp_19 = competenciaRepository.findByCode(19);
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
        comp_19.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_19);

        capacidades.clear();

        Competencia comp_20 = competenciaRepository.findByCode(20);
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
        comp_20.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_20);

        capacidades.clear();

        Competencia comp_21 = competenciaRepository.findByCode(21);
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
        comp_21.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_21);

        capacidades.clear();

        Competencia comp_22 = competenciaRepository.findByCode(22);
        capacidades.add(new Capacidad("Obtiene información del texto oral en inglés",
                "El estudiante recupera y extrae información explícita " +
                        "expresada por los interlocutores"));
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
        comp_22.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_22);

        capacidades.clear();

        Competencia comp_23 = competenciaRepository.findByCode(23);
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
                "Los " +
                        "procesos de reflexión y evaluación están relacionados porque ambos suponen que el " +
                        "estudiante se distancie de los textos escritos situados en épocas y lugares distintos, y que " +
                        "son presentados en diferentes soportes y formatos. Reflexionar implica comparar y " +
                        "contrastar aspectos formales y de contenido del texto con la experiencia, el conocimiento " +
                        "formal del lector y diversas fuentes de información. Evaluar implica analizar y valorar los " +
                        "textos escritos para construir una opinión personal o un juicio crítico sobre aspectos " +
                        "formales, contenidos de los textos considerando los efectos que producen, la relación con " +
                        "otros textos, y el contexto sociocultural del texto y del lector."));
        comp_23.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_23);

        capacidades.clear();

        Competencia comp_24 = competenciaRepository.findByCode(24);
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
        comp_24.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_24);

        capacidades.clear();

        Competencia comp_25 = competenciaRepository.findByCode(25);
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
        comp_25.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_25);

        capacidades.clear();

        Competencia comp_26 = competenciaRepository.findByCode(26);
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
        comp_26.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_26);

        capacidades.clear();

        Competencia comp_27 = competenciaRepository.findByCode(27);
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
        comp_27.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_27);

        capacidades.clear();

        Competencia comp_28 = competenciaRepository.findByCode(28);
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
        comp_28.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_28);

        capacidades.clear();

        Competencia comp_29 = competenciaRepository.findByCode(29);
        capacidades.add(new Capacidad("Comprende el funcionamiento del sistema económico y financiero",
                "Supone identificar los " +
                        "roles de los diversos agentes que intervienen en el sistema, analizar las interacciones entre " +
                        "ellos y comprender el rol del Estado en dichas interrelaciones."));
        capacidades.add(new Capacidad("Toma decisiones económicas y financieras",
                "Supone planificar el uso de sus recursos " +
                        "económicos de manera sostenible, en función a sus necesidades y posibilidades. También " +
                        "implica asumir una posición crítica frente a los sistemas de producción y de consumo, así " +
                        "como ejercer sus derechos y responsabilidades como consumidor informado."));
        comp_29.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_29);


        capacidades.clear();

        Competencia comp_30 = competenciaRepository.findByCode(30);
        capacidades.add(new Capacidad("Percibe manifestaciones artístico-culturales",
                "Consiste en usar los sentidos para " +
                        "observar, escuchar, describir y analizar las cualidades visuales, táctiles, sonoras y " +
                        "kinestésicas de diversas manifestaciones artístico-culturales."));
        capacidades.add(new Capacidad("Contextualiza las manifestaciones culturales",
                "Es informarse acerca de la cultura en que " +
                        "se origina una manifestación artística para entender cómo el contexto social, cultural e " +
                        "histórico de esta influye en su creación y la manera en que transmite sus significados"));
        capacidades.add(new Capacidad("Reflexiona creativa y críticamente",
                "Supone interpretar las intenciones y significados de " +
                        "manifestaciones artístico-culturales que hayan visto o experimentado y emitir juicios de " +
                        "valor, entrelazando información obtenida a través de la percepción, el análisis y la " +
                        "comprensión de los contextos."));
        comp_30.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_30);

        capacidades.clear();

        Competencia comp_31 = competenciaRepository.findByCode(31);
        capacidades.add(new Capacidad("Explora y experimenta los lenguajes del arte",
                "Significa experimentar, improvisar y desarrollar " +
                        "habilidades en el uso de los medios, materiales, herramientas y técnicas de los diversos " +
                        "lenguajes del arte."));
        capacidades.add(new Capacidad("Aplica procesos creativos",
                "Supone generar ideas, investigar, tomar decisiones y poner en " +
                        "práctica sus conocimientos para elaborar un proyecto artístico individual o colaborativo en " +
                        "relación a una intención específica"));
        capacidades.add(new Capacidad("Evalúa y socializa sus procesos y proyectos",
                "Significa registrar sus experiencias, comunicar " +
                        "sus descubrimientos y compartir sus creaciones con otros, para profundizar en ellos y " +
                        "reflexionar sobre sus ideas y experiencias."));
        comp_31.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_31);

        capacidades.clear();

        Competencia comp_32 = competenciaRepository.findByCode(32);
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
        comp_32.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_32);

        capacidades.clear();

        Competencia comp_33 = competenciaRepository.findByCode(33);
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
        comp_33.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_33);

        capacidades.clear();

        Competencia comp_34 = competenciaRepository.findByCode(34);
        capacidades.add(new Capacidad("Determina una alternativa de solución tecnológica",
                "al detectar un problema " +
                        "y propone alternativas de solución creativas basadas en conocimientos " +
                        "científico, tecnológico y prácticas locales, evaluando su pertinencia para " +
                        "seleccionar una de ellas."));
        capacidades.add(new Capacidad("Diseña la alternativa de solución tecnológica",
                "es representar de manera " +
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
        comp_34.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_34);

        capacidades.clear();

        Competencia comp_35 = competenciaRepository.findByCode(35);
        capacidades.add(new Capacidad("Comprende su cuerpo",
                "es decir interioriza su cuerpo en estado estático o en " +
                        "movimiento en relación al espacio, el tiempo, los objetos y demás personas de su " +
                        "entorno, representando mentalmente su cuerpo y desarrollando su identidad."));
        capacidades.add(new Capacidad("Se expresa corporalmente",
                "usa el lenguaje corporal para comunicar emociones, " +
                        "sentimientos y pensamientos. Implica utilizar el tono, los gestos, mímicas, posturas y " +
                        "movimientos para expresarse, desarrollando la creatividad al usar todos los recursos " +
                        "que ofrece el cuerpo y el movimiento."));
        comp_35.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_35);

        capacidades.clear();

        Competencia comp_36 = competenciaRepository.findByCode(36);
        capacidades.add(new Capacidad("Comprende las relaciones entre la actividad física, alimentación, postura e higiene corporal y la salud",
                "Es analizar y comprender los procesos vinculados con la " +
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
        comp_36.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_36);

        capacidades.clear();

        Competencia comp_37 = competenciaRepository.findByCode(37);
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
        comp_37.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_37);

        capacidades.clear();

        /**
         * Competencias 38 y 39 son de Religión no hay capacidades.
         */

        Competencia comp_40 = competenciaRepository.findByCode(40);
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
        comp_40.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_40);

        capacidades.clear();

        Competencia comp_41 = competenciaRepository.findByCode(41);
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
        comp_41.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_41);

        capacidades.clear();

        Competencia comp_42 = competenciaRepository.findByCode(42);
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
        comp_42.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_42);

        capacidades.clear();

        Competencia comp_43 = competenciaRepository.findByCode(43);
        capacidades.add(new Capacidad( "Traduce cantidades a expresiones numéricas",
                "es transformar las relaciones entre " +
                        "datos y condiciones de un problema a una expresión numérica (modelo) que " +
                        "reproduzca las relaciones entre estos; esta expresión se comporta como un sistema " +
                        "compuesto por números, operaciones y sus propiedades. Es plantear problemas a " +
                        "partir de una situación o una expresión numérica dada. También implica evaluar " +
                        "si el resultado obtenido o la expresión numérica formulada (modelo), cumplen las " +
                        "condiciones iniciales del problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre los números y las operaciones",
                "Es expresa la comprensión de los conceptos numéricos, las operaciones y propiedades, las unidades " +
                        "de medida, las relaciones que establece entre ellos; usando lenguaje numérico " +
                        "y diversas representaciones; así como leer sus representaciones e información con " +
                        "contenido numérico."));
        capacidades.add(new Capacidad( "Usa estrategias y procedimientos de estimación y cálculo",
                "Es seleccionar, adaptar, combinar o crear una variedad de estrategias, procedimientos como el cálculo mental " +
                        "y escrito, la estimación, la aproximación y medición, comparar cantidades; y emplear " +
                        "diversos recursos."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre las relaciones numéricas y las operaciones",
                "Es elaborar afirmaciones sobre las posibles relaciones entre números naturales, " +
                        "enteros, racionales, reales, sus operaciones y propiedades; basado en comparaciones " +
                        "y experiencias en las que induce propiedades a partir de casos particulares; así " +
                        "como explicarlas con analogías, justificarlas, validarlas o refutarlas con ejemplos y " +
                        "contraejemplos"));
        comp_43.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_43);

        capacidades.clear();

        Competencia comp_44 = competenciaRepository.findByCode(44);
        capacidades.add(new Capacidad("Traduce datos y condiciones a expresiones algebraicas y gráficas",
                "Significa transformar los datos, valores desconocidos, variables y relaciones de un problema a una expresión gráfica " +
                        "o algebraica (modelo) que generalice la interacción entre estos. Implica también evaluar el " +
                        "resultado o la expresión formulada con respecto a las condiciones de la situación; y formular " +
                        "preguntas o problemas a partir de una situación o una expresión."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las relaciones algebraicas",
                "Significa expresar su comprensión de la noción, concepto o propiedades de los patrones, funciones, ecuaciones " +
                        "e inecuaciones estableciendo relaciones entre estas; usando lenguaje algebraico y diversas " +
                        "representaciones. Así como interpretar información que presente contenido algebraico."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para encontrar equivalencias y reglas generales",
                "Es seleccionar, adaptar, combinar o crear, procedimientos, estrategias y algunas propiedades " +
                        "para simplificar o transformar ecuaciones, inecuaciones y expresiones simbólicas que le " +
                        "permitan resolver ecuaciones, determinar dominios y rangos, representar rectas, parábolas, y " +
                        "diversas funciones."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones de cambio y equivalencia",
                "Significa elaborar afirmaciones sobre variables, reglas algebraicas y propiedades algebraicas, razonando de " +
                        "manera inductiva para generalizar una regla y de manera deductiva probando y comprobando " +
                        "propiedades y nuevas relaciones."));
        comp_44.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_44);

        capacidades.clear();

        Competencia comp_45 = competenciaRepository.findByCode(45);
        capacidades.add(new Capacidad("Modela objetos con formas geométricas y sus transformaciones",
                "Es contruir un modelo que reproduzca las características de los objetos, su localización y movimiento, mediante " +
                        "formas geométricas, sus elementos y propiedades; la ubicación y transformaciones en el " +
                        "plano. Es también evaluar si el modelo cumple con las condiciones dadas en el problema."));
        capacidades.add(new Capacidad("Comunica su comprensión sobre las formas y relaciones geométricas",
                "Es comunicar su comprensión de las propiedades de las formas geométricas, sus transformaciones y la " +
                        "ubicación en un sistema de referencia; es también establecer relaciones entre estas formas, " +
                        "usando lenguaje geométrico y representaciones gráficas o simbólicas."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para medir y orientarse en el espacio",
                "Es seleccionar, adaptar, combinar o crear, una variedad de estrategias, procedimientos y recursos para " +
                        "construir formas geométricas, trazar rutas, medir o estimar distancias y superficies, y " +
                        "transformar las formas bidimensionales y tridimensionales."));
        capacidades.add(new Capacidad("Argumenta afirmaciones sobre relaciones geométricas",
                "Es elaborar afirmaciones sobre las posibles relaciones entre los elementos y las propiedades de las formas geométricas a partir " +
                        "de su exploración o visualización. Asimismo, justificarlas, validarlas o refutarlas, basado en " +
                        "su experiencia, ejemplos o contraejemplos, y conocimientos sobre propiedades geométricas; " +
                        "usando el razonamiento inductivo o deductivo."));
        comp_45.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_45);

        capacidades.clear();

        Competencia comp_46 = competenciaRepository.findByCode(46);
        capacidades.add(new Capacidad("Representa datos con gráficos y medidas estadísticas o probabilísticas",
                "Es representar el comportamiento de un conjunto de datos, seleccionando tablas o gráficos estadísticos, " +
                        "medidas de tendencia central, de localización o dispersión. Reconocer variables de la población " +
                        "o la muestra al plantear un tema de estudio. Así también implica el análisis de situaciones " +
                        "aleatorias y representar la ocurrencia de sucesos mediante el valor de la probabilidad."));
        capacidades.add(new Capacidad("Comunica su comprensión de los conceptos estadísticos y probabilísticos",
                "Es comunicar su comprensión de conceptos estadísticos y probabilísticos en relación a la situación. Leer, " +
                        "describir e interpretar información estadística contenida en gráficos o tablas provenientes de " +
                        "diferentes fuentes."));
        capacidades.add(new Capacidad("Usa estrategias y procedimientos para recopilar y procesar datos",
                "Es seleccionar, adaptar, combinar o crear una variedad de procedimientos, estrategias y recursos para recopilar, " +
                        "procesar y analizar datos, así como el uso de técnicas de muestreo y el cálculo de las medidas " +
                        "estadísticas y probabilísticas."));
        capacidades.add(new Capacidad("Sustenta conclusiones o decisiones con base en la información obtenida",
                "Es tomar decisiones, hacer predicciones o elaborar conclusiones y sustentarlas con base en la " +
                        "información obtenida del procesamiento y análisis de datos, así como de la revisión o " +
                        "valoración de los procesos."));
        comp_46.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_46);

        capacidades.clear();

        Competencia comp_47 = competenciaRepository.findByCode(47);
        capacidades.add(new Capacidad("Obtiene información del texto oral",
                "el estudiante recupera y extrae información " +
                        "explícita expresada por los interlocutores"));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto oral",
                "el estudiante construye el sentido del " +
                        "texto. Para ello, infiere estableciendo diversas relaciones entre la información explícita " +
                        "e implícita con el fin de deducir nueva información y completar los vacíos del texto " +
                        "oral. A partir de estas inferencias, el estudiante interpreta integrando la información " +
                        "explícita e implícita, los recursos verbales, no verbales y paraverbales para construir " +
                        "el sentido global y profundo del texto oral, y explicar el propósito, el uso estético del " +
                        "lenguaje, las intenciones e ideologías de los interlocutores, así como su relación con el " +
                        "contexto sociocultural."));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla las ideas de forma coherente y cohesionada",
                "el estudiante expresa sus ideas adaptándose al propósito, destinatario, características " +
                        "del tipo de texto, género discursivo y registro, considerando las normas y modos " +
                        "de cortesía, así como los contextos socioculturales que enmarcan la comunicación. " +
                        "Asimismo, expresa las ideas en torno a un tema de forma lógica, relacionándolas " +
                        "mediante diversos recursos cohesivos para construir el sentido de distintos tipos de " +
                        "textos y géneros discursivos."));
        capacidades.add(new Capacidad("Utiliza recursos no verbales y paraverbales de forma estratégica",
                "el estudiante emplea variados recursos no verbales (como gestos o movimientos corporales) o " +
                        "paraverbales (como el tono de la voz o silencios) según la situación comunicativa para " +
                        "enfatizar o matizar significados y producir determinados efectos en los interlocutores"));
        comp_47.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_47);

        capacidades.clear();

        Competencia comp_48 = competenciaRepository.findByCode(48);
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "el estudiante localiza y selecciona información " +
                        "en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto",
                "el estudiante construye el sentido del texto. " +
                        "ello, infiere estableciendo diversas relaciones entre la información explícita e implícita " +
                        "con el fin de deducir nueva información y completar los vacíos del texto. A partir de estas " +
                        "inferencias, el estudiante interpreta integrando la información explícita e implícita, así como " +
                        "los recursos textuales, para construir el sentido global y profundo del texto, y explicar el " +
                        "propósito, el uso estético del lenguaje, las intenciones del autor, las ideologías de los textos así " +
                        "como su relación con el contexto sociocultural del lector y del texto."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto",
                "los procesos de reflexión " +
                        "evaluación están relacionados porque ambos suponen que el estudiante se distancie de los " +
                        "textos escritos situados en épocas y lugares distintos, y que son presentados en diferentes " +
                        "soportes y formatos. Reflexionar implica comparar y contrastar aspectos formales y de " +
                        "contenido del texto con la experiencia, el conocimiento formal del lector y diversas fuentes de " +
                        "información. Evaluar implica analizar y valorar los textos escritos para construir una opinión " +
                        "personal o un juicio crítico sobre aspectos formales, estéticos, contenidos e ideologías de " +
                        "los textos considerando los efectos que producen, la relación con otros textos, y el contexto " +
                        "sociocultural del texto y del lector."));
        comp_48.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_48);

        capacidades.clear();

        Competencia comp_49 = competenciaRepository.findByCode(49);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "el estudiante considera el propósito, " +
                        "tipo de texto, género discursivo y registro que utilizará al escribir los textos, así " +
                        "como los contextos socioculturales que enmarcan la comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "el estudiante ordena " +
                        "lógicamente las ideas en torno a un tema, ampliándolas y complementándolas, estableciendo " +
                        "relaciones de cohesión entre ellas y utilizando un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente",
                "el estudiante usa de forma " +
                        " apropiada " +
                        "recursos textuales, gramaticales y ortográficos para garantizar la claridad, el uso " +
                        "estético del lenguaje y el sentido del texto escrito."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y contexto del texto escrito",
                "el estudiante " +
                        "se distancia del texto que ha escrito para revisar de manera permanente el contenido, la " +
                        "coherencia, cohesión y adecuación a la situación comunicativa con la finalidad de mejorarlo. " +
                        "También implica analizar, comparar y contrastar las características de los usos del lenguaje " +
                        "escrito y sus posibilidades, así como su repercusión en otras personas o su relación con otros " +
                        "textos según el contexto sociocultural."));
        comp_49.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_49);

        capacidades.clear();

        Competencia comp_50 = competenciaRepository.findByCode(50);
        capacidades.add(new Capacidad("Obtiene información de textos orales",
                "consiste en recuperar y extraer información explícita expresada por los interlocutores."));
        capacidades.add(new Capacidad("Infiere e interpreta información de textos orales",
                "el estudiante construye el sentido " +
                        "del texto a partir de relacionar información explícita e implícita para deducir una " +
                        "nueva información o completar los vacíos del texto oral. A partir de estas inferencias, " +
                        "el estudiante interpreta el sentido del texto, los recursos verbales, no verbales y gestos, " +
                        "el uso estético del lenguaje y las intenciones de los interlocutores con los que se " +
                        "relaciona en un contexto sociocultural determinado"));
        capacidades.add(new Capacidad("Adecúa, organiza y desarrolla las ideas de forma coherente y cohesionada",
                "Consiste en desarrollar ideas adecuándolas al propósito, destinatario, características del tipo de " +
                        "texto, registro y contexto, considerando las normas y modos de cortesía. Organiza la " +
                        "información en torno a un tema y usa diversos recursos cohesivos para relacionar las " +
                        "ideas del texto oral"));
        comp_50.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_50);

        capacidades.clear();

        Competencia comp_51 = competenciaRepository.findByCode(51);
        capacidades.add(new Capacidad("Obtiene información del texto escrito",
                "El estudiante localiza y selecciona información " +
                        "explícita en textos escritos con un propósito específico."));
        capacidades.add(new Capacidad("Infiere e interpreta información del texto escrito",
                "el estudiante construye el sentido " +
                        "del texto. Para ello, establece relaciones entre la información explícita e implícita de éste " +
                        "para deducir una nueva información o completar los vacíos del texto escrito. A partir de " +
                        "estas deducciones, el estudiante interpreta la relación entre la información implícita y la " +
                        "información explícita, así como los recursos textuales, para construir el sentido global y " +
                        "profundo del texto, y explicar el propósito, el uso estético del lenguaje, las intenciones del " +
                        "autor, así como la relación con el contexto sociocultural del lector y del texto."));
        capacidades.add(new Capacidad("Reflaxiona y evalúa la forma, el contenido y el contexto del texto escrito",
                "Los procesos " +
                        "de reflexión y evaluación están relacionados porque ambos suponen que el estudiante se " +
                        "distancie de los textos escritos situados en épocas y lugares distintos, y que son presentados " +
                        "en diferentes soportes y formatos. Para ello, compara y contrasta aspectos formales y de " +
                        "contenido del texto con la experiencia, el conocimiento formal del lector y diversas fuentes " +
                        "de información. Asimismo, emite una opinión personal sobre aspectos formales, estéticos, " +
                        "contenidos de los textos considerando los efectos que producen, la relación con otros textos, " +
                        "y el contexto sociocultural del texto y del lector"));

        comp_51.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_51);

        capacidades.clear();

        Competencia comp_52 = competenciaRepository.findByCode(52);
        capacidades.add(new Capacidad("Adecúa el texto a la situación comunicativa",
                "el estudiante considera el propósito, " +
                        "destinatario, tipo de texto, género discursivo y registro que utilizará al escribir los textos, así " +
                        "como los contextos socioculturales que enmarcan la comunicación escrita."));
        capacidades.add(new Capacidad("Organiza y desarrolla las ideas de forma coherente y cohesionada",
                "el estudiante ordena " +
                        "lógicamente las ideas en torno a un tema, ampliándolas y complementándolas, estableciendo " +
                        "relaciones de cohesión entre ellas y utilizando un vocabulario pertinente."));
        capacidades.add(new Capacidad("Utiliza convenciones del lenguaje escrito de forma pertinente",
                "el estudiante usa de forma " +
                        "apropiada recursos textuales para garantizar la claridad, el uso estético del lenguaje y el " +
                        "sentido del texto escrito."));
        capacidades.add(new Capacidad("Reflexiona y evalúa la forma, el contenido y el contexto del texto escrito",
                "El estudiante " +
                        "se distancia del texto que ha escrito para revisar de manera permanente el contenido, la " +
                        "coherencia, cohesión y adecuación a la situación comunicativa con la finalidad de mejorarlo. " +
                        "También implica analizar, comparar y contrastar las características de los usos del lenguaje " +
                        "escrito y sus posibilidades, así como su repercusión en otras personas o su relación con otros " +
                        "textos según el contexto sociocultural."));
        comp_52.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_52);

        capacidades.clear();

        Competencia comp_53 = competenciaRepository.findByCode(53);
        capacidades.add(new Capacidad("Percibe manifestaciones artístico-culturales",
                "Consiste en usar los sentidos para " +
                        "observar, escuchar, describir y analizar las cualidades visuales, táctiles, sonoras y kinestésicas " +
                        "de diversas manifestaciones artístico-culturales."));
        capacidades.add(new Capacidad("Contextualiza manifestaciones artístico-culturales",
                "Es informarse acerca de la cultura " +
                        "en que se origina una manifestación artística para entender cómo el contexto social, cultural " +
                        "e histórico de esta influye en su creación y la manera en que transmite sus significados."));
        capacidades.add(new Capacidad("Reflexiona creativa y críticamente sobre manifestaciones artístico-culturales",
                "Supone interpretar las intenciones y significados de manifestaciones artístico-culturales " +
                        "que hayan visto o experimentado y emitir juicios de valor, entrelazando información " +
                        "obtenida a través de la percepción, el análisis y la comprensión de los contextos."));
        comp_53.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_53);

        capacidades.clear();

        Competencia comp_54 = competenciaRepository.findByCode(54);
        capacidades.add(new Capacidad("Explora y experimenta los lenguajes artísticos",
                "significa experimentar, improvisar y " +
                        "desarrollar habilidades en el uso de los medios, materiales, herramientas y técnicas de los " +
                        "diversos lenguajes del arte."));
        capacidades.add(new Capacidad("Aplica procesos creativos",
                "supone generar ideas, investigar, tomar decisiones y poner en " +
                        "práctica sus conocimientos para elaborar un proyecto artístico individual o colaborativo en " +
                        "relación a una intención específica."));
        capacidades.add(new Capacidad("Evalúa y comunica sus procesos y proyectos",
                "significa registrar sus experiencias, " +
                        "comunicar sus descubrimientos y compartir sus creaciones con otros, para profundizar en " +
                        "ellos y reflexionar sobre sus ideas y experiencias."));
        comp_54.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_54);

        capacidades.clear();

        Competencia comp_55 = competenciaRepository.findByCode(55);
        capacidades.add(new Capacidad("Interpreta críticamente fuentes diversas",
                "es reconocer la diversidad de fuentes y su " +
                        "diferente utilidad para abordar un hecho o proceso histórico. Supone ubicarlas en su " +
                        "contexto y comprender, de manera crítica, que estas reflejan una perspectiva particular y " +
                        "tienen diferentes grados de fiabilidad. También implica recurrir a múltiples fuentes."));
        capacidades.add(new Capacidad("Comprende el tiempo histórico",
                "es usar las nociones relativas al tiempo de manera " +
                        "pertinente, reconociendo que los sistemas de medición temporal son convenciones que " +
                        "dependen de distintas tradiciones culturales y que el tiempo histórico tiene diferentes " +
                        "duraciones. Asimismo, implica ordenar los hechos y procesos históricos cronológicamente " +
                        "y explicar los cambios, permanencias y simultaneidades que se dan en ellos."));
        capacidades.add(new Capacidad("Elabora explicaciones sobre procesos históricos",
                "es jerarquizar las causas de los " +
                        "procesos históricos relacionando las motivaciones de sus protagonistas con su cosmovisión " +
                        "y la época en la que vivieron. También es establecer las múltiples consecuencias de los " +
                        "procesos del pasado y sus implicancias en el presente, así como reconocer que este va " +
                        "construyendo nuestro futuro."));
        comp_55.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_55);

        capacidades.clear();

        Competencia comp_56 = competenciaRepository.findByCode(56);
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
        comp_56.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_56);

        capacidades.clear();

        Competencia comp_57 = competenciaRepository.findByCode(57);
        capacidades.add(new Capacidad("Comprende las relaciones entre los elementos del sistema económico y financiero",
                "Supone identificar los " +
                        "roles de los diversos agentes que intervienen en el sistema, analizar las interacciones entre " +
                        "ellos y comprender el rol del Estado en dichas interrelaciones."));
        capacidades.add(new Capacidad("Toma decisiones económicas y financieras",
                "Supone planificar el uso de sus recursos " +
                        "económicos de manera sostenible, en función a sus necesidades y posibilidades. También " +
                        "implica asumir una posición crítica frente a los sistemas de producción y de consumo, así " +
                        "como ejercer sus derechos y responsabilidades como consumidor informado."));
        comp_57.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_57);

        capacidades.clear();

        Competencia comp_58 = competenciaRepository.findByCode(58);
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
        comp_58.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_58);

        capacidades.clear();

        Competencia comp_59 = competenciaRepository.findByCode(59);
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
        comp_59.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_59);

        capacidades.clear();

        Competencia comp_60 = competenciaRepository.findByCode(60);
        capacidades.add(new Capacidad("Comprende su cuerpo",
                "es decir interioriza su cuerpo en estado estático o en " +
                        "movimiento en relación al espacio, el tiempo, los objetos y demás personas de su " +
                        "entorno, representando mentalmente su cuerpo y desarrollando su identidad."));
        capacidades.add(new Capacidad("Se expresa corporalmente",
                "usa el lenguaje corporal para comunicar emociones, " +
                        "sentimientos y pensamientos. Implica utilizar el tono, los gestos, mímicas, posturas y " +
                        "movimientos para expresarse, desarrollando la creatividad al usar todos los recursos " +
                        "que ofrece el cuerpo y el movimiento."));
        comp_60.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_60);

        capacidades.clear();

        Competencia comp_61 = competenciaRepository.findByCode(61);
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
        comp_61.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_61);

        capacidades.clear();

        Competencia comp_62 = competenciaRepository.findByCode(62);
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
        comp_62.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_62);

        capacidades.clear();

        Competencia comp_63 = competenciaRepository.findByCode(63);
        capacidades.add(new Capacidad("Conoce a Dios y asume su identidad religiosa como persona digna, libre y trascendente",
                "El estudiante entiende y experimenta que Dios es Amor, comprende que Dios es su Padre y " +
                        "creador, que lo ama y le ha dado la vida para ser feliz. "));
        capacidades.add(new Capacidad("Cultiva y valora las manifestaciones religiosas de su entorno argumentando su fe de manera comprensible y respetuosa",
                "El estudiante comprende el mensaje cristiano en " +
                        "relación con los problemas existenciales comunes a las religiones y característicos de todo " +
                        "ser humano, con las concepciones de la vida presentes en la cultura, y con los problemas " +
                        "morales fundamentales en los que hoy se ve envuelta la humanidad. También expresa con " +
                        "libertad su fe respetando las diversas creencias y expresiones religiosas de los demás."));
        comp_63.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_63);

        capacidades.clear();

        Competencia comp_64 = competenciaRepository.findByCode(64);
        capacidades.add(new Capacidad("Transforma su entorno desde el encuentro personal y comunitario con Dios y desde la fe que profesa",
                "Los estudiantes deben asumir con renovado entusiasmo y decisión, el reto de " +
                        "contribuir a la gestación de una nueva sociedad, más justa, más solidaria, más fraterna y " +
                        "más cristiana, de acuerdo con los valores de la civilización del amor."));
        capacidades.add(new Capacidad("Actúa coherentemente en razón de su fe según los principios de su conciencia moral en situaciones concretas de la vida",
                "Los estudiantes deben actuar según los principios de la " +
                        "conciencia moral cristiana: verdad, bondad y misericordia en situaciones concretas de la " +
                        "convivencia humana. Toman decisiones razonables en coherencia con los principios " +
                        "evangélicos y su escala de valores morales."));
        comp_64.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_64);

        capacidades.clear();

        Competencia comp_65 = competenciaRepository.findByCode(65);
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
        comp_65.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_65);

        capacidades.clear();

        Competencia comp_66 = competenciaRepository.findByCode(66);
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
        comp_66.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_66);

        capacidades.clear();

        Competencia comp_67 = competenciaRepository.findByCode(67);
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
        comp_67.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_67);

        capacidades.clear();

        Competencia comp_68 = competenciaRepository.findByCode(68);
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
        comp_68.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_68);

        capacidades.clear();

        Competencia comp_69 = competenciaRepository.findByCode(69);
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
        comp_69.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_69);

        capacidades.clear();

        Competencia comp_70 = competenciaRepository.findByCode(70);
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
        comp_70.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_70);

        capacidades.clear();

        Competencia comp_71 = competenciaRepository.findByCode(71);
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
        comp_71.getCapacidades().addAll(capacidadRepository.saveAll(capacidades));
        competenciaRepository.save(comp_71);

    }

    private void initMatrixDesempeyos() {
        List<Desempenyo> desempenyos = new ArrayList<>();

        Competencia comp_1 = competenciaRepository.findByCode(1);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_1.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_1);

        Competencia comp_2 = competenciaRepository.findByCode(2);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_2.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_2);

        Competencia comp_3 = competenciaRepository.findByCode(3);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_3.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_3);

        Competencia comp_4 = competenciaRepository.findByCode(4);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_4.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_4);

        Competencia comp_5 = competenciaRepository.findByCode(5);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_5.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_5);

        Competencia comp_6 = competenciaRepository.findByCode(6);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_6.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_6);

        Competencia comp_7 = competenciaRepository.findByCode(7);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_7.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_7);

        Competencia comp_8 = competenciaRepository.findByCode(8);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_8.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_8);

        Competencia comp_9 = competenciaRepository.findByCode(9);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_9.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_9);

        Competencia comp_10 = competenciaRepository.findByCode(10);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_10.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_10);

        Competencia comp_11 = competenciaRepository.findByCode(11);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_11.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_11);

        Competencia comp_12 = competenciaRepository.findByCode(12);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_12.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_12);

        Competencia comp_13 = competenciaRepository.findByCode(13);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_13.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_13);

        Competencia comp_14 = competenciaRepository.findByCode(14);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_14.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_14);

        /**
         * ==========================PRIMARIA==========================
         */

        Competencia comp_15 = competenciaRepository.findByCode(15);
        desempenyos.add(new Desempenyo(5,
                "Traduce acciones de juntar, agregar, quitar cantidades, a " +
                "expresiones de adición y sustracción con números " +
                "naturales; al plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(5,
                "Expresa su comprensión del número como ordinal hasta el " +
                        "décimo, como cardinal hasta 50 y de la decena hasta 20, de " +
                        "la comparación de dos cantidades, y de las operaciones de " +
                        "adición y sustracción hasta 20, usando diversas " +
                        "representaciones y lenguaje cotidiano."));
        desempenyos.add(new Desempenyo(5,
                "Emplea estrategias heurísticas, estrategias de cálculo " +
                        "mental, como la suma de cifras iguales, el conteo y las " +
                        "descomposiciones del 10; el cálculo escrito (sumas y restas " +
                        "sin canjes); estrategias de comparación como la " +
                        "correspondencia uno a uno; y otros procedimientos. " +
                        "Compara en forma vivencial y concreta, la masa de objetos " +
                        "usando unidades no convencionales, y mide o compara el " +
                        "tiempo usando unidades convencionales y (días de la " +
                        "semana, meses del año) y referentes de actividades " +
                        "cotidianas."));
        desempenyos.add(new Desempenyo(5,
                "Explica las equivalencias de un número con ejemplos " +
                        "concretos y menciona los pasos que siguió en la resolución " +
                        "de un problema."));
        desempenyos.add(new Desempenyo(6,
                "Traduce una o dos acciones de separar, agregar, quitar, " +
                        "comparar e igualar cantidades, identificadas en problemas, " +
                        "a expresiones de sustracción y adición con números " +
                        "naturales; al plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(6,
                "Expresa su comprensión del número como ordinal (hasta el " +
                        "vigésimo), de la decena como grupo de diez, como unidad " +
                        "superior, del valor posicional en números de hasta dos " +
                        "cifras y sus equivalencias; de la comparación de dos " +
                        "cantidades, del significado de las operaciones de adición y " +
                        "sustracción así como del doble y la mitad; usando diversas " +
                        "representaciones y lenguaje cotidiano."));
        desempenyos.add(new Desempenyo(6,
                "Emplea estrategias heurísticas, estrategias de cálculo " +
                        "mental como descomposiciones aditivas o el uso de " +
                        "decenas completas (70 + 20; 70 + 9), el cálculo escrito " +
                        "(sumas o restas con y sin canjes); estrategias de " +
                        "comparación y otros procedimientos. Compara en forma " +
                        "vivencial y concreta, la masa de objetos usando unidades " +
                        "no convencionales, y mide o compara el tiempo usando " +
                        "unidades convencionales (días, horarios semanales) y " +
                        "referentes de actividades cotidianas."));
        desempenyos.add(new Desempenyo(6,
                "Explica las equivalencias de un número de dos cifras en " +
                        "decenas y unidades, y por qué debe sumar o restar en un " +
                        "problema, con ejemplos concretos; así como su proceso de " +
                        "resolución."));
        desempenyos.add(new Desempenyo(7,
                "Traduce una o más acciones de agregar, quitar, igualar, " +
                        "repetir cantidades, combinar colecciones identificadas " +
                        "en problemas; a expresiones de adición, sustracción, " +
                        "multiplicación y división, con números naturales; al " +
                        "plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(7,
                "Expresa su comprensión de la centena como unidad " +
                        "superior, del valor de posición de un dígito en números " +
                        "de tres cifras y los representa mediante equivalencias, " +
                        "de la comparación de cantidades; de los números pares " +
                        "e impares; así como de la propiedad conmutativa de la " +
                        "adición, del significado de la multiplicación y división, y " +
                        "de la relación inversa entre operaciones. Para esto usa " +
                        "diversas representaciones y lenguaje matemático."));
        desempenyos.add(new Desempenyo(7,
                "Emplea estrategias heurísticas, estrategias de cálculo " +
                        "mental " +
                        "como: " +
                        "descomposiciones aditivas y " +
                        "multiplicativas, multiplicación por 10, completar " +
                        "decenas o centenas y redondeos; así como el cálculo " +
                        "escrito y otros procedimientos. Mide la masa y el " +
                        "tiempo, usando unidades convencionales y no " +
                        "convencionales (kilogramo – horas exactas."));
        desempenyos.add(new Desempenyo(7,
                "Realiza afirmaciones sobre operaciones inversas con " +
                        "números naturales y las relaciones que observa entre " +
                        "expresiones numéricas (Por ejemplo: 200 U = 20D = 2 C) " +
                        "y entre las operaciones, las prueba con material " +
                        "concreto. Explica su proceso de resolución."));
        desempenyos.add(new Desempenyo(8,
                "Traduce una o más acciones de agregar, quitar, igualar, repetir o " +
                        "repartir cantidades, combinar colecciones; así como de partir y " +
                        "repartir una unidad en partes iguales, identificadas en " +
                        "problemas; a expresiones de adición, sustracción, multiplicación " +
                        "y división, con números naturales y expresiones de adición y " +
                        "sustracción, con fracciones usuales 39 ; al plantear y resolver " +
                        "problemas."));
        desempenyos.add(new Desempenyo(8,
                "Expresa su comprensión del valor de posición de un dígito en " +
                        "números de hasta cuatro cifras y los representa mediante " +
                        "equivalencias; expresa mediante representaciones, la " +
                        "comprensión de las nociones de multiplicación, sus propiedades " +
                        "conmutativa y asociativa, y las nociones de la división (como " +
                        "reparto y agrupación), Representa de diversas formas su " +
                        "comprensión de la noción de fracción como parte de la unidad y " +
                        "las equivalencias entre fracciones usuales. Para esto usa lenguaje " +
                        "numérico."));
        desempenyos.add(new Desempenyo(8,
                "Emplea estrategias heurísticas, estrategias de cálculo mental " +
                        "como el uso de las propiedades de las operaciones, " +
                        "descomposiciones aditivas y multiplicativas, completar centenas, " +
                        "el redondeo a múltiplos de 10, equivalencias entre fracciones, así " +
                        "como el cálculo escrito y otros procedimientos. Mide de manera " +
                        "exacta o aproximada la masa y el tiempo, seleccionando " +
                        "unidades convencionales (kilogramo, gramo, año, hora, media " +
                        "hora y cuarto de hora)."));
        desempenyos.add(new Desempenyo(8,
                "Realiza afirmaciones sobre operaciones inversas con números " +
                        "naturales, y sobre relaciones entre naturales y fracciones; las " +
                        "justifica en base a ejemplos concretos y sus conocimientos " +
                        "matemáticos. Así también, justifica sus procesos de resolución."));
        desempenyos.add(new Desempenyo(9,
                "Traduce una o más acciones de comparar, igualar, repetir y " +
                        "repartir cantidades y de dividir una cantidad discreta en partes " +
                        "iguales; a expresiones aditivas y multiplicativas con números " +
                        "naturales y expresiones aditivas con fracciones y números " +
                        "decimales; al plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(9,
                "Expresa su comprensión del valor posicional en números hasta " +
                        "seis cifras, los múltiplos, las operaciones y sus propiedades " +
                        "(distributiva), así como de los decimales (hasta el centésimo) " +
                        "y de las operaciones de adición y sustracción de decimales o " +
                        "fracciones. Para esto usa diversas representaciones y lenguaje " +
                        "matemático."));
        desempenyos.add(new Desempenyo(9,
                "Emplea estrategias heurísticas, de cálculo mental y escrito: " +
                        "exacto o aproximado y procedimientos, para realizar " +
                        "operaciones con fracciones, números naturales y decimales " +
                        "exactos. Selecciona y usa unidades convencionales " +
                        "(expresadas con naturales, fracciones y decimales) para medir " +
                        "la masa y el tiempo; y hacer conversiones."));
        desempenyos.add(new Desempenyo(9,
                "Realiza afirmaciones sobre las relaciones entre números " +
                        "naturales, decimales, fracciones; así como relaciones entre " +
                        "operaciones y propiedades. Las justifica con varios ejemplos. " +
                        "Así también, justifica su proceso de resolución."));
        desempenyos.add(new Desempenyo(10,
                "Traduce una o más acciones de comparar, igualar, repetir, " +
                        "repartir cantidades, dividir una cantidad en partes iguales, " +
                        "a expresiones aditivas, multiplicativas y a potencias " +
                        "cuadrada y cúbica con números naturales; así como a " +
                        "operaciones de adición, sustracción y multiplicación de " +
                        "fracciones y decimales (hasta el centésimo); al plantear y " +
                        "resolver problemas."));
        desempenyos.add(new Desempenyo(10,
                "Expresa su comprensión del sistema de numeración " +
                        "decimal con números naturales hasta seis cifras, de " +
                        "divisores y múltiplos, primos y compuestos, así como del " +
                        "valor posicional en números decimales hasta los " +
                        "centésimos; con lenguaje numérico y representaciones " +
                        "diversas. Representa de diversas formas su comprensión " +
                        "de la noción de fracción como operador y como cociente, " +
                        "así como las equivalencias entre decimales, fracciones o " +
                        "porcentajes usuales."));
        desempenyos.add(new Desempenyo(10,
                "Selecciona y emplea estrategias heurísticas, estrategias " +
                        "de cálculo aproximado y exacto, mental o escrito y otros " +
                        "procedimientos, para realizar operaciones con fracciones, " +
                        "números naturales y decimales exactos, así como para " +
                        "calcular porcentajes. Mide la masa, el tiempo y la " +
                        "temperatura, de manera exacta o aproximada, " +
                        "seleccionando y usando la unidad de medida que " +
                        "conviene en una situación y emplea estrategias de cálculo " +
                        "para convertir medidas expresadas con naturales y " +
                        "decimales."));
        desempenyos.add(new Desempenyo(10,
                "Justifica con varios ejemplos y sus conocimientos " +
                        "matemáticos, sus afirmaciones sobre la relaciones entre " +
                        "las cuatro operaciones y sus propiedades. Así también, " +
                        "justifica su proceso de resolución."));
        comp_15.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_15);

        Competencia comp_16 = competenciaRepository.findByCode(16);
        desempenyos.add(new Desempenyo(5,
                "Traduce problemas de equivalencias entre dos\n" +
                        "grupos de hasta 10 objetos, regularidades con\n" +
                        "objetos, colores, diseños, sonidos o movimientos,\n" +
                        "con cantidades que aumentan de forma regular; a\n" +
                        "igualdades que contienen adiciones, a patrones de\n" +
                        "repetición o a patrones aditivos; al plantear y\n" +
                        "resolver problemas. Por ejemplo: Representa con\n" +
                        "una igualdad lo que observa en la balanza (2 + 5 =\n" +
                        "3 + 4), en un platillo hay 2 pelotas rojas y 5 pelotas\n" +
                        "azules (del mismo tamaño) y en el otro platillo hay\n" +
                        "3 pelotas amarillas y 4 pelotas rojas."));
        desempenyos.add(new Desempenyo(5,
                "Expresa cómo continúa el patrón de repetición (de\n" +
                        "un criterio perceptual) y el patrón aditivo creciente\n" +
                        "hasta el 20 (de 1 en 1 y 2 en 2); expresa también\n" +
                        "su comprensión de la equivalencia. Para esto, usa\n" +
                        "lenguaje cotidiano y diversas representaciones.\n" +
                        "Por ejemplo: En una balanza de platillos, se\n" +
                        "colocan 5 cubos en el lado izquierdo y 8 cubos en el\n" +
                        "lado derecho. ¿Cuántos cubos hay que poner del\n" +
                        "lado izquierdo para lograr el equilibrio de ambos\n" +
                        "lados?"));
        desempenyos.add(new Desempenyo(5,
                "Emplea estrategias heurísticas, estrategias de\n" +
                        "cálculo como el conteo y la descomposición aditiva\n" +
                        "para encontrar equivalencias o crear, continuar y\n" +
                        "completar patrones."));
        desempenyos.add(new Desempenyo(5,
                "Explica cómo continúa el patrón y lo que debe\n" +
                        "hacer para encontrar una equivalencia, así como,\n" +
                        "su proceso de resolución. Por ejemplo: En una\n" +
                        "balanza de platillos, se colocan 5 cubos en el lado\n" +
                        "izquierdo y 8 cubos en el lado derecho. ¿Cuántos\n" +
                        "cubos hay que poner del lado izquierdo para lograr\n" +
                        "el equilibrio de ambos lados?"));
        desempenyos.add(new Desempenyo(6,
                "Traduce equivalencias entre dos grupos de hasta 20\n" +
                        "objetos, regularidades con objetos, diseños, sonidos o\n" +
                        "movimientos que se repiten, o con cantidades que\n" +
                        "aumentan o disminuyen de forma regular; a igualdades\n" +
                        "que contienen adición o sustracción, a patrones de\n" +
                        "repetición o a patrones aditivos; al plantear y resolver\n" +
                        "problemas."));
        desempenyos.add(new Desempenyo(6,
                "Expresa cómo continúa el patrón de repetición (con dos\n" +
                        "criterios perceptuales) y cómo aumentan o disminuyen\n" +
                        "los números en un patrón aditivo con números de hasta\n" +
                        "2 cifras; y su comprensión de las equivalencias e\n" +
                        "igualdades; expresa también su comprensión de las\n" +
                        "equivalencias e igualdades. Para esto, usa lenguaje\n" +
                        "cotidiano y diversas representaciones. Por ejemplo: En\n" +
                        "una balanza de platillos, se colocan 5 cubos en el lado\n" +
                        "izquierdo y 8 cubos en el lado derecho. ¿Cuántos cubos\n" +
                        "hay que poner del lado izquierdo para lograr el equilibrio\n" +
                        "de ambos lados?"));
        desempenyos.add(new Desempenyo(6,
                "Emplea estrategias heurísticas y estrategias de cálculo,\n" +
                        "de conteo o la descomposición aditiva, para encontrar\n" +
                        "equivalencias, mantener la igualdad (“equilibrio”) o\n" +
                        "crear, continuar y completar patrones. Por ejemplo: Si tú\n" +
                        "tienes tres frutas y yo cinco, ¿qué podemos hacer para\n" +
                        "que cada uno tenga el mismo número de frutas?"));
        desempenyos.add(new Desempenyo(6,
                "Explica lo que debe hacer para mantener el “equilibrio”\n" +
                        "o la igualdad; cómo continúa el patrón y las semejanzas\n" +
                        "que encuentra en dos versiones del mismo patrón, en\n" +
                        "base a ejemplos concretos. Así también, explica su\n" +
                        "proceso de resolución. Por ejemplo: Dicen “yo sé que 11\n" +
                        "- 6 es 5, así que creo que 12 - 7 será lo mismo”, “yo\n" +
                        "necesitaría dos barras verdes para lograr la misma\n" +
                        "longitud de la barra azul”. Por ejemplo: Dicen: El collar\n" +
                        "lleva dos hojas, tres frutos secos, una concha, una y otra\n" +
                        "vez y los bloques van dos rojos, tres azules y uno blanco,\n" +
                        "una y otra vez; ambos van dos, luego tres, luego uno."));
        desempenyos.add(new Desempenyo(7,
                "Traduce equivalencias, regularidades y cambios de\n" +
                        "una magnitud con respecto al paso del tiempo; a\n" +
                        "igualdades que contienen adiciones, sustracciones o\n" +
                        "multiplicaciones; a tablas o dibujos; o a patrones de\n" +
                        "repetición (con criterios perceptuales o de cambio de\n" +
                        "posición) o a patrones aditivos (con números de\n" +
                        "hasta 3 cifras); al plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(7,
                "Expresa su comprensión de la equivalencia y de la\n" +
                        "igualdad, representa un patrón de distinta manera, y\n" +
                        "describe el cambio de una magnitud con respecto al\n" +
                        "tiempo. Para esto usa lenguaje cotidiano y diversas\n" +
                        "representaciones. Por ejemplo: Representar el mismo\n" +
                        "patrón de diferentes maneras: triángulo, rectángulo,\n" +
                        "triángulo como ABA, ABA, ABA."));
        desempenyos.add(new Desempenyo(7,
                "Emplea estrategias heurísticas y estrategias de\n" +
                        "cálculo como la descomposición aditiva y otras, para\n" +
                        "encontrar equivalencias, mantener la igualdad\n" +
                        "(“equilibrio”), encontrar relaciones de cambio entre\n" +
                        "dos magnitudes o continuar, completar y crear\n" +
                        "patrones."));
        desempenyos.add(new Desempenyo(7,
                "Hace afirmaciones y explica lo que sucede al\n" +
                        "modificar las cantidades que intervienen en una\n" +
                        "relación de igualdad y cómo equiparar dos\n" +
                        "cantidades, lo que debe considerar para continuar o\n" +
                        "completar el patrón, las semejanzas que encuentra\n" +
                        "en dos versiones del mismo patrón usando ejemplos\n" +
                        "concretos. Así también, explica su proceso de\n" +
                        "resolución. Por ejemplo: si quito 2 kilos en este platillo\n" +
                        "de la balanza, se perderá el equilibrio."));
        desempenyos.add(new Desempenyo(8,
                "Traduce\n" +
                        "equivalencias\n" +
                        "(dos\n" +
                        "relacionadas),\n" +
                        "regularidades y el cambio de una magnitud con\n" +
                        "respecto de otra, identificadas en problemas; a\n" +
                        "igualdades que contienen adiciones, sustracciones,\n" +
                        "multiplicaciones o divisiones; a tablas o dibujos; a\n" +
                        "patrones de repetición (que combinan criterios\n" +
                        "perceptuales y un criterio geométrico de simetría) o a\n" +
                        "patrones aditivos y patrones aditivos o multiplicativos\n" +
                        "(con números de hasta cuatro cifras); al plantear y\n" +
                        "resolver problemas."));
        desempenyos.add(new Desempenyo(8,
                "Expresa su comprensión de la regla de formación de\n" +
                        "un patrón, de la igualdad (con un término\n" +
                        "desconocido) y del signo igual, distinguiéndolo de su\n" +
                        "uso en el resultado de una operación; así también\n" +
                        "describe la relación de cambio de una magnitud con\n" +
                        "respecto de otra. Para esto, usa lenguaje algebraico\n" +
                        "(íconos y operaciones) y diversas representaciones."));
        desempenyos.add(new Desempenyo(8,
                "Emplea estrategias heurísticas o estrategias de\n" +
                        "cálculo, para encontrar equivalencias, completar,\n" +
                        "crear o continuar patrones o para encontrar\n" +
                        "relaciones de cambio entre dos magnitudes."));
        desempenyos.add(new Desempenyo(8,
                "Hace afirmaciones sobre la equivalencia entre\n" +
                        "expresiones (propiedades de la igualdad, aditiva y\n" +
                        "multiplicativa) y regularidades en sus variaciones, las\n" +
                        "relaciones de cambio entre magnitudes, así como\n" +
                        "sobre los números o elementos que siguen en un\n" +
                        "patrón, justificándolas con sus experiencias\n" +
                        "concretas. Así también, justifica sus procesos de\n" +
                        "resolución."));
        desempenyos.add(new Desempenyo(9,
                "Traduce datos y valores desconocidos, relaciones\n" +
                        "de equivalencias, el cambio de una magnitud con" +
                        "respecto de otra; a ecuaciones simples (Por " +
                        "ejemplo: x + a = b) con números naturales; a tablas " +
                        "de proporcionalidad o a la regla de formación de " +
                        "un patrón de repetición (que combine un criterio " +
                        "geométrico de simetría o traslación y un criterio " +
                        "perceptual) y de un patrón aditivo (de segundo " +
                        "orden: Por ejemplo: 13 – 15 – 18 – 22 – 27 - ...); al " +
                        "plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(9,
                "Expresa su comprensión del significado de " +
                        "símbolos o letras en la ecuación y de la " +
                        "proporcionalidad como un cambio constante; " +
                        "usando lenguaje algebraico y diversas " +
                        "representaciones."));
        desempenyos.add(new Desempenyo(9,
                "Emplea   estrategias   heurísticas,estrategias   de cálculo  y  propiedades  (por  ejemplo  el  uso  de  las propiedades de la igualdad, aditivas y multiplicativas)   para   encontrar   el   valor   de   la incógnita  en  una  ecuación,  para  hallar  la  regla  de formación  de  un  patrón  o  para  encontrar  valores de magnitudes proporcionales."));
        desempenyos.add(new Desempenyo(9,
                "Elabora   afirmaciones   sobre   los   elementos   no inmediatos  que  continúan  un  patrón.  Las  justifica con  ejemplos,  cálculos  sencillos  y  propiedades  de la   igualdad   o   sus   conocimientos.   Así   también, justifica sus procesos de resolución."));
        desempenyos.add(new Desempenyo(10,
                "Traduce equivalencias y no equivalencias (“desequilibrio”), valores desconocidos, regularidades y el cambio entre dos magnitudes, identificadas en situaciones, a ecuaciones con que  contienen  las  cuatro  operaciones  y  desigualdades;  la proporcionalidad  directa  o  a  patrones  de  repetición  (con criterios geométricos de traslación y giros) patrones (con y sin  configuraciones  puntuales)  cuya  regla  se  asocia  a  la posición   de    sus   elementos      y   patrones   aditivos   o multiplicativos; al plantear y resolver problemas."));
        desempenyos.add(new Desempenyo(10,
                "Expresa su comprensión del término general de un patrón (Por ejemplo: 2, 5, 8, 11, 14,....--> término general = triple de   un   número,   menos   1),   así   como   condiciones   de desigualdad expresadas con los signos > y <,  así como de la relación  proporcional  como  un  cambio  constante;  usando lenguaje algebraico y diversas representaciones."));
        desempenyos.add(new Desempenyo(10,
                "Emplea  estrategias  heurísticas,  estrategias  de  cálculo  y propiedades  de  las  igualdades  para  resolver  ecuaciones  o hallar valores que cumplen una condición de desigualdad o de proporcionalidad; así como para determinar la regla o el término general de un patrón."));
        desempenyos.add(new Desempenyo(10,
                "Elabora afirmaciones  sobre los términos no inmediatos  en un patrón y sobre lo que ocurre cuando modifica cantidades que  intervienen  en  los  miembros  de  una  desigualdad.  Las justifica con ejemplos, cálculos o propiedades de la igualdad o en sus conocimientos. Así también, justifica su proceso de resolución."));
        comp_16.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_16);

        Competencia comp_17 = competenciaRepository.findByCode(17);
        desempenyos.add(new Desempenyo(5,
                "Modela   objetos,   sus   características,   datos   de ubicación y recorridos; identificados en problemas; con  formas  bidimensionales,  tridimensionales,  o con  cuadrículas  en  las  que  ubica  puntos  y  hace trazos de desplazamientos."));
        desempenyos.add(new Desempenyo(5,
                "Describe las formas bidimensionales y tridimensionales mediante sus elementos: lados, líneas rectas y curvas, caras, vértices. También traza y describe desplazamientos y posiciones, en cuadriculados y puntos de referencia. Para esto, usa lenguaje coloquial (si ruedan, se sostienen, no se sostienen tiene puntas, esquinas, etc.), expresiones espaciales (detrás de, encimade, debajo de, detrás de, dentro, fuera, en el borde), su cuerpo como punto de referencia y representaciones concretas o gráficas."));
        desempenyos.add(new Desempenyo(5,
                "Emplea  estrategias  heurísticas  y  procedimientos de   comparación   para   medir   directamente   la longitud    de    dos    objetos    con    unidades    no convencionales (dedos, manos, pies, pasos, brazos y objetos como clips, lápices, palillos, etc.)."));
        desempenyos.add(new Desempenyo(5,
                "Explica  algunas  propiedades  físicas  o  semejanzas de   los   objetos;   y   las   muestra   con   ejemplos concretos. Así también, explica el proceso seguido. Por  ejemplo: “Los objetos con puntas no ruedan”, “Estos dos objetos tienen la misma forma (pelota y canica)”. Por ejemplo: “Los objetos con puntas no ruedan”, “Estos dos objetos tienen la misma forma (pelota y canica)”."));
        desempenyos.add(new Desempenyo(6,
                "Modela    objetos,    sus    características,    datos    de ubicación  y  recorridos,  identificados  en  problemas; con   formas   bidimensionales   y   tridimensionales, considerando   algunos   de   sus   elementos;   o   con cuadrículas en las que ubica puntos y hace trazos de desplazamientos."));
        desempenyos.add(new Desempenyo(6,
                "Describe las formas bidimensionales y tridimensionales mediante sus elementos: número de lados, esquinas, lados curvos y rectos; número de puntas caras, formas de sus caras. También traza y describe desplazamientos y posiciones, en cuadriculados y puntos de referencia. Para esto, usa lenguaje coloquial (tiene puntas, esquinas, etc.), lenguaje direccional(Por ejemplo: “sube”, “entra”, “hacia adelante”, “hacia arriba”, “a la derecha”, y “por el borde”, “en frente de”, etc.), diferentes puntos de referencia y representaciones concretas, gráficas o simbólicas (códigos de flechas)."));
        desempenyos.add(new Desempenyo(6,
                "Emplea  estrategias  y  procedimientos  basados  en  la manipulación,  para  construir  objetos  y  medir  su longitud   (ancho   y   largo)   usando   unidades   no convencionales."));
        desempenyos.add(new Desempenyo(6,
                "Explica  semejanzas  y  diferencias  entre  las  formas geométricas, con ejemplos concretos y con base en sus conocimientos matemáticos. Así mismo, explica el proceso  seguido. Por ejemplo: Afirma que: Todas las figuras que tienen tres lados son triángulos o que una forma geométrica sigue siendo la misma aunque cambie de posición. "));
        desempenyos.add(new Desempenyo(7,
                "Modela características geométricas de los objetos del entorno,   identificados   en   problemas;   conformas bidimensionales y tridimensionales (cuerpos redondos y  compuestos)  y  sus  elementos;  así  como  datos  de ubicación  y  recorridos  de  objetos,  a  cuadrículas  y croquis."));
        desempenyos.add(new Desempenyo(7,
                "Describe  la  comprensión  de  formas  bidimensionales (número   de   lados,   vértices,   eje   de   simetría)   y tridimensionales;  Traza  y  describe  desplazamientos  y posiciones.    Para    esto    usa    lenguaje    geométrico, diferentes puntos de referencia y diversas representaciones concretas o gráficas."));
        desempenyos.add(new Desempenyo(7,
                "Emplea estrategias heurísticas y procedimientos como la   composición   y   descomposición,   el   doblado,   el recorte,  y  diversos  recursos  para  construir  formas  y figuras  simétricas (a partir de instrucciones escritas  u orales).  Así mismo, usa diversas estrategias para medir de manera  exacta o aproximada  (estimar) la  longitud (centímetro,    metro),    contorno    de    una    figura, superficie  (unidades  patrón)  y  capacidad  (unidades arbitrarias)  de  los  objetos;  empleando  la  unidad  de medida,   no   convencional   o   convencional,   según convenga,    así    como    algunos    instrumentos    de medición. "));
        desempenyos.add(new Desempenyo(7,
                "Explica  con  ejemplos  concretos  o  dibujos,  algunas propiedades    de    las    formas,    su    composición    o descomposición;  así  como  el  proceso  seguido(Por ejemplo:  Todos  los  cuadrados  se  pueden  formar  con dos triángulos iguales)."));
        desempenyos.add(new Desempenyo(8,
                "Modela     características     geométricas de los objetos identificados  en  problemas;  con  formas  bidimensionales (polígonos)  y  tridimensionales  (cubos  y  prismas  de  base cuadrangular) y sus elementos. Así como datos de ubicación y  desplazamientos  de  objetos  a  posiciones  a  cuadriculas  y croquis."));
        desempenyos.add(new Desempenyo(8,
                "Describe    la    comprensión    de    cubo, prisma de base cuadrangular y polígono a partir de reconocer elementos, y líneas   paralelas   y   perpendiculares.   Así   mismo   describe posiciones de objetos en el cuadriculado usando puntos de referencia,  los  representa  en  croquis.  También  representa de  diversas  formas,  la  traslación  de  una  figura  plana  en  el plano   cartesiano.   Todo   ello   lo   hace   usando   lenguaje geométrico."));
        desempenyos.add(new Desempenyo(8,
                "Emplea estrategias y procedimientos como la composición y descomposición,  así como  el  uso  de  las  cuadrículas;  para construir   formas   simétricas,   ubicar   objetos   y   trasladar figuras,    usando    recursos.    Así    también,    usa    diversas estrategias  para  medir,  de  manera  exacta  o  aproximada (estimar), la medida de los ángulos respecto al ángulo recto, la  longitud  (perímetro,  metro  y  centímetro),  la  superficie (unidades  patrón)  y  la  capacidad  (litro  y  fracciones)  de  los objetos   y   hace   conversiones   de   unidades   de   longitud. Emplea    la    unidad    de    medida,    convencional    o    no convencional, según convenga, así como algunos instrumentos  de  medición  (cinta  métrica,  regla,  envases  o recipientes)."));
        desempenyos.add(new Desempenyo(8,
                "Elabora    afirmaciones    sobre algunas relaciones entre elementos  de  las  formas,  su  desarrollo  en  el  plano  y  sobre sus atributos medibles. Así mismo explica sus semejanzas ydiferencias con ejemplos concretos o dibujos con base en su exploración o visualización, usando razonamiento inductivo. Así  también,  explica  el  proceso  seguido. Por ejemplo:” Un cubo  se  puede  construir  con  una  plantilla  que  contenga  6 cuadrados del mismo tamaño”. “Podemos medir la superficie de la pizarra midiendo solo el largo y ancho; y multiplicando ambas cantidades”."));
        desempenyos.add(new Desempenyo(9,
                "Modela  características  de  los objetos, datos de ubicación  y  cambios  de  tamaño  de  los  objetos, identificadas en problemas; con formas bidimensionales (cuadriláteros)o tridimensionales (prismas rectos) y sus elementos; así como ampliaciones, reducciones y reflexiones en el plano cartesiano."));
        desempenyos.add(new Desempenyo(9,
                "Describe   la   comprensión   del prisma recto y cuadrilátero  a  partir  de  reconocer  elementos,  y líneas  paralelas  y  perpendiculares.  Así  mismo describe   posiciones   de   objetos   en   el   plano usando  puntos  cardinales  y  de  referencia,  los representa  en  croquis.  También  representa  de diversas    formas,    la    simetría,    ampliaciones, reducciones y reflexiones de una figura plana en el  plano  cartesiano. Todo  ello  lo  hace  usando lenguaje geométrico."));
        desempenyos.add(new Desempenyo(9,
                "Emplea  estrategias  de  cálculo y  procedimientos de composicióny descomposición para construir formas, ángulos, realizar ampliaciones, reducciones,  reflexiones  y  traslaciones  de  las figuras  así  como  para  hacer  trazos  en  el  plano cartesiano.  Para  ello,  usa  diversos  recursos  e instrumentos  de  dibujo.  También,  usa  diversas estrategias   para   medir,   de   manera   exacta   o aproximada  (estimar),  la  medida  de  ángulos,  la longitud    (perímetro,    kilómetro,    metro),    la superficie (unidades patrón), la capacidad (litros y decimales) de los objetos y realiza conversiones de   unidades   de   longitud   haciendo   cálculos numéricos.  Emplea la unidad no convencional o convencional, según convenga, así como algunos instrumentos de medición."));
        desempenyos.add(new Desempenyo(9,
                "Elabora  afirmaciones  sobre  las  relaciones  entre los  elementos  de  las  formas  geométricas,  su desarrollo en el plano y atributos medibles, y las explica  con  argumentos  basados  en  ejemplos concretos,   gráficos   y   en   sus   conocimientos matemáticos   con   base   en   su   exploración   o visualización,   usando   razonamiento   inductivo. Así  también,   explica   el  proceso   seguido. Por ejemplo:  “Dos  rectángulos  pueden  tener diferente área pero el mismo perímetro”, “El área de un triángulo la puedo obtener dividiendo a la mitad el área delcuadrado”. "));
        desempenyos.add(new Desempenyo(10,
                "Modela   características   de   los   objetos, datos de ubicación, cambios de  tamaños    y    movimientos identificados en problemas; con formas bidimensionales (triángulos, cuadriláteros y círculos) o tridimensionales   (prismas   rectos   y   cilindro)   y   sus elementos;   así   como   a   la   rotación   en   el   plano cartesiano."));
        desempenyos.add(new Desempenyo(10,
                "Describe    la    comprensión del prisma, triángulo, cuadrilátero y círculo a partir de reconocer elementos, y   líneas   paralelas   y   perpendiculares.   Así   mismo describe  posiciones  de  objetos  en  el  plano  usando puntos  cardinales  y  de  referencia,  los  representa  en croquis. También representa de diversas formas, giros en  cuartos  y  medias  vueltas,  traslación,  y  dos  o  más ampliaciones   de   una   figura   plana   en   el   plano cartesiano.    Todo    ello    lo    hace    usando    lenguaje geométrico."));
        desempenyos.add(new Desempenyo(10,
                "Emplea  estrategias  heurísticas,  estrategias  de cálculo y  procedimientos  de  composicióny descomposición para  construir  formas  desde  perspectivas,  desarrollo de  sólidos,  realizar  giros  en  el  plano,  así  como  para trazar    recorridos.    Usa    diversas    estrategias    para construir ángulos, medir la longitud (cm), la superficie (m2,  cm2)  o  la  capacidad  de  los  objetos,  de  manera exacta o aproximada. Realiza cálculos numéricos para hacer    conversiones    de    medidas    (unidades    de longitud).     Emplea     la     unidad     de     medida     no convencional  o  convencional,  según  convenga,  así como instrumentos, de dibujo (compás, transportador) y de medición, y diversos recursos."));
        desempenyos.add(new Desempenyo(10,
                "Elabora  afirmaciones  sobre  las  relaciones  entre  los elementos  de  las  formas  geométricas,  propiedades básicas, su desarrollo en el plano y atributos medibles, y  las  explica  con  argumentos  basados  en  ejemplos concretos, gráficos, propiedades y en sus conocimientos     matemáticos     con     base     en     su exploración   o   visualización,   usando   razonamiento inductivo. Así también, explica el proceso seguido.  Por ejemplo: Al duplicar el perímetro de un rectángulo su área se cuadruplica."));
        comp_17.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_17);

        Competencia comp_18 = competenciaRepository.findByCode(18);
        desempenyos.add(new Desempenyo(5,
                "Organiza  datos  cualitativos  (por  ejemplo:  color  de los  ojos:  pardos,  negros;  plato  favorito:  cebiche, arroz  con  pollo,  etc.),  en  situaciones  de  su  interés personal    o    de    sus    pares,    en    pictogramas horizontales  (el  símbolo  representa  una  unidad)  y gráficos de barras verticales simples (sin escala)."));
        desempenyos.add(new Desempenyo(5,
                "Lee la  información  contenida  en  pictogramas  y gráficos   de   barras   simples,   representados   con material concreto o gráfico. "));
        desempenyos.add(new Desempenyo(5,
                "Expresa la ocurrencia de acontecimientos cotidianos, usando nociones de siempre, a veces y nunca."));
        desempenyos.add(new Desempenyo(5,
                "Recolecta  datos  en  listas  o  tablas  de conteo,  con material concreto, realizando preguntas sencillas a sus compañeros."));
        desempenyos.add(new Desempenyo(5,
                "Toma   decisiones   y   las   explica   a   partir   de   la información obtenida en los gráficos o pictogramas."));
        desempenyos.add(new Desempenyo(6,
                "Organiza  datos  cualitativos  (por  ejemplo:  color  de  los ojos: pardos, negros; plato favorito: cebiche, arroz con pollo, etc.), en situacionesde su interés personal o de sus  pares,  en  pictogramas  horizontales  (el  símbolo representa  una  o  dos  unidades)  y  gráficos  de  barras verticales simples (sin escala)."));
        desempenyos.add(new Desempenyo(6,
                "Lee   información   contenida   en   tablas   de   conteo, pictogramas y gráficos de barras simples, identificando el  dato  o  datos  que  obtuvieron  mayor  frecuencia, representados con material concreto y gráfico. "));
        desempenyos.add(new Desempenyo(6,
                "Expresa  la  ocurrencia  de  acontecimientos  cotidianos, usando nociones de posible e imposible."));
        desempenyos.add(new Desempenyo(6,
                "Recolecta  datos  a  través  de  preguntas sencillas,  los registra    en    listas    o    tablas    de    conteo    simple (frecuencias)."));
        desempenyos.add(new Desempenyo(6,
                "Toma decisiones y las explica a partir de la información obtenida en el análisis de datos."));
        desempenyos.add(new Desempenyo(7,
                "Elabora pictogramas verticales y horizontales (el símbolo   representa   más   de   una unidad)   y gráficos de barras horizontales (simples y escala dada  de  2  en  2,  5  en  5  y  10  en  10).  Para  esto clasifica datos cualitativos (por ejemplo: color de ojos: pardos, negros; profesión: médico, abogado,   etc.)y   cuantitativos   discretos   (por ejemplo: número de hermanos: 3, 2; cantidad de goles: 2, 4, 5, etc.), relacionados con un tema de estudio. "));
        desempenyos.add(new Desempenyo(7,
                "Interpreta  información  contenida  en  tablas  de frecuencia simples, gráficos    de    barras    o pictogramas. Expresa la ocurrencia de acontecimientos  cotidianos  usando nociones  de seguro, posible e imposible."));
        desempenyos.add(new Desempenyo(7,
                "Emplea     procedimientos de recolección y organización de datos usando encuestas, entrevistas  sencillas,  tablas  de  frecuencia,  para resolver problemas estadísticos."));
        desempenyos.add(new Desempenyo(7,
                "Toma decisiones y elabora algunas conclusiones a partir de la información obtenida en el análisis de datos. "));
        desempenyos.add(new Desempenyo(8,
                "Elaboratablas   de   frecuencia   simples,   pictogramas verticales y horizontales (cada símbolo representa más de  una  unidad)  gráficos  de  barras  con  escala  dada (múltiplos de 10). Para esto clasifica datos cualitativos (por ejemplo: color de ojos: pardos, negros; profesión: médico,  abogado,  etc.)  y  cuantitativos  discretos  (por ejemplo:  número  de  hermanos:  3,  2;  cantidad  de goles:  2,  4,  5,  etc.)  relacionados  con  un  tema  de estudio y con experimentos aleatorios."));
        desempenyos.add(new Desempenyo(8,
                "Interpreta información contenida en gráficos de barras simples y dobles, tablas de doble entrada y pictogramas, comparando frecuencias y usando el significado de la moda de un conjunto de datos; Expresa la ocurrencia de sucesos cotidianos usando las nociones de seguro, más probable, menos probable."));
        desempenyos.add(new Desempenyo(8,
                "Recolecta  datos con  encuestas  sencillas  y  entrevistas cortas con preguntas adecuadas y las registra en tablas de   frecuencia   simples,   para   resolver   problemas estadísticos."));
        desempenyos.add(new Desempenyo(8,
                "Toma decisiones y elabora algunas conclusiones a partir de la información obtenida en el análisis de datos. "));
        desempenyos.add(new Desempenyo(9,
                "Elabora  tablas  de  doble  entrada  y  gráficos  de barras  dobles,  seleccionando  el  más  adecuado.  Para   esto,   reconoce   variables   (Por   ejemplo: deportes, número de hijos, comidas) y representa datos cualitativos  (Por  ejemplo:  vóley,  tenis)  y cuantitativos discretos (por ejemplo: 3, 4, 5 hijos) que  debe  recoger  o  ha  obtenido  en  situaciones aleatorias o en temas de su interés."));
        desempenyos.add(new Desempenyo(9,
                "Interpreta información contenida en tablas de doble entrada y gráficos de barras dobles, usando el significado de la moda y su representatividad del conjunto de datos. Expresa la ocurrencia de sucesos usando nociones de “más probables” o “menos probables”."));
        desempenyos.add(new Desempenyo(9,
                "Recolecta    datos    con    encuestas    sencillas    y entrevistas cortas con preguntas adecuadas y las registra  en  tablas de  frecuencia  simples,  para resolver problemas estadísticos."));
        desempenyos.add(new Desempenyo(9,
                "Elabora   y   justifica   predicciones,   decisiones   y conclusiones,    basándose    en    la    información obtenida en el análisis de datos."));
        desempenyos.add(new Desempenyo(10,
                "Elabora   tablas   de   frecuencia   de   doble   entrada   y gráficos de líneas seleccionando el gráfico estadístico más    adecuado.        Para    esto,    reconoce    variables cualitativas     (por     ejemplo: color,     material)     y cuantitativas   discretas   (Por   ejemplo:   número   de hermanos)    y    sus    respectivos    modalidades    (Por ejemplo: rojo, amarillo; cuero, plástico) o valores (Por ejemplo: 1, 2, 3) que ha obtenido en un tema e estudio o en situaciones aleatorias."));
        desempenyos.add(new Desempenyo(10,
                "Interpretar  información  contenida  en  gráficos  y  en diversas fuentes de información, usando el significado de  la  moda  y  expresa  la  probabilidad  de  un  evento relacionando el número de casos favorables y el total de casos posibles."));
        desempenyos.add(new Desempenyo(10,
                "Recolecta  datos  a  través  de  encuestas  y  de diversas fuentes  de  información, y  las  registra  en  tablas de frecuencia simples, para resolver problemas estadísticos."));
        desempenyos.add(new Desempenyo(10,
                "Elabora     y     justifica     predicciones,     decisiones     y conclusiones,  basándose  en  la  información  obtenida en  el  análisis  de  datos  o  en  la  probabilidad  de  un evento."));
        comp_18.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_18);

        Competencia comp_19 = competenciaRepository.findByCode(19);
        desempenyos.add(new Desempenyo(5,
                "Expresa  oralmente  sus necesidades,  intereses,  experiencias  y emociones de forma espontánea, adecuando su texto oral a sus interlocutores y contexto de acuerdo al propósito comunicativo y utilizando recursos no verbales y paraverbales. "));
        desempenyos.add(new Desempenyo(5,
                "Desarrolla ideas en torno a un tema, aunque en ocasiones puede salirse    de    este    o    reiterar    información    innecesariamente. Establece  relaciones  lógicas  entre  las  ideas  (en  especial,  de adición)   a   través   de   algunos   conectores,   e   incorpora   un vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(5,
                "Interactúa en diversas situaciones orales, formulando preguntas, dando  respuestas  o  haciendo  comentarios  relacionados  con  el tema, utilizando un vocabulario de uso frecuente y recurriendo a normas y modos de cortesía según el contexto sociocultural."));
        desempenyos.add(new Desempenyo(5,
                "Obtiene  información  explícita  como  el  nombre  de  personas, personajes,  hechos  y  lugares,  en  textos  orales  que  presentan vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(5,
                "Infiere   información   deduciendo   características   de   personas, personajes, animales, objetos, hechos y lugares, el significado de palabras y expresiones  en contexto, así como relaciones lógicas de causa-efecto a partir de información explícita del texto."));
        desempenyos.add(new Desempenyo(5,
                "Interpreta  el  sentido  del  texto  oral  según  modos  culturales diversos,  relacionando  algunos  recursos  verbales,  no  verbales  y paraverbales,   explicando   partes   de   su   contenido   así   como acciones de personas y personajes."));
        desempenyos.add(new Desempenyo(5,
                "Reflexiona  como  hablante  y  oyente  sobre  textos  orales  del ámbito escolar, social y de medios audiovisuales, opinando sobre ideas,  hechos,  temas,  personas  y  personajes  a  partir  de  su experiencia y el contexto en el que se desenvuelve."));
        desempenyos.add(new Desempenyo(6,
                "Expresa   oralmente   sus   necesidades,   intereses,   experiencias   y emociones  de  forma  espontánea,  adecuando  su  texto  oral  a  sus interlocutores  y  contexto  de  acuerdo  al  propósito  comunicativo  y utilizando recursos no verbales y paraverbales. "));
        desempenyos.add(new Desempenyo(6,
                "Desarrolla  ideas  en torno  a  un  tema,  aunque  en  ocasiones  puede reiterar     información     innecesariamente.     Organiza     las     ideas estableciendo relaciones lógicas entre ellas (en especial, de adición, secuencia y causa) a través de algunos conectores, e incorporando un vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(6,
                "Interactúa  en  diversas  situaciones  orales,  formulando  preguntas, dando respuestas y haciendo comentarios relacionados con el tema, utilizando un vocabulario de uso frecuente y recurriendo a normas y modos de cortesía según el contexto sociocultural. "));
        desempenyos.add(new Desempenyo(6,
                "Obtiene   información   explícita   relevante   como   el   nombre   de personas,  personajes,  acciones,  hechos,  lugares  y  fechas  en  textos orales que presentan vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(6,
                "Infiere    información    deduciendo    características    de    personas, personajes,  animales,  objetos,  hechos  y  lugares,  el  significado  de palabras y expresiones en contexto, así como relaciones lógicas de semejanza-diferencia  y  de  causa-efecto  a  partir  de  información explícita del texto."));
        desempenyos.add(new Desempenyo(6,
                "Interpreta el sentido del texto oral según modos culturales diversos, relacionando algunos recursos verbales, no verbales y paraverbales, explicando el tema y propósito, las acciones y estados de ánimo de personas y personajes, así como adjetivaciones. "));
        desempenyos.add(new Desempenyo(6,
                "Reflexiona  como  hablante  y  oyente  sobre  los  textos  orales  del ámbito  escolar,  social  y  de  medios  audiovisuales,  opinando  sobre ideas,  hechos,  temas,  personas  y  personajes,  así  como  el  uso  de algunos recursos verbales, no verbales y paraverbales a partir de su experiencia y el contexto en el que se desenvuelve."));
        desempenyos.add(new Desempenyo(7,
                "Expresa oralmente ideas y emociones, adecuando su texto oral   a   sus   interlocutores   y   contexto   de   acuerdo   al propósito comunicativo, reconociendo el registro formal, y utilizando   recursos   no   verbales   y   paraverbales   para enfatizar la información."));
        desempenyos.add(new Desempenyo(7,
                "Desarrolla  ideas  en  torno  a  un  tema,  evitando  reiterar información     innecesariamente.     Organiza     las     ideas estableciendo relaciones lógicas entre ellas (en especial, de adición, secuencia y causa) a través de algunos conectores y  referentes,  e  incorporando  un  vocabulario  pertinente que algunos términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(7,
                "Interactúa   en   diversas   situaciones   orales,   formulando preguntas,     explicando     sus     respuestas y     haciendo comentarios relevantes al tema, utilizando un vocabulario pertinente  de   uso   frecuente,   recurriendo   a   normas  y modos de cortesía según el contexto sociocultural."));
        desempenyos.add(new Desempenyo(7,
                "Obtiene información explícita, relevante y complementaria,    en    textos    orales    que    presentan vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(7,
                "Infiere información     deduciendo     características     de personas, personajes, animales, objetos, hechos y lugares, el  significado  de  palabras  en  contexto  y  expresiones  con sentido    figurado,    así    como    relaciones    lógicas    de semejanza-diferencia,    de    causa-efecto    y    problema-solución  a  partir  de  información  explícita  e  implícita  del texto."));
        desempenyos.add(new Desempenyo(7,
                "Interpreta el sentido del texto oral según modos culturales diversos,   relacionando   algunos   recursos   verbales,   no verbales  y  paraverbales,  explicando  el  tema  y  propósito, las acciones y estados de ánimo de personas y personajes, así    como    enseñanzas    y    mensajes,    adjetivaciones    y personificaciones."));
        desempenyos.add(new Desempenyo(7,
                "Reflexiona como hablante y oyente sobre los textos orales del  ámbito   escolar,   social   y   de   medios   audiovisuales, opinando    sobre    ideas,    hechos,    temas,    personas    y personajes,  así  como  sobre  la  adecuación  a  la  situación comunicativa,  el  uso  de  algunos  recursos  verbales,  no verbales,  paraverbales  y  a  partir  de  su  experiencia  y  el contexto en el que se desenvuelve."));
        desempenyos.add(new Desempenyo(8,
                "Expresa  oralmente  ideas  y  emociones,  adecuando  su  texto oral a sus interlocutores y contexto de acuerdo al propósito comunicativo,  distinguiendo  el  registro  formal  e  informal,  y utilizando recursos no verbales y paraverbales para enfatizar la información."));
        desempenyos.add(new Desempenyo(8,
                "Desarrolla ideas en torno a un tema, ampliando información de   forma   pertinente.   Organiza   y   jerarquiza   las   ideas estableciendo  relaciones  lógicas  entre  ellas  (en  especial,  de causa, secuencia y contraste) a través de algunos conectores y  referentes,  e  incorporando  un  vocabulario  pertinente  que incluye sinónimos y algunos términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(8,
                "Interactúa    en    diversas    situaciones    orales,    formulando preguntas, explicando sus respuestas y haciendo comentarios relevantes, utilizando un vocabulario pertinente que incluye sinónimos  y  algunos  términos  propios  de  los  campos  del saber  y  recurriendo  a  normas  y  modos  de  cortesía  según  el contexto sociocultural."));
        desempenyos.add(new Desempenyo(8,
                "Obtiene  información  explícita,  relevante  y  complementaria, en  textos  orales,  que  presentan  expresiones  con  sentido figurado,  y  vocabulario  que  incluye  sinónimos  y  términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(8,
                "Infiere  información  deduciendo  características  de  personas, personajes, animales, objetos, hechos y lugares, el significado de palabras en contexto y expresiones con sentido figurado, así como relaciones lógicas (semejanza-diferencia, de causa-efecto y problema-solución) y jerárquicas (ideas principales) a partir de información explícita e implícita del texto."));
        desempenyos.add(new Desempenyo(8,
                "Interpreta  el  sentido  del  texto  oral  según  modos  culturales diversos,   relacionando   recursos   verbales,   no   verbales   y paraverbales,  explicando  el  tema  y  propósito,  enseñanzas  y mensajes, puntos de  vista, así como  motivaciones y  estados de    ánimo    de    personas    y    personajes,    adjetivaciones, personificaciones, comparaciones, clasificando y sintetizando la información."));
        desempenyos.add(new Desempenyo(8,
                "Reflexiona como hablante y oyente sobre los textos orales del ámbito  escolar,  social  y  de  medios  audiovisuales,  opinando sobre ideas, hechos, temas, personas y personajes, así como sobre la adecuación del texto a la situación comunicativa, el uso de algunos recursos verbales, no verbales y paraverbales, la  coherencia  y  cohesión  entre  las  ideas,  a  partir  de  su experiencia y el contexto en el que se desenvuelve."));
        desempenyos.add(new Desempenyo(9,
                "Expresa oralmente ideas y emociones, adecuando su texto oral a sus    interlocutores    y    contexto    de    acuerdo al    propósito comunicativo,   distinguiendo   el   registro   formal   e   informal, utilizando  recursos  no  verbales  y  paraverbales  para  enfatizar  la información o mantener el interés del público."));
        desempenyos.add(new Desempenyo(9,
                "Desarrolla ideas en torno a un tema, ampliando información de forma pertinente.  Organiza y jerarquiza las ideas, estableciendo relaciones lógicas entre ellas (en especial, de causa, contraste y consecuencia)  a  través  de  conectores  y  algunos  referentes,  e incorporando un vocabulario pertinente que incluye sinónimos y algunos términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(9,
                "Interactúa  en  diversas  situaciones  orales,  considerando  lo  que dicen  sus  interlocutores  para  argumentar,  explicar,  aclarar  y complementar  las  ideas  expuestas,  utilizando  un  vocabulario pertinente  que  incluye  sinónimos  y  algunos  términos  de  los campos  del  saber,  recurriendo  a  normas  y  modos  de  cortesía según el contexto sociocultural."));
        desempenyos.add(new Desempenyo(9,
                "Obtiene  información  explícita,  relevante  y  complementaria,  en textos orales, que presentan expresiones con sentido figurado, y vocabulario  que  incluye  sinónimos  y  términos  propios  de  los campos del saber."));
        desempenyos.add(new Desempenyo(9,
                "Infiere   información   deduciendo   características   de   personas, personajes, animales, objetos, hechos y lugares, el significado de palabras  en  contexto  y  expresiones  con  sentido  figurado,  asícomo relaciones lógicas (semejanza-diferencia, de causa-efecto y problema-solución) y jerárquicas (ideas principales y complementarias) a partir de información explícita e implícita del texto. "));
        desempenyos.add(new Desempenyo(9,
                "Interpreta  el  sentido  del  texto  oral  según  modos  culturales diversos,    relacionando    recursos    verbales,    no    verbales    y paraverbales,   explicando   el   tema   y   propósito,   enseñanzas, valores y mensajes, puntos de vista, así como estados de ánimo y motivaciones de personas y personajes, hipérboles, repeticiones, comparacionesy la intención de sus interlocutores, clasificando y sintetizando la información. "));
        desempenyos.add(new Desempenyo(9,
                "Reflexiona y evalúa como hablante y oyente los textos orales del ámbito  escolar,  social  y  de  medios  de  comunicación,  opinando sobre  la  adecuación  del  texto  a  la  situación  comunicativa,  la pertinencia   de   algunos   recursos   verbales,   no   verbales   y paraverbales, la coherencia y cohesión entre las ideas, y el uso de conectores y referentes."));
        desempenyos.add(new Desempenyo(9,
                "Justifica  su  posición  sobre  ideas,  hechos,  temas,  personas  y personajes del texto oral a partir de su experiencia y el contexto en que se desenvuelve."));
        desempenyos.add(new Desempenyo(10,
                "Expresa oralmente ideas y emociones, adecuando su texto oral a sus interlocutores  y  contexto  de  acuerdo  al  propósito  comunicativo, distinguiendo   el   registro   formal   e   informal,   así   como   algunas características   del   género   discursivo,   y   utilizando   recursos   no verbales y paraverbales para enfatizar la información, mantener  el interés  del  público,  o  producir  efectos  como  el  suspenso  o  el entretenimiento."));
        desempenyos.add(new Desempenyo(10,
                "Desarrolla  ideas  en  torno  a  un  tema,  ampliando  información  de forma  pertinente.  Organiza  y  jerarquiza  las  ideas,  estableciendo relaciones  lógicas  entre  ellas  (en  especial  de  causa,  contraste  y consecuencia)   a   través   de   conectores   y   algunos   referentes,   e incorporando  un  vocabulario  pertinente  que  incluye  sinónimos  y términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(10,
                "Interactúa en diversas situaciones orales, considerando lo que dicen sus     interlocutores,     para     argumentar,     explicar,     aclarar     y complementar   las   ideas   expuestas,   utilizando   un   vocabulario pertinente que incluye sinónimos y algunos términos propios de los campos del saber, y recurriendo a normas y modos de cortesía según el contexto sociocultural."));
        desempenyos.add(new Desempenyo(10,
                "Obtiene   información   explícita,   relevante   y   complementaria,   en textos  orales  que  presentan  expresiones  con  sentido  figurado,  y vocabulario que incluye sinónimos y términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(10,
                "Infiere   información   deduciendo   características   y   cualidades   de personas,   personajes,   animales,   objetos,   hechos   y   lugares,   el significado  de  palabras  en  contexto  y  expresiones  con  sentido figurado, así como relaciones lógicas (semejanza-diferencia, causa-efecto   y   problema-solución)   y   jerárquicas   (ideas   principales   y complementarias)  a  partir  de  información  explícita  e  implícita  del texto."));
        desempenyos.add(new Desempenyo(10,
                "Interpreta el sentido del texto oral según modos culturales diversos, relacionando   recursos   verbales,   no   verbales   y   paraverbales, explicando  el  tema  y  propósito,  enseñanzas,  valores  y  mensajes, puntos  de  vista,  así  como  estados  de  ánimo  y  motivaciones  de personas y personajes, hipérboles, repeticiones, comparaciones y la intención   de   sus   interlocutores,   clasificando   y   sintetizando   la información, y elaborando conclusiones sobre el texto escuchado."));
        desempenyos.add(new Desempenyo(10,
                "Reflexiona  y  evalúa  como  hablante  y  oyente  los  textos  orales  del ámbito escolar, social y de medios de comunicación, opinando sobre la adecuación del texto a la situación comunicativa, la pertinencia de ecursos  verbales,  no  verbales  y  paraverbales,  la  coherencia  y cohesión  entre  las  ideas,  y  el  uso  de  conectores  y  referentes, considerando  las  diferentes  variedades  lingüísticas  del  país  para valorar su diversidad."));
        desempenyos.add(new Desempenyo(10,
                "Justifica su posición sobre sobre ideas, hechos, temas y la intención de  los  interlocutores  del  texto  oral  a  partir  de  su  experiencia  y  el contexto en que se desenvuelve."));
        comp_19.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_19);

        Competencia comp_20 = competenciaRepository.findByCode(20);
        desempenyos.add(new Desempenyo(5,
                "Obtiene información explícita que se encuentra en lugares evidentes del texto (título, subtítulo, inicio, final) y que es claramente distinguible de otra, en diversos tipos de textos con ilustraciones. "));
        desempenyos.add(new Desempenyo(5,
                "Infiere información anticipando el contenido del texto a partir de algunos indicios (título, ilustraciones, palabras conocidas) y deduciendocaracterísticas de personajes, animales, objetos y lugares, así como relaciones lógicas de causa-efecto que se pueden establecer fácilmente a partir de información explícita del texto."));
        desempenyos.add(new Desempenyo(5,
                "Reflexiona sobre los textos que lee, opinando acerca de personas, personajes y hechos, y expresando sus preferencias cuando elige o recomienda textos a partir de su experiencia, necesidades e intereses."));
        desempenyos.add(new Desempenyo(6,
                "Obtiene información explícita y relevante que se encuentra dentro de los párrafos, distinguiéndola de otra información semejante en diversos tipos de textos, con o sin ilustraciones."));
        desempenyos.add(new Desempenyo(6,
                "Infiere información anticipando el contenido del texto, a partir de algunos indicios (título, ilustraciones, palabras y expresiones conocidas) y deduciendo características de personajes, animales, objetos y lugares, así como el significado de palabras y expresiones por contexto, las relaciones lógicas de causa-efecto y semejanza-diferencia a partir de información explícita del texto."));
        desempenyos.add(new Desempenyo(6,
                "Interpreta el sentido global del texto explicando el tema, propósito y las acciones de personas y personajes,así como relaciones texto-ilustración en textos que lee por sí mismo."));
        desempenyos.add(new Desempenyo(6,
                "Reflexiona sobre los textos que lee, opinando acerca de personas, personajes y hechos, y expresando sus preferencias cuando elige o recomienda textos a partir de su experiencia, necesidades e intereses."));
        desempenyos.add(new Desempenyo(7,
                "Obtiene información explícita y relevante ubicada en distintas partes del texto, distinguiéndola de otra información semejante en diversos tipos de textos con vocabulario variado."));
        desempenyos.add(new Desempenyo(7,
                "Infiere información anticipando el contenido del texto, a partir de algunos indicios (silueta del texto, tamaño de la letra) y deduciendo características de personajes, animales, objetos y lugares, así como el significado de palabras en contexto y expresiones con sentido figurado, las relaciones lógicas (causa-efecto y semejanza-diferencia) a partir de información explícita e implícita del texto."));
        desempenyos.add(new Desempenyo(7,
                "Interpreta el sentido global del texto, explicando tema, propósito, enseñanzas, relaciones texto-ilustración, así como adjetivaciones, motivaciones de personas y personajes. "));
        desempenyos.add(new Desempenyo(7,
                "Reflexiona sobre los textos que lee, opinandoacerca del contenido y explicando el sentido de algunos recursos textuales (ilustraciones, tamaño de letra, entre otros) y justificando sus preferencias cuando elige o recomienda textos a partir de su experiencia, necesidades e intereses."));
        desempenyos.add(new Desempenyo(8,
                "Obtiene información explícita y relevante ubicada en distintas partes del texto, distinguiéndola de otra cercana y semejante en diversos tipos de textos con algunos elementos complejos en su estructura y vocabulario variado."));
        desempenyos.add(new Desempenyo(8,
                "Infiere información anticipando el contenido del texto, a partir de algunos indicios (subtítulos, índice) y deduciendo característicasde personajes, animales, objetos y lugares, así como el significado de palabras en contexto y expresiones con sentido figurado, las relaciones lógicas (semejanza-diferencia y problema-solución) y relaciones jerárquicas (ideas principales) a partir de información explícita e implícita del texto."));
        desempenyos.add(new Desempenyo(8,
                "Interpreta el sentido global del texto, explicando el tema, propósito, punto de vista, motivaciones de personas y personajes, comparaciones y personificaciones, así como enseñanzas y valores del texto, clasificando y sintetizando la información."));
        desempenyos.add(new Desempenyo(8,
                "Reflexiona sobre los textos que lee, opinando acerca del contenido y explicando el sentido de algunos recursos textuales (uso de negritas, mayúsculas, entre otros), a partir de su experiencia y contexto, justificando sus preferencias cuando elige o recomienda textos a partir de sus necesidades, intereses y su relación con otros textos."));
        desempenyos.add(new Desempenyo(9,
                "Obtiene información explícita, relevante y complementaria, distinguiéndola de otra cercana y semejante e integra datos que se encuentran en distintas partes de diversos tipos de texto cuya estructura contiene algunos elementos complejos y vocabulario variado."));
        desempenyos.add(new Desempenyo(9,
                "Infiere información, anticipando el contenido del texto, a partir de algunos indicios (tipografía, índice) y deduciendo las características de personas, personajes, objetos y lugares, así como el significado de palabras en contexto y expresiones con sentido figurado, las relaciones lógicas (semejanza-diferencia y problema-solución) y jerárquicas (ideas principales y complementarias), a partir de información explícita e implícita del texto."));
        desempenyos.add(new Desempenyo(9,
                "Interpreta el sentido global del texto, explicando el tema, propósito, puntos de vista, motivaciones de personas, personajes, comparaciones e hipérboles, problema central, así como enseñanzas y valores del texto, clasificando y sintetizando la información."));
        desempenyos.add(new Desempenyo(9,
                "Reflexiona y evalúa los textos que lee, opinando acerca del contenido, la organización textual y el sentido de algunos recursos textuales (negritas, esquemas), y explicando el efecto del texto en los lectores a partir de su experiencia y contexto en que se desenvuelve."));
        desempenyos.add(new Desempenyo(9,
                "Justifica la elección o recomendación de textos de su preferencia de acuerdo a sus necesidades, intereses y la relación con otros textos, sustentando su punto de vista sobre el texto cuando lo comparte con otros, y comparando textos entre sí para indicar algunas similitudes y diferencias entre tipos textuales."));
        desempenyos.add(new Desempenyo(10,
                "Obtiene información explícita, relevante y complementaria, distinguiéndola de otra cercana y semejante, e integra datos que se encuentran en distintas partes del texto, o mediante una lectura intertextual, en diversos tipos de texto con varios elementos complejos en su estructura y vocabulario variado."));
        desempenyos.add(new Desempenyo(10,
                "Infiere información deduciendo características de personas, personajes, objetos y lugares, el significado de palabras en contexto y expresiones con sentido figurado, así como relaciones lógicas (causa-efecto, semejanza-diferencia y problema-solución) y jerárquicas (ideas principales y complementarias) a partir de información explícita e implícita del texto, o mediante una lectura intertextual."));
        desempenyos.add(new Desempenyo(10,
                "Interpreta el sentido global del texto, explicando el tema, propósito, puntos de vista, motivaciones de personas y personajes, comparaciones e hipérboles, problema central, enseñanzas, valores, e intención del autor, clasificando y sintetizando la información, y elaborando conclusiones sobre el texto."));
        desempenyos.add(new Desempenyo(10,
                "Reflexiona y evalúa los textos que lee, opinando acerca del contenido, la organización textual, el sentido de diversos recursos textuales, la intención del autor, y explicando el efecto del texto en los lectores a partir de su experiencia y de los contextos en que se desenvuelve."));
        desempenyos.add(new Desempenyo(10,
                "Justifica la elección o recomendación de textos de su preferencia de acuerdo a sus necesidades, intereses y la relación con otros textos, sustentando su posición sobre valores presentes en los textos cuando los comparte con otros, y comparando textos entre sí para indicar algunas similitudes y diferencias entre tipos textuales y géneros discursivos."));
        comp_20.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_20);

        Competencia comp_21 = competenciaRepository.findByCode(21);
        desempenyos.add(new Desempenyo(5,
                "Escribe  diversos  tipos  de  textos  en  el  nivel  alfabético, utilizando   las   regularidades   del   sistema   de   escritura, considerando   el   destinatario   y   tema   de   acuerdo   al propósito comunicativo, e incorporando un vocabulario de uso frecuente. "));
        desempenyos.add(new Desempenyo(5,
                "Desarrolla   las   ideas  en   torno   a   un   tema,   aunque   en ocasiones   puede   salirse   de   este.   Establece   relaciones lógicas entre las ideas (sobre todo de adición), a través de algunos   conectores,   y   utiliza   recursos   gramaticales   y ortográficos  como  mayúsculas  en  nombres  propios  y  el punto final, para contribuir con el sentido de su texto."));
        desempenyos.add(new Desempenyo(5,
                "Reflexiona  sobre  el  texto  que  escribe,  opinando  sobre  el contenido   y   revisando   si   el   contenido   se   adecúa   al destinatario y propósito y tema con el fin de mejorarlo."));
        desempenyos.add(new Desempenyo(6,
                "Escribe   diversos   tipos   de   textos,   considerando   el   tema, adecuándose  al  destinatario  y  tipo  textual de  acuerdo  al propósito  comunicativo,  e  incorporando  un  vocabulario  de uso frecuente."));
        desempenyos.add(new Desempenyo(6,
                "Desarrolla las ideas en torno a un tema, aunque en ocasiones puede  salirse  de  este,  reiterar  o  contradecir  información. Organiza   las   ideas   estableciendo   relaciones   lógicas   (en especial,   de   adición   y   secuencia)   a   través   de   algunos conectores,  y  utiliza  recursos  gramaticales  y  ortográficos como  mayúsculas  y  el  punto  final,  para  contribuir  con  el sentido de su texto."));
        desempenyos.add(new Desempenyo(6,
                "Emplea  fórmulas  retóricas  para  marcar  el  inicio  y  el  final en las narraciones que escribe, elabora rimas y juegos verbales."));
        desempenyos.add(new Desempenyo(6,
                "Reflexiona  sobre  el  texto  que  escribe,  opinando  sobre  el contenido y revisando si se adecúa al destinatario y propósito, así como el uso de algunos conectores y recursos ortográficos empleados  (punto  final  y  mayúscula  en  nombres  propios), para mejorar y garantizar el sentido de su texto."));
        desempenyos.add(new Desempenyo(7,
                "Escribe    diversos    tipos    de    textos,    adecuándose    al destinatario   y   tipo   textual   de   acuerdo   al   propósito comunicativo,  e  incorporandoun  vocabulario  pertinente así  como  algunos  términos  propios  de  los  campos  del saber."));
        desempenyos.add(new Desempenyo(7,
                "Desarrolla  sus  ideas  en  torno  a  un  tema,  evitando  salirse de este, contradecirse y reiterar información innecesariamente,  aunque  en  ocasiones  puede  presentar vacíos  de  información.  Organiza  las  ideas  estableciendo relaciones    lógicas    (en    especial,    de    adición,    causa, secuencia)  a  través  de  algunos  referentes  y  conectores, utilizando  recursos  gramaticales  y  ortográficos  (como  la tildación) que contribuyen al sentido de su texto."));
        desempenyos.add(new Desempenyo(7,
                "Emplea algunos recursos textuales (como las adjetivaciones)  para  caracterizar  personas,  personajes  y escenarios, y elabora rimas y juegos  verbales apelando al ritmo y musicalidad de las palabras, con el fin de expresar sus experiencias y emociones."));
        desempenyos.add(new Desempenyo(7,
                "Reflexiona  sobre  el  texto  que  escribe,  revisando  si  se adecúa  al  destinatario, propósito,  tema  y  tipo  textual,  así como  la  coherencia  entre  las  ideas,  el  uso  de  algunos conectores  y  referentes,  vocabulario  pertinente,  además de  los  recursos  ortográficos  empleados  para  mejorar  y garantizar el sentido de su texto."));
        desempenyos.add(new Desempenyo(8,
                "Escribe diversos tipos de textos, adecuándose al destinatario y   tipo   textual   de   acuerdo   al   propósito   comunicativo, distinguiendo  el  registro  formal  e  informal,  considerando  el formato  y  soporte,  incorporando  un  vocabulario  pertinente que  incluye  sinónimos y  algunos  términos  propios  de  los campos del saber."));
        desempenyos.add(new Desempenyo(8,
                "Desarrolla  sus  ideas  en  torno  a  un  tema,  de  acuerdo  al propósito  comunicativo.  Organiza  las  ideas  en  oraciones  y párrafos   estableciendo   relaciones   lógicas   entre   ellas   (en especial,  de  causa  y  consecuencia),  a  través  de  algunos referentes  y  conectores,  utilizando  recursos  gramaticales  y ortográficos (como punto seguido y aparte) que contribuyen al sentido de su texto."));
        desempenyos.add(new Desempenyo(8,
                "Emplea  algunos  recursos  textuales  (como  comparaciones,  y adjetivaciones)   para   caracterizar   personas,   personajes   y escenarios, para elaborar rimas y juegos verbales apelando al ritmo y musicalidad de las palabras, con el fin de expresar sus experiencias y emociones."));
        desempenyos.add(new Desempenyo(8,
                "Reflexiona   sobre   el   texto   que   escribe,   revisando   si   el contenido se adecúa al destinatario, propósito, tema, registro y tipo textual, así como la  coherencia  entre las ideas, el uso de  algunos  conectores,  referentes  y  vocabulario  pertinente, además de los recursos ortográficos empleados para mejorar y garantizar el sentido de su texto."));
        desempenyos.add(new Desempenyo(8,
                "Opina   sobre   el   sentido   de   algunas   palabras,   recursos ortográficos  y  estilísticos  utilizados  en  su  texto,  así  como  el efecto de su texto en los lectores."));
        desempenyos.add(new Desempenyo(9,
                "Escribe diversos tipos de textos, adecuándose al destinatarioy   tipo   textual   de   acuerdo   al   propósito   comunicativo, distinguiendo  el  registro  formal  e  informal,  considerando  el formato y soporte, e incorporando un vocabulario pertinente que  incluye  sinónimos  y  algunos  términos  propios  de  los campos del saber."));
        desempenyos.add(new Desempenyo(9,
                "Desarrolla  sus  ideas  en  torno  a  un  tema  de  acuerdo  al propósito  comunicativo  ampliando  la  información  de  forma pertinente.  Organiza  y  jerarquiza  las  ideas  en  párrafos  y subtemas,  estableciendo  relaciones  lógicas  (en  especial,  de causa, secuencia y contraste), a través de algunos referentes y    conectores,    y    utilizando    recursos    gramaticales    y ortográficos    (coma    y    punto    seguido    y    aparte)    que contribuyen al sentido de su texto."));
        desempenyos.add(new Desempenyo(9,
                "Emplea  algunos  recursos  textuales  (como  uso  de  negritas, comparaciones,   personificaciones   y adjetivaciones)   para reforzar  el  sentido  del  texto  para  caracterizar  personas, personajes  y  escenarios,  para  elaborar  patrones  rítmicos  o versos   libres   con   el   fin   de   expresar   sus   experiencias   y emociones."));
        desempenyos.add(new Desempenyo(9,
                "Reflexiona   y   evalúa   de   manera   permanente   el   texto, revisando    si    el    contenido    se    adecúa    al    destinatario, propósito,   tema,   registro   y   tipo   textual,   así   como   la coherencia  entre  las  ideas,  el  uso  pertinente  de  algunos conectores, referentes y vocabulario, además de los recursos ortográficos empleados para mejorar y garantizar el sentido de su texto."));
        desempenyos.add(new Desempenyo(9,
                "Opina sobre el sentido de los recursos formales y estilísticos utilizados    y    el    efecto    de    su    texto    en    los    lectores, sistematizando algunos aspectos gramaticales y ortográficos, y otras convenciones vinculadas con el lenguaje escrito."));
        desempenyos.add(new Desempenyo(10,
                "Escribe  diversos  tipos  de  textos,  adecuándose  al  destinatario, tipo textual y a algunas características del género discursivo de acuerdo  al  propósito  comunicativo,  distinguiendo  el  registro formal   e   informal,   considerando   el   formato   y   soporte,   e incorporando un vocabulario pertinente que incluye sinónimos y diversos términos propios de los campos del saber."));
        desempenyos.add(new Desempenyo(10,
                "Desarrolla sus ideas en torno a un tema, de acuerdo al propósito comunicativo  ampliando  la  información  de  forma  pertinente.  Organiza   y   jerarquiza   las   ideas   en   párrafos   y   subtemas, estableciendo relaciones lógicas (enespecial, de consecuencia y  contraste)  a  través  de  algunos  referentes  y  conectores, utilizando  recursos  gramaticales  y  ortográficos  (coma  y  punto seguido y aparte) que contribuyen al sentido de su texto."));
        desempenyos.add(new Desempenyo(10,
                "Emplea  algunos  recursos  textuales  (como  uso  de  negritas, comillas,  comparaciones,  personificaciones  e  hipérboles)  para reforzar   el   sentido   del   texto,   así   como   para   caracterizar personas,  personajes  y  escenarios,  o  para  elaborar  patrones rítmicos  y  versos  libres,  con  el  fin  de  producir  efectos  en  el lector (como el entretenimiento o el suspenso)."));
        desempenyos.add(new Desempenyo(10,
                "Reflexiona y evalúa de manera permanente el texto, revisando si  se  adecúa  a  la  situación  comunicativa,  si  las  ideas  son coherentes  entre  sí  o  se  presentan  vacíos  de  información,  así como  el  uso  pertinente  de  algunosconectores,  referentes  y vocabulario, además los recursos ortográficos empleados para mejorar y garantizar el sentido de su  texto."));
        desempenyos.add(new Desempenyo(10,
                "Opina  sobre  el  sentido  de  los  recursos  formales  y  estilísticos utilizados y el efecto de su texto en los lectores, sistematizando algunos  aspectos  gramaticales  y  ortográficos,  así  como  otras convenciones vinculadas con el lenguaje escrito."));
        comp_21.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_21);

        Competencia comp_22 = competenciaRepository.findByCode(22);
        desempenyos.add(new Desempenyo(5,
                "Obtiene  información  explícita  en  textos  orales  breves  y sencillos eninglés con vocabulario sencillo como nombres, lugares  y  personajes  apoyándose  en  el  contexto,  ayuda audiovisual, gestos y expresiones corporales del emisor."));
        desempenyos.add(new Desempenyo(5,
                "Infiere     información     básica     en     inglés     deduciendo características  de  personas,  animales  y  objetos  entextos orales  breves  y  sencillos  e  interpreta  el  sentido  del  texto oral   apoyándose   en   la   ayuda   audiovisual,   gestos   y expresiones corporales del emisor"));
        desempenyos.add(new Desempenyo(5,
                "Expresa oralmente sus ideas en inglés adecuándose a su interlocutor y acompañando su expresión oral con recursos no verbales."));
        desempenyos.add(new Desempenyo(5,
                "Desarrolla  ideas  en  torno  un  tema,  aunque  en  ocasiones podría  salirse  de  éste.  Incorpora  un  vocabulario  simple  y palabras y expresiones básicas."));
        desempenyos.add(new Desempenyo(5,
                "Interactúa en situaciones orales con otras personas en inglés respondiendo preguntas utilizando vocabulario simple"));
        desempenyos.add(new Desempenyo(5,
                "Opina sobre el texto oral que escucha en inglés expresando lo que le gusta o le disgusta mediante ilustraciones y recursos no verbales."));
        desempenyos.add(new Desempenyo(6,
                "Obtiene  información  explícita  en  textos  orales  breves  y sencillos en inglés como nombres, acciones, hechos, fechas y   lugares   con   vocabulario   sencillo   apoyándose   en   el contexto,    ayuda    audiovisual,    gestos    y    expresionescorporales del emisor."));
        desempenyos.add(new Desempenyo(6,
                "Infiere     información     básica     en     inglés     deduciendo características   de   personas,   objetos   y   de   su   entorno inmediato en textos orales breves y sencillos e interpreta el sentido  del  texto oral  apoyándose  en  el  contexto,  ayuda audiovisual, gestos y expresiones corporales del emisor."));
        desempenyos.add(new Desempenyo(6,
                "Expresa oralmente sus ideas en inglés acerca de sí mismo, su   familia,   su   entorno   físico,   la   escuela,   animales   e intereses   adecuándose   a   su   interlocutor   y   contexto acompañando su expresión oral con recursos no verbales."));
        desempenyos.add(new Desempenyo(6,
                "Desarrolla ideas en torno a un tema, aunque en ocasiones podría  salirse  de  éste.  Organiza  las  ideas  de  forma  lógica incorporando un vocabulario simple, expresiones básicas y oraciones simples."));
        desempenyos.add(new Desempenyo(6,
                "Interactúa  en  situaciones  orales,  con  otras  personas  en inglés   formulando   algunas   preguntas   y   respondiendo utilizando vocabulario simple."));
        desempenyos.add(new Desempenyo(6,
                "Opina sobre el texto oral que escucha en inglés expresando sus  preferencias  dando  razones  sencillas  mediante  el  uso de ilustraciones y recursosno verbales."));
        desempenyos.add(new Desempenyo(7,
                "Obtiene información explicita en textos orales sencillos en inglés  con  vocabulario  sencilloparticipando  como  oyente activo apoyándose   en   el   contexto,   ayuda   audiovisual, gestos y expresiones corporales del emisor."));
        desempenyos.add(new Desempenyo(7,
                "Infiere información en inglés deduciendo características de personas, objetos, lugares, el significado de palabras dentro de un contexto en textos orales sencillos e interpreta el sentido del texto oral apoyándose en el contexto, ayuda audiovisual, gestos y expresiones corporales del emisor."));
        desempenyos.add(new Desempenyo(7,
                "Expresa oralmente sus ideas en inglés    acerca de su familia, la escuela, animales, objetos, ubicación espacial, actividades preferencias, lugares y servidores de la comunidad adecuándose a su interlocutor y contexto acompañando su expresión oral con recursos no verbales."));
        desempenyos.add(new Desempenyo(7,
                "Desarrolla ideas en torno a un tema evitando reiteraciones. Organiza las ideas estableciendo relaciones (en especial de adición)    e    incorporando    un vocabulario    sencillo    y construcciones gramaticales sencillas."));
        desempenyos.add(new Desempenyo(7,
                "Interactúa en diversas situaciones orales con otras personas en inglés formulando y respondiendo preguntas utilizando vocabulario sencillo y pronunciación adecuada para su nivel. "));
        desempenyos.add(new Desempenyo(7,
                "Opina  sobre  eltexto  oral  que  escucha  en  inglés,  dando razones    sencillas    sobre    sus    preferencias    acerca    de personas, hechos, o situaciones cotidianas relacionando la información con sus conocimientos del tema."));
        desempenyos.add(new Desempenyo(8,
                "Obtiene información explicita y relevante en textos orales sencillos  en  inglés  con  vocabulario  sencillo  participando como  oyente  activo  apoyándose  en  el  contexto,  ayuda audiovisual y gestos del emisor."));
        desempenyos.add(new Desempenyo(8,
                "Infiere información en inglés deduciendo características de personas,   objetos,   lugares   y   hechos,   el  significado   de palabras  y  frases  dentro  de  un  contexto  en  textos  orales sencillos e interpreta el sentido del texto oral apoyándose en  el  contexto,  ayuda  audiovisual,  gestos  y  expresiones corporales del emisor."));
        desempenyos.add(new Desempenyo(8,
                "Expresa oralmente sus ideas en inglés acerca de características de personas, animales, objetos, actividades diarias, alimentos, preferencias, profesiones y lugares adecuándose a su interlocutor y contexto acompañando su expresión oral con recursos no verbales. "));
        desempenyos.add(new Desempenyo(8,
                "Desarrolla ideas en torno a un tema evitando reiteraciones. Organiza las ideas estableciendo relaciones (en especial de adición  y  contraste)  a  través  de  algunos  conectores  e incorporando   un   vocabulario   sencillo   y   construcciones gramaticales sencillas."));
        desempenyos.add(new Desempenyo(8,
                "Interactúa   en   diversas    situaciones   orales   con   otras personas  en  inglés formulando  y  respondiendo  preguntas y  haciendo  algunos  comentarios  utilizando vocabulario sencillo y pronunciación adecuada para su nivel."));
        desempenyos.add(new Desempenyo(8,
                "Opina sobre el texto oral que escucha en inglés, dando su punto de vista acerca de personas, hechos, objetos, lugares y  secuencias  temporales  relacionando  la  información  con sus conocimientos del tema."));
        desempenyos.add(new Desempenyo(9,
                "Obtiene información explícita y relevante en textos orales en inglés, con vocabulario de uso frecuente reconociendo el propósito comunicativo, participando como oyente activo y apoyándose en el contexto, alguna ayuda audiovisual y gestos del emisor."));
        desempenyos.add(new Desempenyo(9,
                "Infiere información en inglés deduciendo características de personas, objetos, lugares y hechos, el significado de palabras y frases dentro de un contexto, así como relaciones lógicas e interpreta el sentido del texto oral apoyándose en el contexto, alguna ayuda audiovisual, gestos y expresiones corporales del emisor."));
        desempenyos.add(new Desempenyo(9,
                "Expresa oralmente sus ideas en inglés acerca de personas, objetos, lugares, tiempo, actividades diarias, frecuencia de eventos, habilidades, obligaciones, sentimientos, hábitos alimenticios adecuándose a su interlocutor y contexto utilizando recursos no verbales y para-verbales para enfatizar la información."));
        desempenyos.add(new Desempenyo(9,
                "Desarrolla ideas en torno a un tema ampliando información de forma pertinente. Organiza las ideas con coherencia, cohesión y fluidez a su nivel, estableciendo relaciones (en especial de adición, contraste y secuencia) a través de algunos conectores e incorporando vocabulario de uso frecuente y construcciones gramaticales determinadas usando oraciones simples y algunas de mediana complejidad."));
        desempenyos.add(new Desempenyo(9,
                "Interactúa en diversas situaciones orales con otras personas en inglés formulando y respondiendo preguntas, haciendo algunos comentarios y explicando ideas utilizando vocabulario de uso frecuente y pronunciación adecuada para su nivel."));
        desempenyos.add(new Desempenyo(9,
                "Opina sobre el texto oral que escucha en inglés, expresando su punto de vista acerca de personas, objetos, lugares, secuencias temporales y propósito comunicativo relacionando la información con sus conocimientos del tema"));
        desempenyos.add(new Desempenyo(10,
                "Obtiene información explícita y relevante en textos orales en inglés, con vocabulario de uso frecuente reconociendo el   propósito   comunicativo   participando   como   oyente activo,    apoyándose    en    el    contexto    y    algún    apoyo audiovisual."));
        desempenyos.add(new Desempenyo(10,
                "Infiere información en inglés deduciendo características de personas,   objetos,   lugares   y   hechos,   el  significado   de palabras,  frases  y  expresiones  dentro  de  un  contexto,  así como    relaciones    lógicas    (semejanza y    diferencia)    y jerárquicas (ideas principales) en textos orales e interpreta el sentido del texto oral apoyándose en recursos verbales, no verbales y para-verbales del emisor."));
        desempenyos.add(new Desempenyo(10,
                "Expresa  oralmente  sus  ideas  en  inglés  sobre  personajes, hechos,   vida   saludable,eventos   presentes   y   pasados planes,  comparación  de  objetos,  lugares,  clima,  personas, preferencias e intereses adecuándose a sus interlocutores y contexto utilizando recursos no verbales y para-verbales para enfatizar la información."));
        desempenyos.add(new Desempenyo(10,
                "Desarrolla ideas en torno a un tema ampliando información de  forma  pertinente.  Organiza  las  ideas  con  coherencia, cohesión  y  fluidez  a  su  nivel,  estableciendo  relaciones lógicas  (en  especial  de  adición,  contraste,  secuencia  y causa)  a  través  de  algunos  conectores  e  incorporando vocabulario de uso frecuente y construcciones gramaticales  determinadas  usando  oraciones  simples  y algunas de mediana complejidad."));
        desempenyos.add(new Desempenyo(10,
                "Interactúa   en   diversas    situaciones   orales   con   otras personas en inglés formulando y respondiendo preguntas, haciendo algunos comentarios, explicando y complementando   ideas   utilizando   vocabulario   de   uso frecuente y pronunciación adecuada para su nivel."));
        desempenyos.add(new Desempenyo(10,
                "Opina   sobre   el   texto   oral   que   escucha    en   inglés, expresando   su   punto   de   vista   acerca   de   personas, animales,     objetos,     lugares,     secuencias     temporales, propósito   comunicativo   y   relaciones   de   semejanza   y diferencia relacionando la información con sus conocimientos del tema."));
        comp_22.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_22);

        Competencia comp_23 = competenciaRepository.findByCode(23);
        desempenyos.add(new Desempenyo(7,
                "Obtiene    información    explicita        ubicada    en    lugares evidentes  del  texto  escrito  en  inglés    que  es  claramente identificada, con vocabulario  y expresiones sencillas"));
        desempenyos.add(new Desempenyo(7,
                "Infiere  información de textos escritos en inglés  a partir de indicios      acerca  de    su  familia  ,  la  escuela,    animales, objetos,    ubicación    espacial,    actividades    preferencias,   lugares,    servidores    de    la    comunidad    y    secuencias temporales a partir  de información explicita del texto"));
        desempenyos.add(new Desempenyo(7,
                "Interpreta  el  sentido  del  texto  a  partir  de  información recurrente así como relaciones texto-ilustración."));
        desempenyos.add(new Desempenyo(7,
                "Reflexiona   sobre   el   texto   escrito   que   lee   en   inglés, opinando   sobre   personas,   animales,   objetos   y   lugares expresando sus preferencias sobre lo leído."));
        desempenyos.add(new Desempenyo(8,
                "Obtiene información explicita y relevante ubicada en lugares evidentes del texto escrito en inglés (título, inicio y final) con estructuras simples, vocabulario y expresiones sencillas. "));
        desempenyos.add(new Desempenyo(8,
                "Infiere    información  de  textos  escritos  en  inglés    a  partir  de indicios  y  deduciendo  características  de  personas,  animales, objetos,  actividades  ,  alimentos,  preferencias  ,  profesiones,  lugares y secuencias temporales, a  partir    de  información  explicita  del  texto  así  como  el significado de expresiones en contexto"));
        desempenyos.add(new Desempenyo(8,
                "Interpreta   el   sentido   del   texto   a   partir   de   información recurrente así como relaciones texto-ilustración."));
        desempenyos.add(new Desempenyo(8,
                "Reflexiona sobre el texto escrito que lee en inglés, opinando sobre  personas,  objetos,  lugares  y  secuencias  temporales relacionando la información con sus conocimientos del tema. expresando sus preferencias sobre lo leído"));
        desempenyos.add(new Desempenyo(9,
                "Obtiene    información  explícita  y  relevante      que  está  en distintas partes del texto  escrito  en inglés, distinguiéndola de otra semejante con estructura simple y   vocabulario de uso frecuente"));
        desempenyos.add(new Desempenyo(9,
                "Infiere    información    de    textos    escritos    en    inglés deduciendo características de personas,  objetos,  lugares,  tiempo,    actividades    diarias, frecuencia de eventos, habilidades, obligaciones,  sentimientos, hábitos alimenticios  a partir de información explicita    e  implícita  del  texto  así  como    el  significado  de vocabulario  deuso frecuente"));
        desempenyos.add(new Desempenyo(9,
                "Interpreta    el    sentido    global    del    texto    a    partir    de información explícita identificando partes de su contenido y acciones de sus personajes."));
        desempenyos.add(new Desempenyo(9,
                "Reflexiona   sobre   el   texto   escrito   que   lee   en   inglés, opinando sobre el contenido y sentido de algunos recursos textuales,  expresando  sus  preferencias  sobre  lo  leído  a partir de su experiencia y el contexto"));
        desempenyos.add(new Desempenyo(10,
                "Obtiene información explícita y relevante   que está en distintas partes del texto escrito en inglés,distinguiéndola de otra cercana y semejante en diversos tipos de texto con estructura simple y   vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(10,
                "Infiere información de textos escritos en inglés deduciendo características de personajes, hechos, vida saludable, eventos presentes y pasados planes, comparación de objetos, lugares, clima  , personas, preferencias e intereses    a partir de información explicita  e implícita del texto así como  el significado de vocabulario  de uso frecuente"));
        desempenyos.add(new Desempenyo(10,
                "Interpreta el sentido global del texto a partir de información explícita identificando su contenido, acciones de sus personajes y el contexto."));
        desempenyos.add(new Desempenyo(10,
                "Reflexiona sobre el texto escrito que lee en inglés, opinando sobre el contenido y sentido de algunos recursos textuales, expresando sus preferencias sobre lo leído a partir de su experiencia y el contexto."));
        comp_23.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_23);

        Competencia comp_24 = competenciaRepository.findByCode(24);
        desempenyos.add(new Desempenyo(7,
                "Escribe textos breves en inglés considerando el tema y adecuando su texto al destinatario y propósito del texto utilizando vocabulario de uso frecuente. "));
        desempenyos.add(new Desempenyo(7,
                "Desarrolla sus ideas en torno a un tema aunque puede salirse de éste en ocasiones. Establece relaciones simples entre ideas usando algunos conectores de adición y vocabulario sencillo así como el uso de ilustraciones para contribuircon el sentido de su texto."));
        desempenyos.add(new Desempenyo(7,
                "Utiliza  algunas  convenciones  del  lenguaje  escrito  como recursos ortográficos básicos (el punto y la mayúscula) así como  construcciones  gramaticales  sencillas  que  le  dan claridad a su tema."));
        desempenyos.add(new Desempenyo(7,
                "Reflexiona sobre el texto que escribe en inglés, verificando el uso de palabras, frases y oraciones con el fin de mejorarlo."));
        desempenyos.add(new Desempenyo(8,
                "Escribe textos breves y sencillos en inglés de una extensión de 30 a 50 palabras considerando el tema y adecuando su texto    al    destinatario    y    de    acuerdo    al    propósito comunicativo utilizando vocabulario de uso frecuente."));
        desempenyos.add(new Desempenyo(8,
                "Desarrolla  sus  ideas  en  torno  a  un  tema  aunque  puede salirse   de   éste   en   ocasiones   repitiendo   información. Organiza sus ideas estableciendo relaciones simples entre éstas   en   especial   de   adición   y   contraste   utilizando conectores y vocabulario sencillo apoyándose en el uso de algunas  ilustraciones  para  contribuir  con  el  sentido  de  su texto."));
        desempenyos.add(new Desempenyo(8,
                "Utiliza  algunas  convenciones  del  lenguaje  escrito  como recursos ortográficos básicos (el punto y la mayúscula) así como  construcciones  gramaticales  sencillas  que  le  dan claridad a su tema."));
        desempenyos.add(new Desempenyo(8,
                "Reflexiona sobre el texto que escribe en inglés, verificando el uso de palabras, frases y oraciones revisando si se adecúa al propósito con el fin de mejorarlo."));
        desempenyos.add(new Desempenyo(9,
                "Escribe textos sencillos en inglés de una extensión de 30 a 50 palabras adecuando su texto al destinatario y de acuerdo al propósito comunicativo distinguiendo el registro formal e informal utilizando vocabulario cotidiano."));
        desempenyos.add(new Desempenyo(9,
                "Desarrolla  sus  ideas  en  torno  a  un  tema  de  acuerdo  al propósito  comunicativo.  Organiza  sus  ideas  en  oraciones estableciendo relaciones simples entre éstas en especial de en  especial  de  adición,  contraste  y  secuenciautilizando conectores apropiados y vocabulario de uso frecuente que contribuyen a dar sentido al texto."));
        desempenyos.add(new Desempenyo(9,
                "Utiliza algunas convenciones del lenguaje escrito como recursos ortográficos básicos (el punto, la coma y la mayúscula) así como construcciones gramaticales determinadas algunas simples y otras de mediana complejidad."));
        desempenyos.add(new Desempenyo(9,
                "Reflexiona sobre el texto que escribe en inglés, revisando si se adecúaal destinatario y propósito verificando el uso de palabras, frases y oraciones así como de algunos recursos ortográficos usados con el fin de mejorarlo. "));
        desempenyos.add(new Desempenyo(10,
                "Escribe textos sencillos en inglés de una extensión de 50 a 90   palabras   adecuando   su   texto   al   destinatario   y   de acuerdo    al    propósito    comunicativo    distinguiendo    el registro formal e informal utilizando vocabulario cotidiano."));
        desempenyos.add(new Desempenyo(10,
                "Desarrolla sus ideas en torno a un tema central ampliando la   información   de   acuerdo   al   propósito   comunicativo. Organiza   sus   ideas   en   oraciones   y   párrafos   cortos estableciendo relaciones lógicas entre éstas en especial de adición, contraste, secuencia y causautilizando conectores apropiados     y     vocabulario     de     uso     frecuente     que contribuyen a dar sentido al texto."));
        desempenyos.add(new Desempenyo(10,
                "Utiliza  algunas  convenciones  del  lenguaje  escrito  como recursos ortográficos  básicos  (punto  seguido,  punto  final, coma,  mayúscula)  así  como  construcciones  gramaticales determinadas    algunas    simples    y    otras    de    mediana complejidad."));
        desempenyos.add(new Desempenyo(10,
                "Reflexiona sobre el texto que escribe en inglés, revisando si se  adecúa  al  destinatario,  propósito  y  tema  verificando  la coherencia entre las ideas, el vocabulario empleado, el uso de   algunos   conectores   así   como   recursos   ortográficos usados con el fin de mejorar su texto."));
        comp_24.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_24);

        Competencia comp_25 = competenciaRepository.findByCode(25);
        desempenyos.add(new Desempenyo(5,
                "Expresa con agrado sus características físicas, preferencias y  gustos  y  siente  satisfacción  al  realizar  pequeñas  tareas solo."));
        desempenyos.add(new Desempenyo(5,
                "Expresa   las   costumbres   y   actividades   de   su   familia   y escuela, y se siente parte de ellas."));
        desempenyos.add(new Desempenyo(5,
                "Señala  las  emociones    que  siente,    las  manifiesta  y  regula  en interacción con sus compañeros y docente."));
        desempenyos.add(new Desempenyo(5,
                "Menciona  acciones  cotidianas  que  considera  buenas  o malas a partir de sus propias experiencias. "));
        desempenyos.add(new Desempenyo(5,
                "Se reconoce como niña o niño y se  relaciona con respeto con    sus    pares,    participando    de    juegos        sin    hacer distinciones de género."));
        desempenyos.add(new Desempenyo(5,
                "Expresa  afecto  a  las  personas  que  aprecia  y  acude  a  ellas cuando las necesita."));
        desempenyos.add(new Desempenyo(6,
                "Expresa  sus  características  físicas,  habilidades  y  gustos  y explica  aquello  que  le  gusta  desí  mismo.  Realiza  tareas sencillas solo y muestra disposición a asumir retos."));
        desempenyos.add(new Desempenyo(6,
                "Expresa  las  vivencias  y  manifestaciones  culturales  de  su familia, escuela y comunidad y las comparte con orgullo."));
        desempenyos.add(new Desempenyo(6,
                "Describe  las  emociones  a  partir  de  su  experiencia  y  de  lo que observa en los demás y las regula teniendo en cuenta las normas establecidas de manera conjunta."));
        desempenyos.add(new Desempenyo(6,
                "Identifica   acciones   que   le   causan   malestar   o   a   sus compañeros y las explica con razones sencillas. "));
        desempenyos.add(new Desempenyo(6,
                "Se reconoce como niña o niño, y se relaciona con respeto consus  pares,  señalando  que  todos  pueden  realizar  las mismas actividades tanto en la escuela como en la casa."));
        desempenyos.add(new Desempenyo(6,
                "Identifica  a  las  personas  que  le  muestran  afecto  y  que  le hacen sentir protegido y seguro,  recurre a ellas cuando las necesita."));
        desempenyos.add(new Desempenyo(7,
                "Describe  aquellas  características  personales,  cualidades, habilidades  y  logros  que  lo  hacen  sentirse  orgulloso  de  sí mismo,  reconociéndose  como  una  persona  valiosa  con características propias."));
        desempenyos.add(new Desempenyo(7,
                "Comparte   las   manifestaciones   culturales,   tradiciones   y costumbres  propias  de  su  familia  que  lo  hacen  sentirse orgulloso de su origen."));
        desempenyos.add(new Desempenyo(7,
                "Describe    sus    emociones    en    situaciones    cotidianas, reconociendo  sus  causas  y  consecuencias  y  las  regula haciendo uso de diversas estrategias."));
        desempenyos.add(new Desempenyo(7,
                "Identifica  situaciones  que  le  causas  agrado  o  desagrado  y explica     de     manera     sencilla     el     porqué     de     esos  comportamientos."));
        desempenyos.add(new Desempenyo(7,
                "Explica que los niños y las niñas  pueden asumir  las mismas responsabilidades  y  tareas  y  que  pueden  establecer  lazos de amistad."));
        desempenyos.add(new Desempenyo(7,
                "Reconoce  a  las  personas  a  quienes  se  puede  recurrir  en situaciones de riesgo o  en las que se vulnera su privacidad."));
        desempenyos.add(new Desempenyo(8,
                "Describe sus características físicas, cualidades e intereses, y su capacidad de obtener logros, manifestando que es una persona valiosa."));
        desempenyos.add(new Desempenyo(8,
                "Participa  con  seguridad  y  confianza  en  las  tradiciones, costumbres  y  prácticas  quecaracterizan  a  su  familia  y escuela mostrando aprecio por ellas."));
        desempenyos.add(new Desempenyo(8,
                "Relaciona  sus  emociones  con  su  comportamiento  y  el  de sus compañeros, menciona las causas de estas y las regula haciendo uso de diferentes  estrategias."));
        desempenyos.add(new Desempenyo(8,
                "Explica con argumentos sencillos por qué considera buenas o malas determinadas acciones."));
        desempenyos.add(new Desempenyo(8,
                "Se relaciona con niños y niñas  con igualdad, reconoce que puede   desarrollar   diversas   habilidades   a   partir   de   las experiencias vividas y fortalece sus relaciones de amistad."));
        desempenyos.add(new Desempenyo(8,
                "Distingue  situaciones  que afectan  su  privacidad  o  la  de otros  y  expresa  la  importancia  de  buscar  ayuda  cuando alguien no la respeta."));
        desempenyos.add(new Desempenyo(9,
                "Explica    sus    cualidades y características personales reconociendo  los  cambios  que  ha  experimentado,  y  las acepta como parte de su desarrollo."));
        desempenyos.add(new Desempenyo(9,
                "Se  identifica  con  los  diversos  grupos  a  los  que  pertenece como su familia, escuela y comunidad."));
        desempenyos.add(new Desempenyo(9,
                "Reconoce  que  sus  acciones  tienen  consecuencias  y  que generan diferentes emociones    así    mismo    y    a    sus compañeros,  usa estrategias de autorregulación"));
        desempenyos.add(new Desempenyo(9,
                "Da  razones  del  por  qué  una  acción  es  incorrecta  o  no  a partir  de  sus  experiencias    y  propone  acciones  que  se ajustan a los acuerdos establecidos."));
        desempenyos.add(new Desempenyo(9,
                "Se relaciona con las niñas y niños con igualdad,  respeto y cuidado del otro, e identifica aquellos mensajes que se dan y que generan desigualdad."));
        desempenyos.add(new Desempenyo(9,
                "Describe  situaciones  que  ponen  en  riesgo  su  integridad  y las conductas para evitarlas o protegerse."));
        desempenyos.add(new Desempenyo(10,
                "Explica los cambios corporales, sexuales y de personalidad (cualidades, gustos, fortalezas, limitaciones) que está experimentando, y los acepta como parte de su desarrollo."));
        desempenyos.add(new Desempenyo(10,
                "Expresa su pertenencia cultural a un país diverso"));
        desempenyos.add(new Desempenyo(10,
                "Describe las causas y consecuencias de sus emociones y las de  sus  compañeros  en  situaciones  reales  o  hipotéticas      y utiliza estrategias de autorregulación"));
        desempenyos.add(new Desempenyo(10,
                "Argumenta  su  postura  en  situaciones  propias  de  su  edad que  involucran  un  dilema moral,  tomando  en  cuenta  las normas sociales y principios éticos."));
        desempenyos.add(new Desempenyo(10,
                "Se   relaciona   con   sus   compañeros   y   compañeras   con igualdad,   reflexiona   sobre   situaciones      en   las   que   es necesario   sobreponerse   a   pérdidas   o   cambios   en   las relaciones."));
        desempenyos.add(new Desempenyo(10,
                "Muestra  conductas  que  laprotegen  de  situaciones  que ponen en riesgo su integridad en relación a su sexualidad."));
        comp_25.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_25);

        Competencia comp_26 = competenciaRepository.findByCode(26);
        desempenyos.add(new Desempenyo(5,
                "Comparte actividades con sus compañeros tratándolos con amabilidad y sin apartarlos por sus características físicas, y muestra interés por conocer acerca de la forma de vida de sus compañeros de aula."));
        desempenyos.add(new Desempenyo(5,
                "Pone en práctica responsabilidades que puede cumplir en el aula de acuerdo a su edad."));
        desempenyos.add(new Desempenyo(5,
                "Colabora  en  la  elaboración  de  acuerdos  y  normas  que reflejen  el  buen  trato  entre  compañeros  en  el  aula  y expresa su disposición a cumplirlas."));
        desempenyos.add(new Desempenyo(5,
                "Pone en práctica estrategias para manejar sus conflictos en el aula y recurre al adulto cercano cuando lo necesita."));
        desempenyos.add(new Desempenyo(5,
                "Delibera  sobre  asuntos  públicos  enfatizando  los  que  se generan en la convivencia del día a día y argumenta en base a sus experiencias personales e información cotidiana. Elige la opción más beneficiosa para todos."));
        desempenyos.add(new Desempenyo(5,
                "Participa  en  actividades  colectivas  orientadas  a  un  logro común a partir de la identificación de necesidades comunes del aula."));
        desempenyos.add(new Desempenyo(6,
                "Establece relaciones con sus compañeros, sin ofenderlos ni maltratarlos y reconoce que los niños tienen derecho a ser cuidados y tratados con afecto."));
        desempenyos.add(new Desempenyo(6,
                "Muestra interés por conocer acerca de la cultura de otras personas con las que se vincula."));
        desempenyos.add(new Desempenyo(6,
                "Realiza sus responsabilidades y evalúa el cumplimiento de las mismas."));
        desempenyos.add(new Desempenyo(6,
                "Colabora  en  la  elaboración  de  acuerdos  y  normas  que reflejen  el  buen  trato  entre  compañeros  en  el  aula  y  las cumple."));
        desempenyos.add(new Desempenyo(6,
                "Pone   en   práctica   estrategias   para   la   resolución   de conflictos en el aula y recurre al adulto en caso lo necesite."));
        desempenyos.add(new Desempenyo(6,
                "Delibera  sobre  asuntos  públicos  enfatizando  en  aquellos que  involucran  una  problemática  de  grupo  y  argumenta desde su experiencia previa y usando razones que van más allá del agrado o desagrado. Elige la postura que beneficie a todos los miembros del aula."));
        desempenyos.add(new Desempenyo(6,
                "Participa en acciones que fomentan el reconocimiento y el respeto  de  sus  derechos  como  niño:  a  la  vida,  al  nombre (tener DNI), a la recreación, a la educación, al buen trato y a la salud, a partir de situaciones cotidianas."));
        desempenyos.add(new Desempenyo(7,
                "Muestra    un    trato    respetuoso    e    inclusivo    con    sus compañeros   de   aula   y   expresa   su   desacuerdo   con situaciones  de  maltrato  que  se  dan  entre  niños  de  su escuela."));
        desempenyos.add(new Desempenyo(7,
                "Expresa  interés  al  conocer  la  manera  de  vivir  de  otros pueblos, de su comunidad o región."));
        desempenyos.add(new Desempenyo(7,
                "Pone  en  práctica  responsabilidades  en  el  aula  y  da  ideas sobre cómo mejorar el trabajo de los demás"));
        desempenyos.add(new Desempenyo(7,
                "Participa  en  la   elaboración  de  acuerdos  y  normas  de convivencia  enel  aula,  escuchando  las  propuestas  de  sus compañeros; explica la  importancia de la participación  de todos en su elaboración."));
        desempenyos.add(new Desempenyo(7,
                "Interviene   al   observar   un   conflicto   que   se   da   entre compañeros  recurriendo  al  diálogo  o  a  un  adulto  cercano para que intervenga cuando es necesario. Expresa sus ideas para hallar soluciones."));
        desempenyos.add(new Desempenyo(7,
                "Delibera  sobre  asuntos  públicos  enfatizando  en  aquellos que  involucran  a  todos  los  miembros  de  su  escuela  y comunidad."));
        desempenyos.add(new Desempenyo(7,
                "Sustenta  su  opinión  en  la  idea  del  bienestar  de  todos  y apoya la postura máscercana al bien común."));
        desempenyos.add(new Desempenyo(7,
                "Participa  en  acciones  para  alcanzar  un  objetivo  común  a partir de la identificación de necesidades de la escuela."));
        desempenyos.add(new Desempenyo(7,
                "Recurre a mecanismos de participación en su escuela para concretar sus acciones para el bien de todos."));
        desempenyos.add(new Desempenyo(8,
                "Establece relaciones con sus compañeros, sin maltratarlos y expresa su desacuerdo frente a situaciones de maltrato a los niños."));
        desempenyos.add(new Desempenyo(8,
                "Muestra    interés    y    agrado    por    las    manifestaciones culturales en el país."));
        desempenyos.add(new Desempenyo(8,
                "Fomenta cotidianamente que él y sus compañeros cumplan sus responsabilidades en el aula."));
        desempenyos.add(new Desempenyo(8,
                "Participa  en  la  elaboración  y  evaluación  de  acuerdos  y normasde convivencia en el aula, a partir de las propuestas de  sus  compañeros;  explica  la  importancia  de  que  las normas  ayudan  a  convivir  en  armonía  y  de  que  todos participen."));
        desempenyos.add(new Desempenyo(8,
                "Comprende  que  los  conflictos  son  parte  de  las  relaciones entre las personas y proponealternativas de solución a los conflictos por los que atraviesa haciendo uso del diálogo y buscando  la  intervención  de  mediadores  cuando  lo  crea necesario."));
        desempenyos.add(new Desempenyo(8,
                "Delibera  sobre  asuntos  públicos  enfatizando  en  aquellos que involucran a todos los miembros de su comunidad"));
        desempenyos.add(new Desempenyo(8,
                "Sustenta su opinión en la idea que todos tenemos derechos y  responsabilidades;  escucha  la  opinión  de  los  demás  y apoya  la  postura  que  considera  más  favorable  para  el beneficio de todos."));
        desempenyos.add(new Desempenyo(8,
                "Participa en acciones orientadas al bien común y la defensa de los derechos del niño, especialmente los establecidos en la  Convención  sobre  los  Derechos  del  Niño.  Para  ello,  usa mecanismos de participación propios de la escuela."));
        desempenyos.add(new Desempenyo(9,
                "Establece relaciones con sus compañeros sin discriminarlos y    expresa    su    desacuerdo    frente    a    situaciones    de discriminaciónen la escuela."));
        desempenyos.add(new Desempenyo(9,
                "Muestra interés por conocer el origen y sentido de algunas costumbres de sus compañeros de origen cultural distinto al suyo."));
        desempenyos.add(new Desempenyo(9,
                "Colabora   en   el   seguimiento   al   cumplimiento   de   las responsabilidades en el aula."));
        desempenyos.add(new Desempenyo(9,
                "Participa  en  la  construcción  y  evaluación  de  normas  de convivencia en el aula teniendo en cuenta los derechos del niño."));
        desempenyos.add(new Desempenyo(9,
                "Comprende  que  muchos  conflictos  se  originan  por  no reconocer   a   los   otros   como   sujetos   con   los   mismos derechos y por falta de control de las emociones, y utiliza el diálogoy la negociación para superar los conflictos."));
        desempenyos.add(new Desempenyo(9,
                "Delibera  sobre  asuntos  públicos  enfatizando  en  aquellos que  involucran  a  todos  los  miembros  de  su  comunidad  y región"));
        desempenyos.add(new Desempenyo(9,
                "Sustenta su posición reconociendo el punto de vista de los actores  involucrados  y  aporta  a la  construcción  de  una postura común."));
        desempenyos.add(new Desempenyo(9,
                "Participa  con  sus  compañeros  en  acciones  orientadas  a  la solidaridad y la protección de los derechos, especialmente los de las personas vulnerables, mediante mecanismos de participación estudiantil."));
        desempenyos.add(new Desempenyo(10,
                "Establece relaciones con sus compañeros sin discriminarlos y expresa su desacuerdo frente a prejuicios y estereotipos más comunes en su entorno."));
        desempenyos.add(new Desempenyo(10,
                "Muestra  disposición  a  acercarse  a  una  persona  de  una cultura distinta para aportar y tratar de aprender de ella."));
        desempenyos.add(new Desempenyo(10,
                "Evalúa el cumplimiento de las responsabilidades."));
        desempenyos.add(new Desempenyo(10,
                "Participa en la construcción y evaluación de los acuerdos y normas basándose en los derechos del niño, considerando las  características  e  intereses  de  todos  sus  compañeros  y comprende    que    las    normas    facilitan    la    convivencia armónica en el aula y escuela."));
        desempenyos.add(new Desempenyo(10,
                "Distingue un conflicto de una agresión, y recurre al diálogo, la  igualdad  o  equidad  para  proponer  alternativas,  y  a   mediadores  para  la  solución  de  conflictos,  y  argumenta sobre su importancia."));
        desempenyos.add(new Desempenyo(10,
                "Delibera   sobre   asuntos   de   interés   público   en   donde argumenta su posición, considera la opinión de los demás y aporta a la construcción de una postura común, a partir del   reconocimiento   de   la   institucionalidad   y   de   los principios   democráticos   (igualdad,   libertad,   pluralismo, etc.)."));
        desempenyos.add(new Desempenyo(10,
                "Propone,  a  partir  de  un  diagnóstico,  acciones  colectivas orientadas  al  bien  de  la  escuela  o  la  comunidad  y  la protección de los derechos, especialmente los establecidos en la Convención sobre los Derechos del Niño."));
        desempenyos.add(new Desempenyo(10,
                "Explica    y    evalúa    los    mecanismos    de    participación estudiantil y las acciones que desarrollan sus representantes estudiantiles."));
        comp_26.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_26);

        Competencia comp_27 = competenciaRepository.findByCode(27);
        desempenyos.add(new Desempenyo(5,
                "Obtiene  información  sobre  el  mismo  o  diversos  hechos cotidianos del pasado a partir del testimonio oral de dos o más personas, así como de objetos en desuso, fotografías, etcétera."));
        desempenyos.add(new Desempenyo(5,
                "Ordena  hechos  o  acciones  cotidianas  usando  expresiones que  hagan  referencia  al  paso  del  tiempo:  minutos,  horas, semanas  y  meses;  ayer,  hoy,  mañana;  al  inicio,  al  final; mucho tiempo, poco tiempo."));
        desempenyos.add(new Desempenyo(5,
                "Describe acontecimientos de su historia o de la de otros en los  que  compara  el  presente  y  el  pasado,  identificando algunas de las causas y posibles consecuencias de estos."));
        desempenyos.add(new Desempenyo(6,
                "Obtiene  información  de  imágenes  antiguas  y  testimonios de    personas,    reconociendo    que    estos    le    brindan información sobre el pasado."));
        desempenyos.add(new Desempenyo(6,
                "Distingue en su vida cotidiana aquellas actividades que son más   largas   que   otras.   Secuencia   hechos   o   acciones cotidianas e identifica las cosas que han cambiado y las que continúan. Describe acciones o fenómenos que transcurren en el mismo tiempo."));
        desempenyos.add(new Desempenyo(6,
                "Describe acontecimientos de su historia o de la de otros en los  que  compara  el  presente  y  el  pasado,  identificando algunas de las causas y posibles consecuencias de estos."));
        desempenyos.add(new Desempenyo(7,
                "Obtiene información sobre el pasado en textos cortos, así como en edificios antiguos y conjuntos arqueológicos de la localidad, valorando su importancia e identificando al autor o colectivo humano que los produjeron."));
        desempenyos.add(new Desempenyo(7,
                "Secuencia  cambios  concretos  que  ha  experimentado  la humanidad   en   diversos   aspectos   aplicando   conceptos relacionados con el tiempo: pasado, presente, futuro."));
        desempenyos.add(new Desempenyo(7,
                "Narra  hechos  o  procesos  históricos  claves  en  su  región, reconociendo más de una causa y algunas consecuencias."));
        desempenyos.add(new Desempenyo(8,
                "Obtiene información sobre hechos concretos en fuentes de divulgación y difusión histórica (enciclopedias, web, libros de texto, videos), y la utiliza para responder sus preguntas."));
        desempenyos.add(new Desempenyo(8,
                "Describe algunas características que muestran el cambio y la  permanencia  en  diversos  aspectos  de  la  vida  cotidiana. Identifica distintos ritmos de cambio en diferentes objetos."));
        desempenyos.add(new Desempenyo(8,
                "Narra  hechos  o  procesos  históricos,  incorporando  más  de un aspecto. Explica la importancia que tiene en su vida los hechos de la historia de su comunidad o región."));
        desempenyos.add(new Desempenyo(9,
                "Obtiene información sobre determinados hechos históricos   a   partir   de   cuadros   estadísticos   y   gráficossencillos,  libros  de  síntesis  o  investigaciones  históricas. Identifica  en  qué  se  diferencian  las  narraciones  sobre  un mismo acontecimiento del pasado."));
        desempenyos.add(new Desempenyo(9,
                "Identifica  cambios  y  permanencias  en  distintas  épocas. Reconoce   el   nacimiento   de   Cristo   como   punto   de referencia para contar años en la cultura occidental y utiliza las convenciones de década y siglo para hacer referencia al tiempo, así como la denominación y orden de las grandes etapas convencionales que dividen la historia nacional."));
        desempenyos.add(new Desempenyo(9,
                "Elabora  explicaciones  sobre  hechos  o  procesos  históricos, reconociendo  la  participación  de  hombres  y  mujeres  en dichos   acontecimientos.   Identifica   algunas   causas   de dichos hechos o procesos que tienen su origen en acciones individuales y otras que se originan en acciones colectivas."));
        desempenyos.add(new Desempenyo(10,
                "Selecciona  las  fuentes  que  le  proporcionan  información sobre  un  hecho  o  proceso  histórico  y las  ubica  en  el momento  en  que  se  produjeron.  Explica  las  diferencias entre las versiones que las fuentes presentan."));
        desempenyos.add(new Desempenyo(10,
                "Identifica algunas características que le permiten distinguir entre los periodos históricos. Secuencia distintos hechos de la  historia  local,  regional,  nacional,  identificando  aquellos que  sucedieron  al  mismo  tiempo  en  lugares  diferentes  y explicando la relación entre ellos."));
        desempenyos.add(new Desempenyo(10,
                "Elabora explicaciones coherentes sobre hechos o procesos históricos    peruanos,    identificando    algunas    causas    y consecuencias  cuya  aparición  es  inmediata  y  otras  que aparecen a largo plazo. "));
        desempenyos.add(new Desempenyo(10,
                "Utiliza  conceptos  sociopolíticos  que  se  encarnan  en  un personaje."));
        comp_27.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_27);

        Competencia comp_28 = competenciaRepository.findByCode(28);
        desempenyos.add(new Desempenyo(5,
                "Describe  los  elementos  naturales  y  sociales  del  espacio donde realiza sus actividades cotidianas."));
        desempenyos.add(new Desempenyo(5,
                "Se  desplaza  en  su  espacio  cotidiano usando  puntos  de referencia."));
        desempenyos.add(new Desempenyo(5,
                "Representa  de  diversas  maneras  su  espacio  cotidiano, utilizando puntos de referencia."));
        desempenyos.add(new Desempenyo(5,
                "Menciona problemas ambientales que afectan a su espacio cotidiano  y  los  efectos  en  su  vida.  Reconoce  y  sigue  las señales de evacuación ante una emergencia."));
        desempenyos.add(new Desempenyo(6,
                "Da   ejemplos   de   relaciones   simples   entre   elementos naturales    y    sociales    del    espacio    donde    realiza    sus actividades cotidianas."));
        desempenyos.add(new Desempenyo(6,
                "Se  desplaza  en  su  espacio  cotidiano  usando  puntos  de referencia."));
        desempenyos.add(new Desempenyo(6,
                "Representa  de  diversas  maneras  su  espacio  cotidiano, utilizando puntos de referencia."));
        desempenyos.add(new Desempenyo(6,
                "Identifica posibles causas yconsecuencias de los problemas ambientales y de los peligros naturales o provocados por el ser humano, que afectan a su espacio cotidiano y desarrolla actividades sencillas para cuidarlo."));
        desempenyos.add(new Desempenyo(7,
                "Distingue   los   elementos   naturales   y   sociales   de   su localidad,  asociando  recursos  naturales  con  actividades económicas."));
        desempenyos.add(new Desempenyo(7,
                "Identifica  los elementos  que  están  presentes  en  planos  y mapas     y     reconoce     los     cuatro     puntos     cardinales relacionando la posición del Sol consigo mismo."));
        desempenyos.add(new Desempenyo(7,
                "Describe    los    problemas    ambientales    y    los    peligros frecuentes  de  su  escuela.  Realiza  actividades  específicas para cuidar el ambiente en su escuela."));
        desempenyos.add(new Desempenyo(8,
                "Describe  los  espacios  urbanos  y  rurales  de  su  localidado región,  reconociendo  los  elementos  naturales  y  sociales que componen cada uno."));
        desempenyos.add(new Desempenyo(8,
                "Utiliza  mapas  físico-políticos  para  ubicar  elementos  en  el espacio.   Representa   de   diversas   maneras   el   espacio geográfico tomando en cuenta los elementos cartográficos."));
        desempenyos.add(new Desempenyo(8,
                "Establece  las  causas  y  consecuencias  de  los  problemas ambientales y las relaciones que hay entre ellas."));
        desempenyos.add(new Desempenyo(8,
                "Realiza actividades concretas para el cuidado del ambiente y participa en actividades para la prevención ante peligros."));
        desempenyos.add(new Desempenyo(9,
                "Describe las relaciones que se establecen entre los espacios urbanos y rurales, y las características de la población que habita en ellos."));
        desempenyos.add(new Desempenyo(9,
                "Interpreta   mapas   físico-políticos,   cuadros,   gráficos   e imágenes para obtener información geográfica."));
        desempenyos.add(new Desempenyo(9,
                "Explica  qué  es  una  problemática  ambiental  y  qué  una territorial.  Organiza  actividades  para  el  manejo  adecuado de   residuos   en   su   escuela y   propone   acciones   para disminuir los riesgos en su escuela."));
        desempenyos.add(new Desempenyo(10,
                "Compara los espacios geográficos de su localidad, región y país,  y  explica  cómo  los  distintos  actores  sociales  los modifican."));
        desempenyos.add(new Desempenyo(10,
                "Elabora  mapas  temáticos  de  una  sola  variable  a  partir  de información obtenida en diversas fuentes."));
        desempenyos.add(new Desempenyo(10,
                "Identifica las causas y consecuencias de una problemática ambiental  y  una  territorial.  Fomenta  cotidianamente  el cuidado    del    ambiente    mediante    acciones    diversas. Organiza acciones para disminuir los riesgos en su escuela."));
        comp_28.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_28);

        Competencia comp_29 = competenciaRepository.findByCode(29);
        desempenyos.add(new Desempenyo(5,
                "Explica  las  ocupaciones  económicas  que  desarrollan  las personas de su espacio cotidiano y cómo estas atienden las necesidades de las personas y la comunidad."));
        desempenyos.add(new Desempenyo(5,
                "Utiliza  responsablemente  los  recursos  que  le  brinda  su familia y la escuela, reconociendo que estos se agotan."));
        desempenyos.add(new Desempenyo(6,
                "Explica cómo algunas instituciones satisfacen las necesidades de su familia. Comprende que todo producto tiene un costo y que al obtenerlo se debe retribuir por ello (dinero/trueque),   asimismo   identifica   acciones   que   le permiten el ahorro, cuidado y preservación de recursos en su familia y su escuela."));
        desempenyos.add(new Desempenyo(6,
                "Participa  del  ahorro  de  recursos  en  el  aula  para cubrir  las necesidades del grupo, reconoce que dichos recursos que se  consumen  en  su  hogar  y  escuela  tienen  un  costo  y  los usa con responsabilidad."));
        desempenyos.add(new Desempenyo(7,
                "Explica que el trabajo que realizan sus  familiares y demás personas   permiten   la   obtención   de   ciertos   bienes   y servicios  con  la  finalidad  de  satisfacer  las  necesidades  de consumo.  Explica  que  las  acciones  de  ahorro  contribuyen en su economía familiar."));
        desempenyos.add(new Desempenyo(7,
                "Usa de manera responsable los recursos dado que estos se agotan  y  realiza  acciones  cotidianas  de  ahorro  del  uso  de bienes  y  servicios  que  se  consumen  en  su  hogar  y  su escuela, señalando ejemplos en donde la publicidad busca influir en dichos consumos."));
        desempenyos.add(new Desempenyo(8,
                "Describe los roles económicos que cumplen las personas de su comunidad (consumidor,     vendedor,     comprador, productor de bienes y servicios) y explica cómo estos roles y  algunas  situaciones  económicas  (por  ejemplo,  la  subida del  precio  de  los  combustibles,  entre  otros)  inciden  en  la satisfacción de necesidades de las otras personas."));
        desempenyos.add(new Desempenyo(8,
                "Desarrolla  acciones  para  el  cuidado  de  los  recursos  de  su aula   y   escuela   reconociendo   que   estos   le   permiten satisfacer  necesidades.  Establece  una  meta  personal  de ahorro e inversión, diferenciando para ello las necesidades de los deseos de consumo. "));
        desempenyos.add(new Desempenyo(9,
                "Explica cómo las personas y las empresas cumplen distintos roles   económicos,   se   organizan,   producen   bienes   y servicios,  haciendo  uso  del  dinero  para  la  adquisición  de estos."));
        desempenyos.add(new Desempenyo(9,
                "Promueve  la  importancia  del  ahorro  y  la  inversión  de recursos, así como y la cultura del pago de impuestos y de las  deudas  contraídas.  Explica  cómo  influye  el  rol  de  la publicidad en sus decisiones de consumo."));
        desempenyos.add(new Desempenyo(10,
                "Explica cómo el Estado promueve y garantiza los intercambios económicos y como las empresas  producen bienes   y   servicios   para   contribuir   al   desarrollo   de   la sociedad. Expresa que el uso inadecuado del dinero afecta el bienestar de las personas y las familias."));
        desempenyos.add(new Desempenyo(10,
                "Promueve y formula planes de ahorro e inversión personal de  acuerdo  con  metas  trazadas.  Demuestra  con  acciones concretas  de  por  qué  es  importante  ser  un  consumidor informado."));
        comp_29.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_29);

        Competencia comp_30 = competenciaRepository.findByCode(30);
        desempenyos.add(new Desempenyo(5,
                "Observa,  escucha  y  disfruta  de  los  estímulos  visuales, táctiles, sonoros y kinestésicos en la naturaleza, el entorno y en manifestaciones artísticas con lasque interactúa."));
        desempenyos.add(new Desempenyo(5,
                "Hace preguntas sobre manifestaciones artístico-culturales de su entorno local y comprende que transmiten historias de un determinado tiempo y lugar."));
        desempenyos.add(new Desempenyo(5,
                "Responde    a    los    estímulos    sensoriales    que    percibe, comunicando  sus  ideas  sobre  ellos  o  recreándolos  de manera   libre,   a   través   de   dibujos,   sonidos,   expresión corporal. Identificasus preferencias acerca de manifestaciones     artístico-culturales     que     observa     o experimenta. "));
        desempenyos.add(new Desempenyo(6,
                "Observa,  escucha,  describe  y  registra  los  elementos básicos   del   arte   (como   líneas,   formas,   sonidos   y movimientos)   que   encuentra   en   su   entorno   y   en manifestaciones artísticas con las que interactúa y los asocia a ideas y sentimientos.  "));
        desempenyos.add(new Desempenyo(6,
                "Hace   preguntas   y   contribuye   a   discusiones   sobre contextos  históricos  y  culturales  de  manifestaciones artístico-culturales y comprende que transmiten ideas y sentimientos. "));
        desempenyos.add(new Desempenyo(6,
                "Comunica ideas y genera hipótesis sobre el significado de  manifestaciones artístico-culturales  en  base  a  sus observaciones y las sensaciones que les genera. Explica sus preferencias por algunas de ellas.  "));
        desempenyos.add(new Desempenyo(7,
                "Identifica  y  describe  los  elementos  básicos  del  arte  que encuentra  en  su  entorno  y  en manifestaciones artístico-culturales  diversas  y  especula  sobre  los  procesos  que  el artista  ha  usado  para  crear  su  obra.  Reconoce  que  los elementos pueden trasmitir diversas sensaciones. "));
        desempenyos.add(new Desempenyo(7,
                "Investiga los procesos y técnicas usadas en manifestaciones artístico-culturales   de   su   comunidad   e   identifica   sus distintos  usos  y  propósitos  (ritual,  recreativo,  comercial, decorativo, utilitario, etc.). "));
        desempenyos.add(new Desempenyo(7,
                "Comenta   sobre   los   posibles   significados   en   base   a   lo investigado y emite una opinión personal sobre ella."));
        desempenyos.add(new Desempenyo(8,
                "Describe y analiza los elementos del arte que identifica en el entorno y en manifestaciones artístico-culturales usando vocabulario propio de los lenguajes del arte e identifica los medios   utilizados.   Relaciona   los   elementos   a   ideas, mensajes y sentimientos. "));
        desempenyos.add(new Desempenyo(8,
                "Investiga  el  significado  de  los  símbolos  y  características principales    de    manifestaciones    artístico-culturales    de diferentes  lugares  y  tiempos  y  comprende  que  cumplen diversos propósitos y que comunican ideas sobre la cultura en que fue creada."));
        desempenyos.add(new Desempenyo(8,
                "Comenta   sobre   la   manera   en   que   los   elementos,   los procesos, los medios y las técnicas usadas comunican ideas y  genera  hipótesis  sobre  el  significado  y  la  intención  del artista."));
        desempenyos.add(new Desempenyo(9,
                "Describe  las  características  de  manifestaciones  artístico-culturales  que  observa,  analiza  sus  elementos  e  interpreta las ideas y sentimientos que transmiten."));
        desempenyos.add(new Desempenyo(9,
                "Indaga  sobre  los  contextos  de  diversas  manifestaciones artístico-culturales  e  identifica  cómo  el  arte  nos  ayuda  a conocer las creencias, valores o actitudes de un artista o una sociedad. "));
        desempenyos.add(new Desempenyo(9,
                "Genera  hipótesis  sobre  el significado  y  la  intención  de  una manifestación artístico-cultural e incorpora la opinión de los demás para reformular sus opiniones sobre ella."));
        desempenyos.add(new Desempenyo(10,
                "Describe y analiza las cualidades de los elementos visuales, táctiles,  sonoros,  kinestésicos  que  percibe  en manifestaciones artístico-culturales y establece relaciones entre  sus  hallazgos  y  las  ideas  y  emociones  que  estas  le generan."));
        desempenyos.add(new Desempenyo(10,
                "Investiga en diversas fuentes acerca del origen y formas en que  manifestaciones  artístico-culturales  tradicionales  y contemporáneas   transmiten   las   características   de   una sociedad."));
        desempenyos.add(new Desempenyo(10,
                "Desarrolla  y  aplica  criterios  relevantes  para  evaluar  una manifestación   artística   en   base   a   la   información   que maneja sobre su forma y contexto de creación y ensaya una postura personal frente a ella."));
        comp_30.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_30);

        Competencia comp_31 = competenciaRepository.findByCode(31);
        desempenyos.add(new Desempenyo(5,
                "Experimenta    con    los    medios,    materiales    y    técnicas artísticas para crear efectos visuales, sonoros o vocales en respuesta a estímulos del profesor o en base a sus propias exploraciones."));
        desempenyos.add(new Desempenyo(5,
                "Explora  ideas  libremente  a  partir  de  su  imaginación,  sus experiencias  u  observaciones  y  experimenta  o  memoriza maneras  en  que  los  elementos  del  arte  (movimientos, acciones,  formas,  colores  o  sonidos)  pueden  usarse  o  ser repetidos para comunicar una idea. "));
        desempenyos.add(new Desempenyo(5,
                "Presenta sus trabajos y creaciones y responde a preguntas sencillas sobre ellos, describiendo las características de sus propios trabajos y el trabajo de sus compañeros."));
        desempenyos.add(new Desempenyo(6,
                "Explora  e  improvisa  con  maneras  de  usar  los  medios  y materiales y técnicas artísticas y descubre que pueden ser utilizados para expresar ideas y sentimientos."));
        desempenyos.add(new Desempenyo(6,
                "Genera ideas a partir de intereses, experiencias personales y de la observación de su entorno natural y social. Empieza a    seleccionar    y    organizar    elementos    (movimientos, acciones o efectos visuales o sonoros) para presentar una idea de una manera en particular."));
        desempenyos.add(new Desempenyo(6,
                "Presenta  sus  trabajos  y  creaciones  en  forma  individual  y grupal  y  describe  de manera  sencilla  cómo  ha  creado  y organizado su trabajo."));
        desempenyos.add(new Desempenyo(7,
                "Improvisa   y   experimenta   con   maneras   de   usar   los elementos del arte y reconoce los efectos que puede lograr combinando  diversos  medios,  materiales,  herramientas, técnicas para comunicar ideas."));
        desempenyos.add(new Desempenyo(7,
                "Planifica  sus  proyectos  basándose  en  las  maneras  en  que otros  artistas  han  usado  los  elementos  del  arte  y  las técnicas  (por  ejemplo  en  prácticas  artísticas  tradicionales de su comunidad) para comunicar sus propias experiencias o    sentimientos.    Improvisa,    experimenta    y    combina diversos  elementos,  medios,  materiales  y  técnicas  para descubrir cómo puede comunicar una idea. "));
        desempenyos.add(new Desempenyo(7,
                "Presenta el desarrollo de alguna idea o temática específica en   sus   procesos   de   improvisación   y   experimentación. Dialoga  sobre  las  técnicasque  ha  usado  y  las  influencias que han tenido otros trabajos artísticos sobre sus propias creaciones."));
        desempenyos.add(new Desempenyo(8,
                "Combina  y  busca  alternativas  para  usar  elementos  de  los lenguajes   artísticos,   medios,  materiales,  herramientas, técnicas,recursostecnológicosasualcance,así́comoprácticas  tradicionales  de  su  comunidad  para  expresar  de diferentes maneras sus ideas."));
        desempenyos.add(new Desempenyo(8,
                "Desarrolla sus ideas a partir de observaciones, experiencias y  el  trabajo  artístico  de  otros,  seleccionando  elementos  y materiales  para  componer  una  imagen  de  acuerdo  a  sus intenciones. "));
        desempenyos.add(new Desempenyo(8,
                "Registra sus procesos y planifica maneras de presentar sus trabajos  para  comunicar  sus  ideas  efectivamente,  donde asume  un  rol  específico.  Reflexiona  sobre  las  razones  por las que ha seleccionado medios, materiales, herramientas y técnicas específicas en sus trabajos y evalúa con criteriosdados si logró su propósito."));
        desempenyos.add(new Desempenyo(9,
                "Explora los elementos de los lenguajes de las artes visuales, la  música,  el  teatro  y  la  danza  y  los  aplica  con  fines expresivos  y  comunicativos.  Prueba  y  propone  formas  de utilizar los medios, materiales, herramientas y técnicas con fines expresivos y comunicativos. "));
        desempenyos.add(new Desempenyo(9,
                "Genera  ideas  a  partir  de  estímulos  y  fuentes  diversas (tradicionales,  locales  y  globales)  y  planifica  su  trabajo artístico tomando   en   cuenta   la   información   recogida. Manipula   una   serie   de   elementos,   medios,   técnicas, herramientas  y  materiales  para  desarrollar  trabajos  que comunican ideas a una audiencia específica."));
        desempenyos.add(new Desempenyo(9,
                "Registra las cualidades y las influencias de sus creaciones ylas  presenta  de  diversas  maneras.  Asume  roles  en  las diversas fases del proyecto artístico y evalúa el impacto de sus   acciones   en   el   resultado   de   sus   creaciones   o presentaciones. "));
        desempenyos.add(new Desempenyo(10,
                "Explora los elementos de los lenguajes de las artes visuales, la   música,   el   teatro   y   la   danza   y   combina   medios, materiales, herramientas y técnicas y recursos tecnológicos con fines expresivos y comunicativos."));
        desempenyos.add(new Desempenyo(10,
                "Realiza  creaciones  individuales  y  colectivas,  basadas  en  la observación y en el estudio del entorno natural, artístico y cultural  local  y  global.    Combina  y  propone  formas  de utilizar los  elementos,  materiales,  y  técnicas  y  recursos tecnológicos para resolver problemas creativos planteados en su proyecto, incluyendo propuestas de artes integradas."));
        desempenyos.add(new Desempenyo(10,
                "Documenta   la   manera   en   que   sus   ideas   se   han   ido desarrollando y cuáles han sido sus influencias. Planifica la manera   en   que   desea   mostrar   el   resultado   de   sus investigaciones  y  creaciones  y  mejora  su  presentación  a partir de su propia auto-evaluación y la retroalimentación que recibe de otros. Evalúa el resultado de sus creaciones o presentaciones."));
        comp_31.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_31);

        Competencia comp_32 = competenciaRepository.findByCode(32);
        desempenyos.add(new Desempenyo(5,
                "Hace  preguntas  acerca  de  hechos,  fenómenos  u  objetos naturales  y  tecnológicos  que  explora  y  observa  en  su entorno,   plantea   posibles   respuestas   al   describir   sus predicciones, en base a sus experiencias. "));
        desempenyos.add(new Desempenyo(5,
                "Propone acciones para buscar información y los materiales y  herramientas  que  necesitará  para  explorar  y  observar objetos, hechos o fenómenos, recoger datos y responder a la pregunta.  "));
        desempenyos.add(new Desempenyo(5,
                "Obtiene  datos a partir de la observación y exploración de objetos, hechos o fenómenos, los registra en organizadoresusando  dibujos,  o  primeras  formas  de  escritura.  Toma  en cuenta las medidas de seguridad."));
        desempenyos.add(new Desempenyo(5,
                "Describe  características  del  hecho,  fenómeno  u  objeto  natural  y  tecnológico  explorado  u  observado,  para  dar posibles explicaciones."));
        desempenyos.add(new Desempenyo(5,
                "Comunica  al  describir  lo  que  hizo,  lo  que  aprendió,  los logros y dificultades que tuvo en la indagación realizada de manera oral, a través de dibujos o su nivel de escritura. "));
        desempenyos.add(new Desempenyo(6,
                "Hace  preguntas  que  relacionan  las  características  de  los objetos, hechos o fenómenos que explora y observa en su entorno,  plantea  posibles  respuestas, comparándolos  con otros objetos, hechos o fenómenos."));
        desempenyos.add(new Desempenyo(6,
                "Propone acciones para buscar información y las organiza en una secuencia de pasos para llevarlas a cabo, selecciona los materiales  y  herramientas  que  necesitará  para  explorar  y observar  objetos,  hechos  o  fenómenos,  recoger  datos  y responder a la pregunta. "));
        desempenyos.add(new Desempenyo(6,
                "Obtiene    datos,  al  llevar  a  cabo  las  acciones  que  organizó con  los  materiales  y  herramientas  seleccionadas  y  los registra  en  organizadores  o  los  representa  en  dibujos,  o primeras formas de escritura. Toma en cuenta las medidas de seguridad. "));
        desempenyos.add(new Desempenyo(6,
                "Compara  y  establece  si  hay  diferencia  entre  su  posible respuesta  con  los  datos  o  información  obtenida  en  su observación o experimentación y elabora sus conclusiones."));
        desempenyos.add(new Desempenyo(6,
                "Comunica al describir los logros y dificultades que tuvo, los resultados  y  lo  aprendido  en  la  indagación  realizada  de manera oral, a través de dibujos o su nivel de escritura."));
        desempenyos.add(new Desempenyo(7,
                "Hace  preguntas  acerca  de  hechos,  fenómenos  u  objetos naturales  y  tecnológicos  que  explora  y  observa en  su entorno,   plantea   posibles   respuestas   al   describir   sus predicciones, en base a sus experiencias."));
        desempenyos.add(new Desempenyo(7,
                "Propone un plan de acción donde describe las  estrategias que le permitan comprobar la posible respuesta, selecciona herramientas, materiales  y fuentes de información."));
        desempenyos.add(new Desempenyo(7,
                "Obtiene   datos   cualitativos/cuantitativos   con   diferentes instrumentos,  los  registra  y  representa  en  organizadores. Considera instrucciones de seguridad."));
        desempenyos.add(new Desempenyo(7,
                "Establece   relaciones   de   causalidad   entre   los   factores relacionados   al   problema    estudiadoa   partir   de   la comparación de sus resultados con la hipótesis planteada y elabora sus conclusiones."));
        desempenyos.add(new Desempenyo(7,
                "Comunica   al   describir   el procedimiento, logros y dificultades que tuvo durante el desarrollo    de    la indagación,  propone mejoras y comunica lo aprendido en forma oral y escrita, usando conocimientos científicos."));
        desempenyos.add(new Desempenyo(8,
                "Hace  preguntas  acerca  de  un    hecho,  fenómeno  u  objeto natural  o  tecnológico  que  observa  y  elabora  una  posible respuesta evidenciando la relación causa –efecto."));
        desempenyos.add(new Desempenyo(8,
                "Propone un plan de acción donde describe las  estrategias que  le  permitan,  comprobar  la  posible  respuesta,  y  que evidencian  la  relación  entre  los  factores  relacionados  al problema,  selecciona  herramientas,  materiales  y  fuentes de información."));
        desempenyos.add(new Desempenyo(8,
                "Obtiene datos cualitativos/cuantitativos al hacer mediciones con instrumentos de medidas convencionales, los  registra  y  representa  en  organizadores  de  acuerdo  a diferentes criterios. Considera instrucciones de seguridad."));
        desempenyos.add(new Desempenyo(8,
                "Establece   relaciones   de   causalidad   entre   su   posible respuestacon la interpretación de los datos cualitativos/cuantitativos  obtenidos  en  sus  observaciones y elabora sus conclusiones."));
        desempenyos.add(new Desempenyo(8,
                "Comunica   al   describir   el procedimiento, logros y dificultades que tuvo durante el desarrollo    de    la indagación,  propone mejoras y comunica lo aprendido en forma oral y escrita, usando conocimientos científicos."));
        desempenyos.add(new Desempenyo(9,
                "Formula preguntas acerca de las características o causas de un   hecho,  fenómeno  u objeto   natural  o  tecnológico  que observa, identifica los factores involucradas en la  relación causa-efecto  para formular su hipótesis."));
        desempenyos.add(new Desempenyo(9,
                "Propone  estrategias,  selecciona  fuentes  de  información confiable,  herramientas  y   materiales  que  le  ayuden  a observar las variables involucradas, a fin de obtener datos que confirmen o refuten  su hipótesis."));
        desempenyos.add(new Desempenyo(9,
                "Obtiene datos cualitativos/cuantitativos que evidencian la relación entre las variables, mediante el uso de materiales e instrumentos seleccionados, los registra y representa en diferentes    organizadores.        Sigue    instrucciones    para mantener la seguridad."));
        desempenyos.add(new Desempenyo(9,
                "Compara  sus  hipótesis  con  la  interpretación  de    los  datos cualitativos/cuantitativos  obtenidos  en  sus  observaciones o experimentación asícomo con las fuentes de información confiables    y    elabora    conclusiones    que    explican    las relaciones estudiadas."));
        desempenyos.add(new Desempenyo(9,
                "Describe  el procedimiento, los logros y dificultades de su indagación,  propone  mejoras  al  mismo.  Fundamenta  sus conclusiones usando conocimientos científicos de manera oral, escrita o gráfica."));
        desempenyos.add(new Desempenyo(10,
                "Formula preguntas acerca de las características o causas de un   hecho,  fenómeno  u  objeto    natural  o  tecnológico  que observa, identifica las variables dependiente e independiente  involucradas  en  la    relación  causa-efecto  para formular su hipótesis."));
        desempenyos.add(new Desempenyo(10,
                "Propone  estrategias,  selecciona  fuentes  de  información confiable,  herramientas  y  materiales  que   le  ayuden  a observar las variables involucradas y controlar los factores que  lo  pueden  modificar,    a  fin  de  obtener  datos  que confirmen o refuten  su hipótesis. "));
        desempenyos.add(new Desempenyo(10,
                "Obtienedatos cualitativos/cuantitativos que evidencian la relación entrelas variables, mediante el uso de materiales e instrumentos seleccionados, los registra y representa en diferentes    organizadores.        Sigue    instrucciones    para mantener la seguridad."));
        desempenyos.add(new Desempenyo(10,
                "Compara  sus  hipótesis  con  la  interpretación  de    los  datos cualitativos/cuantitativos  obtenidos  en  sus  observaciones o experimentación así como con las fuentes de información confiables.  Describe  comportamiento de las variables que se  repiten  (patrones)  a  partir  de  los  datos  obtenidos  y elabora     conclusiones     que     explican     las     relaciones estudiadas."));
        desempenyos.add(new Desempenyo(10,
                "Describe   el procedimiento, los logros y dificultades de su indagación,  propone  mejoras  al  mismo  y  explica  por  qué sus  resultados  responden  a  la  pregunta  de  indagación. Fundamenta    sus    conclusiones    usando    conocimientos científicos de manera oral, escrita o gráfica."));
        comp_32.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_32);

        Competencia comp_33 = competenciaRepository.findByCode(33);
        desempenyos.add(new Desempenyo(5,
                "Describe,  en  base  a  sus  observaciones  y  experiencias,  las características  y  necesidades  de  los  seres  vivos  y  aplica estos conocimientos a situaciones cotidianas. Por ejemplo: describe que los alimentos de su localidad nos dan energía para realizar diferentes actividades."));
        desempenyos.add(new Desempenyo(5,
                "Establece   relaciones   en   base   a   sus   observaciones   y experiencias, entre las actividades cotidianas con el uso de la  energíay  aplica  estos  conocimientos  a  situaciones cotidianas. Por ejemplo: el transporte de carga  a través de animales, aparatos que funcionan con electricidad o que los alimentos se cuecen por el calor del fogón de leña. "));
        desempenyos.add(new Desempenyo(5,
                "Describe,  en  base  a  sus  observacionesy  experiencias,  las características de los objetos y aplica estos conocimientos a situaciones  cotidianas  Por  ejemplo:  la  esponja  absorbe  el agua y la bolsa de plástico no."));
        desempenyos.add(new Desempenyo(5,
                "Explica, en base a sus observaciones y experiencias, que el suelo  está  formado  por seres  vivos  y    objetos  no    vivos  y aplica estos conocimientos a situaciones cotidianas"));
        desempenyos.add(new Desempenyo(5,
                "Describe, en base a sus observaciones y experiencias, que el agua,  aire  y  suelo  son  importantes  para  los  seres  vivos  y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(5,
                "Establece   relaciones   en   base   a   sus   observaciones   y experiencias,  entre  el  comportamiento  de  los  seres  vivos con  los  cambios  de  clima  y  aplica  estos  conocimientos  a situaciones  cotidianas.  Por  ejemplo:  cuando  hace  frío  las aves de la costa sur de Ica vuelan hacia lugares menos fríos, como la Laguna de Parinacohas o hay más flores en Tacna durante el periodo de primavera."));
        desempenyos.add(new Desempenyo(5,
                "Establece   relaciones,   en   base   a   sus   observaciones   y experiencias, entre los objetos tecnológicos con su utilidad para  satisfacer  las necesidades  de  las  personas  y  opina sobre cómo su uso impacta en él."));
        desempenyos.add(new Desempenyo(6,
                "Establece relaciones, en base a sus observaciones y experiencias, entre las partes externas de los seres vivos con sus funciones  y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(6,
                "Establece relaciones, en base a sus observaciones y experiencias entre   las   semejanzas   externas   de   los   progenitores   y   sus descendientes     durante     el     desarrollo          y     aplica     estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(6,
                "Establece relaciones, en base a sus observaciones y experiencias, los  cambios  que  experimentan  los  objetos  con  la  luz,  calor  o movimiento que actúa sobre ellos  y aplica estos conocimientos a situaciones cotidianas. Por ejemplo: el niño relaciona el hecho de  que  el  chocolate  que  estaba  en  su  bolsillo,  se  derritió  por acción del calor."));
        desempenyos.add(new Desempenyo(6,
                "Describe, en base a sus observaciones y experiencias, los cambios que  sufren  los  objetos  de  acuerdo  a  sus  características  y  aplica estos  conocimientos  a  situaciones  cotidianas.  Por  ejemplo:  el niño  describe  en  base  a  las  características  de  los  materiales, porque con el mismo golpe un vaso de vidrio se rompe mientras que un vaso de cartón solo se deforma."));
        desempenyos.add(new Desempenyo(6,
                "Establece relaciones, en base a sus observaciones y experiencias, entre las características de los seres vivos con su hábitat y aplica estos conocimientos a situaciones cotidianasPor ejemplo: el niño da razón de que las papas nativas desarrollan sus tallos al ras del suelo como una forma de protegerse de los fuertes vientos."));
        desempenyos.add(new Desempenyo(6,
                "Explica, en base a sus observaciones y experiencias, que el ciclo día-noche es causado por la rotación de la Tierra y que la Tierra está  formada por  masas de agua, aire y material  sólido y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(6,
                "Describe,  enbase  a  sus  observaciones  y  experiencias,  el  suelo como  fuente  esencial  de  nutrientes  para  muchos  seres  vivos    y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(6,
                "Explica   que   hay   objetos   tecnológicos   que   transforman   los productos que consume o que usa en tareas específicas y opina cómo  estos  objetos  cambian  su  vida,  la  de  su  familia  o  el ambiente.  Por  ejemplo:  el  niño  explica  que  el  batán  ayuda  a convertir los granos de maíz o de trigo en harina, permitiendo el uso de estos productos en su vida familiar."));
        desempenyos.add(new Desempenyo(7,
                "Describe,  en  base  a  fuentes  documentadascon  respaldo científico,  los  órganos  que  conforman  los  sistemas  de plantas   y   animales      y   aplica   estos   conocimientos   a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(7,
                "Establece,  en  base  a  fuentes  documentadas  con  respaldo científico, semejanzas y diferencias entre diversas especies  y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(7,
                "Explica,  en  base  a  fuentes  documentadas  con  respaldo científico,  que  los    materiales  se  pueden  clasificar  de acuerdo   a   sus   características   físicas   (duros,   blandos, frágiles,  etc.)    y  aplica  estos  conocimientos  a  situaciones cotidianas."));
        desempenyos.add(new Desempenyo(7,
                "Establece relaciones, en base a fuentes documentadas con respaldo científico, entre el movimiento de los cuerpos con las   fuerzas   que   pueden      mover,   frenar   o   cambiar   la dirección      y   aplica   estos   conocimientos   a   situaciones cotidianas."));
        desempenyos.add(new Desempenyo(7,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,  las  diferentes  zonas  climáticas    en  la  superficie terrestre      y   aplica   estos   conocimientos   a   situaciones cotidianas."));
        desempenyos.add(new Desempenyo(7,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,  que  el  hábitat    proporciona  a  los  organismos recursos  para  satisfacer  sus  necesidades  básicas    y  aplica estos conocimientos a situaciones cotidianas. Por ejemplo: el  niño  explica  que  las  plantas  necesitan  aire,  agua,  luz, tierra y que los animalesalimento, agua, aire y abrigo. "));
        desempenyos.add(new Desempenyo(7,
                "Explica,  en  base  a  fuentes  documentadas  con  respaldo científico, que en un hábitat los seres vivos interactúan con  otros  seres  vivos  y  elementos  no  vivos    y  aplica  estos conocimientos  a  situaciones  cotidianas.  Por  ejemplo:el niño explica que los herbívoros, como el conejo,  necesitan de pasto para vivir y que el pasto necesita agua y nutrientes del suelo para crecer. "));
        desempenyos.add(new Desempenyo(7,
                "Explica   que   los   objetos   tecnológicos   son   creados   por personas  de  diferentes  ocupaciones/especialidades  para satisfacer  necesidades  y  opina  sobre  cómo  el  uso  de  los productos tecnológicos cambia la vida de las personas y el ambiente. Por ejemplo: el niño explica que los caballitos de totora  fueron  elaborados  por  pescadores  artesanos  de  la Costa norte, permitiendo el consumo y comercialización de pescados   de   la   zona   o   explica   que   los   puentes   son  diseñados  y  construidos  por  un  ingeniero  civil,  lo  que permite  a  mejorar  el  transporte  o  desplazamiento  de  las personas. "));
        desempenyos.add(new Desempenyo(8,
                "Establece relaciones, en base a fuentes documentadas con respaldo  científico,    entre  los  órganos  y  sistemas  con  las funciones  vitales  en  plantas  y  animales  y  aplica  estos conocimientos  a  situaciones  cotidianas.  Por  ejemplo:  el niño   establece   que   los   alimentos   que   consumen   son transformados  por el sistema digestivo en nutrientes que se distribuyen a través de la sangre a todo el organismo."));
        desempenyos.add(new Desempenyo(8,
                "Describe,    en  base  a  fuentes  documentadas  con  respaldo científico,  que los individuos se reproducen solo con otro de  su  misma  especie    y  aplica  estos  conocimientos  a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(8,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,      que   los   cuerpos   pueden   sufrir   cambios reversibles o irreversibles por acción de la energía  y aplica estos conocimientos a situaciones cotidianas. Por ejemplo: el niño describe que un cubo de hielo se derrite por acción del calor del ambiente y que puede volver a ser un cubo de hielo si se le quita el calor colocándola en un refrigerador."));
        desempenyos.add(new Desempenyo(8,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,    que  las  diferentes  fuerzas  pueden  modificar  la forma, el equilibrio o posición de los objetos  y aplica estos conocimientos  a  situaciones  cotidianas.  Por  ejemplo:  el niño  explica  que  al  tirar  de  un  elástico  este  se  deforma  y cuando cesa esta acción, recupera su forma."));
        desempenyos.add(new Desempenyo(8,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,  que la energía puede cambiar a otras formas y que  esta  sirve  para  diferentes  propósitos    y  aplica  estos conocimientos  a  situaciones  cotidianas.  Por  ejemplo:  el niño  describe  que  un  carro    acontrol  remoto  necesita baterías, y que esta le permite moverse, producir sonido y encender sus luces. "));
        desempenyos.add(new Desempenyo(8,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,    que  todos  los  seres  vivos  cumplen  un  rol  en  el ambiente  que  habitan  y  aplica  estos   conocimientos  a situaciones  cotidianas.  Por  ejemplo:  el  niño  describe  que las plantas, la liebre y la lombriz cumplen funciones dentro de un ecosistema. "));
        desempenyos.add(new Desempenyo(8,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico, que las  plantas y animales  poseen estructuras y comportamientos  adecuados  al  medio  donde  habitan    y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(8,
                "Describe,  en  base  a  fuentes  documentadas  con  respaldo científico,    que  las  diferentes  zonas  climáticas  se  forman por la distribución de laenergía del sol sobre la Tierra y su relieve      y   aplica   estos   conocimientos   a   situaciones cotidianas."));
        desempenyos.add(new Desempenyo(8,
                "Explica  que  los  diversos  objetos  tecnológicos  son  creados para satisfacer las necesidades personales y colectivas. Por ejemplo:  el  niño  explica  que  existen  aparatos,  como  los rayos  X,  que  ayudan  a  los  médicos  a  tratar  a  las  personas que se fracturan los huesos."));
        desempenyos.add(new Desempenyo(8,
                "Opina,   a   partir   de   una   situación   los   cambios   que   la tecnología ha generado en la forma de vivir de las personas y  en  el  ambiente.  Por  ejemplo:  el  niño  explica  que  los puentes  colgantes  permiten  la  comunicación  entre  los pueblos."));
        desempenyos.add(new Desempenyo(9,
                "Explica,  en  base  a  fuentes  con  respaldo  científico,  las diferencias  entre  célula  animal  y  vegetal  y  que  ambas cumplen funciones básicas  y aplica estos conocimientos a situaciones  cotidianas.  Por  ejemplo:  el  niño  explica  que cuando  se  corta  la  piel,  al  cabo  de  unos  días,  esta  se regenera como resultado de la reproducción de sus células."));
        desempenyos.add(new Desempenyo(9,
                "Explica,  en  base  fuentes  con  respaldo  científico,  que  los seres vivospresentan diferentes formas de reproducción  y aplica estos conocimientos a situaciones cotidianas. "));
        desempenyos.add(new Desempenyo(9,
                "Describe, en base a fuentes con respaldo científico, a través de  un  modelo  que  la  materia  se    compone  de  partículas pequeñas     y   aplica   estos   conocimientos   a   situaciones cotidianas. Por ejemplo: el niño describe el por qué cuando frota un globo inflado sobre su cabello, este se pega a él."));
        desempenyos.add(new Desempenyo(9,
                "Explica, en base a fuentes  con respaldo científico, que los ecosistemas se encuentran constituidos por componentes abióticos  y  bióticos  que  se  interrelacionan    y  aplica  estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(9,
                "Explica,   en   base   a   fuentes   con   respaldo   científico,   el carácter dinámico de la estructura externa e interna de la Tierra       y   aplica   estos    conocimientos   a   situaciones cotidianas."));
        desempenyos.add(new Desempenyo(9,
                "Explica que el que hacer tecnológico progresa con el paso del  tiempo  como  resultado  de  la  creciente  comprensión científica y su aplicación ingeniosa para resolver problemas (a los materiales, a los seres  vivos, a los componentes del paisajegeográfico)."));
        desempenyos.add(new Desempenyo(9,
                "Opina  respecto  a  la  influencia  del  uso  de  los  objetos tecnológicos  en  la  comprensión  de  hechos  y  fenómenos. Por   ejemplo:   el   niño   opina   sobre   cómo   el   uso   del microscopio permitió el reconocimiento e microrganismos que afectan nuestra salud."));
        desempenyos.add(new Desempenyo(10,
                "Explica,  en  base  a  fuentes  con  respaldo  científico,  que  todos los organismos están hechos de células y que algunos están  formados  por  una  sola  célula  (las  bacterias,  las amebas, las levaduras) y que cada célula cumple funciones básicas  o especializadas    y  aplica  estos  conocimientos  a situaciones cotidianas. Por ejemplo: el niño explica que los organismos  que  se  observan  en  el  agua  de  florero  son organismos unicelulares, que cumplen funciones básicas."));
        desempenyos.add(new Desempenyo(10,
                "Explica,   en   base   a   fuentes   con   respaldocientífico,   la relación entre la reproducción sexual y la diversidad dentro de una especie  y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(10,
                "Explica,   en   base   a   fuentes   con   respaldo   científico,   la relación   entre   las   características   observables   de   loscuerpos con las  fuerzas que  predominan en  sus átomos o moléculas  (fuerzas de repulsión y cohesión) y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(10,
                "Explica,   en   base   a   fuentes   con   respaldo   científico,   la relación entre el calor y la temperatura con el movimiento molecular    y  aplica  estos  conocimientos  a  situaciones cotidianas. Por ejemplo: el niño explica cómo el calor que se  encuentra  en  el  extremo  de  una  barra  de  metal  llega hasta su otro extremo al cabo de un tiempo."));
        desempenyos.add(new Desempenyo(10,
                "Explica,  en  base  a  fuentes  con  respaldo  científico,  que  la diversidad  de  especies  da  estabilidad  a  los  ecosistemas    y aplica  estos  conocimientos  a  situaciones  cotidianas.  Por ejemplo:  el  niño  explica  que  cuanto  más  biodiverso  es  un ecosistema resiste mejor los cambios ambientales."));
        desempenyos.add(new Desempenyo(10,
                "Explica,  en  base  a  fuentes  con  respaldo  científico,  que  la Tierra  presenta  una  estructura  dinámica  interna  que  se evidencia en los cambios del relieve terrestre  y aplica estos conocimientos a situaciones cotidianas."));
        desempenyos.add(new Desempenyo(10,
                "Explica que algunos objetos  tecnológicos y conocimientos científicos  han  ayudado  a  formular  nuevas  teorías  que propiciaron el cambio en la forma de pensar y el estilo de vida  de las personas. Por ejemplo: el niño explica cómo el uso del telescopio permitió reconocer que la Tierra no es el centro del universo."));
        desempenyos.add(new Desempenyo(10,
                "Defiende   su   punto   de   vista   respecto   a   un   aspecto controversial generado por la producción y uso de nuevas tecnologías, la innovación tecnológica y el saber científico. Por ejemplo: el niño da razonesa favor o en contra, sobre sí la instalación de  antenas de telefonía en zonas pobladas podrían afectar la salud de los seres vivos."));
        comp_33.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_33);

        Competencia comp_34 = competenciaRepository.findByCode(34);
        desempenyos.add(new Desempenyo(5,
                "Describe un problema personal o de su entorno, así como su alternativa de solución en base a conocimientos previos o practicas locales, los requerimientos que debe cumplir y los recursos disponibles para construirlo."));
        desempenyos.add(new Desempenyo(5,
                "Representa  gráficamente  su  alternativa  de  solución  con dibujos y textos, describe lo que hará para construirla."));
        desempenyos.add(new Desempenyo(5,
                "Lleva  a  cabo  el  procedimiento  para  la  implementación  de su  alternativa  de  solución  y  realiza  ensayos  hasta  que funcione.  Usa  unidades  de  medida  no  convencionales  y prueba los materiales que lo ayuden en la construcción de su   alternativa   de   solución,   selecciona   instrumentos   y herramientas que lo ayuden a elaborarla,  cumpliendo las normas de seguridad. "));
        desempenyos.add(new Desempenyo(5,
                "Describe  cómo  construyo  su  solución  tecnológica,  su  uso, beneficios, funcionamiento, en relación a los requerimientos  y  los  conocimientos  previos  o  prácticas locales   aplicadas,   las   dificultades   que   tuvo   y   propone mejoras.   Por   ejemplo:   el   estudiante  menciona   que   al instrumento  musical  que  construyó  le  cambiaría  las  pitas para que suene más fuerte"));
        desempenyos.add(new Desempenyo(6,
                "Describe  un  problema  personal  o  de  su  entorno  y  las posibles causas que lo generan, así como su alternativa de solución   en   base   a   conocimientos   previos   o   practicas locales, los requerimientos que debe cumplir y los recursos disponibles para construirlo."));
        desempenyos.add(new Desempenyo(6,
                "Representa  gráficamente  su  alternativa  de  solución  con dibujos  y  textos,  describe  sus  partes,    secuencia  de  pasos para su implementación y selecciona los materiales según las  características físicas."));
        desempenyos.add(new Desempenyo(6,
                "Lleva  a  cabo  el  procedimiento  para  la  implementación  de su  alternativa  de  solución  y  explica  los  cambios  o  ajustes para cumplir los requerimientos o mejorar el funcionamiento. Usa unidades de medida no convencionales  y  manipula  materiales,    instrumentos  y herramientas según sus funciones,  cumpliendo normas de seguridad. "));
        desempenyos.add(new Desempenyo(6,
                "Describe  cómo  construyo  su  solución  tecnológica,  su  uso, beneficios, funcionamiento, en relación a los requerimientos  y  los  conocimientos  previos  o  prácticas locales   aplicadas,   las   dificultades   que   tuvo   y   propone mejoras.   Por   ejemplo:   el   estudiante   menciona   que   al instrumento  musical  que  construyó  le  cambiaría  las  pitas para que suene más fuerte."));
        desempenyos.add(new Desempenyo(7,
                "Determina    el  problema tecnológico,  las  causas  que  lo generan,  propone    alternativas  de  solución  en  base  a conocimientos     científicos     o     prácticas     locales,     los requerimientos que debe cumplir y los recursos disponibles para construirlo."));
        desempenyos.add(new Desempenyo(7,
                "Representa  gráficamente  su  alternativa  de  solución  con dibujos  y  textos,  describiendo  sus  partes,    secuencia  de pasos para su implementación y selecciona los  materiales por sus  características físicas."));
        desempenyos.add(new Desempenyo(7,
                "Lleva  a  cabo  el  procedimiento  para  la  implementación  de su alternativa de solución y realiza cambios o ajustes para cumplir  los  requerimientos  o  mejorar  el  funcionamiento. Usa   unidades   de   medida   convencionales   y   manipula materiales,      instrumentos   y   herramientas   según   sus funciones,  cumpliendo normas de seguridad."));
        desempenyos.add(new Desempenyo(7,
                "Verifica    si    la    solución    tecnológicacumple    con    los requerimientos  establecidos  y  propone  cómo  mejorar  su funcionamiento.   Explica   cómo   construyó      su   solución tecnológica, su funcionamiento, el conocimiento científico o prácticas locales aplicadas y las dificultades superadas."));
        desempenyos.add(new Desempenyo(8,
                "Determina    el  problema  tecnológico,  las  causas  quelo generan,  propone    alternativas  de  solución  en  base  a conocimientos     científicos     o     prácticas     locales,     los requerimientos que debe cumplir y los recursos disponibles para construirlo."));
        desempenyos.add(new Desempenyo(8,
                "Representa  gráficamente  su  alternativa  de  solución  con dibujos  y  textos,  describiendo  sus  partes  o  etapas,  la secuencia de pasos y características de forma, estructura y función  de  la  misma.  Selecciona  los  materiales  por  sus  características físicas."));
        desempenyos.add(new Desempenyo(8,
                "Lleva  a  cabo  el  procedimiento  para  la  implementación  de su alternativa de solución y realiza cambios o ajustes para cumplir  los  requerimientos  o  mejorar  el  funcionamiento. Usa   unidades   de   medida   convencionales   y   manipula materiales,      instrumentos   y   herramientas   según   sus funciones,  cumpliendo normas de seguridad. "));
        desempenyos.add(new Desempenyo(8,
                "Verifica    si    lasolución tecnológica cumple con los requerimientos  establecidos  y  propone  cómo  mejorar  su funcionamiento.   Explica   cómo   construyó   su   solución tecnológica, su funcionamiento, el conocimiento científico o  prácticas  locales  aplicadas,  las  dificultades  superadas  y los beneficios e inconvenientes de su uso."));
        desempenyos.add(new Desempenyo(9,
                "Determina  el  problema  tecnológico y las  causas  que  lo generan,así  como  su  alternativa  de  solución    en  base  a conocimientos     científicos     o     prácticas     locales,     los  requerimientos que debe cumplir y los recursos disponibles para construirlo."));
        desempenyos.add(new Desempenyo(9,
                "Representa  gráficamente  su  alternativa  de  solución  con dibujos  y  textos,  describiendo  sus  partes  o  etapas,  la secuencia de pasos, características de forma, estructura y función  de  la  misma.  Selecciona  los  materiales  por  sus  características físicas."));
        desempenyos.add(new Desempenyo(9,
                "Lleva  a  cabo  su  alternativa de solución,  manipulando  los materiales,    instrumentos    y    herramientas    según    sus funciones, considerando los requerimientos establecidos y normas    de    seguridad.        Usa    unidades    de    medida convencionales,  verifica  el    funcionamiento  de  cada  parte o  etapa  de  la  solución  tecnológica,  y  realiza  ajustes  o cambios necesarios."));
        desempenyos.add(new Desempenyo(9,
                "Realiza  pruebas  para  verificar si la solución tecnológica cumple con los requerimientos  establecidos,  y  propone cómo mejorar su funcionamiento. Explica cómo construyó su solución tecnológica, su funcionamiento, el conocimiento  científico  o  prácticas  locales  aplicadas,  las dificultades superadas y los beneficios e inconvenientes de su uso. "));
        desempenyos.add(new Desempenyo(10,
                "Determina  el  problema  tecnológico  y    las  causas  que  lo generan, así como su alternativa  de  solución    en  base  a conocimientos     científicos     o     prácticas     locales,     los  requerimientos que debe cumplir y los recursos disponibles para construirlo."));
        desempenyos.add(new Desempenyo(10,
                "Representa  gráficamente  su  alternativa  de  solución  con dibujos y textos, describiendo   sus   partes   (incluyendo dimensiones)     o     etapas,     la     secuencia     de     pasos, características de forma, estructura y función de la misma. Selecciona  los  materiales  por  sus    características  físicas, incluye    los  recursos  a  utilizar,    posibles  costos  y  prevé  el tiempo que le tomará realizarlo."));
        desempenyos.add(new Desempenyo(10,
                "Lleva  a  cabo  su  alternativa  de  solución,  manipulando  los materiales, instrumentos    y    herramientas    según    sus funciones,  considerando  los  requerimientos  establecidos, normas de seguridad. Usa unidades medida convencionales,  verifica  el    funcionamiento  de  cada  parte o  etapa  de  la  solución  tecnológica,  detecta  imprecisiones en  las  dimensiones,  procedimientos,  error  en  la  selección de materiales y realiza ajustes o cambios  necesarios."));
        desempenyos.add(new Desempenyo(10,
                "Realiza  pruebas  para  verificar  si  la  solución  tecnológica cumple  con  los  requerimientos  establecidos,  y  propone cómo mejorar su funcionamiento, en base a sus resultados y pruebas. Explica cómo construyó su solución tecnológica, su  funcionamiento,  el  conocimiento  científico  o  prácticas locales aplicadas, las dificultades superadas y los beneficios e  inconvenientes  de  su  uso.  Infiere  posibles  impactos positivos   o   negativos   de   la   solución   tecnológica   en diferentes contextos. "));
        comp_34.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_34);

        Competencia comp_35 = competenciaRepository.findByCode(35);
        desempenyos.add(new Desempenyo(5,
                "Es autónomo al explorar las posibilidades de su cuerpo en diferentes acciones para mejorar sus movimientos (saltar,  correr,  lanzar)  al  mantener  y/orecuperar  el equilibrio  en  el  espacio  y  con  los  objetos,  cuando explora conscientemente distintas bases de sustentación,    conociendo    en    sí    mismo    su    lado dominante. "));
        desempenyos.add(new Desempenyo(5,
                "Se    orienta    a    través    de    sus    nociones    espacio-temporales (arriba -abajo, dentro -fuera, cerca –lejos) en relación a sí mismo y de acuerdo a sus intereses y necesidades."));
        desempenyos.add(new Desempenyo(5,
                "Descubre     nuevos     movimientos     y     gestos     para representar objetos, personajes y estados de ánimo y ritmos sencillos de distintos orígenes: de la naturaleza, del propio cuerpo, de la música, etc."));
        desempenyos.add(new Desempenyo(5,
                "Se     expresa     motrizmente     para     comunicar     sus emociones  (miedo,  angustia,  alegría,  placer,  torpeza, inhibición, rabia, entre otros) y representa en el juego acciones  cotidianas  de  su  familia  y  de  la  comunidad, afirmando su identidad personal."));
        desempenyos.add(new Desempenyo(6,
                "Explora  de  manera  autónoma  sus  posibilidades  de movimiento   al   realizar   con   seguridad   y   confianza habilidades  motrices  básicas  realizando  movimientos coordinados   según   sus   intereses,   necesidades   y posibilidades."));
        desempenyos.add(new Desempenyo(6,
                "Se  orienta  en  el  espacio  y  tiempo  en  relación  a  sí mismo y a otros puntos de referencia, reconociendo su lado   derecho   e   izquierdo   y   sus   posibilidades   de equilibrio  con  diferentes  bases  de  sustentación  en acciones lúdicas."));
        desempenyos.add(new Desempenyo(6,
                "Resuelve  situaciones  motrices  al  utilizar  su  lenguaje corporal   (gesto,   contacto   visual,   actitud   corporal, apariencia,  etc.),  verbal  y  sonoroque  le  ayudan  a sentirse seguro, confiado y aceptado."));
        desempenyos.add(new Desempenyo(6,
                "Utiliza su cuerpo y el movimiento para expresar ideas y emociones en la práctica de actividades lúdicas con diferentes  tipos  de  ritmos  y  música  para  expresarse corporalmente y usando diversos elementos."));
        desempenyos.add(new Desempenyo(7,
                "Reconoce la izquierda y derecha en relación a objetos y  en  sus  pares para  mejorar  sus  posibilidades  de movimiento en diferentes acciones lúdicas."));
        desempenyos.add(new Desempenyo(7,
                "Se  orienta  en  un  espacio  y  tiempo  determinado,  en relación  a  sí  mismo,  los  objetos  y  sus  compañeros, coordina  sus  movimientos  en  situaciones  lúdicas  y regula su equilibrio al variar la base de sustentación y la  altura  de  la  superficie  de  apoyo,  afianzando  sus habilidades motrices básicas."));
        desempenyos.add(new Desempenyo(7,
                "Resuelve  situaciones  motrices  al  utilizar  su  lenguaje corporal   (gesto,   contacto   visual,   actitud   corporal, apariencia,   etc.),   verbal   y   sonoro   para   comunicar actitudes,  sensaciones  y  estados  de  ánimo,  acciones que  le  posibilitan  comunicarse  mejor  con  los  otros  y disfrutar de las actividades lúdicas."));
        desempenyos.add(new Desempenyo(7,
                "Vivencia  el  ritmo  y  se  apropia  de  secuencias  rítmicas corporales  en  situaciones  de  juego  para  expresarse corporalmente a través de la música."));
        desempenyos.add(new Desempenyo(8,
                "Regula   la   posición   del   cuerpo   en   situaciones   de equilibrio,   con   modificación   del   espacio   teniendo como  referencia  la  trayectoria  de  objetos,  los  otros  y sus    propios    desplazamientos    para    afianzar    sus habilidades motrices básicas."));
        desempenyos.add(new Desempenyo(8,
                "Alterna  sus  lados  corporales  de  acuerdo  a  su  utilidad y/o necesidad y se orienta en el espacio y en el tiempo, en relación a si mismo y a otros puntos de referencia en actividades lúdicas y  predeportivas."));
        desempenyos.add(new Desempenyo(8,
                "Utiliza   su   cuerpo   (posturas,   gestos   y   mímica)   y diferentes  movimientos  para  expresar  formas,  ideas, emociones,    sentimientos    y    pensamientos    en    la actividad física."));
        desempenyos.add(new Desempenyo(8,
                "Utiliza    su  lenguaje  corporal  para  expresar  su  forma particular de moverse, creando secuencias sencillas de movimientos, relacionados con el ritmo, la música de su cultura y la historia de su región."));
        desempenyos.add(new Desempenyo(9,
                "Anticipa las acciones motrices a realizar en un espacio y  tiempo  para  mejorar  las  posibilidades  de  respuesta en  la  acción,  aplicando  la  alternancia  de  sus  lados corporales  de  acuerdo  a  su  preferencia,  utilidad  y/o necesidad en la actividad física. "));
        desempenyos.add(new Desempenyo(9,
                "Pone  en  práctica  las  habilidades  motrices  específicas (relacionadas  con  la  carrera,  salto  y  lanzamientos)  a través de la exploración y regulación de su cuerpo para dar respuesta a las situaciones motrices (en contextos lúdicos, predeportivos, etc.)."));
        desempenyos.add(new Desempenyo(9,
                "Crea   movimientos   y   desplazamientos   rítmicos   e incorpora las particularidades de su lenguaje corporal teniendo como base la música de su región; al asumir diferentes roles en la práctica de actividad física."));
        desempenyos.add(new Desempenyo(9,
                "Valora  en  sí  mismo  y  en  sus  pares  nuevas  formas  de movimiento    y    gestos    corporales;    aceptando    la existencia    de    nuevas    formas    de    movimiento    y expresión   para   comunicar   ideas   y   emociones   en diferentes situaciones motrices."));
        desempenyos.add(new Desempenyo(10,
                "Anticipa las acciones motrices a realizar en un espacio y  tiempo  para  mejorar  las  posibilidades  de  respuesta en  la  acción,  aplicando  la  alternancia  de  sus  lados corporales  de  acuerdo  a  su  preferencia,  utilidad  y/o necesidad en la actividad física. "));
        desempenyos.add(new Desempenyo(10,
                "Afianza las habilidades motrices específicas (relacionadas  con  la  carrera,  salto  y  lanzamientos)  a través de la regulación de su cuerpo para dar respuesta a   las   situaciones   motrices   (en   contextos   lúdicos, predeportivos, etc.) "));
        desempenyos.add(new Desempenyo(10,
                "Aplicasu  lenguaje  corporal  para  expresar  su  forma particular de moverse, al asumir y adjudicar diferentes roles en la práctica de actividad física."));
        desempenyos.add(new Desempenyo(10,
                "Crea  con  sus  pares  una  secuencia  de  movimientos corporales,    expresivos    y/o    rítmicos,    de    manera programada     y     estructurada,     expresándose     de diferentes  maneras  y  con  diversos  recursos,  a  través del  cuerpo  y  el  movimiento  para  comunicar  ideas  y emociones."));
        comp_35.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_35);

        Competencia comp_36 = competenciaRepository.findByCode(36);
        desempenyos.add(new Desempenyo(5,
                "Reconoce    los  alimentos  de  su  dieta  familiar  y  las posturas que son beneficiosas para su salud en la vida cotidiana y en la práctica de actividades lúdicas."));
        desempenyos.add(new Desempenyo(5,
                "Identifica  en  sí  mismo  y  en  otros  la  diferencia  entre inspiración y espiración, en reposo y movimiento en las actividades lúdicas, regulando su esfuerzo al participar en actividades lúdicas. "));
        desempenyos.add(new Desempenyo(5,
                "Realiza  con  autonomía  prácticas  de  cuidado  personal al  asearse,  al  vestirse,  al  adoptar  posturas  adecuadas en  la  práctica  de  actividades  lúdicas  y  de  la  vida cotidiana."));
        desempenyos.add(new Desempenyo(5,
                "Busca  satisfacer  sus  necesidades  corporales  cuando tiene sed y resuelve las dificultades que le producen el cansancio, la incomodidad y la inactividad, mostrando su bienestar al realizar actividades lúdicas, sintiéndose bien consigo mismo, con los otros y con su entorno."));
        desempenyos.add(new Desempenyo(6,
                "Comprende  la  importancia  de  la  activación  corporal (calentamiento) y psicológica (atención, concentración y motivación) antes de la actividad lúdica identificando  los   signos   y   síntomas   relacionados   con:   el   ritmo cardiaco,  la  respiración  agitada  y  la  sudoración  que aparecen  en   el  organismo   al  practicar  actividades lúdicas."));
        desempenyos.add(new Desempenyo(6,
                "Reflexiona sobre los alimentos saludables de  su dieta familiar y de la región, los momentos adecuados para ingerirlos, la importancia de hidratarse, conociendo las posturas adecuadas en la práctica de actividad física y de la vida cotidiana, que le permiten mayor seguridad a  la  hora  de  practicar  actividades  lúdicas  y  de  la  vida cotidiana."));
        desempenyos.add(new Desempenyo(6,
                "Incorpora prácticas de cuidado personal al asearse, al vestirse, al adoptar posturas adecuadas en la práctica de  actividades  lúdicas  y  de  la  vida  cotidiana  que  le permitan  la  participación  en  el  juego  sin  afectar  su desempeño."));
        desempenyos.add(new Desempenyo(6,
                "Reconoce la importancia del autocuidado regulando su esfuerzo en la práctica de actividades lúdicas."));
        desempenyos.add(new Desempenyo(7,
                "Explica   la   importancia   de   la   activación   corporal (calentamiento) y psicológica (atención, concentración y  motivación)  que  le  ayuda a  estar  predispuesto  a  la actividad."));
        desempenyos.add(new Desempenyo(7,
                "Diferencia  los  alimentos  de  su  dieta  familiar  y  de  su región que son saludables de los que no lo son, para la práctica de actividad física y de la vida cotidiana."));
        desempenyos.add(new Desempenyo(7,
                "Aplica  los  conocimientos  de  los  beneficios  de    la práctica de actividad física y salud relacionados con el ritmo  cardiaco,  la  respiración  y  la  sudoración  cuando adapta   su   esfuerzo   en   la   práctica   de   diferentes actividades lúdicas."));
        desempenyos.add(new Desempenyo(7,
                "Incorpora  el  autocuidado  relacionado  con  los  ritmos de actividad-descanso paramejorar el funcionamiento de su organismo."));
        desempenyos.add(new Desempenyo(8,
                "Selecciona   actividades   para   la   activación   corporal (calentamiento) y psicológica (atención, concentración y  motivación)  antes  de  la  actividad  e  identifica  en  sí mismo  las  variaciones  en  la  frecuencia  cardiaca  y respiratoria   en   relación   a   diferentes   niveles   de esfuerzo en la práctica de actividades lúdicas."));
        desempenyos.add(new Desempenyo(8,
                "Selecciona   e   incorpora   en   su   dieta   los   alimentos nutritivos y energéticos existentes en su dieta familiar y  región  que  contribuyen  a  la  práctica  de  actividad física."));
        desempenyos.add(new Desempenyo(8,
                "Incorpora  el  autocuidado  relacionado  con  los  ritmos de  actividad-descanso, hidratación  y  exposición  a  los rayos  solares,  para  mejorar  el  funcionamiento  de  su organismo."));
        desempenyos.add(new Desempenyo(8,
                "Adopta  posturas  adecuadas  para  prevenir  problemas musculares   y   óseos   incorporando   el   autocuidado relacionado  con  los  ritmos  de  actividad  y  descanso para mejorar el funcionamiento del organismo."));
        desempenyos.add(new Desempenyo(9,
                "Identifica  las  condiciones  que  favorecen  la  aptitud física (IMC y pruebas físicas) para mejorar la calidad de vida, en relación a sus características personales."));
        desempenyos.add(new Desempenyo(9,
                "Comprende los cambios físicos propios de la edad y su repercusión  en  la  higiene  en  relación  a  la  práctica  de actividad  física  y  actividades  de  la  vida  cotidiana  y reflexiona sobre las prácticas alimenticias perjudiciales para  el  organismo  analizando  la  importancia  de  la alimentación en relación a su IMC."));
        desempenyos.add(new Desempenyo(9,
                "Identifica posturas y ejercicios contraindicados para la salud en la práctica de actividad física."));
        desempenyos.add(new Desempenyo(9,
                "Aplica   los   beneficios   relacionados   con   la   salud   al realizar actividades de activación corporal, psicológica y  de  recuperación  antes,  durante  y  después  de  la práctica de actividad física."));
        desempenyos.add(new Desempenyo(10,
                "Conoce  los  diferentes  métodos  de  evaluación  para determinar la aptitud física y selecciona los que mejor se adecúen a sus posibilidades, y utiliza la información obtenida en beneficio propio de su salud. "));
        desempenyos.add(new Desempenyo(10,
                "Comprende   la   importancia   de   la actividad   física incorporando   la   práctica   en   su   vida   cotidiana   e identifica  los  cambios  físicos  propios  de  la  edad  y  su repercusión  en  la  higiene  en  relación  a  la  práctica  de actividad física y actividades de la vida cotidiana. "));
        desempenyos.add(new Desempenyo(10,
                "Evita     la     realización     de     posturas     y     ejercicios contraindicados y cualquier práctica de actividad física que perjudique su salud."));
        desempenyos.add(new Desempenyo(10,
                "Previene   hábitos   perjudiciales   para   su   organismo como  el  consumo  de  comida  rápida,  alcohol,  tabaco, drogas, desórdenes alimenticios, entre otros."));
        comp_36.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_36);



        Competencia comp_37 = competenciaRepository.findByCode(37);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_37.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_37);

        Competencia comp_38 = competenciaRepository.findByCode(38);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_38.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_38);

        Competencia comp_39 = competenciaRepository.findByCode(39);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_39.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_39);

        Competencia comp_40 = competenciaRepository.findByCode(40);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_40.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_40);

        Competencia comp_41 = competenciaRepository.findByCode(41);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_41.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_41);

        Competencia comp_42 = competenciaRepository.findByCode(42);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_42.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_42);

        Competencia comp_43 = competenciaRepository.findByCode(43);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_43.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_43);

        Competencia comp_44 = competenciaRepository.findByCode(44);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_44.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_44);

        Competencia comp_45 = competenciaRepository.findByCode(45);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_45.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_45);

        Competencia comp_46 = competenciaRepository.findByCode(46);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_46.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_46);

        Competencia comp_47 = competenciaRepository.findByCode(47);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_47.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_47);

        Competencia comp_48 = competenciaRepository.findByCode(48);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_48.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_48);

        Competencia comp_49 = competenciaRepository.findByCode(49);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_49.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_49);

        Competencia comp_50 = competenciaRepository.findByCode(50);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_50.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_50);

        Competencia comp_51 = competenciaRepository.findByCode(51);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_51.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_51);

        Competencia comp_52 = competenciaRepository.findByCode(52);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_52.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_52);

        Competencia comp_53 = competenciaRepository.findByCode(53);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_53.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_53);

        Competencia comp_54 = competenciaRepository.findByCode(54);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_54.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_54);

        Competencia comp_55 = competenciaRepository.findByCode(55);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_55.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_55);

        Competencia comp_56 = competenciaRepository.findByCode(56);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_56.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_56);

        Competencia comp_57 = competenciaRepository.findByCode(57);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_57.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_57);

        Competencia comp_58 = competenciaRepository.findByCode(58);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_58.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_58);

        Competencia comp_59 = competenciaRepository.findByCode(59);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_59.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_59);

        Competencia comp_60 = competenciaRepository.findByCode(60);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_60.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_60);

        Competencia comp_61 = competenciaRepository.findByCode(61);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_61.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_61);

        Competencia comp_62 = competenciaRepository.findByCode(62);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_62.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_62);

        Competencia comp_63 = competenciaRepository.findByCode(63);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_63.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_63);

        Competencia comp_64 = competenciaRepository.findByCode(64);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_64.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_64);

        Competencia comp_65 = competenciaRepository.findByCode(65);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_65.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_65);

        Competencia comp_66 = competenciaRepository.findByCode(66);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_66.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_66);

        Competencia comp_67 = competenciaRepository.findByCode(67);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_67.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_67);

        Competencia comp_68 = competenciaRepository.findByCode(68);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_68.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_68);

        Competencia comp_69 = competenciaRepository.findByCode(69);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_69.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_69);

        Competencia comp_70 = competenciaRepository.findByCode(70);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_70.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_70);

        Competencia comp_71 = competenciaRepository.findByCode(71);
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        desempenyos.add(new Desempenyo());
        comp_71.getDesempenyos().addAll(desempenyoRepository.saveAll(desempenyos));
        competenciaRepository.save(comp_71);


    }

}
