**University Course Registration System**
A terminal-based university course registration system built in Java, applying robust OOP principles and supporting roles like Students, Professors, Admins, and Teaching Assistants. Includes GPA tracking, feedback, exception handling, and more.

---

## 📘 README.md

# 🎓 University Course Registration System

This Java-based university course registration system simulates the academic workflow of students, professors, administrators, and teaching assistants in a terminal-based environment. The application demonstrates the effective use of **Object-Oriented Programming (OOP)** principles such as inheritance, encapsulation, polymorphism, abstraction, interfaces, generics, and exception handling.

---

## 🧱 Features

### 👩‍🎓 Student Panel

* **Course Registration**: Register for courses by semester while checking prerequisites and credit limits (max 20 credits).
* **Course Drop**: Drop ongoing semester courses freely.
* **Academic Tracking**: View grades, SGPA, and CGPA based on completed courses.
* **Weekly Schedule**: Visualize registered course timings, locations, and instructor details.
* **Complaint Filing**: Submit academic or schedule-related complaints with status tracking.
* **Course Feedback System** *(Generic Programming)*: Provide numeric (1–5) or textual feedback on completed courses.

### 👨‍🏫 Professor Panel

* **Course Management**: View and edit course details including syllabus, prerequisites, and timings.
* **Student List**: Access enrolled students' academic standings and contact information.
* **View Feedback**: Professors can view student feedback for their courses.

### 🧑‍💼 Administrator Panel

* **Course Catalogue Management**: Add, remove, or view university courses.
* **Student Record Management**: Update student personal and academic data.
* **Professor Assignment**: Assign professors to suitable courses.
* **Complaint Resolution**: View, filter, and update complaint statuses.

### 🧑‍🔬 Teaching Assistant (TA) Panel

* **Role via Inheritance**: Built by extending the Student class with extra privileges.
* **Grade Management**: Assist professors by viewing and editing student grades.
* **Student Role Access**: Retains full student functionalities including registration, GPA tracking, and feedback.

---

## 💡 Technical Highlights

* **OOP Concepts Applied**:

  * *Encapsulation*: Private fields with getters/setters.
  * *Inheritance & Polymorphism*: TA inherits from Student; User superclass with overridden methods.
  * *Abstraction & Interfaces*: Interface-driven role-specific functionalities.
  * *Generics*: Feedback system with generic types (e.g., numeric or string).
  * *Exception Handling*: Custom exceptions like `CourseFullException`, `InvalidLoginException`, and `DropDeadlinePassedException`.

* **Custom Exception Classes**:

  * `InvalidLoginException`: Thrown on incorrect credentials.
  * `CourseFullException`: Thrown when a course has no available seats.
  * `DropDeadlinePassedException`: Prevents course drop after the deadline.

* **Menu-Driven Terminal UI**:

  * Role-based dynamic options.
  * Modular sub-menus for deep interactions (e.g., course updates, GPA calculation).
  * Safe exit with data retention simulation.

---

## 📂 Repository Structure

```
/University-Course-Registration
│
├── 📄 Problem_Statement.pdf            → Original project brief
│
├── 📁 Requirement_Analysis/            → Early-stage design thoughts & decisions
│
├── 📁 Solution/
│   ├── User.java
│   ├── Student.java
│   ├── Professor.java
│   ├── TeachingAssistant.java
│   ├── Admin.java
│   ├── Course.java
│   ├── Complaint.java
│   ├── CourseCatalogue.java
│   ├── UniversitySystem.java
│   ├── Feedback<T>.java
│   └── CustomExceptions/
│       ├── InvalidLoginException.java
│       ├── CourseFullException.java
│       └── DropDeadlinePassedException.java
│
└── 📄 README.md
```

---

## 🧪 Test Demonstration

The project has been tested with:

* **3 Students**
* **2 Professors**
* **1 Administrator**
* **1 Teaching Assistant**
* **5 Courses**

This coverage ensures thorough validation of registration logic, GPA calculations, role-specific flows, and exception handling.

---

## ▶️ How to Run

1. **Compile** the Java files:

   ```bash
   javac *.java
   ```

2. **Run** the main system:

   ```bash
   java UniversitySystem
   ```

> Java 8+ recommended. All data interactions are in-memory and driven via terminal.

---

## ✍️ Author Notes

This project was designed to simulate a real-world academic course management system while demonstrating a comprehensive use of object-oriented design. The inclusion of robust features like role extension (TA), feedback using generics, and fine-grained exception management makes it extensible for real-use prototypes or academic showcases.
