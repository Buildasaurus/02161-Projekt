Feature: Set used time
    Description: The User sets the used time on an activity
    Actors: User

Scenario: Worker sets used time on activity
    Given 1 activity exists
    When a worker sets the used time on the activity
    Then the activity has the used time, set by the worker.
