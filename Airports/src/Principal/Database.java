package Principal;

import java.sql.*;

public class Database {
    public static void insertRoute(String originAp, String  destinyAp, String stopover) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport-project", "root", "MySqlPassCuadros");
            Statement statement = connection.createStatement();

//            ResultSet resultSet = statement.executeQuery("select * from routes");

//            while(resultSet.next()) {
//                System.out.println(resultSet.getString("stopover"));
//            }

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


}
