Feature: Add employee to activity

Background:
  Given an activity exists
  And an employee exists

Scenario: Adding employee to project activity
  Given a project exists, the project has an activity, and a project leader is assigned to the project
  And the employee has free time in their schedule
  When the project leader adds the employee to the project activity
  Then the employee is assigned to the activity
  And the employee has the activity in their schedule

Scenario: Employee has full schedule
  Given the employee has no free time in their schedule
  When the employee is added to an activity
  Then the employee is not assigned to the activity
  And the employee does not have the activity in their schedule

Scenario: Adding self to general activity
  Given a general activity exists
  And the employee has free time in their schedule
  When the employee adds themself to a general activity
  Then the employee is assigned to the activity
  And the employee has the activity in their schedule