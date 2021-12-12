package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

class Booking
{
    private IdGenerator idGenerator = IdGenerator.getInstance("bookings.txt");
    private int bookingId;
    private int vehicleId;
    private int passengerId;
    private LocalDateTime bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    public Booking(LocalDateTime bookingDateTime, double slatitude, double slongitude, double elatitude, double elongitude, int vehicleId, int passengerId, double cost) {
        this.bookingId = idGenerator.getNextId();
        this.bookingDateTime = bookingDateTime;
        this.startLocation = new LocationGPS(slatitude, slongitude);
        this.endLocation = new LocationGPS(elatitude, elongitude);
        this.vehicleId = vehicleId;
        this.passengerId = passengerId;
        this.cost = cost;
    }

    public Booking(int bookingId, LocalDateTime bookingDateTime, double slatitude, double slongitude, double elatitude, double elongitude, int vehicleId, int passengerId, double cost) {
        this.bookingId = bookingId;
        this.bookingDateTime = bookingDateTime;
        this.startLocation = new LocationGPS(slatitude, slongitude);
        this.endLocation = new LocationGPS(elatitude, elongitude);
        this.vehicleId = vehicleId;
        this.passengerId = passengerId;
        this.cost = cost;
    }

    //TODO - see specification

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public LocationGPS getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationGPS startLocation) {
        this.startLocation = startLocation;
    }

    public LocationGPS getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationGPS endLocation) {
        this.endLocation = endLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDateTime=" + bookingDateTime +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", cost=" + cost +
                '}';
    }

}
