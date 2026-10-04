package com.invoiceautomation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label pageTitle;

    @FXML
    private void openDashboard() {
        loadPage("/fxml/Dashboard.fxml", "Dashboard");
    }

    @FXML
    private void openExpenses() {
        loadPage("/fxml/Expenses.fxml", "Expenses");
    }

    @FXML
    private void openInvoices() {
        loadPage("/fxml/Invoices.fxml", "Invoices");
    }

    @FXML
    private void openVendors() {
        loadPage("/fxml/Vendors.fxml", "Vendors");
    }

    @FXML
    private void openApprovals() {
        loadPage("/fxml/Approvals.fxml", "Approvals");
    }

    @FXML
    private void openReports() {
        loadPage("/fxml/Reports.fxml", "Reports");
    }

    @FXML
    private void openSettings() {
        loadPage("/fxml/Settings.fxml", "Settings");
    }

    private void loadPage(String resource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent page = loader.load();

            rootPane.setCenter(page);

            if (pageTitle != null) {
                pageTitle.setText(title);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openProfile() {
        System.out.println("Profile selected");
    }

    @FXML
    private void openPreferences() {
        System.out.println("Preferences selected");
    }

    @FXML
    private void logout() {
        System.out.println("Logout selected");
    }
}