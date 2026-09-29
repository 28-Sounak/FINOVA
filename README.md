# FINOVA

**FINOVA** is a desktop-based Invoice and Expense Automation system designed to simplify financial record management for individuals and organizations.

It combines a JavaFX desktop application with a local Python-based AI service to automate invoice and receipt processing, expense categorization, document data extraction, approval workflows, anomaly detection, and financial reporting.

## Features

* Invoice management
* Expense management
* Vendor management
* Receipt and invoice document upload
* OCR-based text extraction
* Local AI-powered invoice and receipt data extraction
* Automatic expense categorization
* Duplicate invoice detection
* Expense policy validation
* Anomaly detection
* Approval workflow
* Payment and reimbursement tracking
* Dashboard and financial analytics
* Audit logging
* Report generation and export
* Local-first data storage

## Architecture

FINOVA follows a modular local-first architecture.

```text
                    FINOVA
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
   JavaFX Desktop              AI Service
       Application             Python + FastAPI
          │                         │
          │ HTTP / JSON             │
          └────────────┬────────────┘
                       │
                       ▼
              OCR + Local AI
                       │
                       ▼
              Structured Data
                       │
                       ▼
                    SQLite
```

### Desktop Application

The desktop application is responsible for the main user interface and business operations.

Technologies:

* Java 21
* JavaFX
* Maven
* SQLite
* JDBC
* BCrypt

### AI Service

The AI service is a local Python/FastAPI service responsible for document processing and AI-related functionality.

Technologies:

* Python
* FastAPI
* OCR
* Local AI/LLM
* Machine Learning
* JSON-based API

## Project Structure

```text
FINOVA/
│
├── ai-service/
│   ├── app/
│   │   ├── api/
│   │   ├── models/
│   │   ├── preprocessing/
│   │   ├── services/
│   │   └── utils/
│   ├── tests/
│   ├── requirements.txt
│   └── README.md
│
├── data/
│   ├── database/
│   ├── invoices/
│   ├── receipts/
│   ├── exports/
│   └── logs/
│
├── desktop/
│   ├── src/
│   └── pom.xml
│
├── models/
│
├── scripts/
│
├── installer/
│
├── .gitignore
└── README.md
```

## System Components

### Desktop

The `desktop` module contains the JavaFX application.

It includes:

* User authentication
* Dashboard
* Invoice management
* Expense management
* Vendor management
* Approval management
* Reports
* Database access
* Business services
* Automation rules
* AI service clients

### AI Service

The `ai-service` module provides the local AI backend.

It handles:

```text
Document
   ↓
Preprocessing
   ↓
OCR
   ↓
Text Extraction
   ↓
AI Extraction
   ↓
Validation
   ↓
Structured JSON
```

### Data

The `data` directory contains application-generated data such as:

* SQLite database
* Invoice documents
* Receipt documents
* Generated exports
* Application logs

Sensitive or generated data should not be committed to the repository.

### Models

The `models` directory is intended for locally installed AI models.

Large model files should generally not be committed directly to Git.

## Installation

### Requirements

Before running FINOVA, install:

* Java 21 or later
* Maven
* Python 3.11 or later
* Git

The AI service may require additional dependencies depending on the selected OCR and local AI model.

## Running the Desktop Application

Navigate to the desktop directory:

```powershell
cd desktop
```

Run:

```powershell
mvn clean javafx:run
```

## Running the AI Service

Navigate to the AI service directory:

```powershell
cd ai-service
```

Install the Python dependencies:

```powershell
pip install -r requirements.txt
```

Start the service using the project startup script or directly with Uvicorn.

Example:

```powershell
python -m uvicorn app.main:app --reload
```

The AI service will normally be available locally through:

```text
http://127.0.0.1:8000
```

## Development Status

FINOVA is currently under active development.

Planned development stages include:

* [x] Project architecture
* [ ] JavaFX application foundation
* [ ] SQLite database layer
* [ ] User authentication
* [ ] Role-based access control
* [ ] Dashboard
* [ ] Vendor management
* [ ] Invoice management
* [ ] Expense management
* [ ] Approval workflow
* [ ] OCR pipeline
* [ ] Local AI model integration
* [ ] Automatic document extraction
* [ ] Anomaly detection
* [ ] Reporting and analytics
* [ ] Application packaging
* [ ] Installer

## Design Principle

FINOVA is designed around a **human-in-the-loop** approach.

AI-generated financial information should be validated before it becomes part of the official financial record.

For example:

```text
Invoice
   ↓
OCR
   ↓
AI extraction
   ↓
Confidence score
   ↓
Human verification
   ↓
Database
```

This reduces the risk of incorrect automated extraction affecting financial records.

## Security

FINOVA is intended to operate primarily on the local machine.

Security considerations include:

* Password hashing
* Role-based access control
* Input validation
* Local data storage
* Audit logging
* Controlled file access
* AI output validation

Production deployments should implement additional security controls appropriate to their environment.

## License

A license will be added to the project when the licensing decision is finalized.

## Author

**Sounak Banerjee**

FINOVA is developed as a software engineering and automation project focused on combining desktop application development, financial workflow automation, OCR, and local AI.
