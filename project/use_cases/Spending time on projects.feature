Feature: Spending time on projects
    Description: Employees spend time on projects
    Actor: Employee

Background:
    Given 1 employees are created

Scenario: Employee spends time on activity
    Given 1 projects are created
    And 1 activities are created in the project
    And the employee spends 4 half-hours on the activity
    Then the employee has 1 timeblocks attached

#Scenario: Two employees spend time on activity
#//Scenario: Two employees spend time on activity
#//    When an employee named "testEmployee1" is created
#//    And an employee named "testEmployee2" is created
#//    And the employee named "testEmployee1" spends 30 minutes on the activity

Scenario: Employee spends 60 minutes on activity
    Given 1 projects are created
    And 1 activities are created in the project
    And the employee spends 2 half-hours on the activity
    Then the employee has 1 timeblocks attached
    And the employee has spent 2 half-hours on the activity