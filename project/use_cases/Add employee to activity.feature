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
  When the employee is added to the project activity
  And the employee is removed from the project activity
  Then 0 employees have been added to the project activity

#//  And there is another employee
#//  When the other employee adds the employee to the project activity
#//  Then the employee is assigned to the activity

#  //Scenario: Employee is reserved
#  //    Given the employee is assigned to a reserved activity in the selected time slot
#  //    When the employee is added to an activity
#  //    Then the employee is not assigned to the activity

