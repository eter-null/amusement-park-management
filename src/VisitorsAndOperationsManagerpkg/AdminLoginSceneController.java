/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AdminLoginSceneController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pressedOperationsManager(MouseEvent event) throws IOException {
        Parent opmanagerParent = FXMLLoader.load(getClass().getResource("OperationsManagerScene.fxml"));
        Scene opmanagerscene = new Scene(opmanagerParent);

        Stage opmanagerStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        opmanagerStage.setScene(opmanagerscene);
        opmanagerStage.show();




    }

    @FXML
    private void pressedHrManager(MouseEvent event) throws IOException {


    }

    @FXML
    private void pressedRetailManager(MouseEvent event) {
    }

    @FXML
    private void pressedHotelManager(MouseEvent event) {
    }

    @FXML
    private void pressedGoBack(MouseEvent event) throws IOException {
        Parent loginsceneParent = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginscene = new Scene(loginsceneParent);

        Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        loginStage.setScene(loginscene);
        loginStage.show();


    }
    
}
