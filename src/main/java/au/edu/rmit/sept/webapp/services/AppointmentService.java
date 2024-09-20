package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;

import java.util.Collection;

public interface AppointmentService {
    public Collection<AppointmentDTO> getAppointments();
    public AppointmentDTO getAppointmentById(Long id);
    public void saveAppointment(AppointmentDTO appointmentDTO);
    public void saveAppointment(AppointmentDTO appointmentDTO, Clinic clinic, CustomUser user, CustomUser vet);
    public void deleteAppointment(Long id);
}
