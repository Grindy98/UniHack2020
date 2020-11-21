package gui.user;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SeedetailsController {

    @FXML
    public Label phone;

    @FXML
    public Label name;

    @FXML
    public Label otherservices;

    public void initialize(){ }

    public void setPhone(String phone_text) {
        phone.setText(phone_text);
    }

    public void setName(String name_text) {
        name.setText(name_text);
    }

    public void setOtherservices(String text) {
        otherservices.setText(text);
    }
}
