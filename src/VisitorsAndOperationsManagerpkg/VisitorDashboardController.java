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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class VisitorDashboardController implements Initializable {

    @FXML
    private Text visitorName;
    public Visitors loggedVisitor;

    public int cartItemsNum;
    @FXML
    private Button cartButton;

    public void initVisitorUpdatedCart(Visitors visitor) {
        loggedVisitor = visitor;
        cartItemsNum = (loggedVisitor.getItemsCart().getVisitorCart().size());
        cartButton.setText("Cart (" + cartItemsNum +")");
        //System.out.println(loggedVisitor.getItemsCart().getCartItemsNumber());

        visitorName.setText(loggedVisitor.getFirstName() + " " + loggedVisitor.getLastName());


    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        //System.out.println(loggedVisitor.getItemsCart().getCartItemsNumber());
        //loggedVisitor.getItemsCart().addItems(new Table( "Table Booking",LocalDate.now(), 12, 7,2,20.00, true));

    }




    @FXML
    private void pressedBuyEntryTicket(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EntryTicketScene.fxml"));

        Parent entryTicketSceneParent = loader.load();

        Scene entryTicketScene = new Scene(entryTicketSceneParent);

        //access the controller
        EntryTicketSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage entryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        entryStage.setScene(entryTicketScene);
        entryStage.show();


    }

    @FXML
    private void pressedBuySpecialTicket(MouseEvent event) {
//        for (Object items : loggedVisitor.getItemsCart().getVisitorCart()){
//            System.out.println(items.toString());
//        }
        System.out.println(loggedVisitor.getItemsCart().getVisitorCart().size());


    }

    @FXML
    private void pressedHotelReservation(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HotelRoomReservationScene.fxml"));

        Parent hotelReservationSceneParent = loader.load();

        Scene hotelReservationScene = new Scene(hotelReservationSceneParent);

        //access the controller
        HotelRoomReservationSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage hoteReservationStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        hoteReservationStage.setScene(hotelReservationScene);
        hoteReservationStage.show();


    }

    @FXML
    private void pressedTableReservation(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TableBookingScene.fxml"));

        Parent tableBookingSceneParent = loader.load();

        Scene tableBookingScene = new Scene(tableBookingSceneParent);

        //access the controller
        TableBookingSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage tableBookingStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        tableBookingStage.setScene(tableBookingScene);
        tableBookingStage.show();

    }


    @FXML
    private void pressedCart(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CartScene.fxml"));

        Parent cartSceneParent = loader.load();

        Scene cartScene = new Scene(cartSceneParent);

        //access the controller
        CartSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage cartStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            cartStage.setScene(cartScene);
            cartStage.show();
        } catch (IllegalStateException ise){}


    }

    @FXML
    private void pressedReviewButton(MouseEvent event) throws IOException{

        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReviewScene.fxml"));


        Parent reviewSceneParent = loader.load();

        Scene reviewScene = new Scene(reviewSceneParent);

        //access the controller
        ReviewSceneController controller = loader.getController();

        //controller = new PersonViewSceneController();
        controller.initVisitorId(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage reviewStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        reviewStage.setScene(reviewScene);
        reviewStage.show();
    }

    @FXML
    private void pressedMerchandise(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BuyMerchandiseScene.fxml"));

        Parent buyMerchSceneParent = loader.load();

        Scene buyMerchScene = new Scene(buyMerchSceneParent);

        //access the controller
        BuyMerchandiseSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage buyMerchStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        buyMerchStage.setScene(buyMerchScene);
        buyMerchStage.show();
        
    }

    @FXML
    private void pressedLogOutButton(MouseEvent event) throws IOException {
        Parent loginsceneParent = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginscene = new Scene(loginsceneParent);

        Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        loginStage.setScene(loginscene);
        loginStage.show();


    }



}
