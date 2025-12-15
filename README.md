🏦 Console Banking Application

A lightweight Java-based console application that simulates core retail banking operations such as account management, deposits, withdrawals, and fund transfers — all without using external frameworks.
This project is ideal for learning object-oriented design, exception handling, and clean Java architecture.

🚀 Project Overview

The Console Banking App provides a simple yet complete model of a banking system’s essential workflows.
It demonstrates how to structure domain models, handle validations, and maintain business logic in a clean, modular way using plain Java.

✨ Features

🧾 Open a new account for customers

💰 Deposit funds into an account with input validation

💸 Withdraw funds with insufficient balance checks

🔁 Transfer funds between accounts with atomic validation

📋 List all accounts, sorted by account number

🔍 Search accounts by customer name (case-insensitive)

⚙️ Custom exception handling:

ValidationException

AccountNotFoundException

InsufficientFundsException


🧩 Project Structure

src/
 ├── app/               # Entry point (Main class)
 ├── domain/            # Core models (Account, Customer, Transaction, Type)
 ├── repository/        # In-memory data repository
 ├── service/           # Business logic (account operations)
 ├── exceptions/        # Custom exception classes
 └── util/              # Validation and utility helpers

🧠 Concepts Demonstrated

Object-Oriented Programming (OOP)

Custom Exceptions and Validation

Repository Pattern (in-memory)

Clean Code and Modular Design

Console-based User Interaction
