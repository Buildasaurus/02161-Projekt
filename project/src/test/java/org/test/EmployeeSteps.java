package org.test;
import org.application.Models.Employee;
import io.cucumber.java.en.When;

/*
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.application.Models.ReservedActivity;
*/

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class EmployeeSteps {
    @When("{int} employees are created")
    public void createEmployees(int numberOfEmployeesToCreate) {
        for(int i = 0; i < numberOfEmployeesToCreate; i++) {
            new Employee(Integer.toString(i));
        }
    }

}
