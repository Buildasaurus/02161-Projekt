# Probably written by Jonathan and Lucia

Feature: Creating reports based on a project
    Description: A projectleader is creating a report on the project they are in
    Actors: Project leader

Background:
    Given 1 projects are created
    Given a Project Leader is assigned to the project
    Given nothing exists with the name "report.csv"

Scenario: Project Leader generates report
    Given 1 activities are created in the project
    When the Project Leader generates the report.
    Then a non-empty report is saved in our default folder
    And the report has listed activities

Scenario: Project Leader generates report in a project with no activities
    When the Project Leader generates the report.
    Then a non-empty report is saved in our default folder
    And the report does not have listed activities

Scenario: Project Leader generates report in a project with no start and end date
    Given the project has no start and end date
    When the Project Leader generates the report.
    Then a non-empty report is saved in our default folder
    And the report does not have listed activities

Scenario: Project Leader generates report but existing folder exists
    Given a folder exists with the name "report.csv"
    When the Project Leader generates the report.
    Then an exception is thrown with the message "Folder already exists at this name: report.csv"
