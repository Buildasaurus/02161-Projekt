Feature: Deleting activities
    Description: An employee is deleting activities
    Actor: Employee


Background:
    Given an employee exists

Scenario: Delete Activity in project
    Given a project is created
    Given 1 activity exists in the project
    When an employee tries to delete a project activity
    Then the project activity no longer exists

Scenario: Delete Activity in employee
    Given 1 activity exists in the employee
    When an employee tries to delete a reserved activity
    Then the reserved activity no longer exists

Scenario: User deletes an activity in a project with multiple activities
    Given 2 activity exists
    When a user tries to delete an activity
    Then the activity no longer exists

Scenario: Activity with registered hours is deleted
    Given 1 activity exists
    And a user has spent time on the activity
    When a user tries to delete an activity
    Then the user is warned that someone has spent time on the activity
    When the user accepts
    Then the activity no longer axists

Scenario: Activity with registered hours is not deleted
    Given 1 activity exists
    And a user has spent time on the activity
    When a user tries to delete an activity
    Then the user is warned that someone has spent time on the activity
    When the user declines
    Then an activity exists