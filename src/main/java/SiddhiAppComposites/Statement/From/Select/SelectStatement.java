package SiddhiAppComposites.Statement.From.Select;

import SiddhiAppComposites.*;
import SiddhiAppComposites.utilities.visitors.IAttributeVisitor;


public class SelectStatement implements ISelectStatement {

    private final IAttributeList selectItemsList;

    public void extractColumnNames(IAttributeVisitor iAttributeVisitor){
        ((SelectStatementAttributeList) selectItemsList)
                .getAttributesWithOrWithoutAliases().forEach(
                        iAttributeVisitor::visit
                );
    }

    public SelectStatement() {
        selectItemsList = new SelectStatementAttributeList();
    }

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectItemsList.addAttribute(selectItem);
    }

    public IAttributeList getSelectItems(){
        return selectItemsList;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "select " + selectItemsList.getSiddhiAppCompositeAsString() + "\n";
    }
}
