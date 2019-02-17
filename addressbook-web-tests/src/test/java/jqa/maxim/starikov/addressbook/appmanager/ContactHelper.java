package jqa.maxim.starikov.addressbook.appmanager;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    fillField(By.name("firstname"), contactData.getName());
    fillField(By.name("lastname"), contactData.getLastname());
    fillField(By.name("address"), contactData.getAddress());
    fillField(By.name("email"), contactData.getEmail());
    fillField(By.name("mobile"), contactData.getPhone());;
  }
}
