package au.edu.rmit.sept.webapp.services;

import java.util.Collection;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Appointment> getAppointments() {
        return repository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        repository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        repository.delete(id);
    }

}
