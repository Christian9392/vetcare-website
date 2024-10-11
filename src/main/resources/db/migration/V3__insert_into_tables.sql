INSERT INTO clinic (name, address, phone_number, email) VALUES
('Healthy Pets Clinic', '123 Main Street Springfield', '555-1111', 'info@healthypets.com'),
('City Vet Clinic', '456 Elm Street Springfield', '555-2222', 'contact@cityvet.com'),
('Greenwood Animal Hospital', '789 Oak Avenue Greenwood', '555-3333', 'info@greenwoodanimal.com');


INSERT INTO user (name, password, email, clinic_id, phone_number, address, user_type) VALUES
('John Doe', '$2a$10$2iY5tmkdvi2OzSIGfDalH.v7b4Iq3EfwXbrDmNepC9a78jt.Bz5zu', 'john.doe@example.com', 1, '555-1111', 'Apt 1 Main Street Springfield', 'PetOwner'),
('Jane Smith', '$2a$10$2iY5tmkdvi2OzSIGfDalH.v7b4Iq3EfwXbrDmNepC9a78jt.Bz5zu', 'jane.smith@example.com', 2, '555-2222', 'Apt 2 Elm Street Springfield', 'Vet'),
('Robert Johnson', '$2a$10$2iY5tmkdvi2OzSIGfDalH.v7b4Iq3EfwXbrDmNepC9a78jt.Bz5zu', 'robert.johnson@example.com', 3, '555-3333', 'Apt 3 Oak Avenue Greenwood', 'Admin'),
('Emily Davis', '$2a$10$2iY5tmkdvi2OzSIGfDalH.v7b4Iq3EfwXbrDmNepC9a78jt.Bz5zu', 'emily.davis@example.com', 1, '555-4444', 'Apt 4 123 Main Street Springfield', 'PetOwner'),
('testUser', '$2a$10$lb8PN70R8kagmOhkvpBfhuUzwdp9gPrcuieCnZD4FcY0Tl3nLnFG6', 'test@test', 1, '000-000', 'testAddress', 'PetOwner');


INSERT INTO pet (owner_id, name, species, breed, age) VALUES
(1, 'Rex', 'Dog', 'Labrador', 5),
(1, 'Whiskers', 'Cat', 'Siamese', 3),
(2, 'Bella', 'Dog', 'Poodle', 2),
(3, 'Charlie', 'Cat', 'Maine Coon', 4),
(4, 'Max', 'Dog', 'Beagle', 6),
(5, 'testPet', 'testSpecies', 'testBreed', 1);


INSERT INTO appointment (user_id, pet_id, clinic_id, appointment_date, status, general_notes, fees, vet_id, appointment_time) VALUES
(1, 1, 1, '2024-09-10', 'Upcoming', 'Routine check-up', 50.00, 2, '10:00:00'),
(1, 2, 1, '2024-09-12', 'Upcoming', 'Annual vaccination', 30.00, 2, '11:00:00'),
(2, 3, 2, '2024-09-15', 'Past', 'Teeth cleaning', 100.00, 2, '09:30:00'),
(3, 4, 3, '2024-09-20', 'Cancelled', 'Behavioral consultation', 75.00, 2, '14:00:00'),
(4, 5, 1, '2024-09-25', 'Upcoming', 'Health check', 60.00, 2, '16:00:00'),
(1, 2, 1, '2024-09-30', 'Upcoming', 'Follow-up visit', 25.00, 2, '10:30:00'),
(2, 1, 2, '2024-10-01', 'Upcoming', 'Ear infection treatment', 45.00, 2, '13:00:00'),
(5, 6, 1, '2024-12-01', 'Upcoming', 'testGeneralNotes', 45.00, 1, '12:00:00');

INSERT INTO medicine (medicine_id, name, description, strength, side_effects, cost) VALUES
(1, 'testMedicine', 'testDescription', 'teststrength', 'testSideEffects', 1);

INSERT INTO prescription (prescription_id, pet_id, vet_id, medicine_id, instructions, dosage_quantity, date_administered, expiry_date, repeats_left, renewal_date) VALUES
(1, 6, 1, 1, 'testInstuctions', 'testdosage', '2024-10-2', '2024-12-8', 5, '2024-10-10'),
(2, 6, 1, 1, 'testInstuctionsExpired', 'testdosage', '2024-10-2', '2024-1-1', 5, '2024-10-10');

INSERT INTO educational_resource (resource_id, title, resource_type, author, publish_date, category, content, description) VALUES 
(1,"7 Tips To Care For Your Dog's Health", "Video", "AnimalWised", "2021-09-06", "Dogs", "https://www.youtube.com/embed/JhKfEOLmDAU", "Tips to care for your dog's health"),
(2,"How To Care For A Kitten", "Video", "AnimalWised", "2019-05-30", "Dogs", "https://www.youtube.com/embed/uV1RMT_ld3k", "How to care for a kitten"),
(3,"How To Take Care Of A Rabbit", "Video", "AnimalWised", "2019-10-31", "Rabbits", "https://www.youtube.com/embed/1s-ydKa7nPA", "How to take care of a rabbit"),
(4, "Grooming Tips For Dogs", "Article", "Kanawal", "2019-08-20", "Dogs", "http://www.kanwalvet.com.au/article/grooming-tips-for-dogs/", 
"Grooming sessions can become a special bonding time between you and your dog and are also a good opportunity to check on your dog’s health."),
(5, "How to Keep Your Dogs Joints Healthy", "Article", "Purina", "2024-05-21", "Dogs", "https://www.purina.com/articles/dog/health/symptoms/joint-health-for-dogs", 
"As your canine companion ages, they can put a lot of wear and tear on their joints. And if joint problems occur, they can be painful, reduce mobility, and affect their quality of life. That’s why joint health for dogs is so important. "),
(6, "How to care for older cats ", "Guide", "RSPCA", "2021-11-26", "Cats", "https://www.rspcapetinsurance.org.au/pet-care/guides/caring-for-older-cats", 
"A guide on caring for an older cat"),
(7, "General Dog Care", "Guide", "ASPCA", "2023-10-10", "Dogs", "https://www.aspca.org/pet-care/dog-care/general-dog-care", 
"A dog can be a wonderful addition to any home, but whether you're an experienced pet parent or a first-time adopter, it's important to keep your canine companion's health and happiness a top priority. Below are some useful tips for all dog parents.");