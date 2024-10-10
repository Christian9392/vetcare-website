package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.PetDTO;
import au.edu.rmit.sept.webapp.models.Pet;

import java.util.List;

import java.util.Optional;

public interface PetService {
    public List<Pet> getPets();
    public Optional<Pet> findPetBypetId(Long petId);
    public Pet getPetById(Long petId);
    public List<PetDTO> findPetsByUserId(Long userId);
    public void savePet(Pet pet);
    public void updatePet(Long petId, PetDTO petDTO);
    
}
