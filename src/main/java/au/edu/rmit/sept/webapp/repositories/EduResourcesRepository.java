package au.edu.rmit.sept.webapp.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import au.edu.rmit.sept.webapp.models.EduResources;

@Repository
public interface EduResourcesRepository extends JpaRepository<EduResources, Long> {

    @Query("SELECT e FROM EduResources e WHERE e.resourceType = :resourceType")
    List<EduResources> findAllResourcesByType(@Param("resourceType") String resourceType);
}
