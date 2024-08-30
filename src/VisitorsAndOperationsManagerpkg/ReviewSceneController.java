/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.management.InstanceNotFoundException;

/**
 * FXML Controller class
 *
 * @author White Wolf
 */
public class ReviewSceneController implements Initializable {

    @FXML
    private TextArea visitorComment;
    @FXML
    private ComboBox<String> itemsDrop;

    @FXML
    private Text oneStar;
    @FXML
    private Text twoStars;
    @FXML
    private Text threeStars;
    @FXML
    private Text fourStars;
    @FXML
    private Text fiveStars;
    private int totalStars = 0;

    private Visitors visitor;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        itemsDrop.getItems().addAll("Roller Coaster", "Drop Tower", "Ferris Wheel", "Pendulum Ride", "Sky Coaster", "Slingshot",
                "Bumper Car", "Carousel", "Ferris Wheel", "Teacups", "Frog Hopper"
        );

    }


    public void initVisitorId(Visitors reviewer){
        visitor = reviewer;
    }
    @FXML
    private void postReview(MouseEvent event) {

        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            file = new File("ReviewObjs.bin");
            if(file.exists()){
                fos = new FileOutputStream(file,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }
            Review userFeedback = new Review(visitor.getUserId(), itemsDrop.getSelectionModel().getSelectedItem(), totalStars, LocalDate.now(), visitorComment.getText());

            oos.writeObject(userFeedback);

        } catch (IOException ex) {
            Logger.getLogger(ReviewSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ReviewSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void pressedReviewGoBack(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VisitorDashboard.fxml"));

        Parent visiDSceneParent = loader.load();

        Scene visiDScene = new Scene(visiDSceneParent);

        //access the controller
        VisitorDashboardController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitorUpdatedCart(visitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage vDashStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        vDashStage.setScene(visiDScene);
        vDashStage.show();
    }



    @FXML
    private void clickOneStar(MouseEvent event) {
        oneStar.setText("★");
        twoStars.setText("☆");
        threeStars.setText("☆");
        fourStars.setText("☆");
        fiveStars.setText("☆");
        totalStars = 1;

    }

    @FXML
    private void clickTwoStar(MouseEvent event) {
        oneStar.setText("★");
        twoStars.setText("★");
        threeStars.setText("☆");
        fourStars.setText("☆");
        fiveStars.setText("☆");
        totalStars = 2;

    }

    @FXML
    private void clickThreeStar(MouseEvent event) {
        oneStar.setText("★");
        twoStars.setText("★");
        threeStars.setText("★");
        fourStars.setText("☆");
        fiveStars.setText("☆");
        totalStars = 3;

    }

    @FXML
    private void clickFourStar(MouseEvent event) {
        oneStar.setText("★");
        twoStars.setText("★");
        threeStars.setText("★");
        fourStars.setText("★");
        fiveStars.setText("☆");
        totalStars = 4;

    }

    @FXML
    private void clickFiveStar(MouseEvent event) {
        oneStar.setText("★");
        twoStars.setText("★");
        threeStars.setText("★");
        fourStars.setText("★");
        fiveStars.setText("★");
        totalStars = 5;

    }

    @FXML
    private void pressedViewReview(MouseEvent event) {
    }

    
}
