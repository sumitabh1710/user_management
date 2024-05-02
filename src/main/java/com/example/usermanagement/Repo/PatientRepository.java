package com.example.usermanagement.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.usermanagement.Entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    Patient getById(Long id);

    @Query(value = "SELECT * FROM patients;", nativeQuery = true)
    List<Patient> getPatients();

}