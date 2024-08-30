package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class SpecialTicket extends Ticket implements Serializable {
    private LocalDate startDate, endDate;
    private LocalTime startTime, endTime;


    public SpecialTicket(String ticketType, int ticketId, String itemName, double itemCost, LocalDate dateOfPurchase, LocalDate ticketValidDate, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        super(ticketType, ticketId, itemName, itemCost, dateOfPurchase, ticketValidDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}