link for the presentation - https://github.com/Ohma07/coursework2.git
Project name - Employee Attendance Tracker
Description - Tracks employee attendance, allowing recording of clock-in and clock-out times, absences, and tardiness.
Student - KOICHUBAEV BEIKUT COMFCI-25
Employee Attendance Tracker
📝 Description

The Employee Attendance Tracker is a Java-based application designed to manage and record employee attendance efficiently. It allows users to add, update, delete, and view attendance records through a user-friendly graphical interface (GUI). The system tracks important details such as employee ID, name, email, attendance type (clock-in, clock-out, absent, late), and timestamp. All data is stored in a file to ensure it is saved permanently between sessions.

🎯 Objectives

The main objectives of this project are:

To develop a functional attendance management system using Java and Object-Oriented Programming (OOP) principles
To allow users to perform full CRUD operations (Create, Read, Update, Delete) on attendance records
To implement data persistence using file handling so that records are saved permanently
To design a simple and user-friendly GUI for easy interaction
To apply OOP concepts such as encapsulation, inheritance, polymorphism, and abstraction in a real project
To ensure input validation and error handling for a stable and reliable system

🧠 Algorithms Used
1. CRUD Algorithm

The system uses basic CRUD logic:

Create: Add new attendance record to a list
Read: Display all records in a table (JTable)
Update: Replace an existing record using index
Delete: Remove a record using index
2. File Persistence Algorithm
Convert each object into a comma-separated string
Write all records into a text file (data.txt)
On startup, read file line-by-line and reconstruct objects
3. Input Validation Algorithm
Check if name is empty
Validate email contains “@”
Ensure numeric fields (ID, index) are valid integers
🧱 Data Structures Used
ArrayList<AttendanceRecord>
Used to store all attendance records dynamically
Allows easy insertion, deletion, and updating of records
JTable (GUI structure)
Displays data in tabular form (like a database)
Helps visualize records in rows and columns
🧩 Modules / Functions Used
1. Model Layer
Employee → stores employee data (ID, name, email)
AttendanceRecord → stores attendance details
2. Service Layer
AttendanceService
addRecord()
updateRecord()
deleteRecord()
getRecords()
FileService
save() → writes data to file
load() → reads data from file
3. UI Layer
GUI.java
Handles user interaction
Contains buttons (Add, Update, Delete, View)
Displays data in JTable
⚠️ Challenges Faced
Data persistence handling: Ensuring data is not lost after closing the program
Synchronizing GUI and data list: Keeping JTable updated after every operation
Input validation: Preventing invalid or empty inputs from breaking the system
File parsing: Converting text file data back into Java objects correctly
GUI design: Making the interface simple but functional for all CRUD operations
💡 Conclusion

This project demonstrates the use of Object-Oriented Programming in a real-world application. It successfully integrates GUI design, file handling, data structures, and algorithms to create a functional attendance management system.
