package com.example.controller;

import com.example.service.UserService;
import com.example.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/account")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody Login loginRequest) {

        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody Registration user) {
        System.out.println("ASDASD");
        return userService.register(user);

    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ROLE_AGENT') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public FlightStatusResponse searchFlight(@RequestParam String id){
        System.out.println("asdsadsda");
        return userService.searchFlight(Integer.valueOf(id));
    }

    @PostMapping("flightregister")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_AGENT')")
    public FlightRegistrationResponse registerFlight(@RequestBody FlightRegistration flight){

        return userService.flightRegister(flight);
    }

    @GetMapping("seetransactions")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_AGENT')")
    public UserTransactionsResponse seeTransactions(UserTransactions user){

        return userService.checkTransactions(user);
    }
}
