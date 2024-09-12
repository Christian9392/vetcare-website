-- This is the schema for the database tables used in the application. 
-- You can use this schema to create the tables while testing the application.

CREATE TABLE `Clinic` (
  `ClinicID` BIGINT AUTO_INCREMENT,
  `Name` VARCHAR(255),
  `StreetNumber` VARCHAR(10),
  `StreetName` VARCHAR(255),
  `Suburb` VARCHAR(100),
  `State` VARCHAR(50),
  `Postcode` VARCHAR(10),
  `PhoneNumber` VARCHAR(20),
  `Email` VARCHAR(255),
  PRIMARY KEY (`ClinicID`)
);

CREATE TABLE `User` (
  `UserID` BIGINT AUTO_INCREMENT,
  `Name` VARCHAR(255),
  `Password` VARCHAR(255),
  `Email` VARCHAR(255),
  `ClinicID` BIGINT,
  `PhoneNumber` VARCHAR(20),
  `UnitNumber` VARCHAR(10),
  `StreetNumber` VARCHAR(10),
  `StreetName` VARCHAR(255),
  `Suburb` VARCHAR(100),
  `State` VARCHAR(50),
  `Postcode` VARCHAR(10),
  `UserType` ENUM('PetOwner', 'Vet', 'Admin'),  
  PRIMARY KEY (`UserID`),
  FOREIGN KEY (`ClinicID`) REFERENCES `Clinic`(`ClinicID`)
);

CREATE TABLE `Pet` (
  `PetID` BIGINT AUTO_INCREMENT,
  `OwnerID` BIGINT,
  `Name` VARCHAR(255),
  `Species` VARCHAR(255),
  `Breed` VARCHAR(255),
  `Age` INT,
  PRIMARY KEY (`PetID`),
  FOREIGN KEY (`OwnerID`) REFERENCES `User`(`UserID`)
);

CREATE TABLE `TreatmentPlan` (
  `TreatmentPlanID` BIGINT AUTO_INCREMENT,
  `PetID` BIGINT,
  `Diagnosis` VARCHAR(255),
  `Description` TEXT,
  `DateAdministered` DATE,
  `EndDate` DATE,
  `Notes` TEXT,
  `VetID` BIGINT,
  PRIMARY KEY (`TreatmentPlanID`),
  FOREIGN KEY (`PetID`) REFERENCES `Pet`(`PetID`),
  FOREIGN KEY (`VetID`) REFERENCES `User`(`UserID`)
);

CREATE TABLE `EducationalResource` (
  `ResourceID` BIGINT AUTO_INCREMENT,
  `Title` VARCHAR(255),
  `ResourceType` VARCHAR(255),
  `Author` VARCHAR(255),
  `PublishDate` DATE,
  `Category` VARCHAR(255),
  `Content` TEXT,
  `Description` TEXT,
  PRIMARY KEY (`ResourceID`)
);

CREATE TABLE `LatestTrends` (
  `TrendID` BIGINT AUTO_INCREMENT,
  `Title` VARCHAR(255),
  `Description` TEXT,
  `Author` VARCHAR(255),
  `PublishDate` DATE,
  `TrendCategory` VARCHAR(255),
  PRIMARY KEY (`TrendID`)
);

CREATE TABLE `Medicine` (
  `MedicineID` BIGINT AUTO_INCREMENT,
  `Name` VARCHAR(255),
  `Description` TEXT,
  `Strength` VARCHAR(50),
  `SideEffects` TEXT,
  `Cost` DECIMAL(10, 2),
  PRIMARY KEY (`MedicineID`)
);

CREATE TABLE `SavedResources` (
  `UserID` BIGINT,
  `ResourceID` BIGINT,
  `SavedAt` TIMESTAMP,
  PRIMARY KEY (`UserID`, `ResourceID`),
  FOREIGN KEY (`UserID`) REFERENCES `User`(`UserID`),
  FOREIGN KEY (`ResourceID`) REFERENCES `EducationalResource`(`ResourceID`)
);

CREATE TABLE `Appointment` (
  `AppointmentID` BIGINT AUTO_INCREMENT,
  `UserID` BIGINT,
  `PetID` BIGINT,
  `ClinicID` BIGINT,
  `AppointmentDate` DATE,
  `Status` ENUM('Upcoming', 'Past', 'Cancelled'),
  `GeneralNotes` TEXT,
  `Fees` DECIMAL(10, 2),
  `VetID` BIGINT,
  `AppointmentTime` TIME,
  PRIMARY KEY (`AppointmentID`),
  FOREIGN KEY (`UserID`) REFERENCES `User`(`UserID`),
  FOREIGN KEY (`PetID`) REFERENCES `Pet`(`PetID`),
  FOREIGN KEY (`ClinicID`) REFERENCES `Clinic`(`ClinicID`),
  FOREIGN KEY (`VetID`) REFERENCES `User`(`UserID`)
);

CREATE TABLE `Prescription` (
  `PrescriptionID` BIGINT AUTO_INCREMENT,
  `PetID` BIGINT,
  `VetID` BIGINT,
  `MedicineID` BIGINT,
  `Instructions` TEXT,
  `DosageQuantity` VARCHAR(50),
  `DateAdministered` DATE,
  `ExpiryDate` DATE,
  `RepeatsLeft` INT,
  `RenewalDate` DATE,
  PRIMARY KEY (`PrescriptionID`),
  FOREIGN KEY (`PetID`) REFERENCES `Pet`(`PetID`),
  FOREIGN KEY (`VetID`) REFERENCES `User`(`UserID`),
  FOREIGN KEY (`MedicineID`) REFERENCES `Medicine`(`MedicineID`)
);

CREATE TABLE `VaccinationRecord` (
  `VaccinationID` BIGINT AUTO_INCREMENT,
  `PetID` BIGINT,
  `VaccineName` VARCHAR(255),
  `DateAdministered` DATE,
  `NextDueDate` DATE,
  `Status` VARCHAR(50),
  `Notes` TEXT,
  `VetID` BIGINT,
  `Cost` DECIMAL(10, 2),
  PRIMARY KEY (`VaccinationID`),
  FOREIGN KEY (`PetID`) REFERENCES `Pet`(`PetID`),
  FOREIGN KEY (`VetID`) REFERENCES `User`(`UserID`)
);

CREATE TABLE `MedicalHistory` (
  `MedicalHistoryID` BIGINT AUTO_INCREMENT,
  `PetID` BIGINT,
  `ChronicConditions` TEXT,
  `Allergies` TEXT,
  `Notes` TEXT,
  `LastVaccinationDate` DATE,
  `LastTreatmentDate` DATE,
  `LastPrescriptionDate` DATE,
  PRIMARY KEY (`MedicalHistoryID`),
  FOREIGN KEY (`PetID`) REFERENCES `Pet`(`PetID`)
);