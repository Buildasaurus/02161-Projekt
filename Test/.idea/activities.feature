Feature: Activities

Scenario: Add expected activity end at week 7
  Given that there is an activity
  Then set the activity to end at week 7
Scenario:  Add expected activity start at week 4
  Given that there is an activity
  Then set the activity to start at week 4
Scenario: Delete Activity
  Given that there is an activity
  And the activity is deleted
  Then the activity is no longer in the database
Scenario: Put a worker on an activity
  Given that there is an available worker
  Then add the worker to the activity