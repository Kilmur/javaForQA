package jqa.maxim.starikov.addressbook;

import org.testng.annotations.*;

public class CreateContactTest extends TestBase {

  @Test
  public void testCreateContact() throws Exception {
    clickOnButton("add new");
    fillContactForm(new ContactData("Ivan", "Ivanov", "Miami", "ivan@ivan.com", "929020"));
    submitCreation();
    clickOnButton("Logout");
  }

}
