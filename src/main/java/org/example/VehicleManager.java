package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                int loadSpaceOrSeats = sc.nextInt();

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    // construct a car object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpaceOrSeats));
                } else if (type.equalsIgnoreCase("Car") || type.equalsIgnoreCase("4x4"))
                {
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpaceOrSeats));
                }

            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //TODO add more functionality as per spec.

    public Vehicle findVehicleByRegistration(String reg) {
        int i = 0;
        for(Vehicle r: vehicleList) {
            if (vehicleList.get(i).getRegistration().toLowerCase().contains(reg.toLowerCase())) {
                return r;
            }
            i++;
        }
        return null;
    }

    public ArrayList<Vehicle> findByType(String type)
    {
        ArrayList<Vehicle> subset = new ArrayList<>();
        int i = 0;
        for(Vehicle r: vehicleList)
        {
            if(vehicleList.get(i).getType().toLowerCase().contains(type.toLowerCase()))
            {
                subset.add(r);
            }
            i++;
        }
        return subset;
    }

    public int bookingVehicle()
    {
        Scanner kb = new Scanner(System.in);
        int i = 1;
        boolean isNumber;
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-10s\n", "Vehicle #");
        for (Vehicle d : vehicleList) {
            System.out.printf("%-10s%-15s%-10s%-10s\n", "#"+i++, "Make: " + d.getMake(), " Type: \t" + d.getType(), " Mileage: \t" + d.getMileage(), " Cost: \t" + d.getCostPerMile());
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Please enter a number between 1 and " + (vehicleList.size()) + " to Book selected Vehicle");
        //validation UserInput
        do {
            if (kb.hasNextInt()) {
                int pos = kb.nextInt() - 1;
                isNumber = true;

                return vehicleList.get(pos).getId();

            } else {
                System.out.println("Please enter a NUMBER!!! ");
                isNumber = false;
                kb.next();
            }
        } while (!(isNumber));

        return 0;

    }

    public void displayVehiclesAll() {
        int i = 0;
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-25s%-50s\n", "Vehicle ID:", "Vehicle Make:", "Vehicle Model:", "Vehicle Type:", "Mileage:", "Cost Per Mile:", "Miles Per KM:", "Vehicle Reg:", "Last Serviced Date:", "Depot Location:");
        for(Vehicle d : vehicleList)
        {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-25s%-50s\n", "#" + d.getId(), d.getMake(), d.getModel(), d.getType(), d.getMileage(), d.getCostPerMile(), d.getMilesPerKm(), d.getRegistration(), d.getLastServicedDate(), d.getDepotGPSLocation());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
