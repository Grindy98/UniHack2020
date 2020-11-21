package gui.user;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccountSettingsController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Button updateFirstNameButton, updateNameButton, updateCityButton, updatePhoneButton;

    public AccountSettingsController(){

    }

    public void setFirstNamePrompt(String firstName){
        firstNameTextField.setPromptText(firstName);
    }

    public void setNameTextFieldPrompt(String name){
        nameTextField.setPromptText(name);
    }

    public void setCityTextFieldPrompt(String city){
        cityTextField.setPromptText(city);
    }

    public void setPhoneTextField(String phone){
        phoneTextField.setPromptText(phone);
    }

    public void setUserNameLabel(String userName){
        userNameLabel.setText(userName);
    }

    //public void firstSetNameButtonCl
}
