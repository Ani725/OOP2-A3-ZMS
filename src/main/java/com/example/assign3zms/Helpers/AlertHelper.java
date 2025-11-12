package com.example.assign3zms.Helpers;

import javafx.scene.control.Alert;

public class AlertHelper {

    // Error Alert
    public static void showErrorAlert(String error, String s, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(s);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Information Alert
    public static void showInfoAlert(String enclosure, String emptyEnclosure, String s) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(enclosure);
        alert.setHeaderText(emptyEnclosure);
        alert.setContentText(s);
        alert.showAndWait();
    }
}
