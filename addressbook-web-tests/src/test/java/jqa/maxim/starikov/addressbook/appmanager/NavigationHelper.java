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

  public void goToPage(String title) {
    click(By.linkText(title));
  }

  // выбрать запись
  public void selectItem() {
    click(By.name("selected[]"));
  }

  // сохранение после модификации записи
  public void clickUpdate() {
    click(By.name("update"));
  }


}
