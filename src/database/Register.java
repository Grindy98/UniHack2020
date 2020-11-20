package database;
import java.sql.*;
import gui.user.*;

public class Register {

    public static boolean registerFunction(User user, String ip) {

        String connectionUrl = "jdbc:sqlserver://" + ip + ":1433;"
                + "database=LoginInformation;"
                + "user=suru;"
                + "password=1234;"
                + "encrypt= false;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";

        try(
                Connection conn = DriverManager.getConnection(connectionUrl);
                Statement stmt = conn.createStatement();
        ){

            String sqlInsert = "INSERT into [LoginInformation] VALUES ("
                    + user.username + "," +
                    user.firstName + "," +
                    user.lastName + "," +
                    user.pass + "," +
                    user.address + "," +
                    user.nr + "," +
                    user.getServices() + "," +
                    user.getType() +
                    ")";

            stmt.executeUpdate(sqlInsert);

            return true;

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
