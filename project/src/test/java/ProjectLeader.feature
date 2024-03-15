Feature: Project leader options

Background:
  Given a project assist, and a Project Leader is assigned to it .

Scenario: Project Leader generates report
  When the Project Leader generates the report.
  Then the Project Leader is prompted to choose a location to save the report
  And an excel file is created and saved to the disk
  And the Project Leader gets a notification that the report is generated

Scenario: Project leader checks overview
  When the project checks the overview
  Then it is possible to generate time usage per activity and total time on the project
