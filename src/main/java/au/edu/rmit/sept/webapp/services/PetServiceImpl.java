package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.*;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import au.edu.rmit.sept.webapp.repositories.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    @Autowired
    public PetServiceImpl(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> getPets() {
        return repository.findAll();
    }

}
