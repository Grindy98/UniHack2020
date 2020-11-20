package gui.user;

import gui.login.Services;

import java.util.StringTokenizer;


public class User {
    public enum Type{
        CLIENT,
        PROVIDER};

    public String firstName, lastName;
    public String pass;
    public String address;
    public String nr;
    public Services offeredJobs; //change this name
    public Type type;
    public String username;

    public static void setList(String tabelaJobs)
    {
        StringTokenizer tokens = new StringTokenizer(tabelaJobs, "\n");
        while (tokens.hasMoreTokens())
        {
            System.out.println(tokens.nextToken());

        }
    }
}
