package com.primus.amigo.configuration;

import com.primus.amigo.model.Student;
import com.primus.amigo.repository.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo){
        return args -> {
            Student jak = new Student(
                    "Javed",
                    "jak@gmail.com",
                    LocalDate.of(1994,Month.JULY,22)
            );
            Student abc = new Student(
                    "abc",
                    "abc@gmail.com",
                    LocalDate.of(1994, Month.JULY,22)
            );
            repo.saveAll(
                    List.of(jak,abc)
            );
        };
    }

}
