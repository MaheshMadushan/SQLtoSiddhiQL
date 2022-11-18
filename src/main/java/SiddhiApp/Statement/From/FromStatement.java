package SiddhiApp.Statement.From;

import SiddhiApp.ISiddhiAppComposite;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Stream;

public class FromStatement implements IFromStatement {
    private ISiddhiAppComposite inputStream;
    private ISiddhiAppComposite filterStatement;

    public FromStatement(){
        inputStream = null;
        filterStatement = null;
    }

    public void setStreamName(String streamName){
        inputStream = new Stream(streamName);
    }

    public void setFilterStatement(IFilterExpression filterStatement) {
        this.filterStatement = filterStatement;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        String fromWithStreamName = "from " + inputStream.getSiddhiAppCompositeAsString();
        if(filterStatement == null) {
            return fromWithStreamName + "\n";
        }
        else{
            return fromWithStreamName + filterStatement.getSiddhiAppCompositeAsString() + "\n";
        }
    }
}
