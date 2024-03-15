Feature: Check Overview
    Description: A project leader is looking at the overview of a project
    Actors: Project leader

Background:
    A project leader exists.

Scenario: Project leader checks overview
    When the project checks the overview
    Then it is possible to generate time usage per activity and total time on the project
