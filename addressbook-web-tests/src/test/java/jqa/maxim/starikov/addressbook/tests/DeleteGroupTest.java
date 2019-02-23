package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().goToPage("groups");
    if (!app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Группа 1", "группа1", "группа-1"));
    }
    app.getNavigationHelper().selectItem();
    app.getGroupHelper().clickDelete();
    app.getNavigationHelper().goToPage("groups");
  }
}
