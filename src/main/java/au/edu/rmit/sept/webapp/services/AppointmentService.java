package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Appointment;

import java.util.List;

public interface AppointmentService {

    // Create a new appointment
    Appointment createAppointment(Appointment appointment);

    // Get all appointments for the current user
    List<Appointment> getAppointmentsForUser(Long userId);

}
