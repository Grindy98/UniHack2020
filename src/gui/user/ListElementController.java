package gui.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ListElementController {

    @FXML
    private Pane pane;
    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize(){
        pane.setStyle("-fx-border-color: black");
    }

    public void initialSetter(EventHandler<ActionEvent> delButtonAction){
        //delButton.setOnAction(delButtonAction);
    }
}
