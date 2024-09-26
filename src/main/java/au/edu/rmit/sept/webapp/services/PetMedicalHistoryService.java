package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PetMedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final VaccinationRecordRepository vaccinationRecordRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PetMedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository,
                                    VaccinationRecordRepository vaccinationRecordRepository,
                                    TreatmentPlanRepository treatmentPlanRepository,
                                    PrescriptionRepository prescriptionRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.vaccinationRecordRepository = vaccinationRecordRepository;
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    public Optional<MedicalHistory> getMedicalHistoryByPetID(Long petID) {
        return medicalHistoryRepository.findByPet_PetID(petID);
    }

    public List<VaccinationRecord> getVaccinationRecordsByPetID(Long petID) {
        return vaccinationRecordRepository.findByPet_PetID(petID);
    }

    public List<TreatmentPlan> getTreatmentPlansByPetID(Long petID) {
        return treatmentPlanRepository.findByPet_PetID(petID);
    }

    public List<Prescription> getPrescriptionsByPetID(Long petID) {
        return prescriptionRepository.findByPet_PetID(petID);
    }
}
