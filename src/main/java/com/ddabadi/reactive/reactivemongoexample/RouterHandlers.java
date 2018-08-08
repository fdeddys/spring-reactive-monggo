package com.ddabadi.reactive.reactivemongoexample;

import com.ddabadi.reactive.reactivemongoexample.model.Employee;
import com.ddabadi.reactive.reactivemongoexample.model.EmployeeEvent;
import com.ddabadi.reactive.reactivemongoexample.repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Component
public class RouterHandlers {

    private EmployeeRepository employeeRepository;

    public RouterHandlers(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {

        return ServerResponse
                    .ok()
                    .body(employeeRepository.findAll(), Employee.class);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {

        String empId = serverRequest.pathVariable("id");
        return ServerResponse
                    .ok()
                    .body(
                            employeeRepository.findById(empId), Employee.class
                    );
    }

    public Mono<ServerResponse> getEvent(ServerRequest serverRequest) {

        String empId = serverRequest.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                        employeeRepository.findById(empId)
                                .flatMapMany(employee -> {
                                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2L));

                                    Flux<EmployeeEvent> employeeEventFlux =
                                            Flux.fromStream(
                                                    Stream.generate(()->  new EmployeeEvent(employee, new Date())
                                                    )
                                            );

                                    return Flux.zip(interval, employeeEventFlux)
                                            .map(objects -> {
                                                return objects.getT2();
                                            });

                                }), EmployeeEvent.class);
    }
}
