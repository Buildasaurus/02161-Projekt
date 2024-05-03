Feature: Creating reports based on a project
    Description: A projectleader is creating a report on the project they are in
    Actors: Project leader

Background:
    Given 1 projects are created
    Given a Project Leader is assigned to the project

Scenario: Project Leader generates report
    When the Project Leader generates the report.
    Then a report is saved in our default folder
