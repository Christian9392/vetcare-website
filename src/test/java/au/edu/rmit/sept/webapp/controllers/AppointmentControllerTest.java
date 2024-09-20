package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.models.CustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void testGetAppointments() throws Exception {
        AppointmentDTO appointment1 = new AppointmentDTO(1L, LocalDate.now(), LocalTime.now(), "Pet1", new CustomUser(), "Notes", "Status", "C", 1F);
        AppointmentDTO appointment2 = new AppointmentDTO(2L, LocalDate.now(), LocalTime.now(), "Pet2", new CustomUser(), "Notes", "Status", "C", 1F);
        Collection<AppointmentDTO> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.getAppointments()).thenReturn(appointments);

        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(view().name("appointments/index"))
                .andExpect(model().attribute("appointments", appointments));
    }

    @Test
    void testViewAppointment() throws Exception {
        AppointmentDTO appointment = new AppointmentDTO(1L, LocalDate.now(), LocalTime.now(), "Pet1", new CustomUser(), "Notes", "Status", "C", 1F);

        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        mockMvc.perform(get("/appointments/view/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("appointments/view"))
                .andExpect(model().attribute("appointment", appointment));
    }

    @Test
    void testEditAppointment() throws Exception {
        AppointmentDTO appointment = new AppointmentDTO(1L, LocalDate.now(), LocalTime.now(), "Pet1", new CustomUser(), "Notes", "Status", "C", 1F);

        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        mockMvc.perform(get("/appointments/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("appointments/edit"))
                .andExpect(model().attribute("appointment", appointment));
    }

    @Test
    void testSaveAppointment() throws Exception {
        mockMvc.perform(post("/appointments/save")
                        .param("appointmentID", "1")
                        .param("appointmentDate", LocalDate.now().toString())
                        .param("appointmentTime", LocalTime.now().toString())
                        .param("name", "Pet1")
                        .param("user", "User1")
                        .param("generalNotes", "Notes")
                        .param("status", "Status"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/appointments"))
                .andExpect(flash().attribute("save_message", "Appointment updated successfully."));
    }

    @Test
    void testDeleteAppointment() throws Exception {
        mockMvc.perform(get("/appointments/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/appointments"))
                .andExpect(flash().attribute("delete_message", "Appointment deleted successfully."));
    }
}
