# FINOVA AI Service

The FINOVA AI Service is the Python-based backend responsible for document processing, OCR, AI-powered data extraction, expense classification, and financial anomaly detection.

It runs locally and communicates with the FINOVA JavaFX desktop application through HTTP/JSON APIs.

## Architecture

```text
Invoice / Receipt
       │
       ▼
Document Service
       │
       ▼
Preprocessing
       │
       ├── PDF Processor
       │
       └── Image Processor
       │
       ▼
OCR Service
       │
       ▼
Extracted Text
       │
       ▼
AI Extraction Service
       │
       ▼
Structured Data
       │
       ▼
Validation
       │
       ▼
JavaFX Desktop Application
```

## Responsibilities

The AI service is responsible for:

* PDF processing
* Image preprocessing
* OCR
* Invoice data extraction
* Receipt data extraction
* Expense classification
* AI-assisted document understanding
* Data validation
* Confidence estimation
* Anomaly detection
* Communication with the desktop application

## Project Structure

```text
ai-service/
│
├── app/
│   ├── main.py
│   │
│   ├── api/
│   │   ├── expenses.py
│   │   ├── health.py
│   │   ├── invoices.py
│   │   └── ocr.py
│   │
│   ├── models/
│   │   ├── expense_schema.py
│   │   ├── invoice_schema.py
│   │   └── llm_model.py
│   │
│   ├── preprocessing/
│   │   ├── image_processor.py
│   │   └── pdf_processor.py
│   │
│   ├── services/
│   │   ├── anomaly_service.py
│   │   ├── classification_service.py
│   │   ├── document_service.py
│   │   ├── extraction_service.py
│   │   └── ocr_service.py
│   │
│   └── utils/
│       ├── logger.py
│       └── validators.py
│
├── tests/
├── requirements.txt
└── README.md
```

## Technologies

* Python
* FastAPI
* Uvicorn
* OCR engine
* Local LLM/document model
* Pydantic
* Machine learning/statistical methods where appropriate

The exact OCR and local AI technologies may change during development.

## Installation

From the `ai-service` directory:

```powershell
cd ai-service
```

Create a virtual environment:

```powershell
python -m venv .venv
```

Activate it on Windows:

```powershell
.venv\Scripts\Activate.ps1
```

Install dependencies:

```powershell
pip install -r requirements.txt
```

## Running the Service

Start the FastAPI application with:

```powershell
python -m uvicorn app.main:app --reload
```

The service will normally run at:

```text
http://127.0.0.1:8000
```

FastAPI documentation is available locally at:

```text
/docs
```

and:

```text
/redoc
```

## API Responsibilities

The API will expose endpoints for operations such as:

```text
GET  /health
POST /ocr
POST /invoices/extract
POST /expenses/extract
```

The exact endpoint structure may evolve as development continues.

## Invoice Extraction

The intended invoice extraction pipeline is:

```text
PDF / Image
     ↓
Preprocessing
     ↓
OCR
     ↓
Text
     ↓
Local AI Model
     ↓
Invoice Schema
     ↓
Validation
```

Example output:

```json
{
    "vendor": "ABC Technologies Pvt Ltd",
    "invoice_number": "INV-1042",
    "invoice_date": "2026-09-28",
    "due_date": "2026-10-28",
    "subtotal": 50000,
    "gst": 9000,
    "total": 59000,
    "category": "Software",
    "confidence": 0.94
}
```

## AI Design

The AI service follows a human-in-the-loop design.

AI-generated information should not automatically become a final financial record without appropriate validation.

A typical workflow is:

```text
AI Extraction
      ↓
Confidence Score
      ↓
Validation
      ↓
Human Verification
      ↓
Final Record
```

Traditional rules and statistical/ML techniques may be used alongside the local AI model for tasks such as duplicate detection and anomaly detection.

## Testing

Tests will be stored under:

```text
tests/
```

Run the test suite with:

```powershell
pytest
```

## Development Guidelines

Keep the AI service modular.

Business logic should remain in:

```text
services/
```

API routes should remain in:

```text
api/
```

Data schemas should remain in:

```text
models/
```

Document preprocessing should remain in:

```text
preprocessing/
```

Avoid placing large AI models directly inside the Git repository.

## Status

The AI service is currently under development.

Current focus:

* FastAPI foundation
* Document processing
* OCR pipeline
* Invoice extraction
* Local AI model integration
* Validation
* Expense classification
* Anomaly detection
