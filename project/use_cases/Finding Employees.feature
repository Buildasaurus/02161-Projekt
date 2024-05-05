# Created by jonat at 05/05/2024
Feature: Finding employees
  # Enter feature description here

  Scenario: User searches for most available employee
    Given 1 projects are created
    Given an employee named "TestEmployee" is created
    And the employee adds an activity with a start week 3 and end week 6
    When the employee spends 3 half-hours on activity 1
    And an employee named "TestEmployee2" is created
    Then the most available employee from week 4 to 5 is "TestEmployee2"