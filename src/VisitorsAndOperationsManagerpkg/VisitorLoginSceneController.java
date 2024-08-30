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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author White Wolf
 */
public class VisitorLoginSceneController implements Initializable {

    @FXML
    private TextField userIdTextField;
    @FXML
    private Text incorrentInfoNotification;
    @FXML
    private PasswordField enteredPasswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    private void clickedLoginButton(MouseEvent event) throws IOException {

        if (verification() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("VisitorDashboard.fxml"));

            Parent visiDSceneParent = loader.load();

            Scene visiDScene = new Scene(visiDSceneParent);

            //access the controller
            VisitorDashboardController controller = loader.getController();

            // Initializing method for the next scene controller to get the item
            controller.initVisitorUpdatedCart(verification());

            // Declares the Stage (container to hold scene) gets all stage items (scene items) and sets the stage as the review scene
            Stage vStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            vStage.setScene(visiDScene);
            vStage.show();



        }





    }


    @FXML
    private void clickedGoBack(MouseEvent event) throws IOException {
        Parent loginsceneParent = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene loginscene = new Scene(loginsceneParent);

        Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        loginStage.setScene(loginscene);
        loginStage.show();

    }


    public Visitors verification(){

        incorrentInfoNotification.setVisible(false);

        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            file = new File("VisitorsObjs.bin");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Visitors visitor;
            try{
                while(true){
                    visitor = (Visitors) ois.readObject();
                    //System.out.println(visitor.getUserId() + visitor.getPassword());

                    // verify the id and password parsing through the visitors info

                    if ((visitor.getUserId().equals(userIdTextField.getText())) && (visitor.getPassword().equals(enteredPasswordField.getText()))) {

                        System.out.println("verification success");

                        // if verification successful load the visitor's dashboard with the visitor's info
                        return visitor;


                    }
                    else {
                        incorrentInfoNotification.setVisible(true);
                    }



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


    return null;


    }



    
}
