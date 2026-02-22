package app;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * Singleton SceneManager for å holde styr på hvilken Scene
 * som vises til enhver tid.
 * <p> 
 * Klassen er offentlig tilgjengelig for hele applikasjonen
 * og gjør at alle controllers kan kalle på den for å endre
 * hvilken tilstand appen skal ha.
 * <p>
 * @param instance – Common SceneManager instance for application
 * @param stage    – Application window
 * 
 **/
public class SceneManager {
    private static SceneManager instance;
    private Stage stage;

    // privat contructor
    private SceneManager() {}

    public static SceneManager getInstance() {
        if (instance==null) { instance = new SceneManager(); }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setScene(String fxmlPath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
