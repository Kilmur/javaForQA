package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.appmanager.ApplicationManager;
import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  public void verifyGroupsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.getDbHelper().groups();
      Groups uiGroups = app.getGroupHelper().getGroupSet();
      assertThat(uiGroups, equalTo(dbGroups.stream()
        .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public void verifyContactsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.getDbHelper().contacts();
      Contacts uiContacts = app.getContactHelper().getContactSet();
      assertThat(uiContacts, equalTo(dbContacts.stream()
        .map((c) -> new ContactData().withId(c.getId()).withName(c.getName()).withLastname(c.getLastname())
          .withAddress(c.getAddress())
          .withAllEmails(c.getEmail() + c.getEmail2() + c.getEmail3())
          .withAllPhones(c.getPhoneHome() + c.getPhoneMobile() + c.getPhoneWork()))
          .collect(Collectors.toSet())));
    }
  }

  public void addContactInGroup(ContactData contact, GroupData group) {
    app.getNavigationHelper().goToPage("home");
    app.getNavigationHelper().selectItemById(contact.getId());
    selectGroupForAdd(group);
    app.getWD().findElement(By.name("add")).click();
  }

  public void selectGroupForAdd(GroupData group) {
    new Select(app.getWD().findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
  }

  protected GroupData getGroupForAddingInContact(ContactData contact) {
    Groups allGroupsBeforeCreate = app.getDbHelper().groups();
    Groups contactGroups = contact.getGroups();
    // оставляем недобавленные в контакт группы
    allGroupsBeforeCreate.removeAll(contactGroups);
    // если все выбранный контакт добавлен во все группы, создадим группу
    if (allGroupsBeforeCreate.isEmpty()) {
      app.getNavigationHelper().goToPage("groups");
      GroupData newGroup = new GroupData().withName("Fresh group");
      app.getGroupHelper().createGroup(newGroup);

      Groups groupsAfterCreate = app.getDbHelper().groups();
      // оставляем только новую группу с новым id
      groupsAfterCreate.removeAll(allGroupsBeforeCreate);
      GroupData groupForAdding = groupsAfterCreate.iterator().next();

      app.getNavigationHelper().goToPage("home");
      return groupForAdding;
    }
    return allGroupsBeforeCreate.iterator().next();
  }
}
