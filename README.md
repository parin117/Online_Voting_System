# 🗳️ Electronic Voting System

A robust, secure, and user-friendly Electronic Voting System built in Java that provides a complete solution for managing elections, candidates, and voters with real-time voting capabilities.

## 🚀 Features

### ✨ Core Functionality
- **Election Management**: Create and schedule elections with custom dates
- **Candidate Registration**: Comprehensive candidate profile management with validation
- **Voter Registration**: Secure voter registration with unique voter ID validation
- **Real-time Voting**: Live voting system with instant vote counting
- **Result Declaration**: Automated result calculation and winner announcement
- **Data Persistence**: CSV-based data storage for elections, candidates, and votes

### 🔒 Security & Validation
- **Age Verification**: Ensures candidates are eligible (19+ years)
- **Nationality Check**: Validates Indian citizenship for candidates
- **Voter ID Validation**: 12-digit voter ID verification
- **Duplicate Prevention**: Prevents duplicate voter registrations
- **Input Validation**: Comprehensive input sanitization and validation

### 📊 Administrative Features
- **Election Scheduling**: Create elections with specific dates
- **Candidate Management**: Add, view, and manage candidate profiles
- **Voter Management**: Register and view voter information
- **Voting Process Control**: Start and manage live voting sessions
- **Result Management**: Calculate and display election results

## 🛠️ Technology Stack

- **Language**: Java 8+
- **Data Storage**: CSV files (election.csv, candidate.csv, voter.csv, vote1.csv)
- **File I/O**: Java NIO for efficient file operations
- **Date Handling**: SimpleDateFormat for date validation
- **Data Structures**: Custom Linked List implementations
- **Input Validation**: Regular expressions and custom validation logic

## 📁 Project Structure

```
voting-system/
├── VotingSystem.java          # Main application class
├── README.md                  # Project documentation
├── election.csv              # Election data storage
├── candidate.csv             # Candidate information
├── voter.csv                 # Voter registration data
└── vote1.csv                # Voting results
```

## 🏗️ Architecture

### Class Design
- **`election`**: Manages election data with linked list structure
- **`candidate`**: Handles candidate information and validation
- **`voter`**: Manages voter registration and verification
- **`administrator`**: Core business logic for all administrative functions
- **`VotingSystem`**: Main application class with user interface

### Data Flow
1. **Registration Phase**: Administrators create elections and register candidates
2. **Voter Registration**: Voters register with unique IDs
3. **Voting Phase**: Real-time voting with instant validation
4. **Result Phase**: Automated result calculation and winner declaration

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, VS Code)

## 📖 Usage Guide

### Administrator Mode
1. **Add Election**: Create new elections with specific dates
2. **Add Candidate**: Register candidates with full profile validation
3. **View Lists**: Display registered candidates and voters
4. **Start Election**: Initiate voting process with date validation
5. **View Results**: Calculate and display election results

### Voter Mode
1. **Register**: Create voter account with unique ID
2. **Vote**: Participate in active elections
3. **View Results**: Check election outcomes

## 🔧 Key Implementation Details

### Data Validation
```java
// Age validation for candidates
if (cage < 19) {
    System.out.println("Candidate not eligible due to age criteria");
    return;
}

// Voter ID validation (12 digits)
while(cvoterid.length() != 12) {
    System.out.println("Invalid Voter Id! Please Enter Again");
    cvoterid = sc.next();
}

// Nationality validation
if (nation.compareTo(cnationality) != 0) {
    System.out.println("You are not inhabitant of India!");
    return;
}
```

### File Operations
- **CSV-based Storage**: Efficient data persistence using CSV files
- **Append Mode**: Prevents data loss during multiple operations
- **Error Handling**: Comprehensive exception handling for file operations

### Voting Algorithm
- **Real-time Processing**: Instant vote counting and validation
- **Duplicate Prevention**: Ensures one vote per voter ID
- **Result Calculation**: Automated winner determination with vote counting

## 🎯 Key Features Highlight

### 1. **Robust Validation System**
- Input sanitization and validation
- Age and nationality verification
- Voter ID uniqueness checking
- Date format validation

### 2. **User-Friendly Interface**
- Clear menu-driven navigation
- Comprehensive error messages
- Professional formatting and display

### 3. **Data Integrity**
- CSV-based persistent storage
- Transaction-like operations
- Backup and recovery mechanisms

### 4. **Scalable Architecture**
- Modular class design
- Extensible linked list structures
- Separation of concerns
