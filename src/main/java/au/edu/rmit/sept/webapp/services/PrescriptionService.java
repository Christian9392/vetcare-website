package au.edu.rmit.sept.webapp.services;

public interface PrescriptionService {
    public boolean checkPrescription(Long PrescriptionID);
    public void decrementPrescription(Long PrescriptionID);
    public void orderPrescription(Long prescriptionID, Long petId);
}
