Feature: Creating reports based on a project
    Description: A projectleader is creating a report on the project they are in
    Actors: Project leader

Background:
    Given a project exists
    Given a Project Leader is assigned to the project

Scenario: Project Leader generates report
    When the Project Leader generates the report.
    And a csv file is created and saved to the disk with the project data
    Then a report is saved to the disk
