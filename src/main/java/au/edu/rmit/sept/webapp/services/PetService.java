package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import au.edu.rmit.sept.webapp.dto.PetDTO;
import au.edu.rmit.sept.webapp.models.Pet;

import java.util.List;

import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PetService {
    public List<Pet> getPets();
    public Pet findPetByName(String name);
    public Optional<Pet> findPetByPetId(Long petId);
    public List<PetDTO> findPetsByUserId(Long userId);
    public void savePet(Pet pet);
}
