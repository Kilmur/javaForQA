package jqa.maxim.starikov.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseHelper {

  protected ApplicationManager app;
  protected WebDriver wd;

  public BaseHelper(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
  }

  public void fillField(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }

  }

  protected void attachFile(By locator, File file) {
    if (file != null) {
      wd.findElement(locator).sendKeys(file.getAbsolutePath());
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

  // выбрать запись по идентификатору
  public void selectItemById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
