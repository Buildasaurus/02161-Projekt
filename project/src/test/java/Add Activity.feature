Feature: Adding / creating activities
    Description: A user is manipulating activities. This can be a leader is adding / creating activities
    or a user adding a vacation activity to themself.
    Actors: Project leader or User

Background:

Scenario: Add expected activity end at week 7
    Given a project leader exists.
    When a project leader adds an activity with expected end in 7 weeks
    Then The activity ends in 7 weeks

Scenario: Add expected activity start at week 4
    Given a project leader exists.
    When a project leader adds an activity with expected end in 4 weeks
    Then The activity starts in 4 weeks

Scenario: Add sick activity
    Given an employee exists
    When the employee adds a sick activity on the next wednesday
    Then the employee is booked the next wednesday

Scenario: Add vacation activity
    Given an employee exists
    When the employee adds a vacation activity on the next wednesday
    Then the employee is booked the next wednesday