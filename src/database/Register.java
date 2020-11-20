package database;
import java.sql.*;
import gui.user.*;

public class Register {

    public static boolean registerFunction(User user) {

        String connectionUrl = "jdbc:sqlserver://" + "79.118.4.205" + ":1433;"
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

            String sqlInsert = "INSERT into LoginInformation VALUES ("
                    + createTemplate(user.username)
                    + createTemplate(user.firstName)
                    + createTemplate(user.lastName)
                    + createTemplate(user.pass)
                    + createTemplate(user.address)
                    + createTemplate(user.nr)
                    + createTemplate(user.getServices())
                    + "'" + user.getType() + "'" +
                    ")";

            stmt.executeUpdate(sqlInsert);

            return true;

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String createTemplate(String str) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("'").append(str).append("'").append(",");

        return tmp.toString();
    }
}
