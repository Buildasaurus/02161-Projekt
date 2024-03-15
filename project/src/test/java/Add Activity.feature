Feature: Adding / creating activities

Background:

Scenario: Add expected activity end at week 7
    When a user adds an activity with expected end in 7 weeks
    Then The activity ends in 7 weeks

Scenario:  Add expected activity start at week 4
    When a user adds an activity with expected start in 7 weeks
    Then The activity start in 7 weeks