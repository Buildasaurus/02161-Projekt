# Written by Martin

Feature: Create project
    Description: An employee creates a new project
    Actor: Employee

Scenario: Create project
    When 1 employees are created
    And 1 projects are created
    Then 1 projects exist
