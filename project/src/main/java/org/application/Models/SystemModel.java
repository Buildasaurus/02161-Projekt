package org.application.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SystemModel { // should be public static class, but java is stupid
    //STATIC class!
    private static List<Project> projects = new ArrayList<Project>();
    private static List<Employee> employees = new ArrayList<>();
    private static int currentRunNumber = 0;

    /**
     * Method for resetting everything. Deleting all saves employees and projects.
     */
    public static void reset() {
        currentRunNumber = 0;
        projects.clear();
        employees.clear();
    }

    public static void createDefaultEmployees() {
        //Loading projects
        Project project = new Project("The Project", new GregorianCalendar(1, 1, 1), new GregorianCalendar(1,10,2));
        projects.add(project);

        //Loading employees
        Employee e = new Employee("404040");
        ReservedActivity activity = new ReservedActivity(new GregorianCalendar(1,10,1), new GregorianCalendar(1,10,1), "Holiday");
        activity.assignEmployee(e);

        ProjectActivity projectActivity = new ProjectActivity(new GregorianCalendar(1,1,1), new GregorianCalendar(1,2,1), 20 , "Testing", project);
        projectActivity.assignEmployee(e);
        e.createTimeBlock(projectActivity, new GregorianCalendar(1,1,1,8,0), new GregorianCalendar(1,1,1,12,0));

        projectActivity = new ProjectActivity(new GregorianCalendar(1,3,1), new GregorianCalendar(1,3,1), 1, "codeRefactor", project);
        projectActivity.assignEmployee(e);

        e.addActivity(activity);

        // TODO : make this read employees from a file
        //  - This is though an extra feature. Not high priority
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
        //TODO : Make this also save the employee to the file
        // - This is though an extra feature. Not high priority
    }

    public static Employee getEmployee(String id) {
        for (Employee employee : employees) {
            if (employee.getID().equals(id)) {
                return employee;
            }
        }
        return null;
    }
    // TODO : Husk at lave assert statements hvis der er preconditions for testing.
    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
        //TODO : Make this also save the employee to the file
        // TODO : consider if this should have some effect on view? perhaps tell it to update.
    }

    static public String getNextProjectID()
    {
        return new GregorianCalendar().get(Calendar.YEAR) + "" + currentRunNumber++;
    }

    /**
     * Finds a sorted list of the employees, based on how available they are in a given time period
     * @param startDate The start of the period
     * @param endDate The end of the period
     * @return A list of sorted employees
     */
    public static List<Employee> findAvailableEmployees(GregorianCalendar startDate, GregorianCalendar endDate) {
        List<Employee> availableEmployees = employees;
        //TODO : figure out if this sorts on correctly or backwards.
        for (Employee employee : employees) {
            System.out.println(employee + " " +   employee.getAvailabilityScore(startDate, endDate));
        }
        availableEmployees.sort((e1, e2) -> Double.compare(e2.getAvailabilityScore(startDate, endDate), e1.getAvailabilityScore(startDate, endDate)));

        return availableEmployees;
    }

    public static void addProject(Project project) {
        projects.add(project);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static List<Project> getProjects() {
        return projects;
    }

    public static Activity getActivity(String activityName) {
        //TODO implement
        return new ProjectActivity(null, null, 0, activityName, null);
    }
}
