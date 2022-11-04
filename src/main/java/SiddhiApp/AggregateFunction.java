package SiddhiApp;

import java.util.List;

public class AggregateFunction implements IFunction,IAttribute{

    private String functionSignature;
    private List<Attribute> argumentList;
    private String alias;

    public AggregateFunction(String functionSignature, List<Attribute> argumentList, String alias) {
        this.functionSignature = functionSignature;
        this.argumentList = argumentList;
        this.alias = alias;
    }

    public void setFunctionSignature(String functionSignature) {
        this.functionSignature = functionSignature;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void addArgument(Attribute attribute){
        this.argumentList.add(attribute);
    }

    public String getFunctionSignature() {
        return functionSignature;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString(boolean withAliases) {

        StringBuilder functionAsSelectAttribute = new StringBuilder(functionSignature);

        functionAsSelectAttribute.append("(");
        boolean isFunctionSupported = SupportedAggregationFunctions.isFunctionSupported(this.functionSignature);
        if(!isFunctionSupported){
            int numOfArguments = SupportedAggregationFunctions.getNumOfArguments(this.functionSignature);
            if(numOfArguments == argumentList.size()){

            }else{
                throw new IllegalArgumentException();
            }

        }

        for(Attribute attribute : argumentList){
            functionAsSelectAttribute.append(attribute.getName());
        }

        if(alias == null){
            this.alias = 
        }

        functionAsSelectAttribute.append(") ").append("as ").append(alias); // eg - count(column_a) as alias
        return functionAsSelectAttribute.toString();
    }

    @Override
    public String getName() {
        return null;
    }
}
