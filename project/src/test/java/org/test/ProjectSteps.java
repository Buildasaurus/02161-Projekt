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
//import org.junit.After;

//TODO look for duplicate steps that could be consolidated
//TODO implement steps

public class ProjectSteps {
    @io.cucumber.java.After
    public void tearDown() {
        SystemModel.reset();
    }

    @Given("a Project Leader is assigned to the project")
    public void aProjectExistsAndAProjectLeaderIsAssignedToIt() {
        List<Project> projects = SystemModel.getProjects();
        assertNotEquals(projects.get(0).getProjectLeaderID(),"PROJECT LEADER NOT SET");
    }

    @Given("the project has no project leader")
    public void theProjectHasNoProjectLeader(){
        Project project = SystemModel.getProjects().get(0);
        project.noProjectLeader();
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

    @When("{int} projects are created")
    public void createProjects(int numberOfProjectsToCreate) {
        for (int i = 0; i < numberOfProjectsToCreate; i++) {
            GregorianCalendar startWeek = new GregorianCalendar();
            startWeek.setWeekDate(2024, 17, 1);
            // set endWeek to week 19.
            GregorianCalendar endWeek = new GregorianCalendar();
            endWeek.setWeekDate(2024, 19, 1);
            new Project(Integer.toString(i), startWeek, endWeek);
        }
    }

    @Given("the project has a project leader")
    public void theProjectHasAProjectLeader() {
        Project project = SystemModel.getProjects().get(0);
        Employee dummyEmployee = new Employee("111111");
        project.assignProjectLeader(dummyEmployee.getID());
    }

    @When("the employee is assigned as project leader")
    public void theEmployeeIsAssignedAsProjectLeader() {
        Employee employee = SystemModel.getEmployees().get(0);
        Project project = SystemModel.getProjects().get(0);
        project.assignProjectLeader(employee.getID());
    }

    @Then("the assignment is successful")
    public void theAssignmentIsSuccessful() {
        Employee employee = SystemModel.getEmployees().get(0);
        Project project = SystemModel.getProjects().get(0);
        assertEquals(employee.getID(),project.getProjectLeaderID());
    }

    @When("{int} projects exist")
    public void projectsExist(int numberOfExpectedProjects) {
        assertEquals(numberOfExpectedProjects,SystemModel.getProjects().size());
    }

}
