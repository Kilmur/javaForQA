package jqa.maxim.starikov.addressbook.appmanager;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void clickEdit(int id) {
    WebElement element = wd.findElement(By.cssSelector("input[id='" + id + "']"));
    element.findElement(By.xpath("./../following-sibling::td[7]//img")).click();
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

  public void modifyContact(ContactData contact) {
    selectItemById(contact.getId());
    clickEdit(contact.getId());
    fillContactForm(contact);
    clickUpdate();
    goToPage("home");
  }

  public void deleteContact(ContactData contact) {
    selectItemById(contact.getId());
    clickDelete();
    getWD().switchTo().alert().accept();
    goToPage("home");
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts getContactSet() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withName(firstName).withLastname(lastName));
    }
    return contacts;
  }
}
