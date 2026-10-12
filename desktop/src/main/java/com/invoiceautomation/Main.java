package com.invoiceautomation;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;

import com.invoiceautomation.database.DatabaseManager;

public class Main extends Application
{
    @Override 
    public void start(Stage stage) throws Exception
    {
        //Initialize the SQLite Database
        DatabaseManager.initializeDatabase();

        //Load the Database

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));

        Scene scene = new Scene(loader.load(), 1200, 750);

        scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());

        stage.setTitle("FINOVA - Invoice and Expense Application");

        stage.setScene(scene);

        stage.setMinWidth(1000);

        stage.setMinHeight(650);

        stage.show();
    }

    public static void main(String args[])
    {
        launch(args);
    }
}