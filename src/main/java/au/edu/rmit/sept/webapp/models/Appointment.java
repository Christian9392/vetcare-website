package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;
import java.time.LocalTime;

public record Appointment(
        Long AppointmentID,
        String UserID,
        String PetID,
        String ClinicID,
        LocalDate AppointmentDate,
        String Status,
        String GeneralNotes,
        Float Fees,
        LocalTime AppointmentTime,
        String status) {
}
