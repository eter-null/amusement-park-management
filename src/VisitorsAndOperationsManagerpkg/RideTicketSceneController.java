/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author White Wolf
 */
public class RideTicketSceneController implements Initializable {

    @FXML
    private ComboBox<String> adultRides;
    @FXML
    private ComboBox<String> childrenRides;
    public Visitors loggedVisitor;

    private double adultRidePrice;
    private double childRidePrice;
    @FXML
    private ComboBox<Integer> adultRideAmount;
    @FXML
    private ComboBox<Integer> childRideAmount;

    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adultRides.getItems().addAll("Roller Coaster", "Drop Tower", "Ferris Wheel", "Pendulum Ride", "Sky Coaster", "Slingshot");
        childrenRides.getItems().addAll("Bumper Car", "Carousel", "Ferris Wheel", "Teacups", "Frog Hopper");
        adultRideAmount.getItems().addAll(1,2,3,4,5);
        childRideAmount.getItems().addAll(1,2,3,4,5);

    }    

    @FXML
    private void pressedAddToCart(MouseEvent event) {
        Random rand = new Random();
        LocalDate dayOfPurchase = LocalDate.now();
        LocalDate tilValid = dayOfPurchase;

        try {
            for (int i = 0; i < adultRideAmount.getSelectionModel().getSelectedItem(); i++){
                loggedVisitor.getItemsCart().getVisitorCart().add(new Ticket("Adult Ride Ticket", rand.nextInt(90000) + 10000,    // random formula always get 5 digit ids
                        adultRides.getSelectionModel().getSelectedItem() + " Ride Ticket", selectedAdultTicketPrice(), dayOfPurchase, tilValid));
            }
        }     catch (RuntimeException re) {}

        try {
            for (int j = 0; j < childRideAmount.getSelectionModel().getSelectedItem(); j++) {
                loggedVisitor.getItemsCart().getVisitorCart().add(new Ticket("Children Ride Ticket", rand.nextInt(90000) + 10000,
                        childrenRides.getSelectionModel().getSelectedItem() + " Ride Ticket", selectedChildrenTicketPrice(), dayOfPurchase, tilValid));
            }

        } catch (RuntimeException re) {}

        // Shows items added to cart
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cart");
        alert.setHeaderText("Successfully added the following items to cart");
        alert.setContentText(adultRideAmount.getSelectionModel().getSelectedItem() + " " + adultRides.getSelectionModel().getSelectedItem() + " Ticket(s) and " +
                childRideAmount.getSelectionModel().getSelectedItem() + " " +  childrenRides.getSelectionModel().getSelectedItem() + " Ticket(s)");
        alert.showAndWait();

    }

    @FXML
    private void pressedGoBack(MouseEvent event) throws IOException {
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
    private void pressedViewQueueTime(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("QueueTimesScene.fxml"));

        Parent rideTicketSceneParent = loader.load();

        Scene queueScene = new Scene(rideTicketSceneParent);

        //access the controller
        QueueTimesSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage queueStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        queueStage.setScene(queueScene);
        queueStage.show();
    }

    // Sets the price of currently selected item for both combo boxes for ease of coding
    public double selectedAdultTicketPrice(){
        if (adultRides.getSelectionModel().getSelectedItem().equals("Roller Coaster") ){
            adultRidePrice = 20.00;
        }
        else if (adultRides.getSelectionModel().getSelectedItem().equals("Drop Tower") ) {
            adultRidePrice = 15.00;
        }
        else if (adultRides.getSelectionModel().getSelectedItem().equals("Ferris Wheel") ) {
            adultRidePrice = 10.00;
        }
        else if (adultRides.getSelectionModel().getSelectedItem().equals("Pendulum Ride") ) {
            adultRidePrice = 15.00;
        }
        else if (adultRides.getSelectionModel().getSelectedItem().equals("Sky Coaster") ) {
            adultRidePrice = 30.00;
        }
        else if (adultRides.getSelectionModel().getSelectedItem().equals("Slingshot") ) {
            adultRidePrice = 25.00;
        }
        return adultRidePrice;
    }

    public double selectedChildrenTicketPrice(){
        if (childrenRides.getSelectionModel().getSelectedItem().equals("Bumper Car")) {
            childRidePrice = 10.00;
        }
        else if (childrenRides.getSelectionModel().getSelectedItem().equals("Carousel")) {
            childRidePrice = 8.00;
        }
        else if (childrenRides.getSelectionModel().getSelectedItem().equals("Ferris Wheel")) {
            childRidePrice = 5.00;
        }
        else if (childrenRides.getSelectionModel().getSelectedItem().equals("Teacups")) {
            childRidePrice = 5.00;
        }
        else if (childrenRides.getSelectionModel().getSelectedItem().equals("Frog Hopper")) {
            childRidePrice = 15.00;
        }
        return childRidePrice;
    }


}
