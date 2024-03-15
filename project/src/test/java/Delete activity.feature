Feature: Deleting activities

Scenario: Delete Activity
    Given 1 activity exists
    When the activity is deleted
    Then the activity no longer exists

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