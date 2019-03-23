package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationContactTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.getDbHelper().contacts().size() == 0) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan").withLastname("Ivanov"));
    }
  }

  @Test
  public void testModificationContact() {
    Contacts before = app.getDbHelper().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData newContact = new ContactData().withId(modifiedContact.getId()).withName("Petr").withLastname("Ivanov")
      .withAddress("Chicago").withEmail("ivanovpetr@email.ru");
    app.getContactHelper().modifyContact(newContact);
    assertThat(app.getContactHelper().getContactCount(), equalTo(before.size()));
    Contacts after = app.getDbHelper().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
  }


}
