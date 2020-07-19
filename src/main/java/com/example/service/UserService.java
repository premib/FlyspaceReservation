package com.example.service;


import com.example.exception.ServiceException;
import com.example.model.FlightModel;
import com.example.model.RegistrationsModel;
import com.example.model.UserModel;
//import com.example.security.JWTProvider;
import com.example.repository.FlightRepository;
import com.example.repository.RegistrationRepository;
import com.example.repository.UserRepository;
import com.example.security.JWTProvider;
import com.example.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public UserTransactionsResponse checkTransactions(UserTransactions user){

        List<RegistrationsModel> registrationsModel = registrationRepository.findByEmail(user.getEmail());
        return modelMapper.map(registrationsModel, UserTransactionsResponse.class);
    }

    public FlightRegistrationResponse createsFlight(FlightCreate flight){

        FlightModel flightModel = modelMapper.map(flight, FlightModel.class);
        FlightModel flightModelCheck = flightRepository.findByFlightId(flight.getFlightId());

        if(flightModelCheck == null){

            flightRepository.save(flightModel);
            return modelMapper.map(flightModel, FlightRegistrationResponse.class);
        }
        else{

            throw new ServiceException("Flight with flightId already exists!!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public FlightRegistrationResponse flightRegister(FlightRegistration flight){

        FlightModel flightModel = flightRepository.findByFlightId(flight.getFlightId());
        System.out.println(flightModel);
        RegistrationsModel registrationsModel = new RegistrationsModel();

        if(flightModel != null){

            registrationsModel.setEmail(flight.getEmail().trim());
            registrationsModel.setFlightId(String.valueOf(flight.getFlightId()).trim());
            Integer seatInterested = flight.getSeatNo();
            registrationsModel.setSeatNo(String.valueOf(seatInterested).trim());
            switch (seatInterested){
                case 1:
                    flightModel.setSeat1(flight.getEmail());
                    break;
                case 2:
                    flightModel.setSeat2(flight.getEmail());
                    break;
                case 3:
                    flightModel.setSeat3(flight.getEmail());
                    break;
                case 4:
                    flightModel.setSeat4(flight.getEmail());
                    break;
                case 5:
                    flightModel.setSeat5(flight.getEmail());
                    break;
            }
            registrationRepository.save(registrationsModel);
            flightRepository.save(flightModel);
            return modelMapper.map(flightModel, FlightRegistrationResponse.class);
        }
        else{

            throw new ServiceException("Invalid flight id", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    public LoginResponse login(Login loginRequest){
        
        try{

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            System.out.println(email+" "+ password);
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String jwtToken= jwtProvider.createToken(email, userRepository.findByEmail(email).getRoles());
            System.out.println(jwtToken);
            return new LoginResponse(jwtToken);
        }
        catch (AuthenticationException e){

            throw new ServiceException("Invalid credentials", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public RegistrationResponse register(Registration user){

        UserModel userModel = modelMapper.map(user, UserModel.class);

        if(!userRepository.existsByEmail(userModel.getEmail())){

            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userRepository.save(userModel);
            String jwtToken = jwtProvider.createToken(userModel.getEmail(), userModel.getRoles());
            return new RegistrationResponse(jwtToken);
        }
        else{

            throw new ServiceException("Username already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    public FlightStatusResponse searchFlight(Integer id){
        FlightModel flightModel = flightRepository.findByFlightId(id);
        if(flightModel != null){

            List<Boolean> seatList = new ArrayList<>();
            if(flightModel.getSeat1() != null)
                seatList.add(false);
            else
                seatList.add(true);
            if(flightModel.getSeat2() != null)
                seatList.add(false);
            else
                seatList.add(true);
            if(flightModel.getSeat3() != null)
                seatList.add(false);
            else
                seatList.add(true);
            if(flightModel.getSeat4() != null)
                seatList.add(false);
            else
                seatList.add(true);
            if(flightModel.getSeat5() != null)
                seatList.add(false);
            else
                seatList.add(true);

            return new FlightStatusResponse(seatList);
        }
        else{

            throw new ServiceException("Flight Id doensn't exist", HttpStatus.NOT_FOUND);
        }
    }
}