package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class HotelRoom implements Serializable {
    private final String itemName;
    private int roomNumber;
    private int numberOfBeds;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean isReserved;

    private double itemCost;

    public HotelRoom(String itemName, int roomNumber, int numberOfBeds, LocalDate checkIn, LocalDate checkOut, boolean isReserved, double itemCost) {
        this.itemName = "Hotel Room Booking";
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isReserved = isReserved;
        this.itemCost = itemCost;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost() {
        this.itemCost = roomitemCost();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = false;
    }

    public double roomitemCost(){
        if ((checkIn != null) && (checkOut != null)) {
            int daysReserved = Period.between(this.checkIn, this.checkOut).getDays();
            return (daysReserved) * itemCost;
        }
        else {
            return this.itemCost;
        }

    }

    public String getItemName() {
        return itemName;
    }


    @Override
    public String toString(){
        return (this.itemName + " , Room no. : " + this.roomNumber + " , No. of Beds: " +
                this.numberOfBeds + " , Room Cost: " + this.itemCost + " , Check In Date: " + this.checkIn +
                " , Check Out Date: " + this.checkOut
        );
        // + " , Reserved: " + this.isReserved
    }
}
