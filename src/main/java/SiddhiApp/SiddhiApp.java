package SiddhiApp;

import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {
    // create define stream
        // create ip stream
        // create op stream
    // from statement
    // select statement
    SelectStatement selectStatement = new SelectStatement();

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectStatement.addSelectItem(selectItem);
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
