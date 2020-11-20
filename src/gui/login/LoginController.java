package gui.login;

import gui.Main;
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
        
    }

//    public void registerButtonClicked(ActionEvent actionEvent){ Main.getI().changeSceneOnMainStage(SceneManager.Type.REGISTER_USER);
//
//    }
}
