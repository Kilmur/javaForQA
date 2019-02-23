package jqa.maxim.starikov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  // сохранение после модификации записи
  public void clickUpdate() {
    click(By.name("update"));
  }


}
