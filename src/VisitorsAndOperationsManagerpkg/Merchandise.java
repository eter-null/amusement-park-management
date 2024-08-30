package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;

public class Merchandise implements Serializable {

    private String itemName;
    private String itemType;
    private double itemCost;


    public Merchandise(String itemName, String itemType, double itemCost) {
        this.itemName = itemName + " " + itemType;
        this.itemType = itemType;
        this.itemCost = itemCost;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName + " " + this.itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    @Override
    public String toString() {
        return ("Merchandise Name: " + this.itemName + " , Merchandise Cost: " + this.itemCost);
    }



}
