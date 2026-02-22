package app;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    // oppretter ny scene som blir startpunkt for appen
    // scenen lastes in i stage som er programmets vindu
    @Override
    public void start(Stage stage) throws IOException {

        // setter stage til automatisk opprettet stage av javafx
        // og scene til main menu fxml fil
        SceneManager.getInstance().setStage(stage);
        SceneManager.getInstance().setScene(Paths.MENU);

        stage.setTitle("hello world!");
        stage.setResizable(false);
        stage.show();
    }
}
