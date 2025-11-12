package com.example.assign3zms.Controllers;

import com.example.assign3zms.Model.Animal;
import com.example.assign3zms.Model.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnimalViewController {

    @FXML
    private Button SaveButton;

    @FXML
    private Button CancelButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    private Enclosure currentEnclosure;
    private Animal selectedAnimal;

    public AnimalViewController() {}

    public void setEnclosure(Enclosure enclosure) {
        this.currentEnclosure = enclosure;
    }

    public void setAnimal(Animal animal) {
        this.selectedAnimal = animal;
        if (animal != null) {
            nameField.setText(animal.getName());
            ageField.setText(String.valueOf(animal.getAge()));
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();

        if (name.isEmpty() || ageText.isEmpty()) {
            showAlert("Error", "Please enter both name and age.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);

            if (selectedAnimal == null) {
                Animal newAnimal = new Animal(name, age);
                currentEnclosure.addAnimal(newAnimal);
                showAlert("Success", "Animal added successfully!");
            } else {
                selectedAnimal.setName(name);
                selectedAnimal.setAge(age);
                showAlert("Success", "Animal updated successfully!");
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Error", "Age must be a valid number.");
        }
    }

    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {
        if (selectedAnimal != null && currentEnclosure != null) {
            currentEnclosure.deleteAnimal(selectedAnimal);
            showAlert("Deleted", "Animal removed from enclosure.");
        } else {
            showAlert("Error", "No animal selected to delete.");
        }
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCancelButtonClick() {
        closeWindow();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
