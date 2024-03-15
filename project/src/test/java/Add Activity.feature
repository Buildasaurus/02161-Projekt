Feature: Adding / creating activities
    Description: A user is manipulating activities. This can be a leader is adding / creating activities
    or a user adding a vacation activity to himself
    Actors: Project leader

Background:
    Given a project leader exists.

Scenario: Add expected activity end at week 7
    When a project leader adds an activity with expected end in 7 weeks
    Then The activity ends in 7 weeks

Scenario:  Add expected activity start at week 4
    When a project leader adds an activity with expected end in 4 weeks
    Then The activity ends in 4 weeks
