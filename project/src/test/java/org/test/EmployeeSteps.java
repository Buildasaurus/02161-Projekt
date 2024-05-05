package org.test;
import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class EmployeeSteps {
    @When("{int} employees are created")
    public void createEmployees(int numberOfEmployeesToCreate) {
        for(int i = 0; i < numberOfEmployeesToCreate; i++) {
            new Employee(Integer.toString(i));
        }
    }

    @When("the employee deletes a project activity")
    public void deleteAnActivity() {
        SystemModel.getProjectActivities().get(0).delete();
    }

    @When("the employee spends time on the activity")
    public void spendTimeOnActivity() {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        ProjectActivity testActivity = (ProjectActivity) SystemModel.getActivities().get(0);
        GregorianCalendar startTime = new GregorianCalendar(1,1,1);
        GregorianCalendar endTime = new GregorianCalendar(1,1,1);
        testEmployee.createTimeBlock(testActivity, startTime, endTime);
    }

    @When("the employee spends {int} half-hours on activity {int}")
    public void spendExactTimeOnActivity(int halfHoursToSpend, int activityidx) {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        ProjectActivity testActivity = (ProjectActivity) SystemModel.getActivities().get(activityidx - 1);
        GregorianCalendar startTime = new GregorianCalendar(1,1,1,0,0);
        GregorianCalendar endTime = new GregorianCalendar(1,1,1, halfHoursToSpend / 2, (halfHoursToSpend % 2) * 30);
        testEmployee.createTimeBlock(testActivity, startTime, endTime);
    }

    @Then("the employee has spent {int} half-hours on the activity")
    public void spentExactTimeOnActivity(int halfHoursSpent) {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        assertEquals(halfHoursSpent, testEmployee.getTimeBlocks().get(0).getDuration());
    }

    @Then("the employee has {int} timeblocks attached")
    public void employeeHasTimeBlockAttached(int expectedTimeBlocks) {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        assertEquals(expectedTimeBlocks,testEmployee.getTimeBlocks().size());
    }

    @When("an employee named {string} is created")
    public void createEmployeeWithName(String employeeString) {
        new Employee(employeeString);
    }

    @When("the employee named {string} is assigned to the project activity")
    public void addNamedEmployeeToActivity(String employeeString) {
        SystemModel.getProjectActivities().get(0).assignEmployee(employeeString);
    }

    @When("an employee picks a project")
    public void anEmployeePicksAProject() {
        Employee employee = SystemModel.getEmployees().get(0);
        Project pickedProject = SystemModel.getProjectByName("0");
    }

}
