package SiddhiApp;

import SiddhiApp.Statement.FilterExpressionStatement.FilterExpression;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Statement.From.FromStatement;
import SiddhiApp.Statement.From.IFromStatement;
import SiddhiApp.Statement.Insert.IInsertStatement;
import SiddhiApp.Statement.Insert.InsertStatement;
import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {

    // create define stream // use this for output stream also
    DefineStreamStatement defineStreamStatement = new DefineStreamStatement();
    // select statement
    SelectStatement selectStatement = new SelectStatement();
    // from statement
    IFromStatement fromStatement = new FromStatement();
    // filter statement
    IFilterExpression filterExpression = new FilterExpression();
    // insert into statement
    IInsertStatement insertStatement = new InsertStatement();
    String inputOutputStreamNamePrefix = "networkTraffic";
    private StringBuilder stringSiddhiApp = new StringBuilder("");

    public void addSymbolToFilterExpression(String symbol){
        ((FilterExpression) this.filterExpression).addSymbol(symbol);
    }

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectStatement.addSelectItem(selectItem);
    }

    public void addColumnWithDataType(ISiddhiAppComposite columnWithDataType){
        defineStreamStatement.addAttributeWithDataType(columnWithDataType);
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

        insertStatement.setOutputStreamName(inputOutputStreamNamePrefix + "OutputStream"); // insert statement set O/P Stream name
        stringSiddhiApp.append(insertStatement.getSiddhiAppCompositeAsString());
        return stringSiddhiApp.toString();
    }
        // has attributes (with aliases or not)
        // functions
    // insert statement

    // will use composite DP as for now
    // need to create sub types
            // select item
            // function item
            //

}
