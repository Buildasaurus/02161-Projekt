Feature: Activities
    Description: The user pays his fines
    Actors: User

Scenario: Add expected activity end at week 7
    When a user adds an activity with expected end in 7 weeks
    Then The activity ends in 7 weeks

Scenario:  Add expected activity start at week 4
    When a user adds an activity with expected start in 7 weeks
    Then The activity start in 7 weeks

Scenario: Delete Activity
    Given 1 activity exists
    When the activity is deleted
    Then the activity no longer exists

Scenario: User deletes an activity in a project with multiple activities
    Given 2 activity exists
    When a user tries to delete an activity
    Then the activity no longer exists

Scenario: Project leader adds new employee to an activity
    Given 1 activity exists
    When a project leader adds a new employee to an activity
    Then the employee is in the activity.

Scenario: Worker sets used time on activity
    Given 1 activity exists
    When a worker sets the used time on the activity
    Then the activity has the used time, set by the worker.

Scenario:
