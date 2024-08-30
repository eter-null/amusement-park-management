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
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


public class CartSceneController implements Initializable {


    public Visitors loggedVisitor;

    @FXML
    private Text cartEmptyText;
    @FXML
    private TableView<Object> cartItemsTable;
    @FXML
    private Text subtotalShow;
    @FXML
    private TableColumn<Object, String> itemNameColumn;
    @FXML
    private TableColumn<Object, Double> itemCostColumn;

    // initializes visitor
    public void initVisitor(Visitors visitor) throws IllegalStateException {
        loggedVisitor = visitor;

        if (loggedVisitor.getItemsCart().getCartItemsNumber() == 0) {
            cartEmptyText.setVisible(true);
            cartItemsTable.setVisible(false);
        } else{
            cartEmptyText.setVisible(false);
            cartItemsTable.setVisible(true);

            // using observable list because it updates changes made of things within the list in real time
            ObservableList<Object> items = FXCollections.observableArrayList(loggedVisitor.getItemsCart().getVisitorCart());

            //set up the columns in the table

            itemNameColumn.setCellValueFactory(new PropertyValueFactory<Object,String>("itemName"));
            itemCostColumn.setCellValueFactory(new PropertyValueFactory<Object,Double>("itemCost"));

            cartItemsTable.setItems(items);

            updateSubtotal();
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void pressedCartGoBack(MouseEvent event) throws IOException {
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
    private void pressedCheckOut(MouseEvent event) {

        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        String filename = loggedVisitor.getUserId();

        try {
            file = new File(filename+".bin");
            if(file.exists()){
                fos = new FileOutputStream(file,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
            }
            for (Object items: loggedVisitor.getItemsCart().getVisitorCart()){
                oos.writeObject(items);
            }


        } catch (IOException ex) {
            Logger.getLogger(CartSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(CartSceneController.class.getName()).log(Level.SEVERE, null, ex);


            }


        }

    }

    @FXML
    private void pressedDeleteItem(MouseEvent event) {

        // sets cart is empty visible/invisible depending on the if statement (just for visual purposes)
        if ((loggedVisitor.getItemsCart().getVisitorCart().size()-1 ==0) || (loggedVisitor.getItemsCart().getVisitorCart().size() ==0)){
            cartItemsTable.setVisible(false);
            cartEmptyText.setVisible(true);
        }
        else {
            cartItemsTable.setVisible(true);
            cartEmptyText.setVisible(false);
        }



        // observable list is added instead of deleting from tableview because of future implementation that uses this list in future
        // i also realised i can do it without observable list by deleting from tableview but i already implemented the codes on multiple files
        ObservableList<Object> items;
        // load tableview items and delete selected one
        items = cartItemsTable.getItems();
        Object selectedRowObj = cartItemsTable.getSelectionModel().getSelectedItem();

        //remove from table and actual loggedvisitor cart
        try {

            items.remove(selectedRowObj);
            loggedVisitor.getItemsCart().getVisitorCart().remove(selectedRowObj);

        } catch (RuntimeException re){}
        updateSubtotal();

    }


    public void updateSubtotal(){

        // update subtotal method where it adds all item cost from tableview list and is called when you delete an item so we
        // can see the latest subtotal always

        double total = 0.0;
        try {
            for(Object item: cartItemsTable.getItems()){
                if (item instanceof Ticket) {
                    total = total + ((Ticket) item).getItemCost();
                }
                if (item instanceof Table) {
                    total = total + ((Table) item).getItemCost();
                }
                if (item instanceof HotelRoom) {
                    total = total + ((HotelRoom) item).getItemCost();
                }
                if (item instanceof SpecialTicket) {
                    total = total + ((SpecialTicket) item).getItemCost();
                }
                if (item instanceof Merchandise) {
                    total = total + ((Merchandise) item).getItemCost();
                }
            }
        } catch (RuntimeException e) {
            total = 0.00;
        }
        // to round the double to two decimal places
        String total2sigfig = String.format("%.2f", total);
        subtotalShow.setText(total2sigfig + " USD");
    }

    @FXML
    private void pressedViewPurchases(MouseEvent event) throws IOException {
        // Declares the parent scene for new class
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewPurchasesScene.fxml"));

        Parent viewPurchaseParent = loader.load();

        Scene viewPurchaseScene = new Scene(viewPurchaseParent);

        //access the controller
        ViewPurchasesSceneController controller = loader.getController();

        // Initializing method for the next scene controller to get the item
        controller.initVisitor(loggedVisitor);

        // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
        Stage viewPurchaseStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        viewPurchaseStage.setScene(viewPurchaseScene);
        viewPurchaseStage.show();


    }

}
