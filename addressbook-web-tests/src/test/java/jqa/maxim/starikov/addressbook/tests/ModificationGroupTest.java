package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ModificationGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToPage("groups");
    if (!app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new GroupData().withName("Новая группа"));
    }
  }

  @Test
  public void testModificationGroup() {
    Groups before = app.getGroupHelper().getGroupSet();
    GroupData modifiedGroup = before.iterator().next();
    GroupData newGroup = new GroupData()
      .withId(modifiedGroup.getId()).withName("Измененная группа").withHeader("новый хедер").withFooter("новый футер");
    app.getGroupHelper().modifyGroup(newGroup);
    assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));
    Groups after = app.getGroupHelper().getGroupSet();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(newGroup)));
  }


}
