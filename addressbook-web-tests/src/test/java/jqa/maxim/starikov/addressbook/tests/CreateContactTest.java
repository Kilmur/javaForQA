package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

  @Test
  public void testCreateContact() throws Exception {
    app.getNavigationHelper().click(By.linkText("add new"));
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "Miami", "ivan@ivan.com", "929020"));
    app.getNavigationHelper().submitCreation();
    app.getNavigationHelper().click(By.linkText("Logout"));
  }

}
