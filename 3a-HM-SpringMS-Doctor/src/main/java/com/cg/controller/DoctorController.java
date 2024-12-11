package com.cg.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.DTO.DoctorDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Doctor;
import com.cg.service.IDoctorService;

@RequestMapping("/dapi")
@RestController

public class DoctorController {
	@Autowired
	IDoctorService doctorService;
	
	//for loggers
	   private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
	
	   
	 //validation
	   
    @Value("${error.doctorNotFound}")
    private String doctorNotFound;
	
//	@GetMapping("/doctors") 
	
//	public List<Doctor> getDoctors(){
//		return doctorService.findAllDoctors();
//	}
	@GetMapping(path = "/doctors", produces = {MediaType.APPLICATION_JSON_VALUE})

	public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        return doctors.stream()
                      .map(DoctorDTO::fromEntity)  // Convert Doctor to DoctorDTO
                      .collect(Collectors.toList()); 
    }	
	
//	@GetMapping(path="/doctors/{did}")
//	public Optional<Doctor>getByDoctorId(@PathVariable int did)
//	 {
//		return doctorService.findDoctorById(did);
//	}
		
	//dto and exception
	
	@GetMapping(path="/doctors/{did}")
	 public ResponseEntity<DoctorDTO> getByDoctorId(@PathVariable int did) throws ResourceNotFoundException {
	        Optional<Doctor> doctor = doctorService.findDoctorById(did);  
	        if (!doctor.isPresent()) {
	            throw new ResourceNotFoundException(String.format(doctorNotFound, did));
	        }
	        DoctorDTO doctorDTO = DoctorDTO.fromEntity(doctor.get());
	        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
	    }
	
	
	@PostMapping("/doctors")
	
//	public Doctor createMyDoctor(@RequestBody Doctor d) {
//		return doctorService.createDoctor(d);
//	}
	
	public DoctorDTO createMyDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDname(doctorDTO.getDname());
        doctor.setQualification(doctorDTO.getQualification());
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return DoctorDTO.fromEntity(createdDoctor);
    }
	
	@DeleteMapping(path="/doctor/{did}")
	
	public boolean deleteMyDoctor(@PathVariable int did) {
		return doctorService.deleteDoctor(did);
	}
	
	@PutMapping("/doctors")
//	public Doctor updateMyDoctor(@RequestBody Doctor d)
//	{
//		return doctorService.updateDoctor(d);
//		
//	}
	
	public DoctorDTO updateMyDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDid(doctorDTO.getDid()); 
        doctor.setDname(doctorDTO.getDname());
        doctor.setQualification(doctorDTO.getQualification());
        Doctor updatedDoctor = doctorService.updateDoctor(doctor);
        return DoctorDTO.fromEntity(updatedDoctor);
    }
	
	//custom method to find by doctor name
	@GetMapping(path="/doctors/dname/{dname}")
	public List<Doctor> findAllBydname(@PathVariable String dname ){
		return doctorService.getDoctorBydname(dname);
	}
	
	//Get doctor by qualification(custom query)
		@GetMapping(path = "/doctors/qualification/{qualification}")
		    public List<Doctor> getDoctorsByQualification(@PathVariable String qualification) {
		        return doctorService.getDoctorsByQualification(qualification);
		    }	
		
	//loggers
		@RequestMapping("/log/alldoctors")
		 public List<Doctor> displayProduct()
		 { 	 logger.info("Doctor list is going to dispaly");
			 List<Doctor> listDoctors = doctorService.findAllDoctors();
			 System.out.println(" Hello");
			 logger.info("Doctor list displayed");
			 logger.debug("Missing Doctor.png");
		
			 return listDoctors;
		 }
}