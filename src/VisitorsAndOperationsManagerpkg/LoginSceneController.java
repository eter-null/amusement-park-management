/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    


    @FXML
    private void clickedLoginVisitors(MouseEvent event) throws IOException {
        Parent vloginParent = FXMLLoader.load(getClass().getResource("VisitorLoginScene.fxml"));
        Scene vlogin = new Scene(vloginParent);

        Stage vloginStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        vloginStage.setScene(vlogin);
        vloginStage.show();

    }

    @FXML
    private void clickedLoginAdmin(MouseEvent event) throws IOException {
        Parent adminloginParent = FXMLLoader.load(getClass().getResource("AdminLoginScene.fxml"));
        Scene adminlogin = new Scene(adminloginParent);

        Stage adminStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        adminStage.setScene(adminlogin);
        adminStage.show();




    }
    
}
