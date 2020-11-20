package gui;

import gui.sceneUtilities.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import gui.sceneUtilities.SceneManager.Type;

public class Main extends Application {

    private Stage stage;
    private static Main instance;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        instance = this;

        // Initialize all scenes
        SceneManager.getI();

        //Choose first appearing scene
        stage.setScene(SceneManager.getI().getScene(Type.LOGIN));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    public static Main getI() {
        return instance;
    }

    public void changeSceneOnMainStage(Type scene){
        stage.setScene(SceneManager.getI().getScene(scene));
    }
}
