package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;
import java.time.LocalDate;

public class Table implements Serializable {
    private final String itemName;
    private LocalDate reservationDate;
    private String reservationTime;
    private int tableNumber;
    private int chairs;

    private double itemCost;
    private boolean isReserved;

    public Table(String itemName , LocalDate reservationDate, String reservationTime, int tableNumber, int chairs, double itemCost, boolean isReserved) {
        this.itemName = "Table Booking";
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.tableNumber = tableNumber;
        this.chairs = chairs;
        this.itemCost = itemCost;
        this.isReserved = isReserved;
    }


    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString(){
        return (this.itemName + " , Table no. : " + this.tableNumber + " , Chairs: " +
                this.chairs + " , Table Cost: " + this.itemCost + " , Date of Purchase: " + this.reservationDate +
                " , Reservation Time: " + this.reservationTime
        );
        // + " , Reserved: " + this.isReserved
    }

}
