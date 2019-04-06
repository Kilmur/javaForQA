package jqa.maxim.starikov.mantis.tests;

import jqa.maxim.starikov.mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTest extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));

  }
}
