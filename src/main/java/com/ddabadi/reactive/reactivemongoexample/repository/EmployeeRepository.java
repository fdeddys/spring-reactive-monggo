package com.ddabadi.reactive.reactivemongoexample.repository;

import com.ddabadi.reactive.reactivemongoexample.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
