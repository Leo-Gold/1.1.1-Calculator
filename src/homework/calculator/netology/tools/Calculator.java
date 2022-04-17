package homework.calculator.netology.tools;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Calculator {
  public static Supplier<Calculator> instance = Calculator::new;

  public BinaryOperator<Integer> plus = (x, y) -> x + y;
  public BinaryOperator<Integer> minus = (x, y) -> x - y;
  public BinaryOperator<Integer> multiply = (x, y) -> x * y;
  public BinaryOperator<Integer> devide = (x, y ) -> y != 0 ? x / y : 0;

  public UnaryOperator<Integer> pow = x -> x * x;
  public UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

  public Predicate<Integer> isPositive = x -> x > 0;

  public Consumer<Integer> println = System.out::println;

}
