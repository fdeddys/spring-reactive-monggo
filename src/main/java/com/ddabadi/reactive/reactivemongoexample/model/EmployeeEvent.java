package com.ddabadi.reactive.reactivemongoexample.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeEvent {

    private Employee employee;
    private Date date;

}
