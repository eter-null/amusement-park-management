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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TableBookingSceneController implements Initializable {

    @FXML
    private TableView<Table> availableTableView;
    @FXML
    private DatePicker reservationDatePicker;
    @FXML
    private ComboBox<String> reservationTimeComboBox;

    private Visitors loggedVisitor;

    public ArrayList<Table> tableArrayList;

    public ArrayList<Table> arrayListToShow;
    @FXML
    private TableColumn<Table, String> tableNumColumn;
    @FXML
    private TableColumn<Table, Integer> chairsColumn;
    @FXML
    private TableColumn<Table, Double> reservationCostColumn;
    @FXML
    private Text notSelectedError;


    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reservationTimeComboBox.getItems().addAll("8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM",
                "4 PM", "5 PM", "6 PM", "7 PM", "8 PM", "9 PM", "10 PM", "11 PM"
                );

        // read from dummy table file and load into arraylist
        tableArrayList = new ArrayList<>();

        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            file = new File("TableObjs.bin");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Table table;
            try{
                while(true){
                    table = (Table)ois.readObject();
                    tableArrayList.add(table);

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
    private void pressedViewTables(MouseEvent event) {

        ObservableList<Table> tableObsList = FXCollections.observableArrayList(tableArrayList);

        //set up the columns in the table

        tableNumColumn.setCellValueFactory(new PropertyValueFactory<Table,String>("tableNumber"));
        chairsColumn.setCellValueFactory(new PropertyValueFactory<Table,Integer>("chairs"));
        reservationCostColumn.setCellValueFactory(new PropertyValueFactory<Table,Double>("itemCost"));


        availableTableView.setItems(tableObsList);
    }

    @FXML
    private void pressedBookTable(MouseEvent event) {
        // load tableview items
        Table selectedRowObj = availableTableView.getSelectionModel().getSelectedItem();
        int selectedRowObjNum = availableTableView.getSelectionModel().getSelectedIndex();


        // checks if relevant data is selected or not
        if ((reservationDatePicker.getValue() != null) && (reservationTimeComboBox.getValue() != null)){

            notSelectedError.setVisible(false);

            // checks if its reserved or not
            if (selectedRowObj.isReserved() == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Table Reservation");
                alert.setHeaderText("Table Already Reserved");
                alert.setContentText("The selected table you're trying to book has already been reserved please try another table.");
                alert.showAndWait();
            }
            else {
                // if not reserved updates the status to reserved in arraylist and adds the item to cart
                tableArrayList.get(selectedRowObjNum).setReserved(true);
                selectedRowObj.setReservationDate(reservationDatePicker.getValue());
                selectedRowObj.setReservationTime(reservationTimeComboBox.getSelectionModel().getSelectedItem());

                //adds the booked table to cart
                loggedVisitor.getItemsCart().getVisitorCart().add(selectedRowObj);



                // shows successful booking of table
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Table Reservation");
                alert.setHeaderText("Table Successfully Reserved");
                alert.setContentText("You have booked table number " + selectedRowObj.getTableNumber() + " on " + selectedRowObj.getReservationDate()
                        + " at " + selectedRowObj.getReservationTime() + " for " + selectedRowObj.getChairs() + " people.");
                alert.showAndWait();

            }
        }

        else{
            // sets no info selected to true to show user the input error
            notSelectedError.setVisible(true);
        }

//        for (Table t: tableArrayList){
//            System.out.println(t.toString());
//        }

    }





}
