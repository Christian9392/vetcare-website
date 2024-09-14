package au.edu.rmit.sept.webapp.models;

import au.edu.rmit.sept.webapp.enums.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = true)
    private Clinic clinic;

    public Long getUser_idId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}