package gui.login;

import java.util.ArrayList;
import java.util.List;

public class Services {
    //in order to add more jobs, just add the enum and coresp constructor
    public enum Type {
        ZUGRAV("Zugrav"),
        DJ("DJ"),
        FOTOGRAF("fotograf"),
        SOFER("sofer"),
        MEDIC("medic"),
        PROFESOR("profesor");
        public final String label;
        private Type(String it) {
            label = it;
        }
    };
    private List<Type> associateService;

    public Services()
    {
        associateService=new ArrayList<>();
    }

    public void addService(Type t){
        associateService.add(t);
    }

    public List<Type> getAssociateService() {
        return associateService;
    }

    public void removeService(Type t)
    {
        associateService.remove(t);
    }


}
