package jqa.maxim.starikov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
  protected WebDriver wd;

  public BaseHelper(WebDriver wd) {
    this.wd = wd;
  }

  protected void fillField(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }

  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  // выбрать запись
  public void selectItem(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void goToPage(String title) {
    click(By.linkText(title));
  }

  // метод сохранения заполненной формы
  public void submitCreation() {
    click(By.name("submit"));
  }

  // сохранение после модификации записи
  public void clickUpdate() {
    click(By.name("update"));
  }

  public WebDriver getWD() {
    return wd;
  }
}
