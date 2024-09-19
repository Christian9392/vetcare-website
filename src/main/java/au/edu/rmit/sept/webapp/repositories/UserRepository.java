package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;
import javax.sql.DataSource;

@Repository
public class UserRepository {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                    rs.getLong("user_id"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("address")  
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    
    public void save(User user) {
        String sql = "UPDATE user SET name = ?, email = ?, phone_number = ?, address = ? WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.name());
            ps.setString(2, user.email());
            ps.setString(3, user.phoneNumber());
            ps.setString(4, user.address());  
            ps.setLong(5, user.userID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(Long userID, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setLong(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete user by ID
    // public void delete(Long userID) {
    //     String sql = "DELETE FROM user WHERE user_id = ?";
    //     try (Connection conn = dataSource.getConnection();
    //          PreparedStatement ps = conn.prepareStatement(sql)) {
    //         ps.setLong(1, userID);
    //         ps.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    public void delete(Long userID) {
        String disableFK = "SET FOREIGN_KEY_CHECKS = 0;";
        String enableFK = "SET FOREIGN_KEY_CHECKS = 1;";
        String sql = "DELETE FROM user WHERE user_id = ?";
    
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement psDisableFK = conn.prepareStatement(disableFK)) {
                psDisableFK.executeUpdate();
            }
    
            try (PreparedStatement psDelete = conn.prepareStatement(sql)) {
                psDelete.setLong(1, userID);
                psDelete.executeUpdate();
            }
    
            try (PreparedStatement psEnableFK = conn.prepareStatement(enableFK)) {
                psEnableFK.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
