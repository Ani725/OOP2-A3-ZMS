package com.example.assign3zms.Controllers;

import com.example.assign3zms.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for managing the Animal View in the Zoo Management System.
 * <p>
 * This class handles user interactions for adding, editing, and deleting animals
 * within a specific {@link Enclosure}. It updates the UI fields based on the
 * currently selected {@link Animal}, validates user input, and communicates
 * changes back to the model layer.
 * </p>
 */
public class AnimalViewController {

    /** Button to save the animal's data (either add or edit). */
    @FXML
    private Button SaveButton;

    /** Button to cancel and close the current window. */
    @FXML
    private Button CancelButton;

    /** Button to delete the selected animal. */
    @FXML
    private Button DeleteButton;

    /** ComboBox for the animal type. */
    @FXML
    private ComboBox<String> animalTypeCombo;

    /** TextField for the animal's name. */
    @FXML
    private TextField nameField;

    /** TextField for the animal's age. */
    @FXML
    private TextField ageField;

    /** The current enclosure where the animal belongs. */
    private Enclosure currentEnclosure;

    /** The currently selected animal being edited (null if adding a new one). */
    private Animal selectedAnimal;

    /** Default constructor. */
    public AnimalViewController() {}

    /** Sets the current enclosure. */
    public void setEnclosure(Enclosure enclosure) {
        this.currentEnclosure = enclosure;
    }

    /**
     * Sets the currently selected animal for editing.
     * Populates all fields and selects the correct animal type.
     */
    public void setAnimal(Animal animal) {
        this.selectedAnimal = animal;
        if (animal != null) {
            nameField.setText(animal.getName());
            ageField.setText(String.valueOf(animal.getAge()));

            // Select correct type in ComboBox
            if (animal instanceof Tiger)
                animalTypeCombo.getSelectionModel().select("Tiger");
            else if (animal instanceof Cougar)
                animalTypeCombo.getSelectionModel().select("Cougar");
            else
                animalTypeCombo.getSelectionModel().select("Lion");
        } else {
            // clear fields for adding new animal
            nameField.clear();
            ageField.clear();
            animalTypeCombo.getSelectionModel().clearSelection();
        }
        // Always show dropdown box
        animalTypeCombo.setDisable(false);
    }

    /** Handles the "Save" button click (add or edit). */
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
            String type = animalTypeCombo.getValue();

            if (type == null || type.isEmpty()) {
                showAlert("Error", "Please select an animal type.");
                return;
            }

            // Edit existing
            if (selectedAnimal != null) {
                selectedAnimal.setName(name);
                selectedAnimal.setAge(age);
                showAlert("Success", "Animal updated successfully!");
            }
            // Add new
            else if (currentEnclosure != null) {
                Animal newAnimal;
                switch (type) {
                    case "Tiger": newAnimal = new Tiger(name, age); break;
                    case "Cougar": newAnimal = new Cougar(name, age); break;
                    case "Lion":
                    default: newAnimal = new Lion(name, age); break;
                }

                currentEnclosure.addAnimal(newAnimal);
                showAlert("Success", "Animal added successfully!");
            } else {
                showAlert("Error", "No enclosure selected.");
                return;
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Error", "Age must be a valid number.");
        }
    }

    /** Handles the "Delete" button click. */
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

    /** Handles cancel button click. */
    @FXML
    protected void onCancelButtonClick() {
        closeWindow();
    }

    /** Closes the current window. */
    private void closeWindow() {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    /** Shows an alert popup with the given message. */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /** Animal types available in dropbox */
    @FXML
    private void initialize() {
        animalTypeCombo.getItems().addAll("Lion", "Tiger", "Cougar");
    }
}
