package homework.calculator.netology;

import homework.calculator.netology.tools.Calculator;

public class Main {
  private static final Calculator calc = new Calculator();

  public static void main(String[] args) {
    int a = calc.plus.apply(1, 2);
    int b = calc.minus.apply(1,1);
    int c = calc.devide.apply(a, b);

    calc.println.accept(c);
  }
}