package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPet_PetID(Long petID);
}
