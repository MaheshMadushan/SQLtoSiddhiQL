package SiddhiApp;

import SiddhiApp.Statement.FilterExpressionStatement.FilterExpression;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {
    // create define stream
    DefineStreamStatement defineStreamStatement = new DefineStreamStatement("InputStream");
    // select statement
    SelectStatement selectStatement = new SelectStatement();
    // from statement
    // filter statement
    IFilterExpression filterExpression = new FilterExpression();

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
        System.out.println(selectStatement.getSiddhiAppCompositeAsString());
        System.out.println(defineStreamStatement.getSiddhiAppCompositeAsString());
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
