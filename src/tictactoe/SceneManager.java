package tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;

public class SceneManager {
    private static SceneManager sm = null;
    private static HashMap<String, Scene> sceneHashMap = new HashMap<>();
    private static double width = 450;
    private static double height = 450;

    private SceneManager() {}

    public static Scene getScene(String name) throws Exception
    {
        return getScene(name, width, height);
    }

    public static Scene getScene(String name, double width, double height) throws Exception
    {
        if (sm == null) sm = new SceneManager();
        if (!sceneHashMap.containsKey(name)) {
            FXMLLoader loader = new FXMLLoader(sm.getClass().getResource(name + ".fxml"));
            Parent node = loader.load();
            Controller controller = loader.getController();
            Scene scene = new Scene(node, width, height);
            scene.setUserData(controller);
            sceneHashMap.put(name, scene);
        }
        return sceneHashMap.get(name);
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        SceneManager.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        SceneManager.height = height;
    }
}
