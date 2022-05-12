import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import tools.Calculator;
import tools.Mathem;
import tools.RuntimeException;
import tools.RuntimeZeroException;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestHamcrest {

    @Test
    public void test1() throws RuntimeException {
        Mathem math = new Mathem();
        String exception = "34+6*(13 + -3) + 45+5 * 23";
        int result = math.expressionIteration(exception);
        int expectedResult = 254;
        assertThat(result, Matchers.equalTo(expectedResult));
    }

    @Test
    public void test2() {
        Mathem math = new Mathem();
        String expression = "(67 + 34) / ((24/2) - 12)";
        boolean expectedResult = false;
        try {
            math.expressionIteration(expression);
        } catch (RuntimeException | RuntimeZeroException e) {
            expectedResult = true;
        }
        assertThat(true, Matchers.equalTo(expectedResult));
    }

    @Test
    public void test3() throws RuntimeException {
        Mathem math = new Mathem();
        String exception = "254";
        int expectedResult = 254;
        int result = math.expressionIteration(exception);
        assertThat(result, Matchers.equalTo(expectedResult));
    }

    @Test
    public void test4() {
        Mathem math = new Mathem();
        String expression = "67 + 34) / ((24/2) + 12)";
        boolean expectedResult = false;
        try {
            math.expressionIteration(expression);
        } catch (RuntimeException e) {
            expectedResult = true;
        }
        assertThat(true, Matchers.equalTo(expectedResult));
    }

    @Test
    public void test5() {
        Calculator calculator = new Calculator();
        int exception = 100;
        boolean result = calculator.isPositive.test(exception);
        assertThat(true, Matchers.equalTo(result));
    }

    @Test
    public void test6() {
        Calculator calculator = new Calculator();
        int exception = 254;
        boolean result = calculator.isPositive.test(exception);
        assertThat(result, Matchers.is(true));
    }

    @Test
    public void test7() {
        Calculator calculator = new Calculator();
        int exception = -254;
        boolean result = calculator.isPositive.test(exception);
        assertThat(result, Matchers.is(false));
    }
}
