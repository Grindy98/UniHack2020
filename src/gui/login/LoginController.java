package gui.login;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import gui.sceneUtilities.SceneManager;

public class LoginController {

    @FXML
    private Button registerButton;


    public void loginButtonClicked(ActionEvent actionEvent) {
//        Main.getI().changeSceneOnMainStage(SceneManager.Type.LOGIN);
    }

    public void registerButtonClicked(ActionEvent actionEvent){
          Main.getI().changeSceneOnMainStage(SceneManager.Type.REGISTER_USER);

    }
}
