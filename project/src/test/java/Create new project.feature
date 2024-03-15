Feature: Project leader options
    User: Employee
    Description: A project leader is creating an

Scenario: An employee creates a project
    Given an employee exists
    When the employee creates a project
    Then A project exists