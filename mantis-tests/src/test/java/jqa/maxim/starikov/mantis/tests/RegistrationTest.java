package jqa.maxim.starikov.mantis.tests;

import jqa.maxim.starikov.mantis.models.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.getMailHelper().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
//    app.getJamesHelper().createUser(user, password);
    app.getRegistrationHelper().start(user, email);
     List<MailMessage> mailMessages = app.getMailHelper().waitForMail(2, 10000);
//    List<MailMessage> mailMessages = app.getJamesHelper().waitForMail(user, password, 120000);
    String confirmationLink = app.getMailHelper().findConfirmationLink(mailMessages, email);
    app.getRegistrationHelper().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.getMailHelper().stop();
  }


}
