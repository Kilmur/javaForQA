package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.getContactHelper().getContactSet();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData().withId(modifiedContact.getId()).withName("Petr").withLastname("Ivanov");
    app.getContactHelper().modifyContact(newContact);
    assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));
    Contacts after = app.getContactHelper().getContactSet();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(newContact)));
  }


}
