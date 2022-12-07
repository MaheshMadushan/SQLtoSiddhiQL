package SiddhiApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AggregateFunction implements IFunction,IAttribute{

    private final String functionName;
    private final List<ISiddhiAppComposite> attributeList;
    private ISiddhiAppComposite alias;
    private Iterator<ISiddhiAppComposite> attributeIterator;

    public AggregateFunction(String functionName) {
        this.attributeList = new ArrayList<>(10);
        if(SupportedAggregationFunctions.isFunctionSupported(functionName)) {
            this.functionName = functionName;
        }
        else{
            this.functionName = null;
            throw new UnsupportedOperationException("Unsupported function " + functionName);
        }
    }

    public ISiddhiAppComposite getAlias() {
        return this.alias;
    }

    public void setAlias(ISiddhiAppComposite alias) {
        this.alias = alias;
    }

    public void addAttribute(ISiddhiAppComposite attribute){
        attributeList.add(attribute);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        attributeIterator = attributeList.iterator();
        if(functionName == null){
            throw new UnsupportedOperationException("Function name is null.");
        }
        StringBuilder functionDeclaration = new StringBuilder(functionName).append("("); // eg. - SUM( -->
        boolean thereIsNextComponent = attributeIterator.hasNext();

        while(thereIsNextComponent){
            ISiddhiAppComposite selectItemComposite = attributeIterator.next();
            thereIsNextComponent = attributeIterator.hasNext();

            if(thereIsNextComponent){
                functionDeclaration
                        .append(
                                selectItemComposite
                                        .getSiddhiAppCompositeAsString()
                        )
                        .append(" "); // --> SUM(col  --> SUM(col + -->
            }else{
                functionDeclaration
                        .append(
                                selectItemComposite.
                                        getSiddhiAppCompositeAsString()
                        ); // --> SUM (col + col
            }
        }
        if(alias == null){
            functionDeclaration
                    .append(")"); // --> SUM (col + col)
        }else {
            functionDeclaration
                    .append(")")
                    .append(
                            ((Alias) alias)
                                    .getSiddhiAppCompositeAsString()
                    ); // --> SUM (col + col) AS alias
        }

        return functionDeclaration
                .toString(); // SUM (col + col) AS alias
    }
}
