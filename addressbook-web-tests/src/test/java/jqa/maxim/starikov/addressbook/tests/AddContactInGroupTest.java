package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // если нет созданных контактов создадим
    if (app.getDbHelper().contacts().size() == 0) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan").withLastname("Ivanov"));
    }
    // если нет созданных групп создадим
    if (app.getDbHelper().groups().size() == 0) {
      app.getNavigationHelper().goToPage("groups");
      app.getGroupHelper().createGroup(new GroupData().withName("Новая группа"));
    }
  }

  @Test
  public void addContactInGroupTest() {

    Contacts allContacts = app.getDbHelper().contacts();
    System.out.println("Все контакты-" + allContacts);
    ContactData contact = allContacts.iterator().next();
    System.out.println("Выбранный контакт-" + contact);

    // получим группу для добавления контакта
    GroupData group = getGroupForAddingInContact(contact);
    System.out.println("Выбранная группа-" + group);

    Groups allGroupsContactBefore = contact.getGroups();

    addContactInGroup(contact, group);

    app.getNavigationHelper().goToPage("home");
    
    Groups after = app.getDbHelper().getContactById(contact.getId()).getGroups();

    assertThat(after, equalTo(allGroupsContactBefore.withAdded(group)));
  }


}
