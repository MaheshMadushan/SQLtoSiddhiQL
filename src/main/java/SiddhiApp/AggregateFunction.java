package SiddhiApp;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

public class AggregateFunction implements IFunction,IAttribute{

    private Function function;
    private Alias alias;

    public AggregateFunction(Function function, Alias alias) {
        this.function = function;
        if(alias == null){
            String stringAlias = function.getAttribute().toString();
            this.alias = new Alias(stringAlias);
        }
        this.alias = alias;
    }

    public Alias getAlias() {
        return this.alias;
    }

    public void setAlias(Alias alias) {
        this.alias = alias;
    }

    public Function getFunction() {
        return this.function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String toString() {
        return this.function + (this.alias != null ? this.alias.toString() : "");
    }

    @Override
    public String toString(boolean withAliases) {
        return toString();
    }

    @Override
    public String getName() {
        return function.getName();
    }
}
