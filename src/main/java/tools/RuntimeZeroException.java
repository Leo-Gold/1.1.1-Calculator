package tools;
public class RuntimeZeroException  extends Exception {
    public RuntimeZeroException() {
        super("деление на 0");
    }
}