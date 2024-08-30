package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

     private ArrayList<Object> visitorCart;
     private int cartSize = 0;

public void addItems(Object item){
    visitorCart.add(item);

}

// redundant method remove later
public int getCartItemsNumber() {

    cartSize = visitorCart.size();
    return cartSize;

}

    public Cart(ArrayList<Object> visitorCart) {
        this.visitorCart = visitorCart;
    }

    public void setVisitorCart(ArrayList<Object> visitorCart) {
        this.visitorCart = visitorCart;
    }

    public ArrayList<Object> getVisitorCart() {
        return visitorCart;
    }
}
