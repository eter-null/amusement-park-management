/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import javafx.scene.control.DatePicker;



public class EntryTicketSceneController implements Initializable {

    private Visitors loggedVisitor;
    @FXML
    private ComboBox<Integer> adultEntryTicketBox;
    @FXML
    private ComboBox<Integer> childEntryTicketBox;
    @FXML
    private DatePicker visitingDatePicker;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adultEntryTicketBox.getItems().addAll(1,2,3,4,5);
        childEntryTicketBox.getItems().addAll(1,2,3,4,5);


    }

    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;

    }

    @FXML
    private void pressedAddToCart(MouseEvent event) {
        Random rand = new Random();
        LocalDate dayOfPurchase = visitingDatePicker.getValue();
        LocalDate tilValid = dayOfPurchase.plusDays(20);
        // Runtime exception occur when you select one forloop ticket and not the other since both for loops get executed
        // if you dont select first forloop ticket, 2nd forloop ticket doesnt get added because of exception.
        try {
            for (int i = 0; i < adultEntryTicketBox.getSelectionModel().getSelectedItem(); i++){
                loggedVisitor.getItemsCart().getVisitorCart().add(new Ticket("Adult", rand.nextInt(900000) + 100000,
                        "Adult Entry Ticket", 50.00, dayOfPurchase, tilValid));
            }
        }     catch (RuntimeException re) {}
        try {

                for (int j = 0; j < childEntryTicketBox.getSelectionModel().getSelectedItem(); j++) {
                    loggedVisitor.getItemsCart().getVisitorCart().add(new Ticket("Children", rand.nextInt(900000) + 100000,
                            "Children Entry Ticket", 20.00, dayOfPurchase, tilValid));
            }

        } catch (RuntimeException re) {}

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cart");
        alert.setHeaderText("Successfully added the following items to cart");
        alert.setContentText(adultEntryTicketBox.getSelectionModel().getSelectedItem() + " Adult Entry Ticket(s) and " +
                childEntryTicketBox.getSelectionModel().getSelectedItem() + " Children Entry Ticket(s)");
        alert.showAndWait();


    }

    @FXML
    private void pressedGoBack(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
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
    private void pressedBuyRideTicket(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RideTicketScene.fxml"));

        Parent rideTicketSceneParent = loader.load();

        Scene rideTicketScene = new Scene(rideTicketSceneParent);

        //access the controller
        RideTicketSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage rideStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        rideStage.setScene(rideTicketScene);
        rideStage.show();
    }




}
