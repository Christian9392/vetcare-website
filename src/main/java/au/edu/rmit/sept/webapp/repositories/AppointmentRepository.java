package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentRepository {
    public List<AppointmentDTO> findAll();
    public AppointmentDTO findById(Long id);
    public void save(AppointmentDTO appointmentDTO);
    public void delete(Long id);
}
