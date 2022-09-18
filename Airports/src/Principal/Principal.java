package Principal;

import java.util.*;

public class Principal {

    public static void main(String[] args) throws Exception {

        ArrayList<Airport> airportsList = new ArrayList<Airport>();

        // Store the data collected from a csv file inside an ArrayList
        airportsList = ReadCSV.getAirportsData();

        // Create a menu with the airport data provided
        Menu mainMenu = new Menu(airportsList);

        // Starts the menu for the user
        mainMenu.showMenu();

    }
}
