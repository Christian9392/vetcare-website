package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByName(String username);
}
