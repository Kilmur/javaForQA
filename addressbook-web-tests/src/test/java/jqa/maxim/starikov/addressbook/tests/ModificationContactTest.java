package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ModificationContactTest extends TestBase {

  @Test
  public void testModificationContact() {
    app.getNavigationHelper().goToPage("home");
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", null, null, null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    System.out.println("BEFORE SIZE - " + before.size());
    app.getNavigationHelper().selectItem(before.size() - 1);
    app.getContactHelper().clickEdit(before.size());
    ContactData newContact = new ContactData(before.get(before.size() - 1).getId(), "Petr", "Ivanov", null, null, null);
    app.getContactHelper().fillContactForm(newContact);
    app.getNavigationHelper().clickUpdate();
    app.getNavigationHelper().goToPage("home");
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(newContact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }
}
