Feature: Generate report
Scenario: Project Leader generateres report
  Given a project assist, and a Project Leader is assigned to it .
  Given the Project Leader generates the report.
  Then the Project Leader is prompted to choose a location to save the report
  Then an excel file is created and saved to the disk
  Then the Project Leader gets a notification that the report is generated