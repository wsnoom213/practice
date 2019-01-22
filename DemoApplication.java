package com.se.team00.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.se.team00.demo.Entity.*;
import com.se.team00.demo.Repository.*;
import java.util.stream.Stream;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    ApplicationRunner init(StudentRepository studentRepository) {
		
		return args -> {
				Stream.of("B5720651").forEach(studentId -> {
				Student student = new Student();
				student.setStudentId(studentId);
				student.setLastName("sit");
				student.setFirstName("siri");
				studentRepository.save(student);
		});
		studentRepository.findAll().forEach(System.out::println);
	  	};
	}
	
}

