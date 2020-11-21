package database;

import gui.user.User;

import java.sql.*;

public class UpdateColumn {

    public static final String serverIP = "79.118.4.205";

    /**
     * @param username the user to whom you apply the changes
     * @param column The column of the table that you would like to update
     * @param toUpdate the data that will be introduced in the db
     * @return It returns a boolean value, true if the process is succesfull
     * and false otherwise
     */
    public static boolean updateGivenColumn(String column, String toUpdate,String username) {

        String connectionUrl = "jdbc:sqlserver://" + serverIP + ":1433;"
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
            /*
             * Requesting all the usernames and passwords from
             * the database so we can check whether the login data
             * received is correct
             */

            String updateStr = "update LoginInformation Set " + column +  "= " + "'" + toUpdate + "'" +
            "where loginID = " + "'" + username + "'";

            int countInserted = stmt.executeUpdate(updateStr);

            if(countInserted == 1)
                return true;

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
