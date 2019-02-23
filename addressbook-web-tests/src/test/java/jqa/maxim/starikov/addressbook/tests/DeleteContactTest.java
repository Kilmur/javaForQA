package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

  @Test
  public void testDeleteContact() {
    app.getNavigationHelper().goToPage("home");
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Miami", "ivan@ivan.com", "929020"));
    }
    app.getNavigationHelper().selectItem();
    app.getContactHelper().clickDelete();
    app.getWD().switchTo().alert().accept();
    app.getNavigationHelper().goToPage("home");
  }
}
