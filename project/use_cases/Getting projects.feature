Feature: Getting projects
    Description: An employee creates a new project
    Actor: Employee

Scenario: Get project by name
    When 1 employees are created
    And 1 projects are created
    Then 1 projects exist
