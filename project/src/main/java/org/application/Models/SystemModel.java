package org.application.Models;
// 50% Martin 40% Jonathan 10% Marinus
import java.util.*;

public class SystemModel { // should be public static class, but java is stupid
    // STATIC class!
    private static final List<Project> projects = new ArrayList<>();
    private static final List<Employee> employees = new ArrayList<>();
    private static int currentRunNumber = 0;

    public static List<ReservedActivity> getReservedActivities() {
        HashMap<ReservedActivity, ReservedActivity> reservedActivitiesMap = new HashMap<ReservedActivity, ReservedActivity>();
        for (Employee employee : employees) {
            for (Activity employeeActivity : employee.getActivities()) {
                if (employeeActivity instanceof ReservedActivity) {
                    reservedActivitiesMap.put((ReservedActivity) employeeActivity, (ReservedActivity) employeeActivity);
                }
            }
        }

        ArrayList<ReservedActivity> reservedActivitiesList = new ArrayList<>(reservedActivitiesMap.values());
        return reservedActivitiesList;
    }

    public static List<ProjectActivity> getProjectActivities() {
        HashMap<ProjectActivity, ProjectActivity> projectActivitiesMap = new HashMap<ProjectActivity, ProjectActivity>();
        for (Project project : projects) {
            for (ProjectActivity projectActivity : project.getActivities()) {
                projectActivitiesMap.put(projectActivity, projectActivity);
            }
        }
        ArrayList<ProjectActivity> projectActivitiesList = new ArrayList<>(projectActivitiesMap.values());
        return projectActivitiesList;
    }

    public static List<Activity> getActivities() {
        List<Activity> activities = new ArrayList<Activity>();
        activities.addAll(getProjectActivities());
        activities.addAll(getReservedActivities());
        return activities;
    }

    /**
     * Method for resetting everything. Deleting all saves employees and projects.
     */
    public static void reset() {
        currentRunNumber = 0;
        projects.clear();
        employees.clear();
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static Employee getEmployee(String id) {
        for (Employee employee : employees) {
            if (employee.getID().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.delete();
    }

    public static Project getProjectByName(String projectName) {
        for (Project project : projects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    static public String getNextProjectID() {
        return new GregorianCalendar().get(Calendar.YEAR) + "" + currentRunNumber++;
    }

    /**
     * Finds a sorted list of the employees, based on how available they are in a given time period
     *
     * @param startDate The start of the period
     * @param endDate   The end of the period
     * @return A list of sorted employees
     */
    public static List<Employee> findAvailableEmployees(GregorianCalendar startDate, GregorianCalendar endDate) {
        List<Employee> availableEmployees = employees;
        for (Employee employee : employees) {
            System.out.println(employee + " " + employee.getAvailabilityScore(startDate, endDate));
        }
        availableEmployees.sort((e1, e2) -> Double.compare(e2.getAvailabilityScore(startDate, endDate),
                e1.getAvailabilityScore(startDate, endDate)));

        return availableEmployees;
    }

    public static void addProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
        }
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static List<Project> getProjects() {
        return projects;
    }

    /**
     * removes a project from the projects list, and deletes it.
     *
     * @param project
     */
    public static void removeProject(Project project) {
        if (projects.remove(project)) {
            project.delete();
        }

    }

    public static Activity findActivityWithName(String activityName) {
        List<Activity> activities = getActivities();
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }
}
