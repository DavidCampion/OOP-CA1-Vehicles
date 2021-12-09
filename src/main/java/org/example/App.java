package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/** David Campion
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        bookingManager = new BookingManager("bookings.txt");

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create BookingManager and load all bookings from file
        // bookingManager = new BookingManager("bookings.txt");

        //pMgr.saveToFile();

        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1-4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehicleMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add Passenger\n"
                + "4. Exit\n"
                + "Enter Option [1-4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_Pass = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if(p==null)
                            System.out.println("No passenger matching the name \"" + name +"\"");
                        else
                            System.out.println("Found passenger: \n" + p.toString());
                        break;
                    case ADD_Pass:
                        addPass();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicles By Registration\n"
                + "3. Find Vehicles By Type \n"
                + "4. Exit\n"
                + "Enter Option [1-4]";

        final int SHOW_ALL_V = 1;
        final int FIND_BY_REG = 2;
        final int FIND_BY_TYPE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL_V:
                        System.out.println("Display ALL Vehicles");
                        vehicleManager.displayAllVehicles();
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicles by reg");
                        System.out.println("Enter vehicle Reg: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = vehicleManager.findVehicleByRegistration(reg);
                        if(v==null)
                            System.out.println("No vehicle with registration "+reg + " was found.");
                        else
                            System.out.println("Found Vehicle: " + v.toString());
                        break;
                    case FIND_BY_TYPE:
                        System.out.println("Please enter a Vehicle Type to find");
                        System.out.println("-------------------------------");
                        String needle = keyboard.nextLine();
                        ArrayList<Vehicle> t = vehicleManager.findByType(needle);
                        if(t==null)
                            System.out.println("No vehicle with registration "+needle + " was found.");
                        else
                            System.out.println("Found Vehicle: " + t.toString());
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Find Bookings by ID\n"
                + "3. Add Bookings\n"
                + "4. Edit Bookings\n"
                + "5. Delete Bookings\n"
                + "6. Exit\n"
                + "Enter Option [1-4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_ID = 2;
        final int ADD_BOOKING = 3;
        final int EDIT_BOOKING = 4;
        final int DELETE_BOOKING = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case SHOW_ALL:
                        System.out.println("Display ALL Bookings");
                        bookingManager.toString();
                        break;
                    case FIND_BY_ID:
                        vehicleManager.bookingVehicle();
                        break;
                    case ADD_BOOKING:
                        addBook();
                        break;
                    case EDIT_BOOKING:

                        break;
                    case DELETE_BOOKING:

                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void addPass() {
        System.out.println("To add a Passenger you should enter: Name, Email, Phone Number, Longitude and Latitude");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        Scanner kb = new Scanner(System.in);

        //validation UserInput
        boolean isNumber;

        System.out.println("Enter Passenger Name: ");
        String pName = kb.nextLine();
        System.out.println("Enter Passenger Email: ");
        String pMail = kb.nextLine();
        System.out.println("Enter Phone Number: ");
        String pNum = kb.nextLine();

        int pLong = 0;
        System.out.println("Please enter Passengers Location - ");
        System.out.println("Enter Longitude: ");
        do {
            if (kb.hasNextInt()) {
                pLong = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Longitude! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        int pLat = 0;
        System.out.println("Enter Latitude: ");
        do {
            if (kb.hasNextInt()) {
                pLat = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Latitude! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        passengerStore.addPassenger( pName, pMail, pNum, pLat, pLong);

        System.out.println("The new Passenger is Entered");
        System.out.println("******************************");
    }

    private void addBook() {
        System.out.println("To add a Booking you should enter: Date, Time, Start Latitude, Start Longitude, End Latitude and End Longitude");
        System.out.println("The Price will be calculated once all these details are collected!");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        Scanner kb = new Scanner(System.in);

        //validation UserInput
        boolean isNumber;
        int bYear = 1900;
        int bMonth = 01;
        int bDay = 01;
        int bHour = 00;
        int bMinute = 00;

        System.out.println("Please enter Booking Date - ");
        System.out.println("Enter Year of booking: ");
        do {
            if (kb.hasNextInt()) {
                bYear = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Year! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        System.out.println("Enter Month of booking: ");
        do {
            if (kb.hasNextInt()) {
                bMonth = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Month! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        System.out.println("Enter Day of booking: ");
        do {
            if (kb.hasNextInt()) {
                bDay = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Day! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        System.out.println("Please enter Booking Time - ");
        System.out.println("Enter hour of booking: ");
        do {
            if (kb.hasNextInt()) {
                bHour = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Day! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        System.out.println("Enter minute of booking: ");
        do {
            if (kb.hasNextInt()) {
                bMinute = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Day! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        LocalDateTime dateBooking = LocalDateTime.of(bYear, bMonth, bDay, bHour, bMinute);

        double sLat = 0.0;
        System.out.println("Please enter Passengers start Location - ");
        System.out.println("Enter Latitude: ");
        do {
            if (kb.hasNextDouble()) {
                sLat = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Latitude! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        double sLong = 0.0;
        System.out.println("Enter Longitude: ");
        do {
            if (kb.hasNextDouble()) {
                sLong = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Longitude! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        double eLat = 0;
        System.out.println("Please enter Passengers End Location - ");
        System.out.println("Enter Latitude: ");
        do {
            if (kb.hasNextDouble()) {
                eLat = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Latitude! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        double eLong = 0;
        System.out.println("Enter Longitude: ");
        do {
            if (kb.hasNextDouble()) {
                eLong = kb.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a NUMBER for Longitude! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        int vID = vehicleManager.bookingVehicle();
        int pID = passengerStore.bookingPassenger();
        double cost = 0.0;

        bookingManager.addBooking(dateBooking, sLat, sLong, eLat, eLong, vID, pID, cost);
        System.out.println("The new Booking is Entered");
        System.out.println("******************************");
    }



}
