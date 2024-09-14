INSERT INTO `clinic` (`name`, `address`, `phone_number`, `email`)
VALUES
    ('Happy Paws Clinic', '123 Pet Street', '555-1234', 'contact@happypaws.com'),
    ('Sunny Days Clinic', '789 Wellness Blvd', '555-9876', 'info@sunnydays.com'),
    ('Healthy Pets Clinic', '321 Animal Ave', '555-4321', 'contact@healthypets.com'),
    ('Pet Haven Clinic', '654 Pet Care Rd', '555-3456', 'support@pethaven.com'),
    ('Urban Vet Clinic', '987 City Street', '555-6789', 'hello@urbanvet.com');

INSERT INTO `user` (`name`, `password`, `email`, `clinic_id`, `phone_number`, `address`, `user_type`)
VALUES
    ('Dr. John Smith', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'john.smith@happypaws.com', 1, '555-5678', '456 Vet Lane', 'Vet'),
    ('Alice Johnson', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'alice.johnson@sunnydays.com', 2, '555-6789', '101 Pet Road', 'Admin'),
    ('Bob Brown', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'bob.brown@healthypets.com', 3, '555-1234', '202 Animal Lane', 'Vet'),
    ('Carol White', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'carol.white@sunnydays.com', 2, '555-9876', '303 Wellness Ave', 'PetOwner'),
    ('David Black', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'david.black@pethaven.com', 4, '555-2345', '404 Care St', 'Vet'),
    ('Emma Green', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'emma.green@urbanvet.com', 5, '555-4567', '505 City Blvd', 'Admin'),
    ('Frank Blue', '$2y$10$uNO35NyiSsVWnKDWMKs9y.7vtEqbGKH.uRuNV/n0yNuwkoSpffUVC', 'frank.blue@healthypets.com', 3, '555-3456', '606 Animal Ave', 'PetOwner');

INSERT INTO `pet` (`owner_id`, `name`, `species`, `breed`, `age`)
VALUES
    (1, 'Rex', 'Dog', 'Labrador', 5),
    (2, 'Whiskers', 'Cat', 'Siamese', 3),
    (3, 'Buddy', 'Dog', 'Beagle', 4),
    (2, 'Fluffy', 'Rabbit', 'Holland Lop', 2),
    (4, 'Sparky', 'Dog', 'Poodle', 6),
    (5, 'Bella', 'Cat', 'Persian', 2),
    (6, 'Max', 'Dog', 'Bulldog', 7),
    (7, 'Mittens', 'Cat', 'Maine Coon', 4);

INSERT INTO `treatment_plan` (`pet_id`, `diagnosis`, `description`, `date_administered`, `end_date`, `notes`, `vet_id`)
VALUES
    (1, 'Ear Infection', 'Treatment for ear infection', '2024-09-01', '2024-09-15', 'Apply ear drops twice a day', 1),
    (2, 'Dental Cleaning', 'Routine dental cleaning', '2024-08-15', '2024-08-30', 'Avoid hard food for a week', 2),
    (3, 'Skin Infection', 'Treatment for skin rash', '2024-09-05', '2024-09-20', 'Apply ointment twice daily', 3),
    (4, 'Vaccination', 'Annual vaccination update', '2024-07-10', '2024-07-10', 'Bring in for follow-up in 1 year', 4),
    (5, 'Worm Treatment', 'Worm treatment for digestive issues', '2024-06-20', '2024-07-05', 'Administer medication daily', 5),
    (6, 'Flea Treatment', 'Flea prevention and treatment', '2024-08-01', '2024-08-20', 'Use flea collar and topical treatment', 6),
    (7, 'General Checkup', 'Routine checkup and health evaluation', '2024-09-10', '2024-09-10', 'Check weight and overall health', 7);

INSERT INTO `educational_resource` (`title`, `resource_type`, `author`, `publish_date`, `category`, `content`, `description`)
VALUES
    ('Pet Health 101', 'Article', 'Dr. Jane Doe', '2024-08-01', 'Health', 'Content about basic pet health care', 'An introductory guide to pet health'),
    ('Pet Grooming Tips', 'Article', 'Dr. Linda Green', '2024-07-20', 'Grooming', 'Content about pet grooming techniques', 'Essential grooming tips for pets'),
    ('Pet Behavior 101', 'Video', 'Dr. Mark Blue', '2024-08-10', 'Behavior', 'Content about pet behavior management', 'Understanding and managing pet behavior'),
    ('Healthy Pet Diet', 'Article', 'Dr. Anna White', '2024-06-25', 'Nutrition', 'Content about balanced pet diet', 'Guide to a balanced diet for pets'),
    ('Emergency Pet Care', 'Video', 'Dr. John Black', '2024-09-01', 'First Aid', 'Content about emergency care for pets', 'What to do in case of a pet emergency');

INSERT INTO `latest_trends` (`title`, `description`, `author`, `publish_date`, `trend_category`)
VALUES
    ('Eco-Friendly Pet Products', 'Latest trends in eco-friendly pet products', 'Sarah Lewis', '2024-09-01', 'Pet Products'),
    ('Smart Pet Tech', 'New technology for pet care', 'James Wright', '2024-08-20', 'Technology'),
    ('Pet Wellness Trends', 'Trends in pet wellness and health', 'Linda Evans', '2024-07-15', 'Health'),
    ('Innovative Pet Foods', 'Trends in innovative pet foods', 'Michael Davis', '2024-06-30', 'Nutrition'),
    ('Pet Fashion Trends', 'Trends in pet clothing and accessories', 'Emily Roberts', '2024-05-25', 'Fashion');

INSERT INTO `medicine` (`name`, `description`, `strength`, `side_effects`, `cost`)
VALUES
    ('Antibiotic X', 'Antibiotic for various infections', '500mg', 'Nausea', 25.00),
    ('Pain Relief Y', 'Pain relief medication for pets', '100mg', 'Drowsiness', 15.00),
    ('Flea Control Z', 'Topical flea control treatment', '50ml', 'Skin irritation', 20.00),
    ('Wormer A', 'Worm treatment for intestinal parasites', '200mg', 'Vomiting', 10.00),
    ('Vaccine B', 'Vaccination for common diseases', '1 dose', 'None', 30.00),
    ('Joint Supplement C', 'Supplement for joint health', '200mg', 'Upset stomach', 18.00),
    ('Allergy Relief D', 'Medication for allergy symptoms', '10mg', 'Drowsiness', 22.00);

INSERT INTO `saved_resources` (`user_id`, `resource_id`, `saved_at`)
VALUES
    (1, 1, '2024-09-14 10:00:00'),
    (2, 2, '2024-09-14 11:00:00'),
    (3, 3, '2024-09-14 12:00:00'),
    (4, 4, '2024-09-14 13:00:00'),
    (5, 5, '2024-09-14 14:00:00'),
    (6, 1, '2024-09-14 15:00:00'),
    (7, 2, '2024-09-14 16:00:00');

INSERT INTO `appointment` (`user_id`, `pet_id`, `clinic_id`, `appointment_date`, `status`, `general_notes`, `fees`, `vet_id`, `appointment_time`)
VALUES
    (1, 1, 1, '2024-09-20', 'Upcoming', 'Routine check-up', 50.00, 1, '09:00:00'),
    (2, 2, 2, '2024-09-22', 'Upcoming', 'Dental cleaning', 75.00, 2, '10:00:00'),
    (3, 3, 3, '2024-09-25', 'Upcoming', 'Skin rash treatment', 60.00, 3, '11:00:00'),
    (4, 4, 4, '2024-09-28', 'Upcoming', 'Annual vaccination', 40.00, 4, '12:00:00'),
    (5, 5, 5, '2024-09-30', 'Upcoming', 'Worm treatment', 20.00, 5, '13:00:00'),
    (6, 6, 1, '2024-10-02', 'Upcoming', 'Flea treatment', 30.00, 1, '14:00:00'),
    (7, 7, 2, '2024-10-05', 'Upcoming', 'General check-up', 50.00, 2, '15:00:00');

INSERT INTO `prescription` (`pet_id`, `vet_id`, `medicine_id`, `instructions`, `dosage_quantity`, `date_administered`, `expiry_date`, `repeats_left`, `renewal_date`)
VALUES
    (1, 1, 1, 'Administer one tablet daily', '1 tablet', '2024-09-01', '2024-10-01', 2, '2024-10-15'),
    (2, 2, 2, 'Apply topical treatment once a week', '1 dose', '2024-08-15', '2024-09-15', 1, '2024-09-20'),
    (3, 3, 3, 'Apply ointment twice daily', '1 dose', '2024-09-05', '2024-09-20', 0, NULL),
    (4, 4, 4, 'Administer one dose monthly', '1 dose', '2024-07-10', '2024-08-10', 0, NULL),
    (5, 5, 5, 'Administer one tablet every 2 weeks', '1 tablet', '2024-06-20', '2024-07-05', 3, '2024-07-20'),
    (6, 6, 6, 'Give one tablet daily', '1 tablet', '2024-08-01', '2024-08-20', 1, '2024-08-25'),
    (7, 7, 7, 'Administer one dose every 3 days', '1 dose', '2024-09-10', '2024-10-10', 2, '2024-10-15');

INSERT INTO `vaccination_record` (`pet_id`, `vaccine_name`, `date_administered`, `next_due_date`, `status`, `notes`, `vet_id`, `cost`)
VALUES
    (1, 'Rabies Vaccine', '2024-08-01', '2025-08-01', 'Completed', 'No adverse reactions', 1, 30.00),
    (2, 'Distemper Vaccine', '2024-07-15', '2025-07-15', 'Completed', 'Update in one year', 2, 35.00),
    (3, 'Parvovirus Vaccine', '2024-06-10', '2025-06-10', 'Completed', 'Follow-up in one year', 3, 28.00),
    (4, 'Leptospirosis Vaccine', '2024-05-25', '2025-05-25', 'Completed', 'Bring pet in for booster next year', 4, 32.00),
    (5, 'Kennel Cough Vaccine', '2024-09-05', '2025-09-05', 'Completed', 'No symptoms observed', 5, 25.00),
    (6, 'Feline Leukemia Vaccine', '2024-08-20', '2025-08-20', 'Completed', 'Check for follow-up appointment', 6, 30.00),
    (7, 'Bordetella Vaccine', '2024-09-15', '2025-09-15', 'Completed', 'Pet is in good health', 7, 27.00);

INSERT INTO `medical_history` (`pet_id`, `chronic_conditions`, `allergies`, `notes`, `last_vaccination_date`, `last_treatment_date`, `last_prescription_date`)
VALUES
    (1, 'None', 'None', 'Healthy and active', '2024-08-01', '2024-09-01', '2024-09-01'),
    (2, 'None', 'None', 'No significant medical issues', '2024-07-15', '2024-08-15', '2024-08-15'),
    (3, 'Seasonal allergies', 'Grass', 'Requires seasonal medication', '2024-06-10', '2024-09-05', '2024-09-05'),
    (4, 'Diabetes', 'None', 'Requires regular insulin', '2024-05-25', '2024-09-28', '2024-09-28'),
    (5, 'None', 'None', 'General check-up scheduled', '2024-09-05', '2024-09-30', '2024-09-30'),
    (6, 'None', 'None', 'Routine wellness exam', '2024-08-20', '2024-10-02', '2024-10-02'),
    (7, 'Heartworm disease', 'None', 'Follow-up appointment in October', '2024-09-10', '2024-10-05', '2024-10-05');
