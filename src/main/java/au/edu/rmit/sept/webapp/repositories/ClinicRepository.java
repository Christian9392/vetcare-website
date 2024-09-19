package au.edu.rmit.sept.webapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import au.edu.rmit.sept.webapp.dto.ClinicDTO;
import au.edu.rmit.sept.webapp.models.Clinic;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    @Query("SELECT new au.edu.rmit.sept.webapp.dto.ClinicDTO(c.clinicID, c.name) FROM Clinic c")
    List<ClinicDTO> findAllClinics();
}