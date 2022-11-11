package SiddhiApp.Statement.Select;

import SiddhiApp.ISiddhiAppComposite;
import SiddhiApp.SelectStatementAttributeList;

import java.util.ArrayList;
import java.util.List;

public class SelectStatement implements ISelectStatement {

    private SelectStatementAttributeList selectListItems;

    public SelectStatement() {
    }

    public void setSelectListItems(SelectStatementAttributeList selectListItems) {
        this.selectListItems = selectListItems;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return null;
    }
    // has functions
    // has attributes
}
