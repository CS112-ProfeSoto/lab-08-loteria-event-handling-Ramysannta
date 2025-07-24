package cs112.lab08;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/cs112/lab08/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 500);
        stage.setTitle("Ramysannta's Loteria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
