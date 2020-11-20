package gui.sceneUtilities;

import gui.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;
import java.util.EnumMap;



/* FOR EACH NEW SCENE:
 *  1. Add a new enum variable
 *  2. Add a new entry in the map
 *  3. Use the public functions to access the scene / controller class
 */

public class SceneManager {

    public enum Type {
        LOGIN,
    }

    private static SceneManager instance = null;
    private EnumMap<Type, Pair<Scene, Object>> sceneMap;

    private SceneManager(){
        instance = this;
        sceneMap = new EnumMap<>(Type.class);

        // Add lines to have each enum covered
        sceneMap.put(Type.LOGIN, loadScene("/resources/login/login.fxml"));

        // Check that all enums have a loaded scene
        for(Type s : Type.values()) {
            if (!sceneMap.containsKey(s)) {
                throw new RuntimeException("Program does not load scene " + s.name());
            }
        }
    }

    public static SceneManager getI() {
        if(instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public Scene getScene(Type sceneKey){
        return sceneMap.get(sceneKey).getKey();
    }

    public <T> T getController(Type sceneKey){
        return (T)sceneMap.get(sceneKey).getValue();
    }

    public static Pair<Scene, Object> loadScene(String fxml){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Location " + fxml + " not loaded properly(FXML URL:" + loader.getLocation() + ")");
        }
        return new Pair<>(new Scene(root), loader.getController());
    }
}
