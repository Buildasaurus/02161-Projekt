Feature: Add employee to activity
  Description: A project leader adds an employee to an activity, or an employee adds themselves to an activity
  Actors: project leader, employee

Background:
    Given an activity exists
    And an employee exists
    And a timeslot is selected

Scenario: Adding employee to project activity
    Given a project activity exists
    And the employee is available in the selected time slot
    And there is another employee
    When the other employee adds the employee to the project activity
    Then the employee is assigned to the activity

Scenario: Employee is reserved
    Given the employee is assigned to a reserved activity in the selected time slot
    When the employee is added to an activity
    Then the employee is not assigned to the activity

Scenario: Adding self to reserved activity
    Given a reserved activity exists
    And the employee is available in the selected time slot
    When the employee adds themselves to the reserved activity
    Then the employee is assigned to the reserved activity
    And the employee has the reserved activity in their schedule