package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.*;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.webapp.models.Pet;

import java.util.List;
import java.util.NoSuchElementException;

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

    private Appointment updateAppointmentDetails(AppointmentDTO appointmentDTO, Appointment appointment) {
        //update appointment object using appointmentDTO data
        appointment.setAppointmentID(appointmentDTO.getAppointmentID());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.getGeneralNotes());
        appointment.setStatus(appointmentDTO.getStatus());
        return appointment;
    }

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        //find existing appointment
        Appointment appointment = repository.findById(appointmentDTO.getAppointmentID())
        .orElseThrow(() -> new NoSuchElementException("appointment not found with id " + appointmentDTO.getAppointmentID()));

        //update details using appointmentDTO
        appointment = updateAppointmentDetails(appointmentDTO, appointment);
        repository.save(appointment);
    }

    @Override
    public void createNewAppointment(AppointmentDTO appointmentDTO, Clinic clinic, CustomUser user, CustomUser vet, Pet pet) {

        //create new appointment object, status should be set to upcoming
        Appointment appointment = new Appointment();
        appointmentDTO.setStatus("Upcoming");
        appointment = updateAppointmentDetails(appointmentDTO, appointment);

        //update additional data
        appointment.setFees(50F);
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setClinic(clinic);
        appointment.setUser(user);
        repository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }
}
