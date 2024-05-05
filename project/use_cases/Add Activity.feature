# Probably written by Martin and Lucia

Feature: Adding / creating activities
    Description: A user is adding activities.
    Actors: Project leader or User

Background:
    When 1 employees are created
    And 1 projects are created

Scenario: Add activity starting in week 4 and ending in week 7
    When the employee adds an activity with a start week 4 and end week 7
    Then 1 activities exist
    And the activity starts in week 4
    And the activity ends in week 7

Scenario: Add reserved activity for 16/9
    When the employee adds a reserved activity for the day 16/9
    Then 1 reserved activities exist
    And the activity ends at 16/9
