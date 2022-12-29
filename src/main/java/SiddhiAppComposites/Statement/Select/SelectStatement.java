package SiddhiAppComposites.Statement.Select;

import SiddhiAppComposites.IAttributeList;
import SiddhiAppComposites.ISiddhiAppComposite;
import SiddhiAppComposites.SelectStatementAttributeList;

public class SelectStatement implements ISelectStatement {

    private final IAttributeList selectItemsList;

    public SelectStatement() {
        selectItemsList = new SelectStatementAttributeList();
    }

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectItemsList.addAttribute(selectItem);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "select " + selectItemsList.getSiddhiAppCompositeAsString() + "\n";
    }
}
