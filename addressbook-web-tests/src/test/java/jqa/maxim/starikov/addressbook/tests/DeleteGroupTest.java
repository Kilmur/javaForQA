package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.getDbHelper().groups().size() == 0) {
      app.getNavigationHelper().goToPage("groups");
      app.getGroupHelper().createGroup(new GroupData().withName("Новая группа"));
    }
  }

  @Test
  public void testDeleteGroup() {
    Groups before = app.getDbHelper().groups();
    app.getNavigationHelper().goToPage("groups");
    GroupData deletedGroup = before.iterator().next();
    app.getGroupHelper().deleteGroup(deletedGroup);
    assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size() - 1));
    Groups after = app.getDbHelper().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }


}
