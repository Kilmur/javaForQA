package jqa.maxim.starikov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  // метод сохранения заполненной формы
  public void submitCreation() {
    click(By.name("submit"));
  }


}
