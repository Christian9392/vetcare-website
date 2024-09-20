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

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = repository.findById(appointmentDTO.getAppointmentID())
        .orElseThrow(() -> new NoSuchElementException("appointment not found with id " + appointmentDTO.getAppointmentID()));
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.getGeneralNotes());
        appointment.setStatus(appointmentDTO.getStatus());
        repository.save(appointment);
    }

    @Override
    public void createAppointment(AppointmentDTO appointmentDTO, Clinic clinic, CustomUser user, CustomUser vet, Pet pet) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentID(appointmentDTO.getAppointmentID());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setGeneralNotes(appointmentDTO.getGeneralNotes());
        appointment.setStatus(appointmentDTO.getStatus());

        //temp, change fee based on what appointment type picks
        appointment.setFees(50F);

        //update clinic, vetName, petName and user
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
