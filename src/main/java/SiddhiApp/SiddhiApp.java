package SiddhiApp;

import SiddhiApp.Statement.FilterExpressionStatement.FilterExpression;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Statement.From.FromStatement;
import SiddhiApp.Statement.From.IFromStatement;
import SiddhiApp.Statement.Insert.IInsertStatement;
import SiddhiApp.Statement.Insert.InsertStatement;
import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {
    // Annotations
        // app name
        // source
        // map
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

    public void addSymbolToFilterExpression(String symbol){
        ((FilterExpression) this.filterExpression).addSymbol(symbol);
    }

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectStatement.addSelectItem(selectItem);
    }

    public void addColumnWithDataType(ISiddhiAppComposite columnWithDataType){
        defineStreamStatement.addAttributeWithDataType(columnWithDataType);
    }

    public String getSelectItemListAsString(){
        // Annotations
            // app name
            // source
        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream");
        System.out.println(defineStreamStatement.getSiddhiAppCompositeAsString()); // IPStream
        // Annotations
            // sink
        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "OutputStream");
        System.out.println(defineStreamStatement.getSiddhiAppCompositeAsString()); // OPStream

        fromStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream");
        fromStatement.setFromStatementComposite(filterExpression);
        System.out.println(fromStatement.getSiddhiAppCompositeAsString());

        System.out.println(selectStatement.getSiddhiAppCompositeAsString());

        insertStatement.setOutputStreamName(inputOutputStreamNamePrefix + "OutputStream");
        System.out.println(insertStatement.getSiddhiAppCompositeAsString());

        return selectStatement.getSiddhiAppCompositeAsString();
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
