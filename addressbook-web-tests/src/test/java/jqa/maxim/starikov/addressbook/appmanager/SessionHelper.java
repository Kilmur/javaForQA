package jqa.maxim.starikov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String user, String password) {
    fillField(By.name("user"), user);
    fillField(By.name("pass"), password);
    click(By.xpath("//input[@value='Login']"));
  }
}
