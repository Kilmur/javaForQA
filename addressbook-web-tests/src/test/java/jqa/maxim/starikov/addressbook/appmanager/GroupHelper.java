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
}
