package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    // Test Case 1: Verify object creation and field access
    @Test
    void testUserCreation() {
        Long userId = 1L;
        String name = "Cristiano Sui";
        String password = "password123";
        String email = "cristiano.sui@example.com";
        String phoneNumber = "123456789";
        String address = "123 Stadium Road, City, Country";        
        User user = new User(userId, name, password, email, phoneNumber, address);
        assertEquals(userId, user.userID());
        assertEquals(name, user.name());
        assertEquals(password, user.password());
        assertEquals(email, user.email());
        assertEquals(phoneNumber, user.phoneNumber());
        assertEquals(address, user.address());
    }
}
