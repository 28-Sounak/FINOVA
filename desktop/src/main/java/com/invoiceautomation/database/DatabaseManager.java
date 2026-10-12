package com.invoiceautomation.database;

import java.io.File;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;

public class DatabaseManager
{
    private static final String DATABASE_DIRECTORY =  "C:\\Users\\SOUNAK BANERJEE\\FINOVA\\data\\database";

    private static final String DATABASE_PATH = DATABASE_DIRECTORY + "\\app.db";

    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_PATH;

    //Returns a connection to the FINOVA DQLite database
    public static Connection getConnection() throws SQLException
    {
        createDatabaseDirectory();

        Connection connection = DriverManager.getConnection(DATABASE_URL);

        //Enable foreign-key constraints for this connection
        try(Statement statement = connection.createStatement())
        {
            statement.execute("PRAGMA foreign_keys = ON");
        }

        return connection;

    }

    //Creates the database directory if it does not exist
    private static void createDatabaseDirectory()
    {
        File directory = new File(DATABASE_DIRECTORY);

        if(!directory.exists())
        {
            boolean created = directory.mkdirs();

            if(!created)
            {
                throw new RuntimeException("Could not create database directory" + DATABASE_DIRECTORY);
            }
        }
    }

    //Initializes the database and creates required tables
    public static void initializeDatabase() 
    {

        createDatabaseDirectory();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) 
        {

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        email TEXT UNIQUE NOT NULL,
                        password_hash TEXT NOT NULL,
                        role TEXT NOT NULL,
                        created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS vendors (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        contact TEXT,
                        email TEXT,
                        category TEXT,
                        status TEXT NOT NULL DEFAULT 'Active',
                        created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS expenses (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        description TEXT NOT NULL,
                        category TEXT,
                        vendor_id INTEGER,
                        amount REAL NOT NULL,
                        expense_date TEXT NOT NULL,
                        status TEXT NOT NULL DEFAULT 'Pending',
                        notes TEXT,
                        created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        FOREIGN KEY (vendor_id)
                            REFERENCES vendors(id)
                            ON DELETE SET NULL
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS invoices (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        invoice_number TEXT UNIQUE NOT NULL,
                        customer_name TEXT NOT NULL,
                        issue_date TEXT NOT NULL,
                        due_date TEXT NOT NULL,
                        amount REAL NOT NULL,
                        status TEXT NOT NULL DEFAULT 'Pending',
                        notes TEXT,
                        created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS invoice_items (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        invoice_id INTEGER NOT NULL,
                        description TEXT NOT NULL,
                        quantity REAL NOT NULL,
                        unit_price REAL NOT NULL,

                        FOREIGN KEY (invoice_id)
                            REFERENCES invoices(id)
                            ON DELETE CASCADE
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS approvals (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        request_type TEXT NOT NULL,
                        reference_id INTEGER,
                        requested_by INTEGER,
                        amount REAL,
                        status TEXT NOT NULL DEFAULT 'Pending',
                        requested_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        FOREIGN KEY (requested_by)
                            REFERENCES users(id)
                            ON DELETE SET NULL
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS receipts (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        expense_id INTEGER,
                        file_path TEXT NOT NULL,
                        ocr_text TEXT,
                        created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        FOREIGN KEY (expense_id)
                            REFERENCES expenses(id)
                            ON DELETE CASCADE
                    )
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS audit_logs (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        user_id INTEGER,
                        action TEXT NOT NULL,
                        entity_type TEXT,
                        entity_id INTEGER,
                        details TEXT,
                        created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        FOREIGN KEY (user_id)
                            REFERENCES users(id)
                            ON DELETE SET NULL
                    )
                    """);

            System.out.println("FINOVA database initialized successfully.");

        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(
                    "Failed to initialize FINOVA database.",
                    e
            );
        }
    }
}