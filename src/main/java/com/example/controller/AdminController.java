package com.example.controller;

import com.example.service.UserService;
import com.example.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public FlightRegistrationResponse createFlight(@RequestBody FlightCreate flight){

        return userService.createsFlight(flight);
    }

}
