package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import au.edu.rmit.sept.webapp.dto.ClinicDTO;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository repository;

    @Autowired
    public ClinicServiceImpl(ClinicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClinicDTO> getClinics() {
        return repository.findAllClinics();
    }
}