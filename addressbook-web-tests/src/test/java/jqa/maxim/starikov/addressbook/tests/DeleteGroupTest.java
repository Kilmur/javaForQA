package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().goToPage("groups");
    int before = app.getGroupHelper().getGroupCount();
    if (!app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Группа 1", "группа1", "группа-1"));
    }
    app.getNavigationHelper().selectItem(before - 1);
    app.getGroupHelper().clickDelete();
    app.getNavigationHelper().goToPage("groups");
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }
}
