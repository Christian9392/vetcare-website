
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
$ git clone https://github.com/cosc2299-2024/team-project-group-p03-02.git
```

## 2. Running the Webserver Container
Make sure you have Docker installed and Docker Engine running. Run the shell script with the following command:

```bash
bash run.sh
```

You can now access the webserver in your web browser at the address `http://localhost:8080`.
The MySQL database is available at `localhost:3307`

> [!NOTE]
> The shell script cleans up existing database volumes, builds the docker containers, and runs them. You can do all of this seperately with these commands:
> ```docker container rm team-project-group-p03-02-database-1
> docker volume rm team-project-group-p03-02_db
> docker-compose build --nocache
> docker-compose up```

## 3. Navigating the Website
- Currently, the main and recommended way to navigate through pages is through the navigation bar at the top of the screen.
- A majority of webpages are not accessible unless you are logged in.
- We have a test user that's been setup with the following credentials:
    - Email: `test@test`
    - Password: `test`