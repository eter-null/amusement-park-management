package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;
import java.util.ArrayList;

public class RideQueue implements Serializable {
    private String rideName;
    private ArrayList<String> visitors;

    public RideQueue(String rideName, ArrayList<String> visitors) {
        this.rideName = rideName;
        this.visitors = visitors;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public ArrayList<String> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<String> visitors) {
        this.visitors = visitors;
    }
}
