# Written by Martin

Feature: Spending time on projects
    Description: Employees spend time on projects
    Actor: Employee

Background:
    Given 1 employees are created
    And 1 projects are created
    And 1 activities are created in the project

Scenario: Employee spends time on activity
    And the employee spends 4 half-hours on the activity
    Then the employee has 1 timeblocks attached

Scenario: Employee spends 60 minutes on activity
    And the employee spends 2 half-hours on the activity
    Then the employee has 1 timeblocks attached
    And the employee has spent 2 half-hours on the activity