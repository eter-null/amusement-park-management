/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HotelRoomReservationSceneController implements Initializable {

    @FXML
    private TableView<HotelRoom> hotelRoomsTableView;
    @FXML
    private TableColumn<HotelRoom, Integer> roomNumColumn;
    @FXML
    private TableColumn<HotelRoom, Integer> bedsColumn;
    @FXML
    private TableColumn<HotelRoom, Double> reservationCostColumn;
    @FXML
    private DatePicker checkInDatePicker;
    @FXML
    private DatePicker checkOutDatePicker;

    private Visitors loggedVisitor;

    public ArrayList<HotelRoom> hotelRoomArrayList;
    @FXML
    private Text notSelectedError;



    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // read from dummy room file and load into arraylist
        hotelRoomArrayList = new ArrayList<>();

        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            file = new File("HotelRoomObjs.bin");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            HotelRoom room;
            try{
                while(true){
                    room = (HotelRoom) ois.readObject();
                    hotelRoomArrayList.add(room);

                }
            }//end of nested try
            catch(Exception e){
                //
            }//nested catch
        } catch (IOException ex) { }
        finally {
            try {

                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }


        // cast arraylist to observable list
        ObservableList<HotelRoom> hotelRoomObsList = FXCollections.observableArrayList(hotelRoomArrayList);

        //set up the columns in the table
        roomNumColumn.setCellValueFactory(new PropertyValueFactory<HotelRoom,Integer>("roomNumber"));
        bedsColumn.setCellValueFactory(new PropertyValueFactory<HotelRoom,Integer>("numberOfBeds"));
        reservationCostColumn.setCellValueFactory(new PropertyValueFactory<HotelRoom,Double>("itemCost"));

        // then put the observable list in tableview
        hotelRoomsTableView.setItems(hotelRoomObsList);



    }



    @FXML
    private void pressedBookTable(MouseEvent event) {
        // load tableview items
        HotelRoom selectedRowObj = hotelRoomsTableView.getSelectionModel().getSelectedItem();


        // checks if relevant data is selected or not
        if ((checkInDatePicker.getValue() != null) && (checkOutDatePicker.getValue() != null)) {

            notSelectedError.setVisible(false);

            // checks if its reserved or not
            if (selectedRowObj.isReserved() == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hotel Room Reservation");
                alert.setHeaderText("Room Already Reserved");
                alert.setContentText("The selected room you're trying to book has already been reserved please try another room.");
                alert.showAndWait();
            } else {
                // if not reserved updates the status to reserved in arraylist and adds the item to cart
                selectedRowObj.setReserved(true);
                selectedRowObj.setCheckIn(checkInDatePicker.getValue());
                selectedRowObj.setCheckOut(checkOutDatePicker.getValue());
                selectedRowObj.setItemCost();

                //adds the booked room to cart
                loggedVisitor.getItemsCart().getVisitorCart().add(selectedRowObj);


                // shows successful booking of table
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Table Reservation");
                alert.setHeaderText("Table Successfully Reserved");
                alert.setContentText("You have booked room number " + selectedRowObj.getRoomNumber()
                        + " containing " + selectedRowObj.getNumberOfBeds() + " king size bed(s)"
                        + " checked in at " + selectedRowObj.getCheckIn()
                        + " and checked out at " + selectedRowObj.getCheckOut() + ".");
                alert.showAndWait();

            }
        } else {
            // sets no info selected to true to show user the input error
            notSelectedError.setVisible(true);

        }

//        for (HotelRoom h: hotelRoomArrayList){
//            System.out.println(h.toString());
//
//

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
}




