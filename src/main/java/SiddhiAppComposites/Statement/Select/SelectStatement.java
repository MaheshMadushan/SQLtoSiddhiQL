package SiddhiAppComposites.Statement.Select;

import SiddhiAppComposites.*;
import SiddhiAppComposites.utilities.visitors.IAttributeVisitor;

import java.util.List;
import java.util.Map;


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

    public void addSelectItem(String streamName, ISiddhiAppComposite selectItem){
        selectItemsList.addAttribute(streamName, selectItem);
    }

    public IAttributeList getSelectItems(){
        return selectItemsList;
    }

    public Map<String, List<ISiddhiAppComposite>> getAttributesListWithStreamName() {
        return selectItemsList.getAttributesListWithStreamName();
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "select " + selectItemsList.getSiddhiAppCompositeAsString() + "\n";
    }

    public String getSiddhiAppCompositeAsStringInJoins() {
        return "select " + selectItemsList.generateString() + "\n";
    }
}
