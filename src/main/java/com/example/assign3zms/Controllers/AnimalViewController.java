package com.example.assign3zms.Controllers;

import com.example.assign3zms.Model.Animal;
import com.example.assign3zms.Model.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    /**
     * Default constructor for the AnimalViewController.
     * Called automatically by the JavaFX framework.
     */
    public AnimalViewController() {}

    /**
     * Sets the current enclosure that the animal belongs to.
     *
     * @param enclosure The {@link Enclosure} where animals are stored.
     */
    public void setEnclosure(Enclosure enclosure) {
        this.currentEnclosure = enclosure;
    }

    /**
     * Sets the currently selected animal to be edited.
     * If the animal is not null, populates the text fields with its data.
     *
     * @param animal The {@link Animal} to be edited, or null if adding a new one.
     */
    public void setAnimal(Animal animal) {
        this.selectedAnimal = animal;
        if (animal != null) {
            nameField.setText(animal.getName());
            ageField.setText(String.valueOf(animal.getAge()));
        }
    }

    /**
     * Handles the "Save" button click event.
     * <ul>
     *     <li>If an animal is being edited, updates its information.</li>
     *     <li>If no animal is selected, creates and adds a new one to the enclosure.</li>
     * </ul>
     * Validates that both fields are filled and that age is a valid number.
     */
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

            // Edit existing animal
            if (selectedAnimal != null) {
                selectedAnimal.setName(name);
                selectedAnimal.setAge(age);
                showAlert("Success", "Animal updated successfully!");
            }

            // Add new animal
            else if (currentEnclosure != null) {
                Animal newAnimal = new Animal(name, age);
                currentEnclosure.addAnimal(newAnimal);
                showAlert("Success", "Animal added successfully!");
            }

            // Missing enclosure
            else {
                showAlert("Error", "No enclosure selected.");
                return;
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Error", "Age must be a valid number.");
        }
    }

    /**
     * Handles the "Delete" button click event.
     * Removes the selected animal from the enclosure if possible.
     *
     * @param event The {@link ActionEvent} triggered by clicking the delete button.
     */
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

    /**
     * Handles the "Cancel" button click event.
     * Closes the current window without making any changes.
     */
    @FXML
    protected void onCancelButtonClick() {
        closeWindow();
    }

    /**
     * Closes the current JavaFX window.
     */
    private void closeWindow() {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays a simple information alert with a given title and message.
     *
     * @param title   The title of the alert window.
     * @param message The message text to display in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
