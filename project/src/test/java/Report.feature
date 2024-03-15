Feature: Project leader options

Background:
    Given a project exists, and a Project Leader is assigned to it .

Scenario: Project Leader generates report
    When the Project Leader generates the report.
    Then the Project Leader is prompted to choose a location to save the report
    And an excel file is created and saved to the disk with the project data
    And the Project Leader gets a notification that the report is generated

