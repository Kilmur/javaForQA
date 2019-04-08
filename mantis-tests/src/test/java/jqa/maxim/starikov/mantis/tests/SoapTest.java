package jqa.maxim.starikov.mantis.tests;

import jqa.maxim.starikov.mantis.models.Issue;
import jqa.maxim.starikov.mantis.models.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTest extends TestBase{

  @Test
  public void testSoap() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.getSoapHelper().getProjects();
    System.out.println("SIZE - " + projects.size());
    for (Project project : projects) {
      System.out.println("project - " + project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.getSoapHelper().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
      .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.getSoapHelper().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }


}
