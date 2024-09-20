package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.enums.UserType;
import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> customUser = Optional.ofNullable(customUserRepository.findByName(username));
        if (customUser.isPresent()) {
            var user = customUser.get();
            return
                    User.builder()
                            .username(user.getName())
                            .password(user.getPassword())
                            .roles(user.getUserType().name())
                            .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    // Get the currently authenticated user
    public CustomUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return customUserRepository.findByName(username);
        } else {
            throw new RuntimeException("User is not authenticated");
        }
    }

    public List<CustomUser> getVets() {
        List<Long> vetIDs = customUserRepository.findVetID(UserType.Vet);
        List<CustomUser> vets = new ArrayList<>();

        for (int i=0; i<vetIDs.size(); i++) {

            CustomUser user = customUserRepository.findById(vetIDs.get(i)).orElse(null);
            vets.add(user);
        }

        return vets;
    }

    public CustomUser findVetByName(String name) {
        Long vetID = customUserRepository.findVetIDByName(name);

        return customUserRepository.findById(vetID)
        .orElseThrow(() -> new NoSuchElementException("Clinic not found with id " + vetID));
    }
}
