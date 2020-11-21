package gui.user;

import gui.login.Services;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import gui.sceneUtilities.SceneManager;

import java.io.IOException;

public class ListElementLogic {
    private final static String listElementFXML = "/resources/user/listElement.fxml";

    private AnchorPane root;
    private ListElementController controller;
    private User user;
    private Services.Type service;


    public ListElementLogic(AnchorPane pane, User user, Services.Type service){
        // Initialize from fxml
        this.user = user;
        this.service = service;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(listElementFXML));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        controller = loader.getController();
        controller.setLabels(user.firstName + " " + user.lastName, service.label);
    }

    public Parent getRoot() {
        return root;
    }



}
