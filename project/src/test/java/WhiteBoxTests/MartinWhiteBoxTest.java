package WhiteBoxTests;

import org.application.Models.Activity;
import org.application.Models.Project;
import org.application.Models.ProjectActivity;
import org.application.Models.SystemModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;

public class MartinWhiteBoxTest {
    //reset after every test
    @Before
    public void start() {
        SystemModel.reset();
    }


    // Test case A: activities list contains an activity with the expected name.
    @Test
    public void testFindActivityWithNameCaseA() {
        GregorianCalendar projectStartTime = new GregorianCalendar();
        projectStartTime.setWeekDate(2024, 21, 1);
        GregorianCalendar projectEndTime = new GregorianCalendar();
        projectEndTime.setWeekDate(2024, 24, 1);
        Project testProject = new Project("testProject", projectStartTime, projectEndTime);

        GregorianCalendar activityStartTime = new GregorianCalendar();
        activityStartTime.setWeekDate(2024, 22, 1);
        GregorianCalendar activityEndTime = new GregorianCalendar();
        activityEndTime.setWeekDate(2024, 23, 1);

        String expectedActivityName = "testActivity";
        Activity testActivity = new ProjectActivity(activityStartTime,activityEndTime,3,expectedActivityName,testProject);
        assertEquals(testActivity,SystemModel.findActivityWithName(expectedActivityName));
    }

    // Test case B: activities list contains 2 activities with unexpected names.
    @Test
    public void testFindActivityWithNameCaseB() {
        GregorianCalendar projectStartTime = new GregorianCalendar();
        projectStartTime.setWeekDate(2024, 21, 1);
        GregorianCalendar projectEndTime = new GregorianCalendar();
        projectEndTime.setWeekDate(2024, 24, 1);
        Project testProject = new Project("testProject", projectStartTime, projectEndTime);

        GregorianCalendar activityStartTime = new GregorianCalendar();
        activityStartTime.setWeekDate(2024, 22, 1);
        GregorianCalendar activityEndTime = new GregorianCalendar();
        activityEndTime.setWeekDate(2024, 23, 1);

        new ProjectActivity(activityStartTime,activityEndTime,4,"notTestActivity1",testProject);
        new ProjectActivity(activityStartTime,activityEndTime,57,"notTestActivity2",testProject);
        String expectedActivityName = "testActivity";
        assertNull(SystemModel.findActivityWithName(expectedActivityName));
    }

    // Test case C: activities list is empty
    @Test
    public void testFindActivityWithNameCaseC() {
        String expectedActivityName = "testActivity";
        assertNull(SystemModel.findActivityWithName(expectedActivityName));
    }
}
