Feature: Check Overview
    Description: A project leader is looking at the overview of a project
    Actors: Project leader

Scenario: Project leader checks overview
    Given a project leader exists.
    And 2 project activities exists with expected duration of 10 half-hours each
    And the employee spends 5 half-hours on the activity
    And the employee spends 3 half-hours on the other activity
    Then the total time spent on the project is 8 half-hours
    And the progress of the project is 0.4
