package org.application.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SystemModel { // should be public static class, but java is stupid
    //STATIC class!
    public static List<Project> projects = new ArrayList<Project>();
    public static List<Employee> employees = new ArrayList<>();

    static {
        //Loading employees
        Employee e = new Employee("404040");
        Activity activity = new ReservedActivity(new Date(1,1,1), new Date(1,1,1), "MyActivity");
        e.addActivity(activity);
        employees.add(e);

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

    public static List<Employee> findAvailableEmployees() {
        // TODO Implement this
        return null;
    }

    public static void createNewProject(Project project) {
        projects.add(project);
    }
}
