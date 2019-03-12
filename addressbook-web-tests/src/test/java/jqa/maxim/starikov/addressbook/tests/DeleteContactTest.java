package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToPage("home");
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan").withLastname("Ivanov"));
    }
  }

  @Test
  public void testDeleteContact() {
    Contacts before = app.getContactHelper().getContactSet();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().deleteContact(deletedContact);
    assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() - 1));
    Contacts after = app.getContactHelper().getContactSet();
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
