package org.test;

import java.util.List;

import org.application.Models.Employee;
import org.application.Models.SystemModel;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.application.Models.Project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class ProjectSteps {

    
    @Given("a Project Leader is assigned to the project")
    public void aProjectExistsAndAProjectLeaderIsAssignedToIt() {
        List<Project> projects = SystemModel.getProjects();
        assertNotEquals(projects.get(0).getProjectLeaderID(),"PROJECT LEADER NOT SET");
    }

    @Given("the project has no project leader")
    public void theProjectHasNoProjectLeader(){
        List<Project> projects = SystemModel.getProjects();
        assertEquals(projects.get(0).getProjectLeaderID(),"PROJECT LEADER NOT SET");
    }

    @When("the Project Leader generates the report.")
    public void theProjectLeaderGeneratesTheReport() {
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
        GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        // set endWeek to week 19.
        GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        Project project = new Project(1, "test-project", startWeek, endWeek);
        SystemModel.createNewProject(project);
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
