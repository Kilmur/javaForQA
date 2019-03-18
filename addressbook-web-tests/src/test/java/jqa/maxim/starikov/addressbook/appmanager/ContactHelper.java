package jqa.maxim.starikov.addressbook.appmanager;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends BaseHelper {

  private Contacts contactCache;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    fillField(By.name("firstname"), contactData.getName());
    fillField(By.name("lastname"), contactData.getLastname());
    fillField(By.name("address"), contactData.getAddress());
    fillField(By.name("mobile"), contactData.getPhoneMobile());
    fillField(By.name("home"), contactData.getPhoneHome());
    fillField(By.name("work"), contactData.getPhoneWork());
    fillField(By.name("email"), contactData.getEmail());
    fillField(By.name("email2"), contactData.getEmail2());
    fillField(By.name("email3"), contactData.getEmail3());
    attachFile(By.name("photo"), contactData.getPhoto());
  }

  public void clickEdit(int id) {
    WebElement element = wd.findElement(By.cssSelector("input[id='" + id + "']"));
    element.findElement(By.xpath("./../following-sibling::td[7]//img")).click();
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void createContact(ContactData contact) {
    click(By.linkText("add new"));
    fillContactForm(contact);
    submitCreation();
    contactCache = null;
    goToPage("home");
  }

  public void modifyContact(ContactData contact) {
    selectItemById(contact.getId());
    clickEdit(contact.getId());
    fillContactForm(contact);
    clickUpdate();
    contactCache = null;
    goToPage("home");
  }

  public void deleteContact(ContactData contact) {
    selectItemById(contact.getId());
    clickDelete();
    getWD().switchTo().alert().accept();
    contactCache = null;
    goToPage("home");
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts getContactSet() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      contactCache.add(new ContactData().withId(id).withName(firstName).withLastname(lastName)
        .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    clickEdit(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    return new ContactData()
      .withId(contact.getId())
      .withName(firstName)
      .withLastname(lastName)
      .withAddress(address)
      .withPhoneHome(home)
      .withPhoneMobile(mobile)
      .withPhoneWork(work)
      .withEmail(email)
      .withEmail2(email2)
      .withEmail3(email3);
  }
}
