package jqa.maxim.starikov.addressbook.tests;

import jqa.maxim.starikov.addressbook.models.ContactData;
import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

  @Test
  public void testCreateContact() throws Exception {
    app.clickOnButton("add new");
    app.fillContactForm(new ContactData("Ivan", "Ivanov", "Miami", "ivan@ivan.com", "929020"));
    app.submitCreation();
    app.clickOnButton("Logout");
  }

}
