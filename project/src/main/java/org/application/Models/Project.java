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

    public Project(String name, GregorianCalendar startWeek, GregorianCalendar endWeek,
                   String projectLeaderID) {
        initialize(name, endWeek, startWeek);
        this.projectLeaderID = projectLeaderID;
    }

    public Project(String name, GregorianCalendar endWeek, GregorianCalendar startWeek) {
        initialize(name, endWeek, startWeek);
        removeProjectLeader();
    }

    public void initialize(String name, GregorianCalendar endWeek, GregorianCalendar startWeek) {
        this.projectID = SystemModel.getNextProjectID();
        this.name = name;
        this.endWeek = endWeek;
        this.startWeek = startWeek;
        SystemModel.addProject(this);
    }

    public Report createReport() {
        StringBuilder reportText = new StringBuilder();
        reportText.append("Project Name: ").append(this.name).append("\n");
        reportText.append("Project ID: ").append(this.projectID).append("\n");
        reportText.append("Start Week: ").append(this.startWeek.getTime()).append("\n");
        reportText.append("End Week: ").append(this.endWeek.getTime()).append("\n");
        reportText.append("Project Leader ID: ").append(this.projectLeaderID).append("\n");
        reportText.append("Activities: \n");
        for (ProjectActivity activity : this.activities) {
            reportText.append(activity.toString()).append(
                    "\n"); // assuming toString method in ProjectActivity class provides relevant details
        }
        int totalSpentTime = 0;
        int totalExpectedDuration = 0;
        for (ProjectActivity activity : activities) {
            totalSpentTime += activity.calculateSpentTime();
            totalExpectedDuration += activity.getExpectedDuration();
        }
        double progress = (double) totalSpentTime / totalExpectedDuration * 100;
        String progressstring = String.format("%.2f", progress);
        reportText.append("Project progress: ").append(progressstring).append("% \n");
        return new Report(reportText.toString());
    }

    /**
     * Adds an activity to the project. Remember that Project activities pr default are added to
     * the project, in the constructor.
     *
     * @param activity
     */
    public void addActivity(ProjectActivity activity) {
        activities.add(activity);
    }

    /**
     * Removes the connection between the activity, and the project.
     * should NEVER be used casually, as it will only remove the reference one way.
     * DOES NOT delete the activity
     *
     * @param activity the activity to remove.
     */
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

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectID() {
        return projectID;
    }

    public GregorianCalendar getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(GregorianCalendar endWeek) {
        this.endWeek = endWeek;
    }

    public GregorianCalendar getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(GregorianCalendar startWeek) {
        this.startWeek = startWeek;
    }

    public String getProjectLeaderID() {
        return projectLeaderID;
    }

    public void removeProjectLeader() {
        this.projectLeaderID = "PROJECT LEADER NOT SET";
    }

    /**
     * Deletes the current project, and all activities connected to it
     */
    public void delete() {
        for (int i = getActivities().size() - 1; i >= 0; i--) {
            activities.get(i).delete();
        }
        SystemModel.removeProject(this);
    }


}
