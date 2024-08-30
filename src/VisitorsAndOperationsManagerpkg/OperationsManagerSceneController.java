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


public class OperationsManagerSceneController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pressedSetMerchandise(MouseEvent event) throws IOException {
        Parent merchParent = FXMLLoader.load(getClass().getResource("SetMerchandiseScene.fxml"));
        Scene merchscene = new Scene(merchParent);

        Stage merchStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        merchStage.setScene(merchscene);
        merchStage.show();


    }

    @FXML
    private void pressedSetSpecialEvent(MouseEvent event) {
    }

    @FXML
    private void pressedHirePerformers(MouseEvent event) {
    }

    @FXML
    private void pressedSetGames(MouseEvent event) {
    }

    @FXML
    private void pressedLogOut(MouseEvent event) throws IOException {
        Parent loginsceneParent = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginscene = new Scene(loginsceneParent);

        Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        loginStage.setScene(loginscene);
        loginStage.show();


    }
    
}
