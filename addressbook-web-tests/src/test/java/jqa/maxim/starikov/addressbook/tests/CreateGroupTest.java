package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class CreateGroupTest extends TestBase {

  @Test
  public void testCreateGroup() throws Exception {
    app.getNavigationHelper().click(By.linkText("groups"));
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Группа 1", "группа1", "группа-1"));
    app.getNavigationHelper().submitCreation();
    app.getNavigationHelper().click(By.linkText("Logout"));
  }

}
