package com.example.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlightRegistration {

    private String email;
    private Integer flightId;
    private Integer seatNo;
}
