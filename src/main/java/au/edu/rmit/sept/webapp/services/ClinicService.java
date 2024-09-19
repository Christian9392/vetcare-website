package au.edu.rmit.sept.webapp.services;
import au.edu.rmit.sept.webapp.models.Clinic;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface ClinicService {
    public List<Clinic> getClinics();
    public Long findClinicIDByName(String name);
    public Clinic findClinicByID(Long clinicID);
}
