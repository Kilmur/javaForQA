package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ModificationContactTest extends TestBase {

  @Test
  public void testModificationContact() {
    app.getNavigationHelper().goToPage("home");
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Miami", "ivan@ivan.com", "929020"));
    }
    app.getNavigationHelper().selectItem(before - 1);
    app.getContactHelper().clickEdit();
    app.getContactHelper().fillContactForm(new ContactData("Petr", "Ivanov", "Paris", "petr@ivanov.com", "921312320"));
    app.getNavigationHelper().clickUpdate();
    app.getNavigationHelper().goToPage("home");
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
