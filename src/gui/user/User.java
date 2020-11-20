package gui.user;

import gui.login.Services;

import java.util.List;
import java.util.StringTokenizer;


public class User {
    public enum Type{
        CLIENT,
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


