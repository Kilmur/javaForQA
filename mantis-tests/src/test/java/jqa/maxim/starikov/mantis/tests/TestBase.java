package jqa.maxim.starikov.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import jqa.maxim.starikov.mantis.appmanager.ApplicationManager;
import jqa.maxim.starikov.mantis.models.Issue;
import jqa.maxim.starikov.mantis.models.Project;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
    app.getFtpHelper().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite
  public void tearDown() throws IOException {
    app.getFtpHelper().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }


  public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpen(int id) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = app.getSoapHelper().getMantisConnect();
    IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
    if (issue.getStatus().getName().equals("resolved") || issue.getStatus().getName().equals("closed")) {
      return false;
    }
    return true;


  }


}
