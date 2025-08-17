# Library Management System

A simple Java-based library management system built using Object-Oriented Programming principles.

## Features

- **Book Management**: Add and view books in the library
- **User Management**: Register members and librarians
- **Borrowing System**: Borrow and return books with automatic due dates
- **Search Books**: Find books by title, author, or genre
- **User-friendly Menu**: Interactive console interface

## Classes

- `Book` - Represents a book with ISBN, title, author, and availability
- `User` - Abstract base class for all users
- `Member` - Library member who can borrow books
- `Librarian` - Library staff member
- `LibraryManagementSystem` - Main system controller

## Requirements

- Java 8 or higher
- VS Code (recommended)
- Terminal/Command Prompt

## How to Run

1. **Create the files**: Copy each class into separate `.java` files
   - `Book.java`
   - `User.java`
   - `Member.java`
   - `Librarian.java`
   - `LibraryManagementSystem.java`

2. **Compile the project**:
   ```bash
   javac *.java
   ```

3. **Run the program**:
   ```bash
   java LibraryManagementSystem
   ```

## Sample Data

The system comes pre-loaded with:
- 5 sample books (including classics like "1984" and "To Kill a Mockingbird")
- 2 sample members
- 1 sample librarian

## Usage

Follow the on-screen menu to:
1. View all books or available books
2. Add new books and members
3. Borrow and return books
4. Search for specific books
5. View all registered users

## OOP Concepts Demonstrated

- **Encapsulation**: Private fields with getter/setter methods
- **Inheritance**: Member and Librarian extend User class
- **Polymorphism**: Different implementations of getUserType()
- **Abstraction**: User as abstract base class

## Author

Created as a demonstration of Java OOP principles for library management.
