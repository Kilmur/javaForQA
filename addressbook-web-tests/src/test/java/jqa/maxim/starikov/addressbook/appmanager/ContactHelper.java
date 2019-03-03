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

  public void clickEdit(int row) {
    String path = "(//img[@alt='Edit'])[" + row + "]";
    click(By.xpath(path));
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
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
