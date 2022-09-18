package Principal;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class ReadCSV {

// METHODS
    // Return an ArrayList with the data collected from a csv file
    public static ArrayList<Airport> getAirportsData() throws Exception {
        ArrayList<Airport> airportsList = new ArrayList<>();
        Scanner sc = new Scanner(new File("src/Principal/planilhaAeroportos.csv"));
        sc.useDelimiter(",|\n");
        sc.nextLine();
        while (sc.hasNext()) {
            String code, name, city, state;
            Double latit, longit;

            code = sc.next();
            latit = Double.parseDouble(sc.next());
            longit = Double.parseDouble(sc.next());
            name = sc.next();
            city = sc.next();
            state = sc.next();

            Airport ap = new Airport(code, name, city, state, latit, longit);
            airportsList.add(ap);

        }
        sc.close();

        return airportsList;
    }
}
