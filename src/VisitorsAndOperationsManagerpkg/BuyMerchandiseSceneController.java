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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BuyMerchandiseSceneController implements Initializable {

    @FXML
    private TableView<Merchandise> allMerchTableView;
    @FXML
    private TableColumn<Merchandise, String> merchNameColumn;
    @FXML
    private TableColumn<Merchandise, String> merchTypeColumn;
    @FXML
    private TableColumn<Merchandise, Double> merchCostColumn;
    @FXML
    private ComboBox<Integer> merchQuantityComboBox;

    public Visitors loggedVisitor;

    public ArrayList<Merchandise> merchArrayList;
    @FXML
    private Text quantityNotSelected;



    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;


        merchArrayList = new ArrayList<>();


        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;


        // load merchandises from object file and loads them into arraylist

        try {
            file = new File("MerchandiseObjs.bin");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Merchandise merch;
            try{
                while(true){
                    merch = (Merchandise) ois.readObject();
                    merchArrayList.add(merch);

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


        ObservableList<Merchandise> merchs = FXCollections.observableArrayList(merchArrayList);

        //set up the columns in the table

        merchNameColumn.setCellValueFactory(new PropertyValueFactory<Merchandise,String>("itemName"));
        merchTypeColumn.setCellValueFactory(new PropertyValueFactory<Merchandise,String>("itemType"));
        merchCostColumn.setCellValueFactory(new PropertyValueFactory<Merchandise,Double>("itemCost"));

        allMerchTableView.setItems(merchs);





    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        merchQuantityComboBox.getItems().addAll(1,2,3,4,5,7,8,9,10);

    }    



    @FXML
    private void pressedAddToCart(MouseEvent event) {
        quantityNotSelected.setVisible(false);
        Merchandise selectedRowMerch = allMerchTableView.getSelectionModel().getSelectedItem();

        try {
            for (int i = 0; i < merchQuantityComboBox.getSelectionModel().getSelectedItem(); i++) {
                loggedVisitor.getItemsCart().getVisitorCart().add(selectedRowMerch);

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cart");
            alert.setHeaderText("Successfully added the following items to cart");
            alert.setContentText(merchQuantityComboBox.getSelectionModel().getSelectedItem() + " " + selectedRowMerch.getItemName()+".");
            alert.showAndWait();

        } catch (IllegalArgumentException iae){quantityNotSelected.setVisible(true);}




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
