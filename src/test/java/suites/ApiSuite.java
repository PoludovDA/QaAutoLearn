package suites;

import api.reqres.ReqressNoPojoTest;
import api.reqres.ReqressPojoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import readProperties.PropertiesTest;

import static org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({ReqressNoPojoTest.class, ReqressPojoTest.class, PropertiesTest.class})
public class ApiSuite {
}
