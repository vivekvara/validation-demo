package com.example.validation;

import com.example.validation.entity.User;
import com.example.validation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ValidationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository repository) {
        return (args) -> {
            repository.save(User.builder().name("James").email("james@domain.com").age(19).country("India").build());
            repository.save(User.builder().name("Chloe").email("chloe@domain.com").age(22).country("US").build());
            repository.save(User.builder().name("Yuan").email("yuah@domain.com").age(25).country("Japan").build());
            repository.save(User.builder().name("David").email("david@domain.com").age(60).country("UK").build());
            repository.save(User.builder().name("Michelle").email("michelle@domain.com").age(45).country("US").build());
            repository.findAll().forEach(System.out::println);
        };
    }
}
