package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AppointmentDTO> getAppointments() {
        return repository.findAllAppointments();
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        return repository.findAppointmentById(id);
    }

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = repository.findById(appointmentDTO.AppointmentID())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setAppointmentID(appointmentDTO.AppointmentID());
        appointment.setAppointmentDate(appointmentDTO.AppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.AppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.GeneralNotes());
        appointment.setStatus(appointmentDTO.Status());
        repository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }
}
