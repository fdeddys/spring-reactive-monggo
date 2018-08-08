package com.ddabadi.reactive.reactivemongoexample;

import com.ddabadi.reactive.reactivemongoexample.model.Employee;
import com.ddabadi.reactive.reactivemongoexample.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoExampleApplication {

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
		return args -> {
			employeeRepository
					.deleteAll()
					.subscribe(null, null, ()->{
						Stream.of(
								new Employee(UUID.randomUUID().toString(),"abcc",10L),
								new Employee(UUID.randomUUID().toString(),"deff",20L),
								new Employee(UUID.randomUUID().toString(),"ghiii",15L),
								new Employee(UUID.randomUUID().toString(),"jkl",40L),
								new Employee(UUID.randomUUID().toString(),"mmm",25L),
								new Employee(UUID.randomUUID().toString(),"noz",30L)
						).forEach(employee -> {
							employeeRepository
									.save(employee)
									.subscribe(emp -> {
										System.out.println(emp);
									});
						});
					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoExampleApplication.class, args);
	}
}
