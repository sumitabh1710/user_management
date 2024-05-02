package com.example.usermanagement.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.usermanagement.Entity.Patient;
import com.example.usermanagement.Repo.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<List<Patient>> getPatient() {

        List<Patient> patientsList = patientRepository.getPatients();

        return ResponseEntity.ok().body(patientsList);
    }

    public ResponseEntity<Patient> addPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return ResponseEntity.ok().body(savedPatient);
    }

    public ResponseEntity<Object> editPatient(Patient patient) {
        Patient existingPatient = patientRepository.getById(patient.getId());

        if (existingPatient == null) {
            return ResponseEntity.badRequest().body("Patient does not exists !");
        }

        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setMedicalHistory(patient.getMedicalHistory());

        patientRepository.save(existingPatient);

        return ResponseEntity.ok().body(existingPatient);
    }

    public ResponseEntity<Object> deletePatient(Long id) {

        Patient existingPatient = patientRepository.getById(id);

        if (existingPatient == null) {
            return ResponseEntity.badRequest().body("Patient does not exists !");
        }

        patientRepository.delete(existingPatient);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Patient deleted successfully");

        return ResponseEntity.ok().body(responseBody);
    }

}
