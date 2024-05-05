# Created by jonat at 05/05/2024
Feature: Delete employee
  # Enter feature description here

  Scenario: Deleting an employee:
    Given 1 projects are created
    Given an employee named "Me" is created
    Given 1 project activities exists with expected duration of 4 half-hours each
    And the employee spends 2 half-hours on activity 0
    When the employee named "Me" is deleted
    Then the progress of the project is 0.5
    And 0 employees exist