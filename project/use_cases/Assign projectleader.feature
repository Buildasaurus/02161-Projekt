Feature: Assign project leader
    Description: A project leader is assigned to a project
    Actors: User

Background:
    Given an employee exists

Scenario: Assigning project leader
    When a project is created
    Given the project has no project leader
    When the employee is assigned as project leader
    Then the assignment is successful

Scenario: Project already has a project leader
    When a project is created
    Given the project has a project leader
    When the employee is assigned as project leader
    Then the assignment fails