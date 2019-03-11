package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

  @Test
  public void testCreateContact() {
    Contacts before = app.getContactHelper().getContactSet();
    ContactData contact = new ContactData().withName("Ivan").withLastname("Ivanov");
    app.getContactHelper().createContact(contact);
    Contacts after = app.getContactHelper().getContactSet();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

}
