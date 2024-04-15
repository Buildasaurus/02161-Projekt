Feature: Adding / creating activities
    Description: A user is adding activities.
    Actors: Project leader or User

Background:
    Given an employee exists

Scenario: Add activity ending in week 7
    When the employee adds an activity with an end date in week 7
    Then An activity is created
    And The activity ends in week 7

Scenario: Add activity starting in week 4
    When the employee adds an activity with an end date in week 4
    Then An activity is created
    And The activity starts in week 4

Scenario: Add reserved activity for 16/9
    When the employee adds a reserved activity for the day 16/9
    Then A reserved activity is created
    And The activity ends in 16/9