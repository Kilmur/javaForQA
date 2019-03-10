package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class CreateGroupTest extends TestBase {

  @Test
  public void testCreateGroup() {
    app.getGroupHelper().goToPage("groups");
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Группа 3", "группа1", "группа-1");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);

    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(after, before);
  }



}
