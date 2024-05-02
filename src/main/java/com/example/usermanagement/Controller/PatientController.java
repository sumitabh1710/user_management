package com.example.usermanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.Entity.Patient;
import com.example.usermanagement.Service.PatientService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public ResponseEntity<List<Patient>> getMethodName() {
        return patientService.getPatient();
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @PutMapping("edit")
    public ResponseEntity<Object> editPatient(@RequestBody Patient patient) {
        return patientService.editPatient(patient);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

}
