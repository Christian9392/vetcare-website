-- This is some test data for the application. Insert these into the
-- H2 console and run some queries to test your application logic!

INSERT INTO `Clinic` (Name, StreetNumber, StreetName, Suburb, State, Postcode, PhoneNumber, Email) VALUES
('Melbourne Veterinary Clinic', '123', 'Main St', 'Melbourne', 'VIC', '3000', '03-9123-4567', 'info@melbourne.com.au'),
('Sydney Vet Care', '456', 'Elm St', 'Sydney', 'NSW', '2000', '02-9876-5432', 'info@sydneyvet.com.au');

INSERT INTO `User` (Name, Password, Email, ClinicID, PhoneNumber, UnitNumber, StreetNumber, StreetName, Suburb, State, Postcode, UserType) VALUES
('Dr. John Smith', 'password123', 'jsmith@melbournevet.com.au', 1, '0411-222-333', NULL, '123', 'Main St', 'Melbourne', 'VIC', '3000', 'Vet'),
('Dr. Jane Doe', 'password456', 'jdoe@sydneyvet.com.au', 2, '0411-222-444', NULL, '456', 'Unique St', 'Sydney', 'NSW', '2000', 'Vet'),
('Alice Johnson', 'password789', 'alice.johnson@example.com', NULL, '0411-222-555', NULL, '789', 'High St', 'Melbourne', 'VIC', '3000', 'PetOwner'),
('Bob Brown', 'password123', 'bob.brown@example.com', NULL, '0411-222-666', NULL, '101', 'Low Rd', 'Sydney', 'NSW', '2000', 'PetOwner'),
('Admin User', 'adminpass', 'admin@example.com', NULL, '0411-222-777', NULL, '12', 'Admin St', 'Melbourne', 'VIC', '3000', 'Admin');

INSERT INTO `Pet` (OwnerID, Name, Species, Breed, Age) VALUES
(3, 'Rex', 'Dog', 'Golden Retriever', 5),
(4, 'Whiskers', 'Cat', 'Siamese', 3),
(3, 'Buddy', 'Dog', 'Beagle', 2),
(4, 'Coco', 'Bird', 'Cockatiel', 1);

INSERT INTO `TreatmentPlan` (PetID, Diagnosis, Description, DateAdministered, EndDate, Notes, VetID) VALUES
(1, 'Skin Infection', 'Antibiotic treatment for skin infection', '2024-04-01', '2024-04-14', 'Administer antibiotics twice daily', 1),
(2, 'Fleas', 'Topical treatment for fleas', '2024-05-01', '2024-05-10', 'Apply flea treatment once weekly', 2);

INSERT INTO `EducationalResource` (Title, ResourceType, Author, PublishDate, Category, Content, Description) VALUES
('Caring for Your Dog', 'Article', 'Veterinary Staff', '2023-06-01', 'Pet Care', 'Detailed guide on dog care', 'Learn the basics of caring for your dog'),
('Feline Health Tips', 'Video', 'Dr. Jane Doe', '2023-07-15', 'Health', 'Video on keeping your cat healthy', 'Tips for maintaining your cat''\''s health');

INSERT INTO `LatestTrends` (Title, Description, Author, PublishDate, TrendCategory) VALUES
('The Rise of Telemedicine in Veterinary Care', 'An article on the increasing use of telemedicine in vet practices', 'Dr. John Smith', '2023-09-01', 'Veterinary Technology'),
('Organic Pet Foods: Benefits and Drawbacks', 'Exploring the pros and cons of organic pet foods', 'Dr. Jane Doe', '2023-10-10', 'Pet Nutrition');

INSERT INTO `Medicine` (Name, Description, Strength, SideEffects, Cost) VALUES
('Amoxicillin', 'Antibiotic used to treat bacterial infections', '250 mg', 'Nausea, vomiting', 15.00),
('Prednisolone', 'Steroid used to treat inflammation', '10 mg', 'Increased thirst, appetite', 20.00);

INSERT INTO `SavedResources` (UserID, ResourceID, SavedAt) VALUES
(3, 1, '2024-06-01 10:15:00'),
(3, 2, '2024-06-02 14:30:00'),
(4, 1, '2024-06-03 09:00:00');

INSERT INTO `Appointment` (UserID, PetID, ClinicID, AppointmentDate, Status, GeneralNotes, Fees, VetID, AppointmentTime) VALUES 
(3, 1, 1, '2024-06-01', 'Past', 'Routine check-up, healthy', 84.95, 1, '10:00:00'),
(4, 2, 2, '2024-06-15', 'Past', 'Annual vaccination, slight reaction to vaccine', 100.00, 2, '11:00:00'),
(3, 3, 1, '2024-11-01', 'Upcoming', 'Follow-up appointment for skin infection', 84.95, 1, '14:00:00');

INSERT INTO `Prescription` (PetID, VetID, MedicineID, Instructions, DosageQuantity, DateAdministered, ExpiryDate, RepeatsLeft, RenewalDate) VALUES 
(1, 1, 1, 'Administer 1 tablet twice daily', '1 tablet', '2024-04-01', '2024-04-14', 0, NULL),
(2, 2, 2, 'Apply 2 drops to each eye daily', '2 drops', '2024-05-01', '2024-05-14', 1, '2024-05-21');

INSERT INTO `VaccinationRecord` (PetID, VaccineName, DateAdministered, NextDueDate, Status, Notes, VetID, Cost) VALUES 
(1, 'Rabies', '2024-01-15', '2025-01-15', 'Completed', 'No adverse reactions', 1, 50.00),
(2, 'Feline Leukemia', '2024-03-20', '2025-03-20', 'Completed', 'Mild reaction', 2, 45.00);

INSERT INTO `MedicalHistory` (PetID, ChronicConditions, Allergies, Notes, LastVaccinationDate, LastTreatmentDate, LastPrescriptionDate) VALUES
(1, 'None', 'None', 'Healthy dog, no issues', '2024-01-15', '2024-02-10', '2024-03-01'),
(2, 'Asthma', 'Fish', 'Allergic to fish, avoid fish-based foods', '2024-03-20', '2024-04-10', '2024-05-01');
