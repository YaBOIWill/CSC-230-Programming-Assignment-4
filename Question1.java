//question 1 for programming assignment 4 for CSC 230
//main public class 
public class Question1 {
    //main method
    public static void main(String[] args) {
        //initialize different shapes
        Shape s1 = new Rectangle("blood red", 3.5, 5.5);
        Shape s2 = new Triangle("royal blue", 17, 19);
        //call string methods and display values for different shapes 
        System.out.println(s1);
        System.out.println("Area is " + s1.getArea());
        System.out.println(s2);
        System.out.println("Area is " + s2.getArea());
    }
}
//abstract shape class
abstract class Shape {
    private String color;
    //shape constructor
    public Shape() {
        this.color = color;
    }
    //abstract method for shape area
    public abstract double getArea();
    //string method override
    @Override
    public String toString() {
        return "Shape color is: " + color;
    }
}
//rectangle class that extends shape class
class Rectangle extends Shape {
   //Private variables for length and width
   private double length;
   private double width;
   //Rectangle constructor
   public Rectangle(String color, double length, double width) {
      this.length = length;
      this.width = width;
   }
   //override string method to return rectangle values
   @Override
   public String toString() {
      return "Rectangle length: " + length + ", Rectangle width: " + width + ", Rectangle color: " + super.toString();
   }
   //Override inherited getArea() to provide implementation
   @Override
   public double getArea() {
      return (length * width);
   }
}
//triangle class that extends shape class
class Triangle extends Shape {
    //Private variables for base and height
   private double base;
   private double height;
   //Trianngle Constructor
   public Triangle(String color, double base, double height) {
      this.base = base;
      this.height = height;
   }
   //override string method to return triangle values
   @Override
   public String toString() {
      return "Triangle base: " + base + ", Triangle height: " + height + ", Triangle color: " + super.toString();
   }
   //Override inherited getArea() to provide implementation
   @Override
   public double getArea() {
      return (0.5 * (base * height));
   }
}