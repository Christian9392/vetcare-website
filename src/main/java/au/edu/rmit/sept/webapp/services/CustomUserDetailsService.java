package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser user = customUserRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getUserType().name())
                .build();
    }
}
