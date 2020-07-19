package com.example.repository;

import com.example.model.RegistrationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationsModel, Integer> {

    boolean existsByTid(Integer tId);

    RegistrationsModel findByTid(Integer tId);

    List<RegistrationsModel> findByEmail(String email);
}
