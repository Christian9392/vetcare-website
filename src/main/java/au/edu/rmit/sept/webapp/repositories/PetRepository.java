package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
