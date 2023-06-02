package SiddhiAppComposites.Statement.From;

import SiddhiAppComposites.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.List;

public class FromStatement implements IFromStatement {
    private String inputStreamOrNamedWindow;
    private final List<ISiddhiAppComposite> fromStatementComposites; // filter statement,window,stream function,join, join with window...

    public FromStatement(){
        inputStreamOrNamedWindow = null;
        fromStatementComposites = new ArrayList<>(10);
    }

    public void setStreamName(String inputStreamOrNamedWindow){
        this.inputStreamOrNamedWindow = inputStreamOrNamedWindow;
    }

    public void setFromStatementComposites(ISiddhiAppComposite filterStatement) {
        fromStatementComposites.add(filterStatement);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        String fromWithStreamName = "from " + inputStreamOrNamedWindow;
        return fromWithStreamName +
                fromStatementComposites.stream().iterator().next().getSiddhiAppCompositeAsString() + "\n";
    }
}
