package jqa.maxim.starikov.sandbox;

public class Point {

  private int x;

  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public double getX() {
    return x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public double getY() {
    return y;
  }

  /**
   * Вычислить расстояние до какой-то точки от текущей
   *
   * @param p точка до которой вычисляем расстояние
   * @return {@link double}
   */
  public double distance(Point p) {
    return Math.sqrt(Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2));
  }


}
