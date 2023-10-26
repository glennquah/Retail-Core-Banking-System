# Retail Core Banking System

## Overview

Hello All. This is a side project that I have done to mimic a Retail Core Banking System. I utilized Enterprise JavaBeans (EJB) and Session Beans to manage a core banking system. This system has 2 clients, an automated teller machine and a teller terminal to create deposit accounts, issue ATM card,s and insert ATM cards.

## Table of Contents

- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Use Cases](#use-cases)

## Project Structure

The project is structured as follows:

- `TellerTerminalClient`: The client application for interacting with the system as a Teller (Actor).
- `AutomatedTellerMachineClient`: The client application for customers to insert ATM cards and its use case
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

## Use Cases
| S/N | Use Case Description/Business Rules                           |
|-----|--------------------------------------------------------------|
| 1   | **Create Customer**                                         |
|     | - For a new customer, teller needs to create a new customer |
|     |   record first before performing any other business        |
|     |   activities.                                              |
|     | - Each customer should be uniquely identified with one     |
|     |   customer record.                                         |
| 2   | **Open Deposit Account**                                    |
|     | - Teller opens a new deposit account for an existing        |
|     |   customer.                                                |
|     | - Customer needs to provide an initial cash deposit of any  |
|     |   amount.                                                 |
|     | - A customer can have multiple deposit accounts of         |
|     |   different types.                                         |
|     | - For this phase, only savings account is required.        |
|     | - For this phase, you may assume that there is only        |
|     |   individual account, i.e., a customer cannot open a       |
|     |   joint account.                                          |
| 3   | **Issue ATM Card**                                          |
|     | - Teller issues a new ATM card to customer.                |
|     | - An ATM card may be associated with one or more deposit   |
|     |   accounts.                                               |
|     | - Each deposit account may be associated with zero or one  |
|     |   ATM card.                                               |
| 3.1 | **Issue Replacement ATM Card**                               |
|     | - Teller issues a replacement ATM card to customer.         |
|     | - Delete the corresponding record of the previous ATM card.|
|     | - Create a new ATM card record as the replacement.         |
| 4   | **Insert ATM Card**                                         |
|     | - Customer enters ATM card number and PIN.                  |
| 5   | **Change PIN**                                              |
|     | - Customer changes the current PIN of the ATM card.        |
| 6   | **Enquire Available Balance**                               |
|     | - Customer inquires available balance for a deposit account|
|     |   associated with the ATM card.                            |
|     | - Available balance refers to the balance amount in a      |
|     |   deposit account that is available for spending,          |
|     |   withdrawal, or transfers.                                |
|     | - Ledger balance is equal to the sum of available balance  |
|     |   plus any holding balance.                                |
|     | - If there is no holding balance, then ledger balance is   |
|     |   equal to available balance.                              |
