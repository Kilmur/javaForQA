package jqa.maxim.starikov.addressbook.appmanager;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

  public void clickEdit() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void createContact(ContactData group) {
    click(By.linkText("add new"));
    fillContactForm(group);
    submitCreation();
    goToPage("home");
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String[] fieldsContact = element.getText().split(" ");
      ContactData contact = new ContactData(fieldsContact[1], fieldsContact[0], fieldsContact[2], fieldsContact[3], fieldsContact[4]);
      contacts.add(contact);
    }
    return contacts;
  }
}
