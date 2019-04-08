package jqa.maxim.starikov.rest;

import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public Executor getExecutor() {
      return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  private boolean isIssueOpen(int id) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + id + ".json"))
      .returnContent().asString();
    String status = new JsonParser().parse(json)
      .getAsJsonObject().get("issues")
      .getAsJsonArray().get(0)
      .getAsJsonObject().get("state_name").getAsString();
    if (status.equals("Resolved") || status.equals("Closed") || status.equals("Deleted")) {
      return false;
    }
    return true;
  }
}
