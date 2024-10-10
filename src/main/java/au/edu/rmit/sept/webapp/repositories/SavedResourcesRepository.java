package au.edu.rmit.sept.webapp.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import au.edu.rmit.sept.webapp.models.SavedResources;

@Repository
public interface SavedResourcesRepository extends JpaRepository<SavedResources, Long> {

}
