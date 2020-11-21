package gui.login;

import database.Register;
import javafx.scene.control.CheckBox;
import gui.sceneUtilities.SceneManager;
import gui.user.User;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

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
        ADDRESS,
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
        mapToTextBox.put(Field.ADDRESS, RegisterUserController.TextSelect.T5);
        mapToTextBox.put(Field.PHONE_NUMBER, RegisterUserController.TextSelect.T6);
        mapToTextBox.put(Field.USERNAME, RegisterUserController.TextSelect.T7);

        reg = SceneManager.getI().getController(SceneManager.Type.REGISTER_USER);
        user.serviceList = new Services();

        reg.getButton().setOnAction(e -> {
                if(validateAll()) {
                    //Add user settings
                    user.firstName = reg.getTextField(RegisterUserController.TextSelect.T1).getText();
                    user.lastName = reg.getTextField(RegisterUserController.TextSelect.T2).getText();
                    user.pass = reg.getTextField(RegisterUserController.TextSelect.T3).getText();
                    user.address = reg.getTextField(RegisterUserController.TextSelect.T5).getText();
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
            reg.setTextErrorLabel(e.getValue(), ret);
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

    /*
    public boolean validateAll() //calls all the validate methods
    {
        boolean ans = validateName();
        ans = validatePassword() && ans;
        ans = validateAddress() && ans;
        ans = validateNumber() && ans;
        ans = validateUsername() && ans;
        return ans;

    }


    private boolean validateText(TextSelect t)
    {
        String text = reg.getTextField(t).getText();
        if ( text.matches("[a-zA-Z]+") && text.length() <=15 ){
            reg.setTextLabel(t, "");
            return true;
        }
        else if(text.length()!=0){
            reg.setTextLabel(t, "name is too long or doesn't contain only letters");
            reg.setTextFieldEmpty(t);
            return false;
        }
        return false;
    }

    public boolean validateUsername()
    {
        String text = reg.getTextField(TextSelect.T7).getText();
        if(checkEmpty(TextSelect.T7))
        {
            return false;
        }
        if (!(text.matches("[a-zA-Z0-9_.]+"))  )
        {
            reg.setTextLabel(TextSelect.T7, "invalid characters");
            reg.setTextFieldEmpty(TextSelect.T7);
            return  false;
        }
        else if( (text.length() <=5 || text.length() >=15) )
        {
            reg.setTextLabel(TextSelect.T7, "invalid length");
            reg.setTextFieldEmpty(TextSelect.T7);
            return  false;
        }
        else
        {
            reg.setTextLabel(TextSelect.T7, "");
            user.username=text;
        }

        return true;
    }

    public boolean validateName() //this makes sure that the names and username contain only letters and is no longer that 15 chars
    {
        TextField tFirstName = reg.getTextField(TextSelect.T1);
        TextField tLastName = reg.getTextField(TextSelect.T2);

        checkEmpty(TextSelect.T1);
        checkEmpty(TextSelect.T2);

        boolean res=true;
        if(validateText( TextSelect.T1 ) )
        {
            user.firstName= reg.getTextField(TextSelect.T1).getText(); //if the user inputted a valid name we store it in out stucture;
        }
        else res=false;
        if ( validateText(TextSelect.T2) )
        {
            user.lastName= reg.getTextField(TextSelect.T2).getText(); //if the user inputted a valid name we store it in out stucture;
        }

        else res=false;
        return res;

    }

    private boolean checkEmpty(TextSelect it)
    {
        TextField t = reg.getTextField(it);
        if (t.getText().length()==0)
        {
            reg.setTextLabel(it, "field cannot be empty");
            return true;
        }
        return false;
    }

    public boolean validatePassword()
    {
        TextField pas1 = reg.getTextField(TextSelect.T3);

        if( !( pas1.getText().matches("[a-zA-Z0-9]+") ) && pas1.getText().length()!=0 )
        {
            reg.setTextLabel(TextSelect.T3, "olny letters and numbers allowed");
        }
        else if( !(pas1.getText().length()>=5) && pas1.getText().length()!=0){
            reg.setTextLabel(TextSelect.T3, "pass too short");
        }
        else if( !(pas1.getText().length()<=20 && pas1.getText().length()!=0) ){
            reg.setTextLabel(TextSelect.T3, "pass too long");
        }
        else if(pas1.getText().length()!=0) {
            reg.setTextLabel(TextSelect.T3, "");
            TextField pas2 = reg.getTextField(TextSelect.T4);
            if( pas2.getText().compareTo(pas1.getText())==0 )
            {
                user.pass=pas2.getText();
                reg.setTextLabel(TextSelect.T4, "");
                return true;
            }
            else reg.setTextLabel(TextSelect.T4, "passwords dont match");
        }
        else checkEmpty(TextSelect.T3);
        return false;
    }

    public boolean validateAddress()
    {
        TextField addr  = reg.getTextField(TextSelect.T5);
        if(checkEmpty(TextSelect.T5))
        {
            return false;
        }
        if (addr.getText().matches("[a-zA-Z0-9]+") && addr.getText().length()<=30)
        {
            user.address=addr.getText();
            return true;
        }
        else reg.setTextLabel(TextSelect.T5, "invalid address");
        return false;
    }

    public boolean validateNumber()
    {
        TextField n  = reg.getTextField(TextSelect.T6);
        if(checkEmpty(TextSelect.T6))
        {
            return false;
        }
        if (n.getText().matches("[0-9]+") && n.getText().length()==10)
        {
            user.nr=n.getText();
            return true;
        }
        else reg.setTextLabel(TextSelect.T6, "invalid phone nr");
        return false;
    }
    */
    public static ValidateReturn checkStringBasedOnType(String str, Field t){
        switch(t){
            case FIRST_NAME:
            case LAST_NAME:
                return validateName(str);
            case ADDRESS:
                return validateAddress(str);
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

