package au.edu.rmit.sept.webapp.models;

public record User(
    Long userID,
    String name,
    String password,
    String email,
    String phoneNumber,
    String address 
    // String unitNumber,
    // String streetNumber,
    // String streetName,
    // String suburb,
    // String state,
    // String postcode
) {}
