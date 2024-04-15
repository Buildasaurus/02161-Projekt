Feature: Creating reports based on a project
    Description: A projectleader is creating a report on the project they are in
    Actors: Project leader

Background:
    Given a project exists, and a Project Leader is assigned to it.

Scenario: project Leader generates report
    When the Project Leader generates the report.
    Then the Project Leader is prompted to choose a location to save the report
    And a csv file is created and saved to the disk with the project data
    And the Project Leader gets a notification that the report is generated
