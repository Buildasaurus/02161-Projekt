Feature: Deleting activities

Scenario: Delete Activity
    Given 1 activity exists
    When the activity is deleted
    Then the activity no longer exists

Scenario: User deletes an activity in a project with multiple activities
    Given 2 activity exists
    When a user tries to delete an activity
    Then the activity no longer exists

