package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().goToPage("groups");
    if (!app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Группа 1", "группа1", "группа-1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getNavigationHelper().selectItem(before.size() - 1);
    app.getGroupHelper().clickDelete();
    app.getNavigationHelper().goToPage("groups");
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
