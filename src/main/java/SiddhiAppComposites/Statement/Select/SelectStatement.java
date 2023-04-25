package SiddhiAppComposites.Statement.Select;

import SiddhiAppComposites.*;
import SiddhiAppComposites.visitors.ColumnNameExtractorVisitor;

import java.util.List;

public class SelectStatement implements ISelectStatement {

    private final IAttributeList selectItemsList;

    public List<String> extractColumnNames(){
        ColumnNameExtractorVisitor columnNameExtractorVisitor = new ColumnNameExtractorVisitor();
        ((SelectStatementAttributeList) selectItemsList)
                .getAttributesWithOrWithoutAliases().forEach(
                        columnNameExtractorVisitor::visit
                );
        return columnNameExtractorVisitor.getColumnNames();
    }

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
