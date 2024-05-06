package JUnitTests;

import org.application.Models.Employee;
import org.application.Models.Project;
import org.application.Models.SystemModel;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConstructorTests
{
    //reset before every test
    @Before
    public void start() {
        SystemModel.reset();
    }

    @Test
    public void ProjectActivityConstructor() {
    }
}
