package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.models.Pet;

import java.util.List;

public interface PetService {
    public List<Pet> getPets();
    public Pet findPetByName(String name);
}
