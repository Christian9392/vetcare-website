package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Prescription;
import au.edu.rmit.sept.webapp.repositories.PrescriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository repository) {
        this.repository = repository;
    }

    public boolean checkPrescription(Long prescriptionID) {
        // Get prescription based on ID
        Prescription prescription = repository.findByPrescriptionID(prescriptionID);
        // Get current date
        LocalDate currentDate = LocalDate.now();
        // Check for remaining refills and expiry
        return (prescription.getRepeatsLeft() > 0 || prescription.getExpiryDate().isAfter(currentDate));
    }

    public void decrementPrescription(Long prescriptionID) {
        // Get prescription based on ID
        Prescription prescription = repository.findByPrescriptionID(prescriptionID);
        // Set repeats to (current - 1)
        prescription.setRepeatsLeft(prescription.getRepeatsLeft() - 1);
        // Save to repository
        repository.save(prescription);
    }

    public void orderPrescription(Long prescriptionID, Long petId) {
        // Handles prescription order requests
        // This is a feature that we do not plan to implement, so it's left as a placeholder.
        System.out.println(String.format("Recieved prescription order '%d' for pet '%d'", prescriptionID, petId));
    }
}
