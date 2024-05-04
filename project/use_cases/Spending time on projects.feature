Feature: Spending time on projects
    Description: Employees spend time on projects
    Actor: Employee

Background:
    Given 1 employees are created

Scenario: Employee spends time on activity
    Given 1 projects are created
    And 1 activities are created in the project
    And the employee spends time on the activity
    Then the employee has 1 timeblocks attached

#//Scenario: Two employees spend time on activity
#//    When an employee named "testEmployee1" is created
#//    And an employee named "testEmployee2" is created
#//    And the employee named "testEmployee1" spends 30 minutes on the activity