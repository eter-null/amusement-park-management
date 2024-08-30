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
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author White Wolf
 */
public class QueueTimesSceneController implements Initializable {

    @FXML
    private ComboBox<String> ridesComboBox;
    @FXML
    private Text waitTime;

    public Visitors loggedVisitor;

    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ridesComboBox.getItems().addAll("Roller Coaster", "Drop Tower", "Ferris Wheel", "Pendulum Ride", "Sky Coaster", "Slingshot",
                "Bumper Car", "Carousel", "Ferris Wheel", "Teacups", "Frog Hopper"
        );
    }    



    @FXML
    private void pressedGoBackToDashboard(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VisitorDashboard.fxml"));

        Parent visiDSceneParent = loader.load();

        Scene visiDScene = new Scene(visiDSceneParent);

        //access the controller
        VisitorDashboardController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitorUpdatedCart(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage vStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        vStage.setScene(visiDScene);
        vStage.show();
    }

    @FXML
    private void pressedViewWaitTime(MouseEvent event) {
    }


}
