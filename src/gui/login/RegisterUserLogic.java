package gui.login;

import database.Register;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import gui.sceneUtilities.SceneManager;
import gui.user.User;

import java.util.EnumMap;
import java.util.Map;

public class RegisterUserLogic {
    public enum ValidateReturn{
        EMPTY,
        TOO_LONG,
        TOO_SHORT,
        INVALID_CHARACTERS,
        VALID,
    }

    public enum Field{
        FIRST_NAME,
        LAST_NAME,
        USERNAME,
        PASSWORD,
        PHONE_NUMBER,
    }

    private RegisterUserController reg;
    private User user = new User();
    private EnumMap<Field, RegisterUserController.TextSelect> mapToTextBox = new
            EnumMap<Field, RegisterUserController.TextSelect>(Field.class);

    public RegisterUserLogic()
    {
        mapToTextBox.put(Field.FIRST_NAME, RegisterUserController.TextSelect.T1);
        mapToTextBox.put(Field.LAST_NAME, RegisterUserController.TextSelect.T2);
        mapToTextBox.put(Field.PASSWORD, RegisterUserController.TextSelect.T3);
        mapToTextBox.put(Field.PHONE_NUMBER, RegisterUserController.TextSelect.T6);
        mapToTextBox.put(Field.USERNAME, RegisterUserController.TextSelect.T7);

        reg = SceneManager.getI().getController(SceneManager.Type.REGISTER_USER);
        user.serviceList = new Services();

        user.address = (String) reg.getCity().getValue();

        reg.getCity().valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue, String t, String t1) {
                user.address=t1;
            }
        });

        reg.getButton().setOnAction(e -> {
                if(validateAll()) {
                    //Add user settings
                    user.firstName = reg.getTextField(RegisterUserController.TextSelect.T1).getText();
                    user.lastName = reg.getTextField(RegisterUserController.TextSelect.T2).getText();
                    user.pass = reg.getTextField(RegisterUserController.TextSelect.T3).getText();
                    user.nr = reg.getTextField(RegisterUserController.TextSelect.T6).getText();
                    user.username = reg.getTextField(RegisterUserController.TextSelect.T7).getText();
                    user.type=reg.getUserType();
                    Register.registerFunction(user);
                }
        } );


        for (Object entry : reg.getArrMap().entrySet())
        {
            Map.Entry<Services.Type, CheckBox>entryCasted = (Map.Entry<Services.Type, CheckBox>) entry;
            entryCasted.getValue().setOnMouseClicked( e ->{
                if (entryCasted.getValue().isSelected())
                    user.serviceList.addService(entryCasted.getKey());
                else{
                    user.serviceList.removeService(entryCasted.getKey());
                }
                System.out.println(user.serviceList.getAssociateService());
            });
        }
    }

    public boolean validateAll(){
        boolean ans = true;
        for(Map.Entry<Field, RegisterUserController.TextSelect> e : mapToTextBox.entrySet()){
            ValidateReturn ret = checkStringBasedOnType(reg.getTextField(e.getValue()).getText(), e.getKey());
            if(ret != ValidateReturn.VALID){
                ans = false;
            }
            reg.setTextLabel(e.getValue(), RegisterUserController.getTextErrorLabel(ret));
        }
        // Also check password match
        if(reg.getTextField(RegisterUserController.TextSelect.T3).getText().compareTo(
                reg.getTextField(RegisterUserController.TextSelect.T4).getText()) == 0){
            reg.setTextLabel(RegisterUserController.TextSelect.T4, "");
        }else {
            reg.setTextLabel(RegisterUserController.TextSelect.T4, "Passwords don't match.");
            ans = false;
        }
        return ans;
    }

    //helper function
    public static ValidateReturn checkStringBasedOnType(String str, Field t){
        switch(t){
            case FIRST_NAME:
            case LAST_NAME:
                return validateName(str);
            case PASSWORD:
                return validatePass(str);
            case USERNAME:
                return validateUsername(str);
            case PHONE_NUMBER:
                return validatePhone(str);
            default:
                return ValidateReturn.INVALID_CHARACTERS;
        }
    }

    private static ValidateReturn validateName(String str){
        if(str.isBlank()){
            return ValidateReturn.EMPTY;
        }
        if(!str.matches("[-A-Za-z ]*")){
            return ValidateReturn.INVALID_CHARACTERS;
        }
        if(str.length() >= 50){
            return ValidateReturn.TOO_LONG;
        }
        return ValidateReturn.VALID;
    }

    private static ValidateReturn validatePass(String str){
        if(str.isBlank()){
            return ValidateReturn.EMPTY;
        }
        if(!str.matches("[-_$!?,.A-Za-z0-9]*")){
            return ValidateReturn.INVALID_CHARACTERS;
        }
        if(str.length() >= 30){
            return ValidateReturn.TOO_LONG;
        }
        if(str.length() < 6){
            return ValidateReturn.TOO_SHORT;
        }
        return ValidateReturn.VALID;
    }

    private static ValidateReturn validateUsername(String str){
        if(str.isBlank()){
            return ValidateReturn.EMPTY;
        }
        if(!str.matches("[a-zA-Z0-9_.]*")){
            return ValidateReturn.INVALID_CHARACTERS;
        }
        if(str.length() >= 30){
            return ValidateReturn.TOO_LONG;
        }
        if(str.length() < 5){
            return ValidateReturn.TOO_SHORT;
        }
        return ValidateReturn.VALID;
    }

    private static ValidateReturn validateAddress(String str){
        if(str.isBlank()){
            return ValidateReturn.EMPTY;
        }
        if(!str.matches("[a-zA-Z0-9]*")){
            return ValidateReturn.INVALID_CHARACTERS;
        }
        if(str.length() >= 50){
            return ValidateReturn.TOO_LONG;
        }
        return ValidateReturn.VALID;
    }

    private static ValidateReturn validatePhone(String str){
        if(str.isBlank()){
            return ValidateReturn.EMPTY;
        }
        if(!str.matches("[+0-9]*")){
            return ValidateReturn.INVALID_CHARACTERS;
        }
        if(str.length() >= 12){
            return ValidateReturn.TOO_LONG;
        }
        if(str.length() <= 9){
            return ValidateReturn.TOO_SHORT;
        }
        return ValidateReturn.VALID;
    }
}

