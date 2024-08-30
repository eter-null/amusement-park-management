package VisitorsAndOperationsManagerpkg;

import java.time.LocalDate;
import java.util.ArrayList;

public class Visitors extends User {

    private Cart itemsCart;


    public Visitors(String firstName, String lastName, String userId, String email, String password, LocalDate dateOfBirth, String gender, Cart itemsCart) {
        super(firstName, lastName, userId, email, password, dateOfBirth, gender);
        this.itemsCart = itemsCart;
    }

    public Cart getItemsCart() {
        return itemsCart;
    }

    public void setItemsCart(Cart itemsCart) {
        this.itemsCart = itemsCart;
    }
}
