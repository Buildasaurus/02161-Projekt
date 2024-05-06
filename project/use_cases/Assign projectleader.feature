# Probably written by Martin and Lucia

Feature: Assign project leader
    Description: A project leader is assigned to a project
    Actors: User

Background:
    When 1 employees are created

Scenario: Assigning project leader in unassigned project
    When 1 projects are created
    And the project has no project leader
    And the employee is assigned as project leader
    Then the assignment is successful

Scenario: Changing project leader
    When 1 projects are created
    Given the project has a project leader
    When the employee is assigned as project leader
    Then the assignment is successful
