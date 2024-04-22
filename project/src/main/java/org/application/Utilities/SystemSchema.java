package org.application.Utilities;

import java.util.List;

import org.application.Models.Employee;
import org.application.Models.Project;

public class SystemSchema {
    private List<Employee> employees;
    private List<Project> projects;

    public SystemSchema(List<Employee> employees, List<Project> projects) {
        this.employees = employees;
        this.projects = projects;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> gProjects() {
        return projects;
    }
}
