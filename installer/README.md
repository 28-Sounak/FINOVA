# FINOVA Installer

This directory contains the resources and configuration required to package FINOVA as a distributable desktop application for Windows.

## Purpose

The installer is responsible for preparing a user-friendly installation package containing the FINOVA desktop application and the components required to run it.

The intended distribution will include:

```text
FINOVA
│
├── Desktop Application
├── Configuration
├── Database Setup
├── AI Service
├── Required Runtime Components
└── Application Resources
```

## Build Process

The installer build process will eventually be automated through:

```text
scripts/build-installer.ps1
```

From the project root:

```powershell
.\scripts\build-installer.ps1
```

The exact packaging technology will be finalized during development.

## Installation Requirements

The final installer is intended to minimize manual configuration.

Depending on the final architecture, FINOVA may require:

* Java runtime
* Python runtime or packaged AI service runtime
* SQLite
* Local AI/OCR components

The final distribution strategy will determine whether these dependencies are bundled or installed separately.

## Data

User-generated application data should remain separate from the application installation files.

FINOVA uses:

```text
data/
├── database/
├── invoices/
├── receipts/
├── exports/
└── logs/
```

The installer should avoid overwriting existing user data during application updates.

## Development Status

The installer is currently a placeholder for the future FINOVA packaging and distribution process.

Planned tasks:

* [ ] Select packaging technology
* [ ] Package JavaFX application
* [ ] Package AI service
* [ ] Configure application directories
* [ ] Create Windows installer
* [ ] Add application shortcuts
* [ ] Add uninstall support
* [ ] Test clean installation
* [ ] Test application upgrades
