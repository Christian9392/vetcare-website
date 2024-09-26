package au.edu.rmit.sept.webapp.dto;
import java.time.LocalDate;
import au.edu.rmit.sept.webapp.models.*;

public class PetDTO {

    private Long petID;
    private String name;
    private String species;
    private String breed;
    private Integer age;

    private LocalDate lastVaccinationDate;
    private boolean activePrescriptions;
    private boolean activeTreatments;

    // Constructor
    public PetDTO(Pet pet) {
        this.petID = pet.getPetID();
        this.name = pet.getName();
        this.species = pet.getSpecies();
        this.breed = pet.getBreed();
        this.age = pet.getAge();
    }

    // Getters and Setters

    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getLastVaccinationDate() {
        return lastVaccinationDate;
    }

    public void setLastVaccinationDate(LocalDate lastVaccinationDate) {
        this.lastVaccinationDate = lastVaccinationDate;
    }

    public boolean isActivePrescriptions() {
        return activePrescriptions;
    }

    public void setActivePrescriptions(boolean activePrescriptions) {
        this.activePrescriptions = activePrescriptions;
    }

    public boolean isActiveTreatments() {
        return activeTreatments;
    }

    public void setActiveTreatments(boolean activeTreatments) {
        this.activeTreatments = activeTreatments;
    }
}
