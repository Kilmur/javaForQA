package jqa.maxim.starikov.sandbox;

public class Main {

  public static void main(String[] args) {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);
    // используем статический метод
    System.out.println(distance(p1, p2));

    // используем метод из класса Point
    System.out.println(p1.distance(p2));
    // изменяем координаты точек
    p1 = new Point(1, 2);
    p2 = new Point(6, 7);
    System.out.println(p1.distance(p2));
    // изменяем координаты точек
    p1 = new Point(10, 5);
    p2 = new Point(0, 0);
    System.out.println(p1.distance(p2));

  }

  public static double distance(Point p1, Point p2) {
    // вычисляем расстояние с помощью теоремы Пифагора
    return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
  }
}
