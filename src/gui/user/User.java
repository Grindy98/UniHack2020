package gui.user;

import gui.login.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;



public class User {

    public static final ArrayList<String> cities = new ArrayList<>(Arrays.asList(
            "citi1",
            "citi2",
            "city3"));

    public enum Type{
        CLIENT,
        PROVIDER};

    public String firstName, lastName;
    public String pass;
    public String address;
    public String nr;
    public Services serviceList;
    public Type type;
    public String username;



    public int getType() {
        if(type == Type.CLIENT)
            return 0;
        else
            return 1;
    }

    public Type getType_2(String str) {
        if(str.equals("0"))
            return Type.CLIENT;
        else
            return Type.PROVIDER;
    }

    public String getServices() {

        StringBuilder tmp = new StringBuilder();
        List<Services.Type> associateList = serviceList.getAssociateService();

        for(Services.Type it: associateList)
            tmp.append(it.label).append("\n");

        return tmp.toString();
    }


    public void setList(String tabelaJobs)
    {
        StringTokenizer tokens = new StringTokenizer(tabelaJobs, "\n");
        serviceList = new Services();
        String temp;
        while (tokens.hasMoreTokens())
        {
            temp = tokens.nextToken();
            for(Services.Type t : Services.Type.values())
            {
                if (temp.compareTo(t.label)==0)
                {
                    serviceList.addService(t);
                    break;
                }
            }
        }
    }
}


