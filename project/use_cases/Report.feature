Feature: Creating reports based on a project
    Description: A projectleader is creating a report on the project they are in
    Actors: Project leader

Background:
    Given 1 projects are created
    Given a Project Leader is assigned to the project

Scenario: Project Leader generates report
    Given 1 activities are created in the project
    When the Project Leader generates the report.
    Then a non-empty report is saved in our default folder
    And the report has listed activities

Scenario: Project Leader generates report in a project with no activities
    When the Project Leader generates the report.
    Then a non-empty report is saved in our default folder
    And the report does not have listed activities


