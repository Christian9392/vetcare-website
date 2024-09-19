package au.edu.rmit.sept.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.repositories.ClinicRepository;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic saveClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public Clinic findClinicById(Long id) {
        return clinicRepository.findById(id).orElse(null);
    }
}
