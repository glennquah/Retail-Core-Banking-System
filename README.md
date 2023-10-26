# Holiday Reservation System

## Overview

Hello All. This is a side project that I have done to mimic a Holiday Reservation System. I utilized Enterprise JavaBeans (EJB) and Session Beans to manage customer holiday reservations. This system allows users to log in, search for holidays, make reservations, and process payments.

## Table of Contents

- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Technologies Used](#technologies-used)

## Project Structure

The project is structured as follows:

- `TellerTerminalClient`: The client application for interacting with the system as a Teller (Actor).
- `AutomatedTellerMachineClient`: The client application for customer to insert ATM card and its use case
- `holidayreservationsystem-ejb`: Enterprise JavaBeans and Session Beans for handling business logic.
- `persistence-ejb`: Contains entity classes and JPA (Java Persistence API) for data persistence.

## Getting Started

To run this project locally, you will need the following prerequisites:

- [Java SE Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [NetBeans IDE](https://netbeans.apache.org/download/nb123/nb123.html)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)

1. Clone this repository to your local machine.

 ```git clone https://github.com/your-username/holiday-reservation.git```

2. Open the project in NetBeans IDE.
3. Configure your MySQL database connection in the project's configuration.
4. Build and deploy the project to your application server (e.g., GlassFish).

## Usage
Start the application from NetBeans IDE.
Follow the prompts to log in, search for holidays, make reservations, and process payments.
Employee Login Details: Username: username | Password: password

## Technologies Used
- Java EE (Enterprise Edition)
- Enterprise JavaBeans (EJB)
- Java Persistence API (JPA)
- MySQL Database
