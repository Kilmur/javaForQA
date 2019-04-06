package jqa.maxim.starikov.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    getWD().get(app.getProperty("web.baseUrl")+ "/login_page.php");
    fillField(By.name("username"), username);
    fillField(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }


}
