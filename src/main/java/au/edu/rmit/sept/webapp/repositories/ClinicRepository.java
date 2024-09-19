package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
