package homework.calculator.netology.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mathem {
    private static List<Integer> number = new ArrayList<>();
    private static List<Character> sign = new ArrayList<>();
    private static int fei = 0; //for expressionIteration
    private static final StringBuilder sbNumber = new StringBuilder();
    private static final Calculator calc = new Calculator();
    private static int correctBracket = 0;
    private static int countNumber = 0;
    private static int countSign = 0;

// разделение строки на числа и символы
    public void expressionIteration(String expression) {
        for (; fei < expression.length(); fei++) {
            if (Character.isDigit(expression.charAt(fei))) {
                addedNumberValue(expression);
                number.add(Integer.parseInt(String.valueOf(sbNumber)));
                sbNumber.delete(0, sbNumber.length());
                countNumber++;
                unaryMinus();
            } else if (expression.charAt(fei) == '*' || expression.charAt(fei) == '/' || expression.charAt(fei) == '+' || expression.charAt(fei) == '-') {
                sign.add(expression.charAt(fei));
                countSign++;
            } else if (expression.charAt(fei) == ')') {
                correctBracket--;
                if (correctBracket == -1) {
                    System.out.println("скобки раставлены не верное");
                    break;
                } else {
                    sign.add(expression.charAt(fei));
                }
            } else if (expression.charAt(fei) == '(') {
                correctBracket++;
                sign.add(expression.charAt(fei));
            } else if (expression.charAt(fei) != ' ') {
                System.out.println("выражение не валидно присутствуют лишние символы");
                break;
            }
        }

    }

    //нахождение всего числа
    private void addedNumberValue(String expression) {
        for (; fei < expression.length(); fei++) {
            if (Character.isDigit(expression.charAt(fei))) {
                sbNumber.append(expression.charAt(fei));
            } else {
                fei--;
                break;
            }
        }
    }


    public void bracketSearch() {
        if (!sign.contains(')')) {
            expressionCount(number, sign, 0);
        } else {

            List<Integer> tempNumber = new ArrayList<>();
            List<Character> tempSign = new ArrayList<>();
            int openBracket = 0,
                    closeBracket = sign.indexOf(')'),
                    countBracket = 0;
            for (int i = 0; i <= closeBracket; i++) {
                if (sign.get(i) == '(') {
                    countBracket++;
                    openBracket = i;
                }
            }
            for (; openBracket <= closeBracket; closeBracket--) {
                if (sign.get(closeBracket) == '(' || sign.get(closeBracket) == ')') {
                    sign.remove(closeBracket);
                } else {
                    tempSign.add(0, sign.remove(closeBracket));
                }
            }

            int startNumber = openBracket - countBracket;
            int endNumber = startNumber + tempSign.size() + 1;
            for (; startNumber < endNumber; endNumber--) {
                tempNumber.add(0, number.remove(endNumber));
            }

            expressionCount(tempNumber, tempSign, openBracket);
        }
    }

    private void expressionCount(List<Integer> arrNumber, List<Character> arrSign, int index) {
        for (int i = 0; i < arrSign.size(); i++) {
            switch (arrSign.get(i)) {
                case '*':
                    arrSign.remove(i);
                    arrNumber.add(i, calc.multiply.apply(arrNumber.remove(i), arrNumber.remove(i)));
                    i--;
                    break;
                case '/':
                    arrSign.remove(i);
                    int first = arrNumber.remove(i);
                    int second = arrNumber.remove(i);
                    if (second != 0) {
                        arrNumber.add(i, calc.devide.apply(first, second));
                        i--;
                    } else {
                        System.out.println("у вас деление на 0");
                        arrNumber.clear();
                        arrSign.clear();
                        number.clear();
                        sign.clear();
                    }
                    break;
            }
        }
        for (int i = 0; i < arrSign.size(); i++) {
            switch (arrSign.get(i)) {
                case '+':
                    arrSign.remove(i);
                    arrNumber.add(i, calc.plus.apply(arrNumber.remove(i), arrNumber.remove(i)));
                    i--;
                    break;
                case '-':
                    arrSign.remove(i);
                    arrNumber.add(i, calc.minus.apply(arrNumber.remove(i), arrNumber.remove(i)));
                    i--;
                    break;
            }
        }
        if (!number.isEmpty()) {
            number.add(index, arrNumber.remove(0));
            if (number.size() == 1) {
                calc.println.accept(number.remove(0));
            } else {
                bracketSearch();
            }
        }

    }

    //добавление унарного минуса или плюса
    private void unaryMinus() {
        if (countSign == countNumber && sign.get(sign.size() - 1) == '-') {
            number.add(number.remove(number.size() - 1) * -1);
            sign.remove(sign.size() - 1);
            countSign--;
        } else if (countSign == countNumber && sign.get(sign.size() - 1) == '+') {
            sign.remove(sign.size() - 1);
            countSign--;
        } else if (countSign == countNumber && (sign.get(sign.size() - 1) == '*' || sign.get(sign.size() - 1) == '/')){
            System.out.println("у вас лишние симвлы");
        }
    }
}
