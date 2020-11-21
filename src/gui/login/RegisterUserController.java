package gui.login;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import gui.sceneUtilities.SceneManager;
import gui.login.Services;
import gui.user.User;
import javafx.scene.text.Font;

import java.util.EnumMap;


public class RegisterUserController<serviceBoxes> {
    enum TextSelect {T1, T2, T3, T4, T6, T7};

    public void goBackClicked(ActionEvent actionEvent){
        Main.getI().changeSceneOnMainStage(SceneManager.Type.LOGIN);
    }

    @FXML
    private Button goBack;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private RadioButton userType1, userType2;

    @FXML
    private Label serviceLabel;

    @FXML
    private Label l1, l2, l3, l4, l6, l7;

    public User.Type getUserType() {
        if(userType1.isSelected())
            return User.Type.CLIENT;
        else return User.Type.PROVIDER;
    }

    @FXML
    private Button submitButton;

    public Button getButton() {
        return submitButton;
    }

    public TextField getTextField(TextSelect t)
    {
        return switch (t) {
            case T1 -> t1;
            case T2 -> t2;
            case T3 -> t3;
            case T4 -> t4;
            case T6 -> t6;
            case T7 -> t7;
        };
    }
    public Label getLabelField(TextSelect t)
    {
        return switch (t) {
            case T1 -> l1;
            case T2 -> l2;
            case T3 -> l3;
            case T4 -> l4;
            case T6 -> l6;
            case T7 -> l7;
        };
    }
    public void setTextLabel(TextSelect t, String message){
        Label l =getLabelField(t);
        l.setText(message);
    }

    public static String getTextErrorLabel(RegisterUserLogic.ValidateReturn errorFlag){
        return switch (errorFlag){
            case EMPTY -> "Field cannot be empty.";
            case VALID -> "";
            case TOO_SHORT -> "Field is too short.";
            case TOO_LONG -> "Field is too long.";
            case INVALID_CHARACTERS -> "Field contains invalid characters.";
        };
    }

    @FXML
    private TextField t1, t2, t3, t4, t6, t7;

    @FXML
    private VBox box;

    private EnumMap<Services.Type, CheckBox> arrMap = new EnumMap<Services.Type, CheckBox>(Services.Type.class);

    public EnumMap<Services.Type, CheckBox> getArrMap() {
        return arrMap;
    }

    @FXML
    public void initialize() {
        buildCheckBox();
        l1.setWrapText(true);
        serviceLabel.setWrapText(true);

        ObservableList<String> options =
                FXCollections.observableArrayList(User.cities);
        cityComboBox.setItems(options);
        cityComboBox.setValue(options.get(0));
    }

    public void clientClicked(ActionEvent actionEvent)
    {
        serviceLabel.setText("What services are u looking for?");
    }

    public void providerClicked(ActionEvent actionEvent)
    {
        serviceLabel.setText("What services do u offer?");
    }

    private void buildCheckBox(){

        for (Services.Type it: Services.Type.values()) {
            CheckBox b = new CheckBox(it.label);
            b.setPadding(new Insets(10,10,10,10));
            b.setStyle(
                    "-fx-font-size: " + "18;" +
                    "-fx-font-weight: " + "bold;");
            arrMap.put(it, b);
        }
        box.getChildren().setAll(arrMap.values());
    }

    public ComboBox<String> getCity(){
        return cityComboBox;
    }

}