package homework.calculator.netology;

import homework.calculator.netology.tools.Calculator;
import homework.calculator.netology.tools.Mathem;

import java.util.Scanner;

public class Main {
  private static final Calculator calc = new Calculator();
  private static final Mathem math = new Mathem();
  private static final Scanner scanner = new Scanner(System.in);
  private static boolean start = true;

  public static void main(String[] args) {
    while (start){
      message();
    }
  }

  private static void message() {
    System.out.println("1 - рассчитать выражение");
    System.out.println("2 - возведение в квадрат");
    System.out.println("3 - модуль числа");
    System.out.println("4 - положительное ли число");
    System.out.println("0 - выйти");
    System.out.println();
    usersSet();
  }

  public static void usersSet(){
    int input = Integer.parseInt(scanner.next());
    scanner.nextLine();
    switch (input){
      case 1:
        calculatorInput();
        break;
      case 2:
        square();
        break;
      case 3:
        abs();
        break;
      case 4:
        positive();
        break;
      case 0:
        start = false;
        break;
      default:
        System.out.println("комманда не найдена");
    }
  }

  private static void calculatorInput() {
    String input = scanner.nextLine();
    math.expressionIteration(input);
  }

  private static void square() {
    int input = scanner.nextInt();
    int result = calc.pow.apply(input);
    calc.println.accept(result);
  }

  private static void abs() {
    int input = scanner.nextInt();
    int result = calc.abs.apply(input);
    calc.println.accept(result);
  }

  private static void positive() {
    int input = scanner.nextInt();
    boolean result = calc.isPositive.test(input);
    if (result){
      System.out.println("да");
    } else {
      System.out.println("нет");
    }
  }
}