package com.example.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "registrations")
@Data
@ToString
public class RegistrationsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    private String email;

    private String flightId;

    private String seatNo;
}
