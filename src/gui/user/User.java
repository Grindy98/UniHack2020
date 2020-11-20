package gui.user;

import gui.login.Services;


public class User {
    public enum Type{CLIENT,
        PROVIDER};
    public String firstName, lastName;
    public String pass;
    public String address;
    public String nr;
    public Services offeredJobs; //change this name
    public Type type;
    public String username;
}
