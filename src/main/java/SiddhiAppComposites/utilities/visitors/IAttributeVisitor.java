package SiddhiAppComposites.utilities.visitors;

import SiddhiAppComposites.*;

public interface IAttributeVisitor extends IVisitor {

    void visit(AggregateFunction aggregateFunction);
    void visit(Column column);
    void visit(SelectItem selectItem);
    void visit(ColumnWithDataType columnWithDataType);
}
