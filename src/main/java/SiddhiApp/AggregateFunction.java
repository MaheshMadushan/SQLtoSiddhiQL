package SiddhiApp;

import java.util.ArrayList;
import java.util.List;

public class AggregateFunction implements IFunction,IAttribute{

    private String functionName;
    private List<ISiddhiAppComposite> attributeList;
    private ISiddhiAppComposite alias;

    public AggregateFunction(String functionName) {
        this.functionName = functionName;
        this.attributeList = new ArrayList<>(10);
    }

    public ISiddhiAppComposite getAlias() {
        return this.alias;
    }

    public void setAlias(ISiddhiAppComposite alias) {
        this.alias = alias;
    }

    public String getFunctionName(){ return this.functionName; }

    public String toString(boolean withAliases) {
        return toString();
    }

    public void addAttribute(ISiddhiAppComposite attribute){
        attributeList.add(attribute);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        StringBuilder functionDeclaration = new StringBuilder(functionName).append("(");
        for(ISiddhiAppComposite attribute : attributeList ){
            functionDeclaration
                    .append(attribute.getSiddhiAppCompositeAsString())
                    .append(", ");
        }
        functionDeclaration
                .append(")").append(((Alias) alias).getSiddhiAppCompositeAsString());

        return functionDeclaration.toString();
    }
}
