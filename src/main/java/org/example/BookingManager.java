package org.example;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }

    private void loadBookingDataFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String time = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                double slatitude = sc.nextDouble();
                double slongitude = sc.nextDouble();
                double elatitude = sc.nextDouble();
                double elongitude = sc.nextDouble();
                int vehicleID = sc.nextInt();
                int passengerId = sc.nextInt();
                double cost = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                bookingList.add(new Booking(id, dateTime, slatitude, slongitude, elatitude, elongitude, vehicleID, passengerId, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //TODO implement functionality as per specification

    //Add booking, find booking, call from app

    public void addBooking(LocalDateTime time, double slatitude, double slongitude, double elatitude, double elongitude, int vehicleID, int passengerId, double cost){
        bookingList.add(new Booking(time, slatitude, slongitude, elatitude, elongitude, vehicleID, passengerId, cost));
    }

    public ArrayList<Booking> findBookingByID(int type)
    {
        ArrayList<Booking> subset = new ArrayList<>();
        int i = 0;
        for(Booking r: bookingList)
        {
            if(type == bookingList.get(i).getBookingId())
            {
                subset.add(r);
            }
            i++;
        }
        return subset;
    }

    public ArrayList<Booking> findBookingByPassengerID(int type)
    {
        ArrayList<Booking> subset = new ArrayList<>();
        int i = 0;
        for(Booking r: bookingList)
        {
            if(type == bookingList.get(i).getPassengerId())
            {
                subset.add(r);
            }
            i++;
        }
        return subset;
    }

    public void displayBookingsAll() {
        int i = 0;
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s%-20s%-50s%-50s%-20s\n", "Booking ID:", "Passenger ID:", "Vehicle ID:", "Booking Date:", "Start Location:", "End Location:", "Cost: ");
        for(Booking d : bookingList)
        {
            System.out.printf("%-20s%-20s%-20s%-20s%-50s%-50s%-20s\n", "#" + d.getBookingId(), "#" +d.getPassengerId(), "#" +d.getVehicleId(), d.getBookingDateTime(), d.getStartLocation(), d.getEndLocation(), d.getCost());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void deleteBooking() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the Booking id of the Booking you would like to delete: ");
        displayBookingsAll();
        //validation UserInput

        String message = "Booking Not Found";

        int delete = 0;
        boolean isNumber;
        do {
            if (kb.hasNextInt())
            {
                delete = kb.nextInt();
                for(int i = 0; i < bookingList.size(); i++)
                {
                    if(bookingList.get(i).getBookingId() == delete)
                    {
                        bookingList.remove(i);
                        message = "You successfully deleted Booking #" + (delete);
                    }
                }
                isNumber = true;
            }
            else
            {
                System.out.println("Please enter a Booking ID to delete a Booking!!! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        System.out.println(message);

        displayBookingsAll();

    }

    public void editBooking(int pos, int bID, LocalDateTime dateBooking, double sLat, double sLong, double eLat, double eLong, int vID, int pID, double cost) {
        bID = bookingList.get(pos).getBookingId();

        Booking editBook = new Booking(bID, dateBooking, sLat, sLong, eLat, eLong, vID, pID, cost);

        bookingList.set(pos, editBook);

        System.out.println("The Booking is Edited");
        System.out.println("******************************");
        displayBookingsAll();
}

}
