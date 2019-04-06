package jqa.maxim.starikov.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends BaseHelper{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    fillField(By.name("username"), username);
    fillField(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    fillField(By.name("password"), password);
    fillField(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
