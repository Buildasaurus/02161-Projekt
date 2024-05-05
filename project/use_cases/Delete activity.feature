Feature: Deleting activities
    Description: An employee is deleting activities
    Actor: Employee

Background:
    Given 1 employees are created

Scenario: Delete Activity in project
    When 1 projects are created
    And 1 activities are created in the project
    And the employee gets added to the project activity
    When an employee deletes a project activity
    Then 0 activities exist
    And 0 activities are assigned to employees

Scenario: Delete Activity in employee
    Given 1 activity are created in the employee
    When an employee tries to delete a reserved activity
    Then the reserved activity no longer exists

Scenario: User deletes an activity in a project with multiple activities
    Given 1 projects are created
    Given 2 activities are created in the project
    When the employee deletes a project activity
    Then 1 activities exist

Scenario: Activity with registered hours is deleted
    When 1 projects are created
    And 1 activities are created in the project
    And a user spends time on the activity
    When an employee deletes a project activity
    Then the project activity no longer exists
    And the time spent is no longer registered