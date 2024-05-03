Feature: Deleting activities
    Description: An employee is deleting activities
    Actor: Employee


Background:
    Given 1 employees are created

Scenario: Delete Activity in project
    When 1 projects are created
    And 1 activities are created in the project
    When an employee tries to delete a project activity
    Then the project activity no longer exists

Scenario: Delete Activity in employee
    Given 1 activity are created in the employee
    When an employee tries to delete a reserved activity
    Then the reserved activity no longer exists

Scenario: User deletes an activity in a project with multiple activities
    Given 1 projects are created
    Given 2 activities are created in the project
    When the employee deletes a project activity
    Then 1 activities exist

Scenario: Activity with registered hours is deleted
    Given 1 projects are created
    And a project activity exists
    And the employee spends time on the activity
    When a user tries to delete an activity
    Then the user is warned that someone has spent time on the activity
    When the user accepts
    Then the activity no longer axists

Scenario: Activity with registered hours is not deleted
    Given a project activity exists
    And a user has spent time on the activity
    When a user tries to delete an activity
    Then the user is warned that someone has spent time on the activity
    When the user declines
    Then an activity exists