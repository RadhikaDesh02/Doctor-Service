package com.cg.DTO;


import com.cg.model.Doctor;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class DoctorDTO {
	
	private int did;
	private String dname;
	private String qualification;
	
	
	
	//bucket se glass        //convert doctor to dto
		public static DoctorDTO fromEntity(Doctor doctor) {
			return new DoctorDTO(doctor.getDid(),doctor.getDname(),doctor.getQualification());
		}
		
		//glass se bucket       //convert dto to doctor
		public Doctor toEntity() {
			return new Doctor(this.did,this.dname,this.qualification);
		}
	

}
