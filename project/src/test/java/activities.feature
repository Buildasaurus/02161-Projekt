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

Scenario: User deletes an activity in a project with multiple activities
  Given a user tries to delete an activity
  And the project the activity is inside has multiple activities
  Then delete the activity

Scenario: User deletes an activity in a project with only 1 activity
  Given a user tries to delete an activity
  And the project the activity is inside has only 1 activity
  Then delete the activity
  And prompt the user for deleting the project
