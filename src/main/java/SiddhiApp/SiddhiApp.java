package SiddhiApp;

import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {
    // create define stream
    DefineStreamStatement defineStreamStatement = new DefineStreamStatement("InputStream");
    // from statement
    // select statement
    SelectStatement selectStatement = new SelectStatement();



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
