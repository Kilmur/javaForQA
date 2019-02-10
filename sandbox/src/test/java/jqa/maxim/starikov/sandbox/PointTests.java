package jqa.maxim.starikov.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);
    Assert.assertEquals(p1.distance(p2), 5.0);

    p1 = new Point(10, 8);
    p2 = new Point(5, 6);;
    Assert.assertEquals(p1.distance(p2), 5.385164807134504);

    p1 = new Point(27, 19);
    p2 = new Point(27, 33);
    Assert.assertEquals(p1.distance(p2), 14.0);
  }

  @Test
  public void testDistanceStaticMethod() {
    Point p1 = new Point(2, 6);
    Point p2 = new Point(5, 10);
    Assert.assertEquals(Main.distance(p1, p2), 5.0);
  }
}
