# Written by Martin

Feature: Project creation/deletion
    Description: An employee creates a new project
    Actor: Employee

Scenario: An employee creates a project
    When 1 employees are created
    And 1 projects are created
    Then 1 projects exist

Scenario: An employee deletes a project
    When 1 projects are created
    And the project is deleted
    Then 0 projects exist

Scenario: Project with specific name is created
    When a project with the name "testProject" is created
    Then a project with the name "testProject" exists

Scenario: An employee is created with a known project leader
    When A project with a project leader named "leader" is created
    Then the project leader of the project is "leader"
