package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT p.petID FROM Pet p WHERE p.name = :name")
    Long findPetIDByName(@Param("name") String name);

    @Query("SELECT p FROM Pet p WHERE p.owner.id = :userId")
    List<Pet> findByUserId(@Param("userId") Long userId);
}
