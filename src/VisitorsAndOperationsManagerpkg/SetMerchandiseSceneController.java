/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisitorsAndOperationsManagerpkg;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author White Wolf
 */
public class SetMerchandiseSceneController implements Initializable {

    @FXML
    private ComboBox<String> merchTypeComboBox;
    @FXML
    private TextField merchNameTextField;
    @FXML
    private TextField merchPriceTextField;
    @FXML
    private TableView<Merchandise> allMerchTableView;
    @FXML
    private TableColumn<Merchandise, String> merchNameColumn;
    @FXML
    private TableColumn<Merchandise, String> merchTypeColumn;
    @FXML
    private TableColumn<Merchandise, Double> merchCostColumn;

    public ArrayList<Merchandise> merchArrayList;
    @FXML
    private Text wrongCostValue;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        merchTypeComboBox.getItems().addAll("T-Shirt", "Bag", "Souvenir", "Mug", "Sweater", "Cap", "Jewelry", "Bag Tag", "Pants", "Toy",
                "Poster");
        
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



    @FXML
    private void pressedChangeMerch(MouseEvent event) {
        wrongCostValue.setVisible(false);

        int selectedRowObjIndex = allMerchTableView.getSelectionModel().getSelectedIndex();


        try {

            merchArrayList.get(selectedRowObjIndex).setItemName(merchNameTextField.getText());
            merchArrayList.get(selectedRowObjIndex).setItemType(merchTypeComboBox.getSelectionModel().getSelectedItem());
            merchArrayList.get(selectedRowObjIndex).setItemCost(Double.parseDouble(merchPriceTextField.getText()));


            // to refresh tableview with the updated arraylist (use this instead of observable list)
            allMerchTableView.refresh();

        }
        catch (IllegalStateException ise) {wrongCostValue.setVisible(true);}
        catch (RuntimeException re){}

    }

    @FXML
    private void pressedAddMerch(MouseEvent event) {
        wrongCostValue.setVisible(false);

        // using observable list because it updates changes made of things within the list in real time

        ObservableList<Merchandise> merchs;
        // load tableview items to observable list and delete selected one
        merchs = allMerchTableView.getItems();

        try {
            merchArrayList.add(new Merchandise(merchNameTextField.getText(), merchTypeComboBox.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(merchPriceTextField.getText())));
            merchs.add(new Merchandise(merchNameTextField.getText(), merchTypeComboBox.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(merchPriceTextField.getText())));
//            allMerchTableView.getItems().add(new Merchandise(merchNameTextField.getText(), merchTypeComboBox.getSelectionModel().getSelectedItem(),
//                    Double.parseDouble(merchPriceTextField.getText())));

        } catch (IllegalArgumentException iae){wrongCostValue.setVisible(true);}
        catch (RuntimeException re){};





    }

    @FXML
    private void pressedDeleteMerch(MouseEvent event) {

        for (Merchandise m : merchArrayList){
            m.toString();
        }

        // using observable list because it updates changes made of things within the list in real time

        ObservableList<Merchandise> merchs;
        // load tableview items to observable list and delete selected one
        merchs = allMerchTableView.getItems();
        Merchandise selectedRowObj = allMerchTableView.getSelectionModel().getSelectedItem();

        //remove from table
        try {

            //remove from tableview and arraylist
            merchArrayList.remove(selectedRowObj);
            merchs.remove(selectedRowObj);


        } catch (RuntimeException re){}

    }

    @FXML
    private void pressedUpdateMerch(MouseEvent event) {




        ObservableList<Merchandise> merchs = FXCollections.observableArrayList(merchArrayList);

        //set up the columns in the table

        merchNameColumn.setCellValueFactory(new PropertyValueFactory<Merchandise,String>("itemName"));
        merchTypeColumn.setCellValueFactory(new PropertyValueFactory<Merchandise,String>("itemType"));
        merchCostColumn.setCellValueFactory(new PropertyValueFactory<Merchandise,Double>("itemCost"));

        allMerchTableView.setItems(merchs);







    }



    @FXML
    private void pressedGoBack(MouseEvent event) throws IOException {

        // deletes current file and loads the update merch arraylist to keep the file updated

        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;


        try {
            file = new File("MerchandiseObjs.bin");
            // if file exist delte it and write updated merch list
            if(file.exists()){
                file.delete();
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                for (Merchandise merch: merchArrayList){
                    oos.writeObject(merch);
                }
            }
            // else state just in case file doesnt exist
            else{
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                for (Merchandise merch: merchArrayList){
                    oos.writeObject(merch);
                }
            }




        } catch (IOException ex) {
            Logger.getLogger(SetMerchandiseSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SetMerchandiseSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




        Parent opmanagerParent = FXMLLoader.load(getClass().getResource("OperationsManagerScene.fxml"));
        Scene opmanagerscene = new Scene(opmanagerParent);

        Stage opmanagerStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        opmanagerStage.setScene(opmanagerscene);
        opmanagerStage.show();

    }


}
