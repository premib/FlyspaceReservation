package com.example.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginResponse {

    String jwtToken;

    public LoginResponse(String jwtToken){

        this.jwtToken = jwtToken;
    }
}
