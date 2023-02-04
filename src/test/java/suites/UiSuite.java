package suites;

import gismeteo.GismeteoTest;
import hh.HhTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({HhTest.class, GismeteoTest.class})
public class UiSuite {

    @Before
    public void setBefore() {
        System.out.println("Запущены UI тесты");
    }

    @After
    public void setAfter() {
        System.out.println("Отработаны UI тесты");
    }
}
