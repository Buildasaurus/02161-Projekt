package org.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.application.Models.Employee;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.application.Models.Time;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.GregorianCalendar;

import org.application.Models.ReservedActivity;

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class ActivitySteps {
    Employee employee;
    ProjectActivity projectActivity;
    GregorianCalendar startWeek;
    GregorianCalendar endWeek;
    Time expectedDuration;
    ReservedActivity sampleActivity;
    ReservedActivity reservedActivity;
    GregorianCalendar startDay;
    GregorianCalendar endDay;
    

    @Then("an activity is created")
    public void anActivityIsCreated() {
        assertNotNull(ProjectSteps.project.getActivity(projectActivity));
    }

    @Then("the activity ends in week {int}")
    public void theActivityEndsInWeek(Integer int1) {
        GregorianCalendar givenend = new GregorianCalendar();
        givenend.setWeekDate(2024, int1, 1);
        assertEquals(givenend.getWeekYear(),projectActivity.getEndWeek());
    }

    @When("the employee adds an activity with a start week {int} and end week {int}")
    public void theEmployeeAddsAnActivityWithAnEndDateInWeek(Integer start, Integer end) {
        startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, start, 1);
        endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, end, 1);
        projectActivity = new ProjectActivity(startWeek, endWeek, expectedDuration, "sample-activity", ProjectSteps.project);    
        employee.addActivity(projectActivity);
    }

    @Then("the activity starts in week {int}")
    public void theActivityStartsInWeek(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee adds a reserved activity for the day {int}\\/{int}")
    public void theEmployeeAddsAReservedActivityForTheDay(Integer day, Integer month) {
        startDay = new GregorianCalendar(2024, month, day);
        endDay = new GregorianCalendar(2024, month, day);
        ReservedActivity reservedActivity = new ReservedActivity(startDay, endDay, "test-activity");
        if (reservedActivity != null) System.out.println("not null");
        employee.addActivity(reservedActivity);
    }

    @Then("a reserved activity is created")
    public void aReservedActivityIsCreated() {
        assertNotNull(employee.getActivity(reservedActivity));
    }

    @Then("the activity ends in {int}\\/{int}")
<<<<<<< HEAD
    public void theActivityEndsIn(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();

//        ReservedActivity testActivity = (ReservedActivity) testEmployee.getActivities().get(0);
//        testActivity.getEndDay().get(Calendar.WEEK_OF_YEAR);
=======
    public void theActivityEndsIn(Integer teststart, Integer testend) {
        assertEquals(reservedActivity.getStartDay(), teststart);
        assertEquals(reservedActivity.getEndDay(),testend);
>>>>>>> 2d47d942b86c4af30aea931129b925ff19f186bf
    }

    @Given("an activity exists")
    public void anActivityExists() {
        // set startWeek to week 17. weekDate is set for the first day of 17th week of 2024.
        startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        sampleActivity = new ReservedActivity(startWeek, endWeek, "sample");
    }

    @Given("a project activity exists")
    public void aProjectActivityExists() {
        // set startWeek to week 17. weekDate is set for the first day of 17th week of 2024.
        startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        //set expected duration to 4 half hours
        expectedDuration = new Time(4);
        projectActivity = new ProjectActivity(startWeek,endWeek, expectedDuration, "project-activity", ProjectSteps.project);
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
        startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        reservedActivity = new ReservedActivity(startWeek, endWeek, "reserved-activity");
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

    @Given("{int} activity exists in the project")
    public void activityExists(Integer amountOfActivities) {
        for (int i = 0; i < amountOfActivities; i++){
            // set startWeek to week 17. weekDate is set for the first day of 17th week of 2024.
            startWeek = new GregorianCalendar();
            startWeek.setWeekDate(2024, 17, 1);
            // set endWeek to week 19.
            endWeek = new GregorianCalendar();
            endWeek.setWeekDate(2024, 19, 1);
            
        }
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
    }
}
    
