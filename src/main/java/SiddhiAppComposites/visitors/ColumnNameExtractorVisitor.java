package SiddhiAppComposites.visitors;

import SiddhiAppComposites.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ColumnNameExtractorVisitor implements IAttributeVisitor{

    private final List<String> columnNames = new ArrayList<>();
    private final Consumer<ISiddhiAppComposite> iSiddhiAppCompositeConsumer = siddhiAppComposite -> {
                Class<? extends ISiddhiAppComposite> siddhiAppCompositeClass = siddhiAppComposite.getClass();
                if(siddhiAppCompositeClass.equals(SelectItem.class)){
                    this.visit((SelectItem) siddhiAppComposite);
                }else if(siddhiAppCompositeClass.equals(Column.class)){
                    this.visit((Column) siddhiAppComposite);
                }else if(siddhiAppCompositeClass.equals(ColumnWithDataType.class)){
                    this.visit((ColumnWithDataType) siddhiAppComposite);
                }else if(siddhiAppCompositeClass.equals(AggregateFunction.class)){
                    this.visit((AggregateFunction) siddhiAppComposite);
                }
            };

    @Override
    public void visit(AggregateFunction aggregateFunction) {
        aggregateFunction.getAttributeList().forEach(iSiddhiAppCompositeConsumer);
    }

    @Override
    public void visit(Column column) {
        columnNames.add(column.getName());
    }

    @Override
    public void visit(SelectItem selectItem) {
        selectItem.getSelectItemComposites().forEach(iSiddhiAppCompositeConsumer);
    }

    @Override
    public void visit(ColumnWithDataType columnWithDataType) {
        columnNames.add(columnWithDataType.getColumnName());
    }

    public void visit(ISiddhiAppComposite iSiddhiAppComposite) {
        iSiddhiAppCompositeConsumer.accept(iSiddhiAppComposite);
    }

    public List<String> getColumnNames() {
        return columnNames;
    }
}
