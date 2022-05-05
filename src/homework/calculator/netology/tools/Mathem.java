package homework.calculator.netology.tools;

import java.util.ArrayList;
import java.util.List;

public class Mathem {
    private List<Integer> numberList = new ArrayList<>();
    private List<Character> signList = new ArrayList<>();
    private int sliderString = 0;
    private Calculator calc = new Calculator();
    private int correctBracket = 0;
    private int countNumber = 0;
    private int countSign = 0;
    private int resultExpression;

// разделение строки на числа и символы
    public void expressionIteration(String expression) throws RuntimeException, RuntimeZeroException {
        sliderString = 0;
        char currentChar;
        ArrayList<Character> calculableSingChars = new ArrayList<Character>() {{
            add('+');
            add('*');
            add('-');
            add('/');
        }};

        for (; sliderString < expression.length(); sliderString++) {
            currentChar = expression.charAt(sliderString);

            if (Character.isDigit(currentChar)) {
                numberList.add(addedNumberValue(expression));
                countNumber++;
                unaryMinus();
            } else if (calculableSingChars.contains(currentChar)) {
                signList.add(currentChar);
                countSign++;
            } else if (currentChar == ')') {
                correctBracket--;
                if (correctBracket == -1) {
                    throw new RuntimeException();
                } else {
                    signList.add(currentChar);
                }
            } else if (currentChar == '(') {
                correctBracket++;
                signList.add(currentChar);
            } else if (currentChar != ' ') {
                throw new RuntimeException();
            }
        }
        bracketSearch();
    }

    //нахождение всего числа
    private int addedNumberValue(String expression) {
        StringBuilder sbNumber = new StringBuilder();
        for (; sliderString < expression.length(); sliderString++) {
            if (Character.isDigit(expression.charAt(sliderString))) {
                sbNumber.append(expression.charAt(sliderString));
            } else {
                sliderString--;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(sbNumber));
    }


    public void bracketSearch() throws RuntimeZeroException, RuntimeException {
        if (!signList.contains(')')) {
            expressionCount(numberList, signList, 0);
        } else if (correctBracket != 0){
            throw new RuntimeException();
        } else{
            List<Integer> tempNumber = new ArrayList<>();
            List<Character> tempSign = new ArrayList<>();
            int openBracket = 0,
                    closeBracket = signList.indexOf(')'),
                    countBracket = 0;
            for (int i = 0; i <= closeBracket; i++) {
                if (signList.get(i) == '(') {
                    countBracket++;
                    openBracket = i;
                }
            }
            for (; openBracket <= closeBracket; closeBracket--) {
                if (signList.get(closeBracket) == '(' || signList.get(closeBracket) == ')') {
                    signList.remove(closeBracket);
                } else {
                    tempSign.add(0, signList.remove(closeBracket));
                }
            }

            int startNumber = openBracket - countBracket;
            int endNumber = startNumber + tempSign.size() + 1;
            for (; startNumber < endNumber; endNumber--) {
                tempNumber.add(0, numberList.remove(endNumber));
            }

            expressionCount(tempNumber, tempSign, startNumber + 1);
        }
    }
    private void expressionCount(List<Integer> arrNumber, List<Character> arrSign, int index) throws RuntimeZeroException, RuntimeException {
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
                        arrNumber.add(i, calc.divide.apply(first, second));
                        i--;
                    } else {
                        throw new RuntimeZeroException();
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
        if (!numberList.isEmpty()) {
            numberList.add(index, arrNumber.remove(0));
            if (numberList.size() == 1) {
                resultExpression = numberList.remove(0);
            } else {
                bracketSearch();
            }
        } else {
            numberList.add(index, arrNumber.remove(0));
            bracketSearch();
        }
    }

    //добавление унарного минуса или плюса
    private void unaryMinus() throws RuntimeException {
        if (countSign == countNumber && signList.get(signList.size() - 1) == '-') {
            numberList.add(numberList.remove(numberList.size() - 1) * -1);
            signList.remove(signList.size() - 1);
            countSign--;
        } else if (countSign == countNumber && signList.get(signList.size() - 1) == '+') {
            signList.remove(signList.size() - 1);
            countSign--;
        } else if (countSign == countNumber && (signList.get(signList.size() - 1) == '*' || signList.get(signList.size() - 1) == '/')){
            throw new RuntimeException();
        }
    }

    public int getResultExpression() {
        return resultExpression;
    }
}
