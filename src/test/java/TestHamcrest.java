import org.junit.jupiter.api.Test;
import tools.Calculator;
import tools.Mathem;
import tools.RuntimeException;
import tools.RuntimeZeroException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class TestHamcrest {

    @Test
    public void test1() throws RuntimeException {
        Mathem math = new Mathem();
        String exception = "34+6*(13 + -3) + 45+5 * 23";
        int result = math.expressionIteration(exception);
        int expectedResult = 254;
        assertEquals(result, expectedResult);
    }

    @Test
    public void test2() {
        Mathem math = new Mathem();
        String expression = "(67 + 34) / ((24/2) - 12)";
        assertThrows(RuntimeZeroException.class, () -> math.expressionIteration(expression), "Деление на 0");
    }

    @Test
    public void test3() throws RuntimeException {
        Mathem math = new Mathem();
        String exception = "254";
        int expectedResult = 254;
        int result = math.expressionIteration(exception);
        assertEquals(result, expectedResult);
    }

    @Test
    public void test4() {
        Mathem math = new Mathem();
        String expression = "67 + 34) / ((24/2) + 12)";
        assertThrows(Exception.class, () -> math.expressionIteration(expression));
    }

    @Test
    public void test5() {
        Calculator calculator = new Calculator();
        int exception = 254;
        boolean result = calculator.isPositive.test(exception);
        assertTrue(result);
    }

    @Test
    public void test6() {
        Calculator calculator = new Calculator();
        int exception = 254;
        boolean result = calculator.isPositive.test(exception);
        assertTrue(result);
    }

    @Test
    public void test7() {
        Calculator calculator = new Calculator();
        int exception = -254;
        boolean result = calculator.isPositive.test(exception);
        assertFalse(result);
    }
}
