package gui.user;

import gui.login.Services;

import java.sql.*;
import java.util.List;
import java.util.StringTokenizer;


public class User {
    public enum Type{
        CLIENT,
        PROVIDER};

    public static final String serverIP = "79.118.4.205";
    public String firstName, lastName;
    public String pass;
    public String address;
    public String nr;
    public Services offeredJobs;
    public Type type;
    public String username;

    public User() {
    }

    public void set_User(String name) {
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
            String selectStr = "select * " +
                    "from [LoginInformation]" + " where loginID like" + "'" + name + "'";

            ResultSet res = stmt.executeQuery(selectStr);

            while(res.next()) {
                firstName = res.getString("firstname");
                lastName = res.getString("lastname");
                pass = res.getString("pass");
                address = res.getString("City");
                setList(res.getString("services"));
                username = res.getString("loginID");
                nr = res.getString("nr");
                type = getType_2(res.getString("userType"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public User(String city) {
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
            String selectStr = "select * " +
                    "from [LoginInformation]" + " where city like" + city;

            ResultSet res = stmt.executeQuery(selectStr);

            while(res.next()) {
                String firstname = res.getString("firstname");
                String lastname = res.getString("lastname");
                String pass = res.getString("pass");
                String address = res.getString("City");
                setList(res.getString("services"));
                String username = res.getString("loginID");
                String nr = res.getString("nr");
                Type type = getType_2(res.getString("userType"));
            }

             }catch(SQLException e) {
                 e.printStackTrace();
            }
    }

    public Type getType_2(String str) {
        if(str.equals("0"))
            return Type.CLIENT;
        else
            return Type.PROVIDER;
    }

    public int getType() {
        if(type == Type.CLIENT)
            return 0;
        else
            return 1;
    }

    public String getServices() {

        StringBuilder tmp = new StringBuilder();
        List<Services.Type> associateList = offeredJobs.getAssociateService();

        for(Services.Type it: associateList)
            tmp.append(it.label).append("\n");

        return tmp.toString();
    }

    public void setList(String tabelaJobs)
    {
        StringTokenizer tokens = new StringTokenizer(tabelaJobs, "\n");
        offeredJobs = new Services();
        String temp;
        while (tokens.hasMoreTokens())
        {
            temp = tokens.nextToken();
            for(Services.Type t : Services.Type.values())
            {
                if (temp.compareTo(t.label)==0)
                {
                    offeredJobs.addService(t);
                    break;
                }
            }
        }
    }
}


