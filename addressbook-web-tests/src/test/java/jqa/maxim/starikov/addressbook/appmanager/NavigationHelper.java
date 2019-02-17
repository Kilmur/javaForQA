package jqa.maxim.starikov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

  private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  // метод сохранения заполненной формы
  public void submitCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void clickOnButton(String s) {
    wd.findElement(By.linkText(s)).click();
  }
}
