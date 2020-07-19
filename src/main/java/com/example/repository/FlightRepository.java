package com.example.repository;

import com.example.model.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightModel, Integer> {

    boolean existsByFlightId(Integer flightId);

    FlightModel findByFlightId(Integer flightId);
}
