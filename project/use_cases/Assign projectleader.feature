Feature: Assign project leader
    Description: A project leader is assigned to a project
    Actors: User

Background:
    Given a project exists
    And an employee exists

Scenario: Assigning project leader in unassigned project
    Given the project has no project leader
    When the employee is assigned as project leader
    Then the assignment is successful

Scenario: Changing project leader
    Given the project has a project leader
    When the employee is assigned as project leader
    Then the assignment is successful