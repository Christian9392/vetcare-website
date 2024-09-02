package au.edu.rmit.sept.webapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDTO(
        Long AppointmentID,
        LocalDate AppointmentDate,
        LocalTime AppointmentTime,
        String PetName,
        String VetName,
        String GeneralNotes,
        String Status
) { }
