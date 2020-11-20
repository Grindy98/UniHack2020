package gui.user;

import gui.login.Services;

import java.util.List;


public class User {
    public enum Type{CLIENT,
        PROVIDER};
    public String firstName, lastName;
    public String pass;
    public String address;
    public String nr;
    public Services offeredJobs;
    public Type type;
    public String username;

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
}


