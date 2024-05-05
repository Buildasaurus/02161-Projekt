Feature: Edit Activity
  # Enter feature description here
  
  Background:
    Given 1 projects are created
    And 1 employees are created

  Scenario: Editing an existing project activity
    Given a project activity called "Test" is created with the employee "0" assigned
    When the user changes the project activity to start in week 2, ends in week 3, and has "0" as project leader
    Then the name of the project activity is "Test"
    And the activity starts in week 2
    And the activity ends in week 3

  Scenario: Editing an existing Reserved activity
    Given a reserved activity exists
    When the user changes the reserved activity to start in week 2, ends in week 3
    Then the name of the project activity is "sample"
    And the activity starts in week 2
    And the activity ends in week 3
