package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Project {
    String name;
    int projectID;
    GregorianCalendar startWeek;
    GregorianCalendar endWeek;
    String projectLeaderID;
    List<Activity> activities = new ArrayList<>();

    public Project(int projectID, String name, GregorianCalendar startWeek, GregorianCalendar endWeek, String projectLeaderID) {
        initialize(projectID, name, endWeek, startWeek);
        this.projectLeaderID = projectLeaderID;
    }

    public Project(int projectID, String name,GregorianCalendar startWeek, GregorianCalendar endWeek) {
        initialize(projectID, name, endWeek, startWeek);
        this.projectLeaderID = "PROJECT LEADER NOT SET";
    }

    public void initialize(int projectID, String name, GregorianCalendar startWeek, GregorianCalendar endWeek) {
        this.projectID = projectID;
        this.name = name;
        this.endWeek = endWeek;
        this.startWeek = startWeek;
    }

    public Report createReport() {
        return null;
        //TODO implement this
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }


    public void deleteActivity(Activity activity) {
        activities.remove(activity);
    }

    public void assignProjectLeader(String projectLeaderID) {
        this.projectLeaderID = projectLeaderID;
    }
}
