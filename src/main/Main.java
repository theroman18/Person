package main;

import com.sun.javafx.runtime.VersionInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Roman-Desktop
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        String runtimeVersion = VersionInfo.getRuntimeVersion();
        System.out.println("runtimeVersion = " + runtimeVersion);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view.fxml"));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Persons");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
