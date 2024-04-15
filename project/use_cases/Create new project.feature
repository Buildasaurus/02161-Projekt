Feature: Create new project
    Description: An employee is creating a new project
    Actor: Employee


Scenario: an employee creates a project
    Given an employee exists
    When the employee creates a project
    Then a project exists