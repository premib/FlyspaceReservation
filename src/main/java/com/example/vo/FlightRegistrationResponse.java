package com.example.vo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@ToString
public class FlightRegistrationResponse {
    private Integer flightId;
    private String origin;
    private String destination;
    String seat1;
    String seat2;
    String seat3;
    String seat4;
    String seat5;
    private LocalDateTime departTime;
    private LocalDateTime arriveTime;
}
