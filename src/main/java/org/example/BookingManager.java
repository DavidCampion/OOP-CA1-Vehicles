package org.example;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager(String fileName) {
        this.bookingList = new ArrayList<>();
        loadBookingDataFromFile(fileName);
    }

    private void loadBookingDataFromFile(String filename) {


    }

    //TODO implement functionality as per specification

    //Add booking, find booking, call from app

    public void addBooking(LocalDateTime time, double slatitude, double slongitude, double elatitude, double elongitude, int vehicleID, int passengerId, double cost){
        int id = (bookingList.get(bookingList.size() - 1).getBookingId())+1;
        bookingList.add(new Booking(id, time, slatitude, slongitude, elatitude, elongitude, vehicleID, passengerId, cost));
    }

}
