package SiddhiApp.Statement.From;

import SiddhiApp.ISiddhiAppComposite;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.DefineStreamStatement;

public class FromStatement implements IFromStatement {
    private ISiddhiAppComposite inputStreamOrNamedWindow;
    private ISiddhiAppComposite fromStatementComposite; // filter statement,window,stream function,join, join with window...

    public FromStatement(){
        inputStreamOrNamedWindow = null;
        fromStatementComposite = null;
    }

    public void setStreamName(String inputStreamName){
        inputStreamOrNamedWindow = new DefineStreamStatement(streamName);
    }

    public void setFromStatementComposite(ISiddhiAppComposite filterStatement) {
        this.fromStatementComposite = filterStatement;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        String fromWithStreamName = "from " + inputStreamOrNamedWindow.getSiddhiAppCompositeAsString();
        if(fromStatementComposite == null) {
            return fromWithStreamName + "\n";
        }
        else{
            return fromWithStreamName + fromStatementComposite.getSiddhiAppCompositeAsString() + "\n";
        }
    }
}
