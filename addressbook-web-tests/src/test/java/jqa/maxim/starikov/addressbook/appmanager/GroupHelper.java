package jqa.maxim.starikov.addressbook.appmanager;

import jqa.maxim.starikov.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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


  public boolean isThereGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
