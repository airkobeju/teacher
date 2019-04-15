package com.jmtp.teacher.config;

import com.jmtp.teacher.repository.AlumnoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.text.SimpleDateFormat;

@EnableMongoRepositories(basePackages = "com.jmtp.teacher.repository")
@Configuration
public class MongoDBConfig {


    @Bean
    CommandLineRunner commandLineRunner(AlumnoRepository alumnoRepository) {
        return strings -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");

//            String dateInString = "2007-09-26";
//            Date date1 = sdf.parse(dateInString);
//            alumnoRepository.save(new Alumno("Mia Mauren", "Ticona Verdeguer", date1,"92341616"));
//
//            dateInString = "2014-09-16";
//            Date date2 = sdf.parse(dateInString);
//            alumnoRepository.save(new Alumno("Donovan Ian", "Ticona Verdeguer", date2,"99991616"));
//
//            dateInString = "1981-11-18";
//            Date date3 = sdf.parse(dateInString);
//            alumnoRepository.save(new Alumno("Celeste Elizabeth", "Verdeguer Argomedo", date3,"41170779"));



        };
    }

}
