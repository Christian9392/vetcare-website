
# RMIT COSC2299 SEPT Major Project

# Group Information

## Group-P03-02

## Members
* Keenan Phillips (s3602528)
* Seanghai Heng (s4060921)
* Christian Nieves (s4005338)
* Paul Johny Mampilly (s3968971)
* William Dash (s3947523)
* Heethasha Sandeep Kumar (s3906349)

## Records

* Github repository: https://github.com/cosc2299-2024/team-project-group-p03-02
* Github Project Board: https://github.com/orgs/cosc2299-2024/projects/62
* MS Teams Discussion Board: https://teams.microsoft.com/l/team/19%3AYJJRSncTOCSTFUk8SAizQqT0G9ruVKpihuXgojJsQLw1%40thread.tacv2/conversations?groupId=710085bf-b720-4960-997b-c3a80ed4aa03&tenantId=d1323671-cdbe-4417-b4d4-bdb24b51316b

See [Instructions](INSTRUCTIONS.md)

# How to Run the Program
## 1. Clone the Repository
First, clone the repository to a local directory with the following command.

```bash
git clone https://github.com/cosc2299-2024/team-project-group-p03-02.git
```

## 2. Setup MySQL Database
Ensure [MySQL Server](https://dev.mysql.com/downloads/mysql/) and [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) is installed. By default, the program attempts to connect to the SQL server with these credentials:
- Username: "root"
- Password: ""
- Port: 3306

> [!NOTE]
> These credentials can be changed inside of `src\main\resources\application.properties` through the parameters `spring.datasource.username` and `spring.datasource.password`

Ensure the MySQL server is running and open MySQL Workbench. Connect to the local database instance and create a new schema named "vetcare" via the toolbar. Set this schema as the default schema.

> [!TIP]
> For a step-by-step guide on how to perform the above steps, refer to section "1.5 Setting Up the MySQL Database Instance" of the user guide Word document.

## 3. Running the Webserver
Make sure the current working directory is the cloned repository and run the following command:

```bash
./mvnw spring-boot:run
```

You can now access the webserver in your web browser at the address `http://localhost:8080`.
## 4. Navigating the Website
- Currently, the main and recommended way to navigate through pages is through the navigation bar at the top of the screen.
- A majority of webpages are not accessible unless you are logged in.