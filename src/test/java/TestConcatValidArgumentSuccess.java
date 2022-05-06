import org.junit.jupiter.api.Assertions;
import tools.Mathem;
import tools.RuntimeException;
import tools.RuntimeZeroException;

public class TestConcatValidArgumentSuccess {

    @org.junit.jupiter.api.Test
    public void testLongExpression() throws RuntimeException, RuntimeZeroException {
        // given:
        Mathem math = new Mathem();
        String exception = "34+6*(13 + -3) + 45+5 * 23";
        int expectedResult = 254 ;
        int result = math.expressionIteration(exception);
        Assertions.assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testDivideZero() {
        Mathem math = new Mathem();
        String expression = "(67 + 34) / ((24/2) - 12)";
        Assertions.assertThrows(ArithmeticException.class, () -> math.expressionIteration(expression));
    }

    @org.junit.jupiter.api.Test
    public void testOneValue() throws RuntimeException, RuntimeZeroException {
        Mathem math = new Mathem();
        String exception = "254";
        int expectedResult = 254 ;
        int result = math.expressionIteration(exception);
        Assertions.assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testNotValidExpression() {
        Mathem math = new Mathem();
        String expression = "67 + 34) / ((24/2) + 12)";
        Assertions.assertThrows(Exception.class, () -> math.expressionIteration(expression));
    }
}
