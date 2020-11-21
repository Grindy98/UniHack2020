package gui.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import gui.sceneUtilities.SceneManager;

import java.io.IOException;

public class ListElementLogic {
    private final static String listElementFXML = "/resources/user/listElement.fxml";

    private AnchorPane root;

    public ListElementLogic(AnchorPane pane){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(listElementFXML));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        // Initialize

        // Bind width property
        root.prefWidthProperty().bind(pane.widthProperty());

        // Add delete button behaviour
        EventHandler<ActionEvent> delButton = event -> {
           ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).deleteListElement(this);
        };
        ((ListElementController)loader.getController()).initialSetter(delButton);
    }

    public Parent getRoot() {
        return root;
    }
}
