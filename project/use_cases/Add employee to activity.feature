Feature: Add employee to activity
  Description: A project leader adds an employee to an activity, or an employee adds themselves to an activity
  Actors: project leader, employee

Background:
  When 1 projects are created
  And 1 activities are created in the project

Scenario: Adding employee to activity
  When 1 employees are created
  And the employee is added to the project activity
  Then 1 employees have been added to the project activity

Scenario: Removing employee from activity
  When 1 employees are created
  And the employee is added to the project activity
  And the employee is removed from the project activity
  Then 0 employees have been added to the project activity

Scenario: Assign employee with name
  When an employee named "testEmployee" is created
  And the employee named "testEmployee" is assigned to the project activity
  Then the employee is assigned to the activity

Scenario: Assign same employee twice to activity
  When 1 employees are created
  And the employee is added to the project activity
  And the employee is added to the project activity
  Then 1 employees have been added to the project activity
