package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ModificationGroupTest extends TestBase {

  @Test
  public void testModificationGroup() {
    app.getNavigationHelper().goToPage("groups");
    if (!app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Группа 1", "группа1", "группа-1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getNavigationHelper().selectItem(before.size() - 1);
    app.getGroupHelper().clickEdit();
    GroupData newGroup = new GroupData(before.get(before.size() - 1).getId(),"Измененная группа", "новый хедер ", "новый футер");
    app.getGroupHelper().fillGroupForm(newGroup);
    app.getNavigationHelper().clickUpdate();
    app.getNavigationHelper().goToPage("groups");
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(newGroup);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }
}
