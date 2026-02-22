package app;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        SceneManager.getInstance().setStage(stage);
        SceneManager.getInstance().setScene(Paths.MENU);

        stage.setTitle("hello world!");
        stage.setResizable(false);
        stage.show();
    }
}
