package jqa.maxim.starikov.mantis.tests;

import jqa.maxim.starikov.mantis.models.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

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
    String email = "user3@localhost";
    String user = "user3";
    app.getRegistrationHelper().start(user, email);
    List<MailMessage> mailMessages = app.getMailHelper().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String password = "password";
    app.getRegistrationHelper().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.getMailHelper().stop();
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m)-> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
