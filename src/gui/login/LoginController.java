package gui.login;

import database.GetCityUser;
import database.LoginDB;
import gui.Main;
import gui.user.User;
import gui.user.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import gui.sceneUtilities.SceneManager;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button registerButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void loginButtonClicked(ActionEvent actionEvent) {

        String user = username.getText();
        String pw = password.getText();

        boolean check = LoginDB.checkLoginData(user, pw);
        User userInst = GetCityUser.getUserByUsername(user);

        if(check){
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setUserName(userInst.username);
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setCityLabel(userInst.address);
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setRoleLabel(getTypeAsString(userInst.type));

            System.out.println(userInst.address + " " + getTypeAsString(userInst.type));

            Main.getI().changeSceneOnMainStage(SceneManager.Type.USER);

        }

        System.out.println(check);
    }

    public String getTypeAsString(User.Type type){
        if(type == User.Type.CLIENT){
            return "Client";
        }
        else{
            return "Provider";
        }
    }

    public void registerButtonClicked(ActionEvent actionEvent){
          Main.getI().changeSceneOnMainStage(SceneManager.Type.REGISTER_USER);


    }
}
