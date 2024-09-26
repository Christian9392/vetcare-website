package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {

    List<VaccinationRecord> findByPet_PetID(Long petID);
}
