package gui.user;

import database.GetCityUser;
import gui.login.Services;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import gui.sceneUtilities.SceneManager;
import javafx.stage.Stage;

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
        controller.seeInfoButton(e -> {
            Stage stage = new Stage();
            FXMLLoader loader_aux = new FXMLLoader();
            loader_aux.setLocation(getClass().getResource("/resources/user/seedetails.fxml"));

            Parent settingsRoot_aux = null;
            try {
                settingsRoot_aux = loader_aux.load();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException();
            }
            SeedetailsController detailsController = loader_aux.getController();
            User userInst = GetCityUser.getUserByUsername(user.username);
            detailsController.setPhone(user.nr);
            detailsController.setName(userInst.firstName + " " + userInst.lastName);
            detailsController.setOtherservices(userInst.serviceList.toString());

            stage.setTitle("Details");
            Scene scene = new Scene(settingsRoot_aux, 500, 300);
            stage.setScene(scene);
            stage.show();
        });
    }

    public Parent getRoot() {
        return root;
    }



}
