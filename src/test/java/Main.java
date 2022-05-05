import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.Mathem;
import tools.RuntimeException;
import tools.RuntimeZeroException;

public class Main {
    @Test
    public void testConcat_validArgument_success() throws RuntimeException, RuntimeZeroException {
        // given:
        Mathem math = new Mathem();
        String exception = "34+6*(13+ -3) + 45";
        int expectedResult = 139;
        // when:
        math.expressionIteration(exception);
        int result = math.getResultExpression();
        // then:
        Assertions.assertEquals(expectedResult, result);
    }
}
