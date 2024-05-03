Feature: Create new project
    Description: An employee is creating a new project
    Actor: Employee

Scenario: An employee creates a project
    When 1 employees are created
    And 1 projects are created
    Then 1 projects exist
