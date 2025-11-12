package com.example.assign3zms.Controllers;

import com.example.assign3zms.Helpers.ImportHelper;
import com.example.assign3zms.Model.*;
import com.example.assign3zms.ZooApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the CompositeEnclosureCollection view.
 * <p>
 * This controller displays the top-level zoo sections/enclosures in a TreeView.
 * Users can double-click an enclosure to open it in a new modal window.
 * </p>
 *
 * @author Rohina
 */
public class CompositeEnclosureCollectionController {

    /** Back button for closing the modal window. Disabled in the main window. */
    @FXML
    public Button backButton;

    /** TreeView displaying the top-level zoo sections/enclosures. */
    @FXML
    public TreeView<String> enclosureTreeView;

    /**
     * Handles the Back button click event.
     * Closes the current window.
     *
     * @param pEvent the ActionEvent triggered by clicking the back button
     */
    @FXML
    public void onBackButtonClick(ActionEvent pEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the TreeView with the root CompositeEnclosureCollection.
     * Sets up double-click behavior for opening enclosures in a new modal window.
     * Disables the back button in the main window.
     */
    @FXML
    public void initialize() {
        // Create the zoo structure using ImportHelper
        CompositeEnclosureCollection rootCollection = ImportHelper.createAnimals();

        // Build TreeView root
        TreeItem<String> rootItem = new TreeItem<>(rootCollection.getName());
        buildTree(rootCollection, rootItem);

        enclosureTreeView.setRoot(rootItem);
        enclosureTreeView.setShowRoot(true);

        // Disable back button for the main window
        backButton.setDisable(true);

        // Handle double-click on TreeView items
        enclosureTreeView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem<String> selectedItem = enclosureTreeView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    Enclosure clickedEnclosure = findEnclosureByName(rootCollection, selectedItem.getValue());
                    if (clickedEnclosure != null) {
                        try {
                            openEnclosureWindow(clickedEnclosure, event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }

    /**
     * Recursively builds the TreeView by adding child collections to the given TreeItem.
     *
     * @param pRootCollection the current composite collection to process
     * @param pRootItem       the TreeItem corresponding to the collection in the TreeView
     */
    private void buildTree(CompositeEnclosureCollection pRootCollection, TreeItem<String> pRootItem) {
        for (var child : pRootCollection.getChildren()) {
            TreeItem<String> childItem = new TreeItem<>(child.getName());
            pRootItem.getChildren().add(childItem);

            if (child instanceof CompositeEnclosureCollection composite) {
                buildTree(composite, childItem);
            }
        }
    }

    /**
     * Recursively searches for an Enclosure with the given name within the specified collection.
     *
     * @param pRootCollection the collection to search
     * @param pName           the name of the enclosure to find
     * @return the Enclosure if found; otherwise, null
     */
    private Enclosure findEnclosureByName(CompositeEnclosureCollection pRootCollection, String pName) {
        for (var child : pRootCollection.getChildren()) {
            if (child instanceof Enclosure enclosure && enclosure.getName().equals(pName)) {
                return enclosure;
            } else if (child instanceof CompositeEnclosureCollection composite) {
                Enclosure result = findEnclosureByName(composite, pName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Opens a new modal window to display the selected enclosure.
     *
     * @param pSelectedEnclosure the Enclosure to display
     * @param pEvent             the MouseEvent used to set window ownership
     * @throws IOException if the FXML file cannot be loaded
     */
    private void openEnclosureWindow(Enclosure pSelectedEnclosure, MouseEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("enclosure-view.fxml"));
        Parent aView = fxmlLoader.load();

        // Pass the enclosure to the new controller
        EnclosureViewController newEnclosureViewController = fxmlLoader.getController();
        newEnclosureViewController.setEnclosure(pSelectedEnclosure);

        // Show a modal window
        Scene nextScene = new Scene(aView, 500, 500);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle(pSelectedEnclosure.getName());
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.showAndWait();
    }
}
