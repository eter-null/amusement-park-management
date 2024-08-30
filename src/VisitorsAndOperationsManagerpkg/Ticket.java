package VisitorsAndOperationsManagerpkg;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Ticket implements Serializable {
    private String ticketType;
    private final int ticketId;
    private double itemCost;
    private final LocalDate dateOfPurchase;
    private LocalDate ticketValidDate;
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Ticket(String ticketType, int ticketId, String itemName, double itemCost, LocalDate dateOfPurchase, LocalDate ticketValidDate) {
        this.ticketType = ticketType;
        this.ticketId = ticketId;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.dateOfPurchase = dateOfPurchase;
        this.ticketValidDate = ticketValidDate;

    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketId() {
        return ticketId;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public LocalDate getTicketValidDate() {
        return ticketValidDate;
    }

    public void setTicketValidDate(LocalDate ticketValidDate) {
        this.ticketValidDate = ticketValidDate;
    }

    @Override
    public String toString() {
        return ("Ticket Name: " + this.itemName + " , Ticket type: " + this.ticketType + " , Ticket ID: " +
                this.ticketId + " , Ticket Cost: " + this.itemCost + " , Date of Purchase: " + this.dateOfPurchase +
                " , Ticket Valid til: " + this.ticketValidDate
                );
    }
}
