/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import student.app.controllers.MainController;

/**
 *
 * @author LinnLinn
 */
public class StudentApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // String title = "Hello JavaFx Application from start Method";
        System.out.println("Before FXML Loading....");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/app/view/main.fxml"));
        Parent root = loader.load();
        System.out.println("After FXML Loading....");

      //    MainController controller = loader.getController();
      //    controller.setTitle(title);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Student App");
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
       

    }

}
