package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class CreateContactTest extends TestBase {

  @Test
  public void testCreateContact() {
    Set<ContactData> before = app.getContactHelper().getContactSet();
    ContactData contact = new ContactData().withName("Ivan").withLastname("Ivanov");
    app.getContactHelper().createContact(contact);
    Set<ContactData> after = app.getContactHelper().getContactSet();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }

}
