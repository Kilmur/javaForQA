package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.annotations.*;

public class CreateGroupTest extends TestBase {

  @Test
  public void testCreateGroup() throws Exception {
    app.clickOnButton("groups");
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Группа 1", "группа1", "группа-1"));
    app.submitCreation();
    app.clickOnButton("Logout");
  }

}
