package database;

import gui.user.User;

import java.sql.*;

public class UserExistence {

    public static final String serverIP = "79.118.4.205";

    /**
     * @param username The username entered by the user
     * @return It returns a boolean value, true if the user exists
     * and false otherwise
     */
    public static boolean checkUserExistence(String username) {

        User user = new User();

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

            String selectStr = "select * " +
                    "from [LoginInformation]" + " where loginID = " + "'" + username + "'";

            ResultSet res = stmt.executeQuery(selectStr);

            while(res.next()){
                String firstName = res.getString("firstName");
                String lastName = res.getString("lastName");
                String pass = res.getString("pass");
                String address = res.getString("City");
                user.setList(res.getString("services"));
                String user1 = res.getString("loginID");
                String nr = res.getString("nr");

                if(user1.equals(username))
                    return true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
