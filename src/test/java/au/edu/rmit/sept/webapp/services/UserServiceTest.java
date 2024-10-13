package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User(1L, "testuser", "encodedPassword", "testuser@example.com", "1234567890", "Test Address");
    }

    // Test Case 1: Ensure loadUserByUsername throws exception if user not found
    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByName("testuser")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("testuser"));
    }
    // Test Case 2: Ensure loadUserByUsername returns UserDetails when user exists
    @Test
    void testLoadUserByUsername_UserFound() {
        when(userRepository.findByName("testuser")).thenReturn(Optional.of(mockUser));
        var userDetails = userService.loadUserByUsername("testuser");
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
    }
}
