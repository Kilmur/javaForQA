package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroupTest extends TestBase {

  @Test
  public void testCreateGroup() {
    app.getGroupHelper().goToPage("groups");
    Groups before = app.getGroupHelper().getGroupSet();
    GroupData group = new GroupData().withName("Группа-2");
    app.getGroupHelper().createGroup(group);
    Groups after = app.getGroupHelper().getGroupSet();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
  }



}
