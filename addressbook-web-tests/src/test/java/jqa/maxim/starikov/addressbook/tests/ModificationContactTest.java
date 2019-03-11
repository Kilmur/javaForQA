package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class ModificationContactTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToPage("home");
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan").withLastname("Ivanov"));
    }
  }

  @Test
  public void testModificationContact() {
    Set<ContactData> before = app.getContactHelper().getContactSet();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData().withId(modifiedContact.getId()).withName("Petr").withLastname("Ivanov");
    app.getContactHelper().modifyContact(newContact);
    Set<ContactData> after = app.getContactHelper().getContactSet();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(newContact);
    Assert.assertEquals(after, before);
  }


}
