package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {

    List<VaccinationRecord> findByPet_PetID(Long petID);
    @Query("SELECT MAX(v.dateAdministered) FROM VaccinationRecord v WHERE v.pet.petID = :petId")
    LocalDate findLastVaccinationDateByPetId(@Param("petId") Long petId);
}
