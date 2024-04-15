Feature: Deleting activities
    Description: An employee is deleting activities
    Actor: Employee


Background:
    Given an employee exists

Scenario: delete Activity
    Given 1 activity exists
    When a user tries to delete an activity
    Then the activity no longer exists

Scenario: user deletes an activity in a project with multiple activities
    Given 2 activity exists
    When a user tries to delete an activity
    Then the activity no longer exists

Scenario: activity with registered hours is deleted
    Given 1 activity exists
    And a user has spent time on the activity
    When a user tries to delete an activity
    Then the user is warned that someone has spent time on the activity
    When the user accepts
    Then the activity no longer axists

Scenario: activity with registered hours is not deleted
    Given 1 activity exists
    And a user has spent time on the activity
    When a user tries to delete an activity
    Then the user is warned that someone has spent time on the activity
    When the user declines
    Then an activity exists