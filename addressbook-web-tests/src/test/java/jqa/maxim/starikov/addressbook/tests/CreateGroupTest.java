package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class CreateGroupTest extends TestBase {

  @Test
  public void testCreateGroup() {
    app.getGroupHelper().goToPage("groups");
    Set<GroupData> before = app.getGroupHelper().getGroupSet();
    GroupData group = new GroupData().withName("Группа-2");
    app.getGroupHelper().createGroup(group);
    Set<GroupData> after = app.getGroupHelper().getGroupSet();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(after, before);
  }



}
