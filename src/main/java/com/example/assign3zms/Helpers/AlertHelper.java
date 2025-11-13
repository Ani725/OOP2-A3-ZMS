package com.example.assign3zms.Helpers;

import javafx.scene.control.Alert;

public class AlertHelper {

    // Error Alert
    public static void showErrorAlert(String pInfo, String pHeader, String pMessage) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(pInfo);
        alert.setHeaderText(pHeader);
        alert.setContentText(pMessage);
        alert.showAndWait();
    }

    // Information Alert
    public static void showInfoAlert(String pInfo, String pHeader, String pMessage) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(pInfo);
        alert.setHeaderText(pHeader);
        alert.setContentText(pMessage);
        alert.showAndWait();
    }
}
