package gui.login;

import database.Register;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import gui.sceneUtilities.SceneManager;
import gui.login.Services;
import gui.user.User;


import java.util.Map;

public class RegisterUserLogic {

    private RegisterUserController reg;
    //private Services offeredJobs;
    private User user = new User();


    public RegisterUserLogic()
    {
        reg = SceneManager.getI().getController(SceneManager.Type.REGISTER_USER);
        user.offeredJobs = new Services();

        reg.getButton().setOnAction(e -> {System.out.println(validateAll());
            user.type=reg.getUserType();
            if(validateAll())
                Register.registerFunction(user);
        } );


        for (Object entry : reg.getArrMap().entrySet())
        {
            Map.Entry<Services.Type, CheckBox>entryCasted = (Map.Entry<Services.Type, CheckBox>) entry;
            entryCasted.getValue().setOnMouseClicked( e ->{
                if (entryCasted.getValue().isSelected())
                    user.offeredJobs.addService(entryCasted.getKey());
                else{
                    user.offeredJobs.removeService(entryCasted.getKey());
                }
                System.out.println(user.offeredJobs.getAssociateService());
            });
        }


    }

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
}

