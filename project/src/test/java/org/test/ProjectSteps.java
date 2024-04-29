package org.test;

import java.util.GregorianCalendar;

import org.application.Models.Project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class ProjectSteps {
    public Project project;

    @Given("a project exists, and a Project Leader is assigned to it.")
    public void aProjectExistsAndAProjectLeaderIsAssignedToIt() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the Project Leader generates the report.")
    public void theProjectLeaderGeneratesTheReport() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Project Leader is prompted to choose a location to save the report")
    public void theProjectLeaderIsPromptedToChooseALocationToSaveTheReport() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a csv file is created and saved to the disk with the project data")
    public void aCsvFileIsCreatedAndSavedToTheDiskWithTheProjectData() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Project Leader gets a notification that the report is generated")
    public void theProjectLeaderGetsANotificationThatTheReportIsGenerated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a project leader exists.")
    public void aProjectLeaderExists() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the project leader checks the overview")
    public void theProjectLeaderChecksTheOverview() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("it is possible to generate time usage per activity and total time on the project")
    public void itIsPossibleToGenerateTimeUsagePerActivityAndTotalTimeOnTheProject() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a project exists")
    public void aProjectExists() {
        startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        project = new Project("000001", "test-project", null, null)
    }

    @Given("the project has a project leader")
    public void theProjectHasAProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee is assigned as project leader")
    public void theEmployeeIsAssignedAsProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the assignment fails")
    public void theAssignmentFails() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
