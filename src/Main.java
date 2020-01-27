import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaxHeight(700);
        primaryStage.setMinHeight(700);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinWidth(1000);
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("Main.fxml"));
            primaryStage.setTitle("Logical Gate");
            primaryStage.setScene(new Scene(root,1000,700));
            primaryStage.show();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
