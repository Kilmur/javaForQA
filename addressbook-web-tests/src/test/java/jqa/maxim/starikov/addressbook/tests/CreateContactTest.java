package jqa.maxim.starikov.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jqa.maxim.starikov.addressbook.models.ContactData;
import jqa.maxim.starikov.addressbook.models.Contacts;
import org.testng.annotations.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      StringBuilder json = new StringBuilder();
      String line = reader.readLine();
      while (line != null) {
        json.append(line);
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> groups = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testCreateContact(ContactData contact) {
    Contacts before = app.getDbHelper().contacts();
    app.getContactHelper().createContact(contact);
    assertThat(app.getContactHelper().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.getDbHelper().contacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    verifyContactsListInUI();
  }

}
