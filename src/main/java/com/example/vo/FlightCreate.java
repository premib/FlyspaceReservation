package com.example.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class FlightCreate {

    private Integer flightId;
    private LocalDateTime arriveTime;
    private LocalDateTime  departTime;
    private String destination;
    private String origin;

    private String seat1 = null;
    private String seat2 = null;
    private String seat3 = null;
    private String seat4 = null;
    private String seat5 = null;


}
