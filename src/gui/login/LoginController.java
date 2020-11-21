package gui.login;

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

        String user_name = username.getText();
        String pw = password.getText();

        User user = LoginDB.checkLoginData(user_name, pw);

        if(user != null){
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setUserName(user.username);
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setUserAddress(user.address);
            ((UserController)SceneManager.getI().getController(SceneManager.Type.USER)).setCityLabel(user.address);
            Main.getI().changeSceneOnMainStage(SceneManager.Type.USER);
        }
    }



    public void registerButtonClicked(ActionEvent actionEvent){
          Main.getI().changeSceneOnMainStage(SceneManager.Type.REGISTER_USER);
    }
}
