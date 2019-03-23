package jqa.maxim.starikov.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jqa.maxim.starikov.addressbook.models.GroupData;;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroupTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      StringBuilder json = new StringBuilder();
      String line = reader.readLine();
      while (line != null) {
        json.append(line);
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testCreateGroup(GroupData group) {
    app.getGroupHelper().goToPage("groups");
    Groups before = app.getDbHelper().groups();
    app.getGroupHelper().createGroup(group);
    assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size() + 1));
    Groups after = app.getDbHelper().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    verifyGroupsListInUI();
  }



}
