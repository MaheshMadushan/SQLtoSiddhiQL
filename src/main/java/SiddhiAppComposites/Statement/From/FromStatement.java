package SiddhiAppComposites.Statement.From;

import SiddhiAppComposites.ISiddhiAppComposite;
import java.util.ArrayList;
import java.util.List;

public class FromStatement implements IFromStatement {
    private String inputStreamOrNamedWindow;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    private String tableName;

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String alias;
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
        if (alias == null){
            // do nothing
        } else {
            fromWithStreamName = fromWithStreamName + " as " + alias;
        }
        return fromWithStreamName +
                fromStatementComposites.stream().iterator().next().getSiddhiAppCompositeAsString() + "\n";
    }
}
