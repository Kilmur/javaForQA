package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {

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
  public void testDeleteContactFromGroup() {
    // получаем контакт
    ContactData contact = app.getDbHelper().contacts().iterator().next();

    Groups allGroups = app.getDbHelper().groups();

    // если выбранный контакт не входит ни в одну группу, добавим его в случайную
    if(contact.getGroups().isEmpty()) {
      addContactInGroup(contact, allGroups.iterator().next());
    }

    Groups contactGroupsBeforeDelete = app.getDbHelper().getContactById(contact.getId()).getGroups();

    GroupData groupForDeleting = contactGroupsBeforeDelete.iterator().next();

    deleteContactFromGroup(contact, groupForDeleting);

    Groups after = app.getDbHelper().getContactById(contact.getId()).getGroups();

    assertThat(after, equalTo(contactGroupsBeforeDelete.without(groupForDeleting)));
  }

}
