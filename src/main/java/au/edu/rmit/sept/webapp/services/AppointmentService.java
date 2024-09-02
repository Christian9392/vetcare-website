package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;

import java.util.Collection;

public interface AppointmentService {
    public Collection<AppointmentDTO> getAppointments();
    public AppointmentDTO getAppointmentById(Long id);
    public void saveAppointment(AppointmentDTO appointmentDTO);
    public void deleteAppointment(Long id);
}
