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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;





public class ViewPurchasesSceneController implements Initializable {

    @FXML
    private TextArea purchasesTextArea;

    public Visitors loggedVisitor;




    public void initVisitor(Visitors visitor) {
        loggedVisitor = visitor;
        

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
    private void pressedViewPurchases(MouseEvent event) {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;


        // loads userid file to view their past purchases
        String fileName;
        fileName = loggedVisitor.getUserId();

        try {
            file = new File(fileName+".bin");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Object items;
            try{
                while(true){
                    items = (Object) ois.readObject();
                    purchasesTextArea.appendText( items.toString() + "\n");



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


}
