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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author White Wolf
 */
public class FortestingController implements Initializable {
    @FXML
    private TextArea outputtest;
    @FXML
    private Text testtext;

    /**
     * Initializes the controller class.
     */



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        dummyRides = new ArrayList<Ride>();
//        dummyRides.add(new Ride("Roller Coaster", 10, LocalTime.of(0,10)));
//        dummyRides.add(new Ride("Merry-Go-Round", 6, LocalTime.of(0,5)));


        ArrayList<Object> arr = new ArrayList<Object>();

        arr.add(new Table("Table Booking" ,LocalDate.now(), "12 AM", 7,2,20.00, true));




    }    

    @FXML
    private void testthis(MouseEvent event) {
        outputtest.setText(null);
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("TableObjs.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Table r;
            try{
                outputtest.setText(null);
                while(true){
                    r = (Table)ois.readObject();
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    outputtest.appendText(r.toString() + "\n");
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
