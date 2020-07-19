package com.example.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FlightStatusResponse {

    List<Boolean> freeSeat;

    public FlightStatusResponse(List<Boolean> freeSeat){

        this.freeSeat = freeSeat;
    }
}
