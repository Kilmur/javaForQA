package jqa.maxim.starikov.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

  @Test
  public void testDeleteContact() {
    app.getNavigationHelper().goToPage("home");
    app.getNavigationHelper().selectItem();
    app.getContactHelper().clickDelete();
    app.getWD().switchTo().alert().accept();
    app.getNavigationHelper().goToPage("home");
  }
}
