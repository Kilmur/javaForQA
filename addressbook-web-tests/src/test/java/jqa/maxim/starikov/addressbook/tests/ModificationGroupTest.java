package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
    Set<GroupData> before = app.getGroupHelper().getGroupSet();
    GroupData modifiedGroup = before.iterator().next();
    GroupData newGroup = new GroupData().withId(modifiedGroup.getId()).withName("Измененная группа").withHeader("новый хедер").withFooter("новый футер");
    app.getGroupHelper().modifyGroup(newGroup);
    Set<GroupData> after = app.getGroupHelper().getGroupSet();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(newGroup);
    Assert.assertEquals(after, before);

  }


}
