package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.CustomUser;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
   
     // Method to get all users
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Long getUserIdByUsername(String username) {
        Optional<User> userOpt = userRepository.findByName(username);
        if (userOpt.isPresent()) {
            return userOpt.get().userID();
        } else {
            System.out.println("User not found for username: " + username);
            throw new RuntimeException("User not found");
        }
    }    
    
    public User updateContactInfo(Long userID, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userID);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            User updated = new User(
                user.userID(),
                updatedUser.name(),
                user.password(),  
                updatedUser.email(),
                updatedUser.phoneNumber(),
                updatedUser.address()  
            );
            userRepository.save(updated);
            return updated;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // public boolean changePassword(Long userID, String oldPassword, String newPassword) {
    //     Optional<User> userOpt = userRepository.findById(userID);
    //     if (userOpt.isPresent()) {
    //         User user = userOpt.get();
    //         if (user.password().equals(oldPassword)) {  // Skip encoding for now
    //             userRepository.updatePassword(userID, newPassword);  // New query for password update
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public boolean changePassword(Long userID, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userID);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(oldPassword, user.password())) {
                String encodedPassword = passwordEncoder.encode(newPassword);
                userRepository.updatePassword(userID, encodedPassword);  
                return true;
            }
        }
        return false;
    }


    // public boolean deleteAccount(Long userID, String password) {
    //     Optional<User> userOpt = userRepository.findById(userID);
    //     if (userOpt.isPresent()) {
    //         User user = userOpt.get();
    //         if (user.password().equals(password)) {
    //             userRepository.delete(userID);  // Delete user from repository
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    public boolean deleteAccount(Long userID, String password) {
        Optional<User> userOpt = userRepository.findById(userID);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.password())) {
                userRepository.delete(userID);  
                return true;
            }
        }
        return false;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
   
   
   

    
}

