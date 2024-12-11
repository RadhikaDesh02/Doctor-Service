package com.cg.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cg.model.Doctor;



public interface IDoctorService {

	List<Doctor> findAllDoctors();
	Optional<Doctor> findDoctorById(int did);
	Doctor createDoctor(Doctor d);
	boolean deleteDoctor(int did);
	Doctor updateDoctor(Doctor d);
	List<Doctor> getDoctorBydname(String dname);
	List<Doctor> getDoctorsByQualification(String qualification);

}