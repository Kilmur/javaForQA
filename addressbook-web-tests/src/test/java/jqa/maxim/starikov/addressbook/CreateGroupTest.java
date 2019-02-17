package jqa.maxim.starikov.addressbook;

import org.testng.annotations.*;

public class CreateGroupTest extends TestBase {

  @Test
  public void testCreateGroup() throws Exception {
    clickOnButton("groups");
    initGroupCreation();
    fillGroupForm(new GroupData("Группа 1", "группа1", "группа-1"));
    submitCreation();
    clickOnButton("Logout");
  }

}
