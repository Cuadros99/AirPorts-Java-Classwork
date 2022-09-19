package Principal;

import java.sql.*;
import java.util.ArrayList;

public class Database {

// METHODS
    // Insert the calculated route into the database
    public static void insertRouteOnDB(String originAp, String  destinyAp, String stopover) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport-project", "root", "MySqlPassCuadros");
            

            String query = "insert into routes (originAp, destinyAp, stopover)" + " values ( ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, originAp );
            preparedStatement.setString (2, destinyAp);
            preparedStatement.setString (3, stopover);

            preparedStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the airports data from the database
    public static ArrayList<Airport> getAirportsData() {
        ArrayList<Airport> airportsList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport-project", "root", "MySqlPassCuadros");
            Statement statement = connection.createStatement();
    
            ResultSet resultSet = statement.executeQuery("select * from airports");
    
            while(resultSet.next()) {
                String code, name, city, state;
                Double latit, longit;

                code = resultSet.getString("sigla");
                latit = resultSet.getDouble("latitude");
                longit = resultSet.getDouble("longitude");
                name = resultSet.getString("name");
                city = resultSet.getString("city");
                state = resultSet.getString("state");

                Airport ap = new Airport(code, name, city, state, latit, longit);
                airportsList.add(ap);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return airportsList;
    }




}
