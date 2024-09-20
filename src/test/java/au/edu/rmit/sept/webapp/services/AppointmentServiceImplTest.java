package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAppointments() {
        List<AppointmentDTO> mockAppointments = new ArrayList<>();
        mockAppointments.add(new AppointmentDTO(1L, LocalDate.now(), LocalTime.now(), "Pet1", new CustomUser(), "Notes", "Status", "c", 1F));
        when(appointmentRepository.findAllAppointments()).thenReturn(mockAppointments);

        List<AppointmentDTO> appointments = appointmentService.getAppointments();

        assertEquals(1, appointments.size());
        assertEquals(mockAppointments.get(0), appointments.get(0));
    }

    @Test
    void testGetAppointmentById() {
        AppointmentDTO mockAppointment = new AppointmentDTO(1L, LocalDate.now(), LocalTime.now(), "Pet1", new CustomUser(), "Notes", "Status", "c", 1F);
        when(appointmentRepository.findAppointmentById(1L)).thenReturn(mockAppointment);

        AppointmentDTO appointment = appointmentService.getAppointmentById(1L);

        assertEquals(mockAppointment, appointment);
    }

    @Test
    void testSaveAppointment() {
        Appointment existingAppointment = new Appointment();
        existingAppointment.setAppointmentID(1L);
        existingAppointment.setAppointmentDate(LocalDate.now());
        existingAppointment.setAppointmentTime(LocalTime.now());
        existingAppointment.setGeneralNotes("Initial Notes");
        existingAppointment.setStatus("Initial Status");

        AppointmentDTO appointmentDTO = new AppointmentDTO(1L, LocalDate.now(), LocalTime.now(), "Pet1", new CustomUser(), "Notes", "Status", "c", 1F);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(existingAppointment));

        appointmentService.saveAppointment(appointmentDTO);

        verify(appointmentRepository).save(existingAppointment);
    }

    @Test
    void testDeleteAppointment() {
        Long appointmentId = 1L;
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(appointmentId);
    }
}

