package SiddhiApp;

import SiddhiApp.Statement.FilterExpressionStatement.FilterExpression;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Statement.From.FromStatement;
import SiddhiApp.Statement.From.IFromStatement;
import SiddhiApp.Statement.Insert.IInsertStatement;
import SiddhiApp.Statement.Insert.InsertStatement;
import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {
    private final DefineStreamStatement defineStreamStatement = new DefineStreamStatement(); // create define stream // use this for output stream also
    private final SelectStatement selectStatement = new SelectStatement(); // select statement
    private final IFromStatement fromStatement = new FromStatement(); // from statement
    private final IFilterExpression filterExpression = new FilterExpression(); // filter statement
    private final IInsertStatement insertStatement = new InsertStatement(); // insert into statement
    private String inputOutputStreamNamePrefix = null;
    private String siddhiAppName = null;
    private final StringBuilder stringSiddhiApp = new StringBuilder("");

    public void addSymbolToFilterExpression(String symbol){
        ((FilterExpression) this.filterExpression).addSymbol(symbol);
    }
    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectStatement.addSelectItem(selectItem);
    }
    public void addColumnWithDataType(ISiddhiAppComposite columnWithDataType){
        defineStreamStatement.addAttributeWithDataType(columnWithDataType);
    }
    public void setStreamNamePrefix(String inputOutputStreamNamePrefix) {
        this.inputOutputStreamNamePrefix = inputOutputStreamNamePrefix;
    }
    public void setSiddhiAppName(String siddhiAppName){
        this.siddhiAppName = siddhiAppName;
    }
    public String getSiddhiAppStringRepresentation(){
        // Annotations
            // app name
            // source
        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // IPStream set I/P Stream Name
        stringSiddhiApp.append(defineStreamStatement.getSiddhiAppCompositeAsString());

        // Annotations
            // sink
        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "OutputStream"); // OPStream set O/P Stream Name
        stringSiddhiApp.append(defineStreamStatement.getSiddhiAppCompositeAsString());
        // Annotations
            // info
        fromStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // From Statement set I/P Stream Name
        fromStatement.setFromStatementComposite(filterExpression); // From statement filter expression
        stringSiddhiApp.append(fromStatement.getSiddhiAppCompositeAsString());

        stringSiddhiApp.append(selectStatement.getSiddhiAppCompositeAsString()); // select statement

        insertStatement.setOutputStreamName(inputOutputStreamNamePrefix + "OutputStream"); // insert statement set O/P Stream name
        stringSiddhiApp.append(insertStatement.getSiddhiAppCompositeAsString());
        return stringSiddhiApp.toString();
    }
}