package com.cg.__SpringRest_Hello;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.controller.DoctorController;
import com.cg.model.Doctor;
import com.cg.service.IDoctorService;
import com.cg.DTO.DoctorDTO;
import com.cg.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerFile {

    @Mock
    private IDoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor(13, "Sakhi", "Cardiologist");
    }

    @Test
    void testGetDoctors() {
        when(doctorService.findAllDoctors()).thenReturn(Arrays.asList(doctor));

        var doctors = doctorController.getDoctors();

        assertFalse(doctors.isEmpty());
        assertEquals(1, doctors.size());
        assertEquals("Sakhi", doctors.get(0).getDname());
    }

    @Test
    void testGetByDoctorId_Found() throws ResourceNotFoundException {
        when(doctorService.findDoctorById(13)).thenReturn(Optional.of(doctor));

        var result = doctorController.getByDoctorId(13);

        assertEquals(doctor.getDname(), result.getBody().getDname());
        assertEquals("Sakhi", result.getBody().getDname());
    }

   

    @Test
    void testCreateMyDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO(13, "Sakhi", "Cardiologist");
        when(doctorService.createDoctor(any(Doctor.class))).thenReturn(doctor);

        DoctorDTO createdDoctor = doctorController.createMyDoctor(doctorDTO);

        assertNotNull(createdDoctor);
        assertEquals("Sakhi", createdDoctor.getDname());
    }

    @Test
    void testDeleteMyDoctor() {
        when(doctorService.deleteDoctor(13)).thenReturn(true);

        doctorController.deleteMyDoctor(13);

        verify(doctorService, times(1)).deleteDoctor(13);
    }

    @Test
    void testUpdateMyDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO(13, "Sakhi", "Cardiologist");
        when(doctorService.updateDoctor(any(Doctor.class))).thenReturn(doctor);

        DoctorDTO updatedDoctor = doctorController.updateMyDoctor(doctorDTO);

        assertNotNull(updatedDoctor);
        assertEquals("Sakhi", updatedDoctor.getDname());
    }

  

    

    @Test
    void testLogger() {
        // Assuming the logging is part of another method you want to verify manually
        // There's no direct way to assert logs with Mockito, so we'd need to use a tool like LogCaptor to capture log output.
        // You can add tests that assert certain behavior when logger is triggered.

        // This is a placeholder test, as testing logs directly in unit tests is challenging.
    }
}
  