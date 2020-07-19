package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RegistrationResponse {
    String jwtToken;

    public RegistrationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
