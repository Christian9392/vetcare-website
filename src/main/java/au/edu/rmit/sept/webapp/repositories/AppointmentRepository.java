package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Appointment;

import java.util.List;

public interface AppointmentRepository {
    public List<Appointment> findAll();
    public Appointment findById(Long id);
    public void save(Appointment appointment);
    public void delete(Long id);
}
