package steps;

import Utilities.RestAssuredExtension;
import org.junit.Before;

public class TestInitialize {

    @Before
    public void TestSetup(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
