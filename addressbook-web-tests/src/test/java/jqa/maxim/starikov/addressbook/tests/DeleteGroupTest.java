package jqa.maxim.starikov.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().goToPage("groups");
    app.getNavigationHelper().selectItem();
    app.getGroupHelper().clickDelete();
    app.getNavigationHelper().goToPage("groups");
  }
}
