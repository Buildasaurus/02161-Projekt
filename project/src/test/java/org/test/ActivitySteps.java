package org.test;

import static org.junit.Assert.*;

import java.util.List;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.hamcrest.core.IsInstanceOf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.application.Models.ReservedActivity;
import org.junit.After;

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class ActivitySteps {

    ProjectActivity projectActivity;
    ReservedActivity reservedActivity;

    @io.cucumber.java.After
    public void tearDown() {
        SystemModel.reset();
    }

    @Then("{int} activities exist")
    public void activitiesExist(int amountOfActivitiesExpected) {
        assertEquals(amountOfActivitiesExpected,SystemModel.getActivities().size());
    }

    @Then("{int} reserved activities exist")
    public void reservedActivitiesExist(int amountOfActivitiesExpected) {
        assertEquals(amountOfActivitiesExpected,SystemModel.getReservedActivites().size());
    }

    @Then("the activity ends in week {int}")
    public void theActivityEndsInWeek(Integer int1) {
        GregorianCalendar givenend = new GregorianCalendar();
        givenend.setWeekDate(2024, int1, 1);
        ProjectActivity testActivity = SystemModel.getProjects().get(0).getActivities().get(0);
        assertEquals(givenend.get(Calendar.WEEK_OF_YEAR),testActivity.getEndDate().get(Calendar.WEEK_OF_YEAR));
    }

    @Then("the activity starts in week {int}")
    public void theActivityStartsInWeek(Integer int1) {
        GregorianCalendar givenstart = new GregorianCalendar();
        givenstart.setWeekDate(2024, int1, 1);
        ProjectActivity testActivity = SystemModel.getProjects().get(0).getActivities().get(0);
        assertEquals(givenstart.get(Calendar.WEEK_OF_YEAR),testActivity.getStartDate().get(Calendar.WEEK_OF_YEAR));
    }

    @When("the employee adds an activity with a start week {int} and end week {int}")
    public void theEmployeeAddsAnActivityWithAnEndDateInWeek(Integer start, Integer end) {
        GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, start, 1);
        GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, end, 1);
        // Set expected duration to 20 half hours
        int expectedDuration = 20;
        Project testProject = SystemModel.getProjects().get(0);
        ProjectActivity testActivity = new ProjectActivity(startWeek, endWeek, expectedDuration, "sample-activity", testProject);
        // Add the testActivity to the employee
        SystemModel.getEmployees().get(0).addActivity(testActivity);
    }

    @When("the employee adds a reserved activity for the day {int}\\/{int}")
    public void theEmployeeAddsAReservedActivityForTheDay(Integer day, Integer month) {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        GregorianCalendar startDay = new GregorianCalendar(2024, month, day);
        GregorianCalendar endDay = new GregorianCalendar(2024, month, day);
        reservedActivity = new ReservedActivity(startDay, endDay, "test-activity",testEmployee);
    }

    @Then("a reserved activity is created")
    public void aReservedActivityIsCreated() {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        assertNotNull(testEmployee.getActivity("test-activity"));
    }

    @Then("the activity ends at {int}\\/{int}")
    public void theActivityEndsIn(int testDay, int testMonth) {
        assertEquals(reservedActivity.getEndDate().get(Calendar.DAY_OF_MONTH), testDay);
        assertEquals(reservedActivity.getEndDate().get(Calendar.MONTH), testMonth);
    }

    @Given("a project activity exists")
    public void aProjectActivityExists() {
        // set startWeek to week 17. weekDate is set for the first day of 17th week of 2024.
        GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        //set expected duration to 4 half hours
        int expectedDuration = 4;
        Project testProject = SystemModel.getProjects().get(0);
        new ProjectActivity(startWeek,endWeek, expectedDuration, "project-activity", testProject);
    }

    @When("the other employee adds the employee to the project activity")
    public void theOtherEmployeeAddsTheEmployeeToTheProjectActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the employee is assigned to the activity")
    public void theEmployeeIsAssignedToTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the employee is assigned to a reserved activity in the selected time slot")
    public void theEmployeeIsAssignedToAReservedActivityInTheSelectedTimeSlot() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee is added to an activity")
    public void theEmployeeIsAddedToAnActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the employee is not assigned to the activity")
    public void theEmployeeIsNotAssignedToTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a reserved activity exists")
    public void aReservedActivityExists() {
        // set startWeek to week 17. weekDate is set for the first day of 17th week of 2024.
        GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        Employee testEmployee = SystemModel.getEmployees().get(0);
        ReservedActivity sampleActivity = new ReservedActivity(startWeek, endWeek, "sample",testEmployee);
        testEmployee.addActivity(sampleActivity);;
    }

    @When("the employee adds themselves to the reserved activity")
    public void theEmployeeAddsThemselvesToTheReservedActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the employee is assigned to the reserved activity")
    public void theEmployeeIsAssignedToTheReservedActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("{int} activities exists in the project")
    public void activitiesExistInProject(int numberOfExpectedActivities) {
        assertEquals(numberOfExpectedActivities,SystemModel.getProjects().get(0).getActivities().size());
    }

    @Then("the employee has the reserved activity in their schedule")
    public void theEmployeeHasTheReservedActivityInTheirSchedule() {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        assertNotEquals(testEmployee.getActivities().size(),0);
    }

    @Given("{int} activities are created in the project")
    public void activityExists(Integer amountOfActivities) {
        Project project = SystemModel.getProjects().get(0);
        for (int i = 0; i < amountOfActivities; i++){
            // set startWeek to week 17. weekDate is set for the first day of 17th week of 2024.
            GregorianCalendar startWeek = new GregorianCalendar();
            startWeek.setWeekDate(2024, 17, 1);
            // set endWeek to week 19.
            GregorianCalendar endWeek = new GregorianCalendar();
            endWeek.setWeekDate(2024, 19, 1);
            // expected duration is 20 half hours
            int expectedDuration = 20;
            new ProjectActivity(startWeek, endWeek, expectedDuration, "sample-activity" + (i + 1), project);        
        }
    }

    @Given("{int} activity are created in the employee")
    public void activityExistsInTheEmployee(Integer amountOfActivities) {
        Employee employee = SystemModel.getEmployees().get(0);
        for (int i = 0; i < amountOfActivities; i++){
            GregorianCalendar startDay = new GregorianCalendar(2024,12,1);
            GregorianCalendar endDay = new GregorianCalendar(2024,12,5);
            ReservedActivity reservedActivity = new ReservedActivity(startDay, endDay,"sample-activity" + (i + 1),employee); 
        }  
    }

    @When("an employee tries to delete a project activity")
    public void anEmployeeTriesToDeleteAProjectActivity() {
        Project project = SystemModel.getProjects().get(0);
        ProjectActivity projectActivity = project.getActivities().get(0);
        project.removeActivity(projectActivity);
    }

    @When("an employee tries to delete a reserved activity")
    public void anEmployeeTriesToDeleteAReservedActivity() {
        Employee employee = SystemModel.getEmployees().get(0);
        ReservedActivity reservedActivity = (ReservedActivity) employee.getActivities().get(0);
        employee.removeActivity(reservedActivity);
    }


    @Then("the project activity no longer exists")
    public void theProjectActivityNoLongerExists() {
        Project project = SystemModel.getProjects().get(0);
        assertTrue(project.getActivities().isEmpty());
    }

    @Then("the reserved activity no longer exists")
    public void theReservedActivityNoLongerExists() {
        Employee employee = SystemModel.getEmployees().get(0);
        assertTrue(employee.getActivities().isEmpty());
    }

    @When("the employee adds used time to the activity")
    public void theEmployeeAddsUsedTimeToTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity has the used time, set by the employee")
    public void theActivityHasTheUsedTimeSetByTheEmployee() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee is added to the project activity")
    public void addEmployeeToProjectActivity() {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        ProjectActivity testActivity = SystemModel.getProjectActivities().get(0);
        testActivity.assignEmployee(testEmployee);
    }

    @Then("{int} employees have been added to the project activity")
    public void employeeHasBeenAddedToProjectActivity(int expectedNumberOfEmployeesAdded) {
        ProjectActivity testActivity = SystemModel.getProjectActivities().get(0);
        assertEquals(expectedNumberOfEmployeesAdded,testActivity.getAssignedEmployees().size());
    }

    @When("the employee is removed from the project activity")
    public void removeEmployeeFromProjectActivity() {
        Employee testEmployee = SystemModel.getEmployees().get(0);
        ProjectActivity testActivity = SystemModel.getProjectActivities().get(0);
        testActivity.removeEmployee(testEmployee);
    }

}
