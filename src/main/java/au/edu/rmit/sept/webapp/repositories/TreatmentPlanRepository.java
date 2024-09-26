package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {

    List<TreatmentPlan> findByPet_PetID(Long petID);
}
