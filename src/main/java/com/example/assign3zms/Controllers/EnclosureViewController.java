package com.example.assign3zms.Controllers;

import java.io.IOException;

import com.example.assign3zms.Helpers.AlertHelper;
import com.example.assign3zms.Model.Animal;
import com.example.assign3zms.Model.Enclosure;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for the Enclosure view in the Zoo Management System application.
 * <p>
 * Handles user interactions and updates the UI components related to an {@link Enclosure} and its {@link Animal}s.
 * Provides functionality to add, edit, delete animals, and navigate between views.
 * </p>
 *
 * <ul>
 *   <li>Displays the name of the current enclosure and its animals or sub-enclosures.</li>
 *   <li>Allows deletion of selected animals from the enclosure.</li>
 *   <li>Handles navigation actions such as going back or closing the application.</li>
 *   <li>Opens modal views for adding or editing animals.</li>
 * </ul>
 *
 * @author Dieudonne
 */
public class EnclosureViewController {

    @FXML
    private Button backButton;

    @FXML
    private ListView<String> aListView;

    private Enclosure aEnclosure; // The current enclosure being viewed
    private Animal aAnimal; // The currently selected animal

    private Stage aStage = new Stage();
    /**
     * Called automatically after FXML loading.
     * Initializes the view.
     */
    @FXML
    private void initialize() {
//        Stage stage = (Stage) aVBOXStage.getScene().getWindow();
//        stage.setTitle(this.aEnclosure.getName());
        //this.aEnclosure =

        //refreshView();
    }

    public void setEnclosure(Enclosure pSelectedEnclosure) {
        this.aEnclosure = pSelectedEnclosure;
        refreshView();
    }

//  TODO  : Add the functionality of double click to select an item
    /**
     * Handles selection of an item in the ListView.
     * Sets the selected animal if found.
     */
    @FXML
    private void onListViewItemSelect() {
        String selectedItem = this.aListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertHelper.showInfoAlert("Selection", "No item selected", "Please select an animal or enclosure from the list.");
            return;
        }
        // Custom logic to set aAnimal based on selection
        if (this.aEnclosure != null && this.aEnclosure.getAnimals() != null) {
            for (Animal animal : this.aEnclosure.getAnimals()) {
                String display = animal.getName() + " (" + animal.getAge() + " years)";
                if (display.equals(selectedItem)) {
                    this.aAnimal = animal;
                    return;
                }
            }
        }

        AlertHelper.showInfoAlert("Selection", "No animal found", "The selected item is not an animal.");
    }

    /**
     * Handles the Back button click.
     * Closes the current window.
     */
    @FXML
    protected void onBackButtonClick(){
        aStage = (Stage) backButton.getScene().getWindow();
        aStage.close();
    }

    /**
     * Handles the Delete button click.
     * Deletes the selected animal from the enclosure.
     */
    @FXML
    private void onDeleteButtonClick(ActionEvent pEvent){
        if (this.aEnclosure == null) {
            AlertHelper.showErrorAlert("Delete Error", "No enclosure selected", "Please select an enclosure before deleting.");
            return;
        }
        if (this.aAnimal == null) {
            AlertHelper.showErrorAlert("Delete Error", "No animal selected", "Please select an animal to delete.");
            return;
        }
        this.aEnclosure.deleteAnimal(this.aAnimal);
        AlertHelper.showInfoAlert("Delete", "Animal Deleted", "The animal has been deleted successfully.");
        this.aAnimal = null;
        refreshView();
    }

    /**
     * Refreshes the view to show the current enclosure's animals.
     */
    @FXML
    private void refreshView() {
        if (this.aEnclosure == null) {
            return;
        }
        this.aStage.setTitle(this.aEnclosure.getName());
        this.aListView.getItems().clear();

        // Show animals if present
        if (this.aEnclosure.getAnimals() != null && !this.aEnclosure.getAnimals().isEmpty()) {
            for (Animal animal : this.aEnclosure.getAnimals()) {
                this.aListView.getItems().add(animal.getName() + " (" + animal.getAge() + " years)");
            }
        } else {
            AlertHelper.showInfoAlert("Enclosure", "Empty enclosure", "This enclosure has no animals.");
        }
    }

    /**
     * Handles the Add button click.
     * Opens the animal view for adding a new animal.
     */
    @FXML
    protected void onAddButtonClick(ActionEvent pEvent) {
        if (this.aEnclosure == null) {
            AlertHelper.showErrorAlert("Add Error", "No enclosure selected", "Please select an enclosure before adding an animal.");
            return;
        }
        //To add a new animal

        openNextView(new Animal(null,0));
    }

    /**
     * Handles the Edit button click.
     * Opens the animal view for editing the selected animal.
     */
    @FXML
    protected void onEditButtonClick(ActionEvent pEvent) {
        if (this.aEnclosure == null) {
            AlertHelper.showErrorAlert("Edit Error", "No enclosure selected", "Please select an enclosure before editing an animal.");
            return;
        }
        if (this.aAnimal == null) {
            AlertHelper.showErrorAlert("Edit Error", "No animal selected", "Please select an animal to edit.");
            return;
        }
        openNextView(this.aAnimal);
    }

    /**
     * Opens the animal view (modal) for adding or editing an animal.
     */
    private void openNextView(Animal pAnimal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/assign3zms/animal-view.fxml"));
            Parent root = loader.load();

            AnimalViewController controller = loader.getController();
            controller.setEnclosure(this.aEnclosure);
            controller.setAnimal(pAnimal);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            AlertHelper.showErrorAlert("Error", "Failed to open the animal view", "Try again, please!\n" + e.getMessage());
        }
    }
}
