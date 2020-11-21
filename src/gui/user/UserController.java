package gui.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gui.sceneUtilities.SceneManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    @FXML
    private VBox list;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button logoutButton;
    @FXML
    private Button accSetButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label userName;
    @FXML
    private Label userAddress;
    @FXML
    private HBox borderTop;

    @FXML
    private Label cityLabel;

    private List<ListElementLogic> listManager;

    @FXML
    public void initialize(){

        // Set Log out button behaviour
        logoutButton.setOnAction(e -> {
            //SceneManager.getI().loadScene(SceneManager.Type.LOGIN);
        });
        // Set AccSetButton behaviour
        accSetButton.setOnAction((e -> {
            accSetClicked();
        }));

        //we should initialize the name and address using a query to the database
        userName.setText("");
        userAddress.setText("");

        //we should initialize the cityLabel also using a query to the database
        cityLabel.setText("");

        listManager = new ArrayList<>();
        addListElement(new ListElementLogic(anchorPane));
        addListElement(new ListElementLogic(anchorPane));
        addListElement(new ListElementLogic(anchorPane));
        addListElement(new ListElementLogic(anchorPane));
        addListElement(new ListElementLogic(anchorPane));
        addListElement(new ListElementLogic(anchorPane));
        addListElement(new ListElementLogic(anchorPane));
    }

    public void setUserName(String userName) {
        this.userName.setText(userName);
    }

    private void addListElement(ListElementLogic elem){
        listManager.add(elem);
        updateGUIList();
    }
    protected void deleteListElement(ListElementLogic elem){
        listManager.remove(elem);
        updateGUIList();
    }

    private void updateGUIList(){
        list.getChildren().clear();
        listManager.forEach((elem) ->
                list.getChildren().add(elem.getRoot()));
    }

    private void accSetClicked(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("/resources/fxml/client/accountSettings.fxml"));

        Parent settingsRoot = null;
        try {
            settingsRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        stage.setTitle("Account settings");
        Scene scene = new Scene(settingsRoot, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void setUserAddress(String address) {
        userAddress.setText(address);
    }

    public void setCityLabel(String address) {
        cityLabel.setText(address);
    }
}
