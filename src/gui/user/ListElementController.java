package gui.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ListElementController {

    @FXML
    public Button seeInfoButton;
    @FXML
    private Pane pane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label serviceLabel;
    @FXML
    private Label nameLabel;

    @FXML
    public void initialize(){
        pane.setStyle("-fx-border-color: black");
    }

    public void seeInfoButton(EventHandler<ActionEvent> e) {
        seeInfoButton.setOnAction(e);
    }

    public void setLabels(String name, String service){
        nameLabel.setText(name);
        serviceLabel.setText(service);
    }
}
