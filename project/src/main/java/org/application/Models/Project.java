package org.application.Models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Project {
    private String name;
    private String projectID;
    private GregorianCalendar startWeek;
    private GregorianCalendar endWeek;
    private String projectLeaderID;
    private List<ProjectActivity> activities = new ArrayList<>();

    public Project(String name, GregorianCalendar startWeek, GregorianCalendar endWeek,
            String projectLeaderID) {
        initialize(name, endWeek, startWeek);
        this.projectLeaderID = projectLeaderID;
    }

    public Project(String name, GregorianCalendar endWeek, GregorianCalendar startWeek) {
        initialize(name, endWeek, startWeek);
        this.projectLeaderID = "PROJECT LEADER NOT SET";
    }

    public void initialize(String name, GregorianCalendar endWeek, GregorianCalendar startWeek) {
        this.projectID = SystemModel.getNextProjectID();
        this.name = name;
        this.endWeek = endWeek;
        this.startWeek = startWeek;
        SystemModel.createNewProject(this);
    }

    public Report createReport() {
        return null;
        // TODO implement this
    }

    public void addActivity(ProjectActivity activity) {
        activities.add(activity);
    }

    public void removeActivity(ProjectActivity activity) {
        activities.remove(activity);
    }


    public List<ProjectActivity> getActivities() {
        return activities;
    }

    public void assignProjectLeader(String projectLeaderID) {
        this.projectLeaderID = projectLeaderID;
    }

    public String getName() {
        return name;
    }

    public String getProjectID() {
        return projectID;
    }

    public GregorianCalendar getEndWeek() {
        return endWeek;
    }

    public GregorianCalendar getStartWeek() {
        return startWeek;
    }

    public String getProjectLeaderID() {
        return projectLeaderID;
    }

    public void noProjectLeader(){
        this.projectLeaderID = "PROJECT LEADER NOT SET";
    }
}
