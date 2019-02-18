package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class ModificationGroupTest extends TestBase {

  @Test
  public void testModificationGroup() {
    app.getNavigationHelper().goToPage("groups");
    app.getNavigationHelper().selectItem();
    app.getGroupHelper().clickEdit();
    app.getGroupHelper().fillGroupForm(new GroupData("Измененная группа", "новый хедер ", "новый футер"));
    app.getNavigationHelper().clickUpdate();
    app.getNavigationHelper().goToPage("groups");

  }
}
