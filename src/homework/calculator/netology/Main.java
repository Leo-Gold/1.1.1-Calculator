package homework.calculator.netology;

import homework.calculator.netology.tools.Calculator;
import homework.calculator.netology.tools.Mathem;

import java.util.Scanner;

public class Main {
  private static final Calculator calc = new Calculator();
  private static final Mathem math = new Mathem();
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    String ask = scanner.nextLine();
    math.expressionIteration(ask);
    math.bracketSearch();

//    int a = calc.plus.apply(1, 2);
//    int b = calc.minus.apply(1,1);
//    int c = calc.devide.apply(a, b);
//
//    calc.println.accept(c);
  }
}