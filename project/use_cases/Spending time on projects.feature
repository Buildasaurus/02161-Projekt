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