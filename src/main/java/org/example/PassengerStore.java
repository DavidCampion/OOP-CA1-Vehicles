package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add

    public Passenger findPassengerByName(String name){
        int i = 0;
        for(Passenger r: passengerList) {
            if (passengerList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                return r;
            }
            i++;
        }
        return null;
    }

    public int findBookingByPassengerName(String name){
        int test = 0;
        int i = 0;
        for(Passenger r: passengerList) {
            if (passengerList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                test = passengerList.get(i).getId();
            }
            i++;
        }
        return test;
    }

    public void addPassenger(String name, String email, String phone, double latitude, double longitude){
        int id = (passengerList.get(passengerList.size() - 1).getId())+1;
        passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
    }

    public int bookingPassenger()
    {
        Scanner kb = new Scanner(System.in);
        int i = 1;
        boolean isNumber;
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-10s\n", "Passengers #");
        for (Passenger d : passengerList) {
            System.out.printf("%-10s%-15s%-20s%-20s\n", "Name: "+(i++) + d.getName(), " Email: \t" + d.getEmail(), " Phone: \t" + d.getPhone());
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Please enter a number between 1 and " + (passengerList.size()) + " to created booking for selected passenger");
        //validation UserInput
        do {
            if (kb.hasNextInt()) {
                int pos = kb.nextInt() - 1;
                isNumber = true;
                return passengerList.get(pos).getId();

            } else {
                System.out.println("Please enter a NUMBER!!! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));

        return 0;

    }

    public void displayPassengersAll() {
        int i = 0;
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s%-30s%-50s\n", "Passenger ID:", "Name:", "Phone:", "Email:", "Location:");
        for(Passenger p : passengerList)
        {
            System.out.printf("%-20s%-20s%-20s%-30s%-50s\n", "#" + p.getId(), p.getName(), p.getPhone(), p.getEmail(), p.getLocation());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void deletePassenger() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the passenger id of the passenger you would like to delete: ");
        displayPassengersAll();
        //validation UserInput
        String message = "Passenger Not Found";

        int delete = 0;
        boolean isNumber;
        do {
            if (kb.hasNextInt())
            {
                delete = kb.nextInt();
                for(int i = 0; i < passengerList.size(); i++)
                {
                    if(passengerList.get(i).getId() == delete)
                    {
                        passengerList.remove(i);
                        message = "You successfully deleted Passenger #" + (delete);
                    }
                }
                isNumber = true;
            }
            else
            {
                System.out.println("Please enter an Passenger ID to delete a Passenger!!! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));
        kb.nextLine();

        System.out.println(message);

        displayPassengersAll();

    }

} // end class
