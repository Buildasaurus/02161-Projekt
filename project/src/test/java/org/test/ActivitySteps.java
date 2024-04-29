package org.test;

import static org.junit.Assert.assertNotEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.application.Models.Activity;
import org.application.Models.Employee;
import org.application.Models.ProjectActivity;
import org.application.Models.ReservedActivity;
import org.application.Models.SystemModel;
import org.application.Models.Time;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class ActivitySteps {
    @Then("an activity is created")
    public void anActivityIsCreated() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        List<Employee> employeeList = SystemModel.getEmployees();
        Employee testEmployee = employeeList.get(0);
//        ReservedActivity testActivity = new ReservedActivity(null, null, null)
        assertNotEquals(0,testEmployee.getActivities().size());

    }

    @Then("the activity ends in week {int}")
    public void theActivityEndsInWeek(Integer int1) {
        // Write code here that turns the phrase above into concrete actions  
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee adds an activity with an end date in week {int}")
    public void theEmployeeAddsAnActivityWithAnEndDateInWeek(Integer weekDay) {
        List<Employee> employeeList = SystemModel.getEmployees();
        Employee testEmployee = employeeList.get(0);
        GregorianCalendar startDay = new GregorianCalendar();
        startDay.set(Calendar.WEEK_OF_YEAR, weekDay);
        GregorianCalendar endDay = new GregorianCalendar();
        endDay.set(Calendar.WEEK_OF_YEAR, weekDay);
        ReservedActivity testActivity = new ReservedActivity(startDay,endDay,"testActivity");
        testEmployee.addActivity(testActivity);
    }

    @Then("the activity starts in week {int}")
    public void theActivityStartsInWeek(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee adds a reserved activity for the day {int}\\/{int}")
    public void theEmployeeAddsAReservedActivityForTheDay(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a reserved activity is created")
    public void aReservedActivityIsCreated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity ends in {int}\\/{int}")
    public void theActivityEndsIn(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();

//        ReservedActivity testActivity = (ReservedActivity) testEmployee.getActivities().get(0);
//        testActivity.getEndDay().get(Calendar.WEEK_OF_YEAR);
    }

    @Given("an activity exists")
    public void anActivityExists() {
        /*
        Date startWeek = new Date(30681);
        Date endWeek = new Date(44813);
        Time time = new Time(301);
        ProjectActivity testActivity = new ProjectActivity(startWeek,endWeek,time,"Test-aktivitet");
        */
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a project activity exists")
    public void aProjectActivityExists() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
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
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
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

    
    @Then("the employee has the reserved activity in their schedule")
    public void theEmployeeHasTheReservedActivityInTheirSchedule() {
        List<Employee> employeeList = SystemModel.getEmployees();
        Employee testEmployee = employeeList.get(0);
        assertNotEquals(0,testEmployee.getActivities().size());
    }

    @Given("{int} activity exists")
    public void activityExists(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
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

    @Given("an employee exists")
    public void anEmployeeExists() {
        Employee testEmployee = new Employee("013424");
        SystemModel.addEmployee(testEmployee);
    }
}
    