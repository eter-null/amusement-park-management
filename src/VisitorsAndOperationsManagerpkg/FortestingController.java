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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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

        // Step 1: Read existing users from the file
        ArrayList<Visitors> existingUsers = new ArrayList<>();
        File f = new File("D:\\IUB\\6th Semester\\csc305\\Group Project\\Prototype\\Visitors&OperationManager\\VisitorsObjs.bin");

        if (f.exists()) {
            try (FileInputStream fis = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                while (true) {
                    try {
                        Visitors user = (Visitors) ois.readObject();
                        existingUsers.add(user);
                    } catch (Exception e) {
                        break; // End of file reached
                    }
                }
            } catch (IOException ex) {
                outputtest.appendText("Error reading file: " + ex.getMessage() + "\n");
            }
        }

        // Step 2: Create a new user
        ArrayList<Object> emptyCartList = new ArrayList<>(); // Create empty ArrayList for cart
        Cart newCart = new Cart(emptyCartList); // Create cart with empty ArrayList
        LocalDate birthDate = LocalDate.of(2003, 2, 8); // February 8th, 2003

        Visitors newUser = new Visitors(
                "Mehzabeen",                    // firstName
                "Nisa",                  // lastName
                "nisa123",                 // userId
                "nisa123@gmail.com",       // email
                "nisa123",                 // password
                birthDate,                  // dateOfBirth
                "Female",                   // gender
                newCart                     // itemsCart
        );

        // Step 3: Add new user to the list
        existingUsers.add(newUser);

        // Step 4: Write all users back to the file
        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Visitors user : existingUsers) {
                oos.writeObject(user);
            }
            outputtest.appendText("New user added successfully!\n");

        } catch (IOException ex) {
            outputtest.appendText("Error writing to file: " + ex.getMessage() + "\n");
        }

        // Step 5: Display all users to verify
        outputtest.appendText("\nAll users in the file:\n");
        for (Visitors user : existingUsers) {
            outputtest.appendText("User: " + user.getFirstName() + " " + user.getLastName() +
                    " (" + user.getEmail() + ")\n");
        }
    }
    
}
