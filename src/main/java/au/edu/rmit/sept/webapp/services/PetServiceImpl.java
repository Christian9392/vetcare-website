package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.dto.*;
import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.models.Pet;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import au.edu.rmit.sept.webapp.repositories.PetRepository;
import au.edu.rmit.sept.webapp.repositories.PrescriptionRepository;
import au.edu.rmit.sept.webapp.repositories.TreatmentPlanRepository;
import au.edu.rmit.sept.webapp.repositories.VaccinationRecordRepository;
import groovy.transform.AutoImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Pet findPetByName(String name) {
        Long petID = repository.findPetIDByName(name);

        return repository.findById(petID)
        .orElseThrow(() -> new NoSuchElementException("Clinic not found with id " + petID));
    }
    
    @Override
    public Optional<Pet> findPetByPetId(Long petID) {
        return repository.findById(petID);
    }

    @Override
    public List<PetDTO> findPetsByUserId(Long userId) {
        List<Pet> pets = repository.findByUserId(userId);
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            PetDTO petDTO = new PetDTO(pet);
            
            // Calculate last vaccination date
            LocalDate lastVaccinationDate = vaccinationRecordRepository.findLastVaccinationDateByPetId(pet.getPetID());
            petDTO.setLastVaccinationDate(lastVaccinationDate);

            // Check for active prescriptions
            boolean hasActivePrescriptions = prescriptionRepository.existsByPetPetIDAndRenewalDateAfter(pet.getPetID(), LocalDate.now());
            petDTO.setActivePrescriptions(hasActivePrescriptions);

            // Check for active treatment plans
            boolean hasActiveTreatments = treatmentPlanRepository.existsByPetPetIDAndEndDateAfter(pet.getPetID(), LocalDate.now());
            petDTO.setActiveTreatments(hasActiveTreatments);

            petDTOs.add(petDTO);
        }

        return petDTOs;
    }

    @Override
    public void savePet(Pet pet) {
        repository.save(pet);
    }
}
