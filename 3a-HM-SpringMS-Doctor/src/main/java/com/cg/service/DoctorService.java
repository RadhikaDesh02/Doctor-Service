
package com.cg.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Doctor;
import com.cg.repository.DoctorRepository;

import jakarta.validation.Valid;

@Service
public class DoctorService implements IDoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;
	@Override
	public List<Doctor> findAllDoctors() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	@Override
	public Optional<Doctor> findDoctorById(int did) {
		// TODO Auto-generated method stub
		return doctorRepository.findById(did);
	}

	@Override
	public Doctor createDoctor(Doctor d) {
		// TODO Auto-generated method stub
		return doctorRepository.save(d);
	}

	@Override
	public boolean deleteDoctor(int did) {
		doctorRepository.deleteById(did);
		return true;
	
	}

	@Override
	public Doctor updateDoctor(Doctor d) {
		// TODO Auto-generated method stub
		return doctorRepository.save(d);
	}
	
	@Override
	public List<Doctor> getDoctorBydname(String dname){
		return doctorRepository.findAllByDname(dname);
	}

	@Override
	public List<Doctor> getDoctorsByQualification(String qualification) {
		// TODO Auto-generated method stub
		return doctorRepository.findDoctorsByQualification(qualification);
	}

}

