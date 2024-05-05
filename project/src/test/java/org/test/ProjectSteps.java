package org.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;
import java.util.List;

import org.application.Models.Employee;
import org.application.Models.SystemModel;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
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
        project.removeProjectLeader();
    }

    @When("the Project Leader generates the report.")
    public void theProjectLeaderGeneratesTheReport() {
        Project project = SystemModel.getProjects().get(0);
        Report report = new Report(project);
        report.saveToDisk("");
    }

    @Then("a non-empty report is saved in our default folder")
    public void theReportIsSavedInOurDefaultFolder(){
        String fileName = "report.csv";
        Path filePath = Paths.get("", fileName);
        try
        {
            List<String> lines = Files.readAllLines(filePath);
            assert !lines.isEmpty();
            System.out.println("Contents of the file:");
            for (String line : lines) {
                System.out.println(line);
            }
        }
        catch (Exception e)
        {
            assert false;
        }
        assert Files.exists(filePath);
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

    @When("the project is deleted")
    public void theProjectIsDeleted() {
        Project testProject = SystemModel.getProjects().get(0);
        testProject.delete();
    }

    @When("a project with the name {string} is created")
    public void createProjectWithName(String projectName) {
        GregorianCalendar startWeek = new GregorianCalendar();
        startWeek.setWeekDate(2024, 17, 1);
        GregorianCalendar endWeek = new GregorianCalendar();
        endWeek.setWeekDate(2024, 19, 1);
        Project testProject = new Project(projectName, startWeek, endWeek);
        SystemModel.addProject(testProject);
    }

    @Then("a project with the name {string} exists")
    public void namedProjectExists(String expectedName) {
        assertNotNull(SystemModel.getProjectByName(expectedName));
    }

    @Then("the total time spent on the project is {int} half-hours")
    public void theTotalTimeSpentOnTheProjectIsHalfHours(Integer expectedTimeSpent) {
        Project project = SystemModel.getProjects().get(0);
        //calculate spent time
        Integer totalSpentTime = 0;
        for (ProjectActivity activity : project.getActivities()) {
            totalSpentTime += activity.calculateSpentTime();
        }
        //check if calculation equal to expected
        assertEquals(totalSpentTime, expectedTimeSpent);
    }

    @Then("the progress of the project is {double}")
    public void theProgressOfTheProjectIs(Double progressExpected) {
        Project project = SystemModel.getProjects().get(0);
        // Calculate total spent time and expected duration for all activities
        int totalSpentTime = 0;
        int totalExpectedDuration = 0;
        for (ProjectActivity activity : project.getActivities()) {
            totalSpentTime += activity.calculateSpentTime();
            totalExpectedDuration += activity.getExpectedDuration();
        }
        Double overallProgress = (double) totalSpentTime / totalExpectedDuration;
        //check if calculation equal to expected
        assertEquals(progressExpected, overallProgress);
    }

    @Then("the project activity no longer exists")
    public void theProjectActivityNoLongerExists() {
        Project project = SystemModel.getProjects().get(0);
        assertTrue(project.getActivities().isEmpty());
    }


}
