# Probably written by Jonathan and Lucia

Feature: Check Overview
    Description: A project leader is looking at the overview of a project
    Actors: Project leader

Background: 
    When 1 projects are created

Scenario: Project leader checks overview
    Given the project has a project leader
    And 2 project activities exists with expected duration of 10 half-hours each
    And the employee spends 5 half-hours on activity 0
    And the employee spends 3 half-hours on activity 1
    Then the total time spent on the project is 8 half-hours
    And the progress of the project is 0.4
