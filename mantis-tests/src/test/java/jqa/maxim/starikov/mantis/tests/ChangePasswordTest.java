package jqa.maxim.starikov.mantis.tests;

import jqa.maxim.starikov.mantis.models.MailMessage;
import jqa.maxim.starikov.mantis.models.UserData;
import jqa.maxim.starikov.mantis.models.Users;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.getMailHelper().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    // входим администратором
    app.getNavigationHelper().login(app.getProperty("web.loginAdmin"), app.getProperty("web.passwordAdmin"));
    // загружаем возможных пользователей
    Users users = app.getDbHelper().getUsers();
    // выбираем случайного пользователя
    UserData user = users.iterator().next();
    // проверим не выбран ли администратор
    if (user.getUsername().equals(app.getProperty("web.loginAdmin"))) {
      user = users.iterator().next();
    }
    // переходим на страницу пользователей
    app.getRegistrationHelper().goToPage("Manage Users");
    // кликаем по пользователю
    app.getDriver().findElement(By.linkText(user.getUsername())).click();
    // нажимаем Reset Password
    app.getNavigationHelper().click(By.cssSelector("input[value='Reset Password']"));
    // ожидаем почту
    List<MailMessage> mailMessages = app.getMailHelper().waitForMail(1, 10000);
    String confirmationLink = app.getMailHelper().findConfirmationLink(mailMessages, user.getEmail());
    String password = "passwd";
    app.getRegistrationHelper().finish(confirmationLink, password);
    // проверяем возможность входа
    assertTrue(app.newSession().login(user.getUsername(), password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.getMailHelper().stop();
  }
}
