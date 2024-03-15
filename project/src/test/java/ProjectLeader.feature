Feature: Project leader options

Scenario: Project Leader generates report
  Given a project assist, and a Project Leader is assigned to it .
  When the Project Leader generates the report.
  Then the Project Leader is prompted to choose a location to save the report
  And an excel file is created and saved to the disk
  And the Project Leader gets a notification that the report is generated