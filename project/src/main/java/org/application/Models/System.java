package org.application.Models;

import java.util.ArrayList;
import java.util.List;

public class System { // should be public static class, but java is stupid
    //STATIC class!
    public static List<Project> projects = new ArrayList<Project>();
    public static List<Employee> employees = new ArrayList<>();

    static
    {
        //Loading employees
        employees.add(new Employee("404040"));

        // TODO : make this read employees from a file
    }
    public static void addEmployee(Employee employee)
    {
        employees.add(employee);
        //TODO : Make this also save the employee to the file
    }

    public static void removeEmployee(Employee employee)
    {
        employees.remove(employee);
        //TODO : Make this also save the employee to the file
        // TODO : consider if this should have some effect on view? perhaps tell it to update.
    }

    public static List<Employee> findAvailableEmployees()
    {
        // TODO Implement this
        return null;
    }
    public static void createNewProject(Project project)
    {
        projects.add(project);
    }
}
