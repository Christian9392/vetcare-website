package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.*;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.repositories.PetRepository;
import au.edu.rmit.sept.webapp.repositories.PrescriptionRepository;
import au.edu.rmit.sept.webapp.repositories.TreatmentPlanRepository;
import au.edu.rmit.sept.webapp.repositories.VaccinationRecordRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    @Autowired
    public PetServiceImpl(PetRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private TreatmentPlanRepository treatmentPlanRepository;

    public List<Pet> getPets() {
        return repository.findAll();
    }
    
    @Override
    public Optional<Pet> findPetBypetId(Long petId) {
        return repository.findById(petId);
    }

    @Override
    public Pet getPetById(Long petId) {
        return repository.findById(petId)
            .orElseThrow(() -> new EntityNotFoundException("Pet not found with ID: " + petId));
    }    


    @Override
    public List<PetDTO> findPetsByUserId(Long userId) {
        List<Pet> pets = repository.findByOwnerUserId(userId);
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            PetDTO petDTO = new PetDTO(pet);
            
            // Calculate last vaccination date
            Optional<LocalDate> lastVaccinationDateOptional = vaccinationRecordRepository.findLatestVaccinationDateByPetId(pet.getPetId());
            petDTO.setLastVaccinationDate(lastVaccinationDateOptional.orElse(null));

            // Check for active prescriptions
            boolean hasActivePrescriptions = prescriptionRepository.existsByPet_PetIdAndRenewalDateAfter(pet.getPetId(), LocalDate.now());
            petDTO.setActivePrescriptions(hasActivePrescriptions);

            // Check for active treatment plans
            boolean hasActiveTreatments = treatmentPlanRepository.existsByPet_PetIdAndEndDateAfter(pet.getPetId(), LocalDate.now());
            petDTO.setActiveTreatments(hasActiveTreatments);

            petDTOs.add(petDTO);
        }

        return petDTOs;
    }


 
    @Override
    public void savePet(Pet pet) {
        repository.save(pet);
    }  

    @Override  
    public void updatePet(Long petId, PetDTO petDTO) {
    }

    
}
