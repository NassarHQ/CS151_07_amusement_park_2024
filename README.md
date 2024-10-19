# Amusement Park Management System

## Overview

The Amusement Park Management System is a object-oriented project designed to simulate the operations of an amusement park. It allows users to interact as visitors, employees, and administrators, providing a variety of features such as purchasing tickets, operating rides, and managing park operations.

This system is divided into multiple classes, each representing different entities within the amusement park such as visitors, employees, rides, and tickets. The system also incorporates an interactive command-line interface to allow users to navigate through different functionalities, including employee and visitor management, ride operations, and ticket handling.

## Design

The project follows object-oriented design principles, with a focus on abstraction, inheritance, and encapsulation. Below is an overview of the major classes and their roles:

### Core Classes

- **Main.java**: This is the entry point of the application. It handles the main menu and controls the flow between different parts of the system, such as the `VisitorUI`, `EmployeeUI`, and `AdminUI`.
    
- **Person.java**: This is an abstract class representing a person in the park. It is extended by both `Employee` and `Visitor`. It includes common properties like name, age, username, and password, and contains abstract methods like `personType()` and `viewProfile()`.
    
- **Visitor.java**: Extends the `Person` class and represents a park visitor. Visitors can buy tickets, enter the park, and leave feedback. This class also manages visitor-specific attributes like height, weight, and their ticket purchase history.
    
- **Employee.java**: Extends the `Person` class and represents an employee of the amusement park. Employees have additional responsibilities, such as clocking in/out, managing rides, and reporting issues. It contains methods for viewing work logs, checking salaries, and handling work schedules.
    
- **AdminUI.java**: This class manages administrative tasks such as viewing park metrics, handling employee accounts, and monitoring park statistics. The admin role provides overall control over the park’s operations.
    
- **VisitorUI.java**: This class provides an interactive menu for visitors, allowing them to buy tickets, view ride eligibility, and leave feedback. It serves as the main interface for visitors to interact with the system.
    
- **EmployeeUI.java**: This class provides an interactive menu for employees to log in, clock in, assign rides, and perform various employee-related tasks. It manages employee authentication and ride management.
    
- **Park.java**: Represents the amusement park itself. It manages the park's state, including employees, visitors, rides, and stores. The `Park` class also tracks park metrics such as revenue, visitor count, and reported issues. It contains methods for selling tickets, calculating revenue, and adding/removing people from the park.
    
- **Ride.java**: This class models a ride in the park. Each ride has properties like a name, capacity, and requirements for height and weight. The class tracks visitors on the ride and the assigned ride operator (employee).
    
- **Ticket.java**: Represents a ticket purchased by a visitor. The `Ticket` class tracks details like ticket ID, price, and discount eligibility. It implements the `Discountable` interface, allowing for ticket price discounts based on visitor type (e.g., child, senior).
    
- **ParkStore.java**: Represents a store inside the park, where visitors can purchase items like food, drinks, and souvenirs. It manages store inventory and sales.
    

### Helper Classes and Interfaces

- **Discountable.java**: An interface implemented by the `Ticket` class, which applies discounts based on the visitor's category (e.g., child, senior, adult).
    
- **PrintHelper.java**: A utility class that provides various static methods to print formatted text to the console, making it easier to maintain a consistent user interface throughout the application.
    
- **ValidationHelper.java**: A helper class used to validate input across the system, ensuring correct and secure input handling.
    
- **ParkInteractables.java**: An interface that defines actions that both employees and visitors can perform inside the park.

### Design Considerations:

1. **Encapsulation**: Each class handles its own data and operations to ensure proper encapsulation. For example, employee and visitor details are managed internally in their respective classes.
2. **Inheritance and Polymorphism**: The `Person` class is an abstract class that is inherited by `Employee` and `Visitor`, reducing code duplication and providing a clear separation of responsibilities.
3. **Interactive UIs**: Separate UI classes for employees, visitors, and admins allow for easy navigation through different features of the system.
4. **Error Handling**: Input validation is performed at various points to ensure the robustness of the system.

## Installation Instructions

To run the Amusement Park Management System, follow these steps:

1. **Prerequisites**: Ensure that you have a Java Development Kit (JDK) installed on your machine. Java 8 or later is required to run this project.
    
2. **Cloning the Project**: Clone the project from the repository or download the source code.
    
    `git clone https://github.com/NassarHQ/CS151_07_amusement_park_2024`
    
3. **Open the Project** using your desired text editor or IDE.

4. **Build the Project**: In our case, VSCode will automatically build the project when you save files.
    
5. **Running the Project**: Run the `Main` class to start the application.
    
    `java Main`
    

## Usage

Once the system is running, you will be presented with a main menu offering different options. Users may type "exit" (case insensitive) to terminate the program at any time. Depending on the role (Visitor, Employee, or Admin), the system will provide the following functionalities:

### Visitor Features:

- Purchase tickets with discounts applied based on age category (children, seniors, adults).
- Provide feedback
- Check purchase history.

### Employee Features:

- Clock in and out of shifts.
- View work schedules and logs.
- Report issues within the park.
- Operate rides

### Admin Features:

- Manage park-wide operations, including viewing daily revenue and number of tickets sold.
- View reported issues from employees.

## Contributions

Each team member contributed to the overall design and testing of the system. Project integration and members assisted each other between all classes, but the description is a broad overview.

This project was developed as a collaborative effort among the following team members:

- **[Mohammed Nassar]**: Design and implementation of the `AdminUI` and `Park` classes with correct input handling.
- **[Uyen Pham]**: Primary development of the `Visitor`, `VisitorUI`, `ParkStore` classes, focusing on correct functionality for any Visitor. Bug fixes in `Park` and code cleanup throughout the project.
- **[Kundyz Serzhankyzy]**: Responsible for the `Ride` class and implementing the strict requirements from `ParkInteractables`. Worked on extensive testing using JUnit tests and additionally developed features in `AdminUI`.
- **[Ryan Tran]**: Development of `Employee` and `EmployeeUI`. Implemented `Discountable` within tickets, and focused overall on employee methods and its connection to `Rides`.
