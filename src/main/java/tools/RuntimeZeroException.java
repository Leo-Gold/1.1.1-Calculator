package tools;
public class RuntimeZeroException  extends ArithmeticException {
    public RuntimeZeroException() {
        super("division by zero");
    }
}
