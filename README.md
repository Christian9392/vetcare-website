
# VetCare - Convenient Care For Your Pets

# Table of Contents

1. [Group Information](#group-information)
2. [How to Run the Program](#how-to-run-the-program)
3. [Functionality](#functionality)

# Group Information

## Members

* Keenan Phillips (s3602528)
* Seanghai Heng (s4060921)
* Christian Nieves (s4005338)
* Paul Johny Mampilly (s3968971)
* William Dash (s3947523)
* Heethasha Sandeep Kumar (s3906349)

## Records

* [Github Repository](https://github.com/cosc2299-2024/team-project-group-p03-02)
* [Github Project Board](https://github.com/orgs/cosc2299-2024/projects/62)
* [MS Teams Discussion Board](https://teams.microsoft.com/l/team/19%3AYJJRSncTOCSTFUk8SAizQqT0G9ruVKpihuXgojJsQLw1%40thread.tacv2/conversations?groupId=710085bf-b720-4960-997b-c3a80ed4aa03&tenantId=d1323671-cdbe-4417-b4d4-bdb24b51316b)

# How to Run the Program

## 1. Clone the Repository

First, clone the repository to a local directory with the following command.

```bash
git clone https://github.com/cosc2299-2024/team-project-group-p03-02.git
```

## 2. Running the Webserver Container

Make sure you have Docker installed and Docker Engine running. Open a new terminal window, navigate to the repository, and run the following commands:

```bash
docker-compose build
docker-compose up
```

> [!TIP]
> If you run into issues, refer to Section 1.1 of the User Guide, located in the root of this repository.

You can now access the webserver in your web browser at the address `http://localhost:8080`.
The MySQL database is available at `localhost:3307`. You can access this via one of two ways:

1. A database client
2. The MySQL CLI. Run the following command in the same terminal window you started the application:

```bash
docker exec -it team-project-group-p03-02-database-1 mysql -u root
use vetcare
```

## 3. Navigating the Website

* You can use the navigation bar at the top of the webpage to perform most tasks.
* A majority of webpages are not accessible unless you are logged in.
* A test user with sample data has been created for your convenience:
  * Email: `test@test`
  * Password: `test`
* Feel free to create your own user and run through the program, using the User Guide as a reference.
* A test vet user has been created for your convenience. You may use this to update basic pet medical data by clicking on the 'Vet Dashboard' link in the navigation bar.
  * Email: `jane.smith@example.com`
  * Password: `1234`

# Functionality

Here is a list of everything you can do on this version of VetCare:

* Register for an account
* Register a new pet to your account
* View your pet's medical records (if any)
* Request prescriptions (if any)
* Book an appointment
* Manage your appointments (edit/view/cancel)
* Browse educational resources for pet care
* Save educational resources to your account
* Manage your account details
