package au.edu.rmit.sept.webapp.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import au.edu.rmit.sept.webapp.models.SavedResources;
import java.util.List;

@Repository
public interface SavedResourcesRepository extends JpaRepository<SavedResources, Long> {

    @Query("SELECT s FROM SavedResources s")
    List<SavedResources> findAllSavedResources();
}
