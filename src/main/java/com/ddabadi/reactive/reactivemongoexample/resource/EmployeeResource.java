//package com.ddabadi.reactive.reactivemongoexample.resource;
//
//import com.ddabadi.reactive.reactivemongoexample.model.Employee;
//import com.ddabadi.reactive.reactivemongoexample.model.EmployeeEvent;
//import com.ddabadi.reactive.reactivemongoexample.repository.EmployeeRepository;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.Date;
//import java.util.stream.Stream;
//
//@RestController
//@RequestMapping("rest/employee")
//public class EmployeeResource {
//
//    private EmployeeRepository employeeRepository;
//
//    public EmployeeResource(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    @GetMapping("all")
//    public Flux<Employee> getAll(){
//        return employeeRepository.findAll();
//    }
//
//    @GetMapping("{id}")
//    public Mono<Employee> findById(@PathVariable("id")String id){
//        return employeeRepository.findById(id);
//    }
//
//    @GetMapping(value = "{id}/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<EmployeeEvent> getEvent(@PathVariable("id")String id){
//        return employeeRepository.findById(id)
//                .flatMapMany(employee -> {
//                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2L));
//
//                    Flux<EmployeeEvent> employeeEventFlux =
//                            Flux.fromStream(
//                                    Stream.generate(()->  new EmployeeEvent(employee, new Date())
//                                    )
//                            );
//
//                    return Flux.zip(interval, employeeEventFlux)
//                        .map(objects -> {
//                            return objects.getT2();
//                        });
//
//                });
//    }
//
//}
