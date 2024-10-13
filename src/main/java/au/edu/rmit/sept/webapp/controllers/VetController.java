package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.dto.PetDTO;
import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.PetService;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vet")
public class VetController {

    private final PetService petService;
    private final UserService userService;

    @Autowired
    public VetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

  // List all pets associated with a user (Accessing User Pet Information)
  @GetMapping("/owners/{userID}/pets")
  public String listPets(@PathVariable Long userID, Model model) {
      List<PetDTO> pets = petService.findPetsByUserId(userID); 
      if (pets.isEmpty()) {
          model.addAttribute("noPetsMessage", "No pets found for this user.");
      } else {
          model.addAttribute("pets", pets);
      }
      model.addAttribute("userID", userID); // Bind userId for URLs
      return "vet/ownerList"; // Renders 'petList.html'
  }

    // Edit pet form
    @GetMapping("/owners/{userId}/pets/{petId}/edit")
    public String editPet(@PathVariable Long userId, @PathVariable Long petId, Model model) {
        Pet pet = petService.getPetById(petId);
        model.addAttribute("pet", pet); // Bind pet to the form
        model.addAttribute("userId", userId); // Bind userId for URLs
        return "vet/editPet"; // Renders 'editPet.html'
    }

    // Save updated pet
    @PostMapping("/owners/{userId}/pets/{petId}/save")
    public String savePet(@PathVariable Long userId, @PathVariable Long petId, @ModelAttribute Pet petForm) {
        // Retrieve the existing pet from the database
        Pet existingPet = petService.findPetBypetId(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with ID: " + petId));

        // Update the basic information of the pet (name, species, breed, age)
        existingPet.setName(petForm.getName());
        existingPet.setSpecies(petForm.getSpecies());
        existingPet.setBreed(petForm.getBreed());
        existingPet.setAge(petForm.getAge());

        // Update the pet's medical history
        MedicalHistory updatedMedicalHistory = petForm.getMedicalHistory();
        if (updatedMedicalHistory != null) {
            petService.updateMedicalHistory(petId, updatedMedicalHistory);
        }

        // Update the pet's vaccinations
        List<VaccinationRecord> updatedVaccinations = petForm.getVaccinations();
        if (updatedVaccinations != null && !updatedVaccinations.isEmpty()) {
            petService.updateVaccinationRecords(petId, updatedVaccinations);
        }

        // Update the pet's treatment plans
        List<TreatmentPlan> updatedTreatmentPlans = petForm.getTreatmentPlans();
        if (updatedTreatmentPlans != null && !updatedTreatmentPlans.isEmpty()) {
            petService.updateTreatmentPlans(petId, updatedTreatmentPlans);
        }

        // Update the pet's prescriptions
        List<Prescription> updatedPrescriptions = petForm.getPrescriptions();
        if (updatedPrescriptions != null && !updatedPrescriptions.isEmpty()) {
            petService.updatePrescriptions(petId, updatedPrescriptions);
        }

        // Save the pet with the updated details
        petService.savePet(existingPet);

        // Redirect back to the pet list for this user
        return "redirect:/vet/owners/" + userId + "/pets";
    }

    // View vet's homepage
    @GetMapping("/home")
    public String vetHomePage() {
        return "vet/index"; // Renders 'vetHome.html'
    }
}
