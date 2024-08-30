/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                //getClass().getResource("VisitorDashboard.fxml")
                //getClass().getResource("fortesting.fxml")
                getClass().getResource("LoginScene.fxml")
                //getClass().getResource("SetMerchandiseScene.fxml")
        );
        Scene scene = new Scene(root);
        stage.setTitle("BuchiLand");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
