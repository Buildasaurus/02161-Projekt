Feature: Set used time
    Description: The User sets the used time on an activity.
    Actors: User

Scenario: worker sets used time on activity
    Given 1 activity exists
    And an employee exists
    When the employee adds used time to the activity
    Then the activity has the used time, set by the employee