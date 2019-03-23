package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.getDbHelper().groups().size() == 0) {
      app.getNavigationHelper().goToPage("groups");
      app.getGroupHelper().createGroup(new GroupData().withName("Новая группа"));
    }
  }

  @Test
  public void testModificationGroup() {
    Groups before = app.getDbHelper().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData newGroup = new GroupData()
      .withId(modifiedGroup.getId()).withName("Измененная группа").withHeader("новый хедер").withFooter("новый футер");
    app.getNavigationHelper().goToPage("groups");
    app.getGroupHelper().modifyGroup(newGroup);
    assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));
    Groups after = app.getDbHelper().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(newGroup)));
  }


}
