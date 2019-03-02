package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

  @Test
  public void testDeleteContact() {
    app.getNavigationHelper().goToPage("home");
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Miami", "ivan@ivan.com", "929020"));
    }
    app.getNavigationHelper().selectItem(before - 1);
    app.getContactHelper().clickDelete();
    app.getWD().switchTo().alert().accept();
    app.getNavigationHelper().goToPage("home");
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
