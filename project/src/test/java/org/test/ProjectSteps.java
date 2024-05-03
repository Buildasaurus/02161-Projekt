package org.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;

import org.application.Models.Employee;
import org.application.Models.SystemModel;
import org.application.Models.Project;
import org.application.Models.Report;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

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
        Employee projectLeader = new Employee("555555");
        Project project = SystemModel.getProjects().get(0);
        project.assignProjectLeader(projectLeader.getID());
    }

    @Given("the project has no project leader")
    public void theProjectHasNoProjectLeader(){
        Project project = SystemModel.getProjects().get(0);
        project.noProjectLeader();
    }

    @When("the Project Leader generates the report.")
    public void theProjectLeaderGeneratesTheReport() {
        Project project = SystemModel.getProjects().get(0);
        Report report = project.createReport();
    }


    @Then("a report is saved in our default folder")
    public void theReportIsSavedInOurDefaultFolder(){
        File f = new File("report.csv");
        assertTrue(f.exists());
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

    @Then("{int} projects exist")
    public void projectsExist(int numberOfExpectedProjects) {
        assertEquals(numberOfExpectedProjects,SystemModel.getProjects().size());
    }

}
