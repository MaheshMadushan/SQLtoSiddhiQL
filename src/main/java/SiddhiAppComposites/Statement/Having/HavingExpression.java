package SiddhiAppComposites.Statement.Having;

public class HavingExpression implements IHavingExpression {

    private final StringBuilder havingStatement = new StringBuilder();
    // TODO : this is a quick implementation. Do create a bin tree
    public void addSymbol(String symbol){
        this.havingStatement.append(symbol).append(" ");
    }

    public Boolean containsExpressions() {
        return havingStatement.toString().length() > 0;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        if(havingStatement.toString().equals("")){
            return havingStatement.toString();
        }
        return "having " + havingStatement.toString() + "\n";
    }
}