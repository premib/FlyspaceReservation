package com.example.model;

import com.example.vo.Registration;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flights")
@Data
@ToString
public class FlightModel {

    @Id
    private Integer flightId;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    String seat1;
    String seat2;
    String seat3;
    String seat4;
    String seat5;



    private LocalDateTime departTime;

    private LocalDateTime arriveTime;

}
