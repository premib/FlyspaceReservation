package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class UserTransactionsResponse {

    private List<FlightSeat> transactions;

    public UserTransactionsResponse(List<FlightSeat> transactions){

        this.transactions = transactions;
    }
}
