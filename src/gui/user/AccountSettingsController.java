package gui.user;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AccountSettingsController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private TextField phoneTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Button updateFirstNameButton;
    @FXML
    private Button updateLastNameButton;
    @FXML
    private Button updateCityButton;
    @FXML
    private Button updatePhoneButton;
    @FXML
    private Label firstNameWarning;
    @FXML
    private Label lastNameWarning;
    @FXML
    private Label phoneWarning;


    @FXML
    public void initialize(){
        ObservableList<String> options =
                FXCollections.observableArrayList(User.cities);
        cityComboBox.setItems(options);
    }

    public Button getUpdateFirstNameButton() {
        return updateFirstNameButton;
    }

    public Button getUpdateLastNameButton() {
        return updateLastNameButton;
    }

    public Button getUpdateCityButton() {
        return updateCityButton;
    }

    public Button getUpdatePhoneButton() {
        return updatePhoneButton;
    }

    public void setUserNameLabel(String userName){
        userNameLabel.setText(userName);
    }

    public void setFirstNameError(String str){
        firstNameWarning.setText(str);
    }
    public void setLastNameError(String str){
        lastNameWarning.setText(str);
    }
    public void setPhoneError(String str){
        phoneWarning.setText(str);
    }
    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public TextField getPhoneTextField() {
        return phoneTextField;
    }

    public TextField getTextField() {
        return phoneTextField;
    }

    public String getUserName() {
        return userNameLabel.getText();
    }
    public ComboBox<String> getCityComboBox(){
        return cityComboBox;
    }

    public void reset(){
        firstNameWarning.setText("");
        lastNameWarning.setText("");
        phoneWarning.setText("");
    }
}
