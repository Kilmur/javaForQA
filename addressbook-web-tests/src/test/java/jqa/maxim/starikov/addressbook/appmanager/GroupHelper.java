package jqa.maxim.starikov.addressbook.appmanager;

import jqa.maxim.starikov.addressbook.models.GroupData;
import jqa.maxim.starikov.addressbook.models.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void fillGroupForm(GroupData groupData) {
    fillField(By.name("group_name"), groupData.getName());
    fillField(By.name("group_header"), groupData.getHeader());
    fillField(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void clickEdit() {
    click(By.name("edit"));
  }

  public void clickDelete() {
    click(By.name("delete"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitCreation();
    goToPage("groups");
  }

  public void modifyGroup(GroupData group) {
    selectItemById(group.getId());
    clickEdit();
    fillGroupForm(group);
    clickUpdate();
    goToPage("groups");
  }

  public void deleteGroup(GroupData group) {
    selectItemById(group.getId());
    clickDelete();
    goToPage("groups");
  }


  public boolean isThereGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public Groups getGroupSet() {
    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }


}
