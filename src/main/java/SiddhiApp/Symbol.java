package SiddhiApp;

public class Symbol implements ISiddhiAppComposite{
    // symbol - % ,/, -, +, (, ), =, ==, etc...
    private String symbol;

    public Symbol(String symbol){
        this.symbol = symbol;
    }

    public enum Symbols{
        ADDITION,
        SUBTRACTION,
        DIVISION,
        MULTIPLICATION,
        AND,
        OR,
        XOR,
        GREATER_THAN,
        MINOR_THAN,
        GREATER_THAN_OR_EQUALS,
        MINOR_THAN_OR_EQUALS,
        EQUAL,
        NOT_EQUALS;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return " " + symbol + " ";
    }
}
