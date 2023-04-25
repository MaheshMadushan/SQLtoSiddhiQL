package SiddhiAppComposites.visitors;

import SiddhiAppComposites.*;
import SiddhiAppComposites.Statement.Select.ISelectStatement;
import SiddhiAppComposites.Statement.Select.SelectStatement;

public interface IAttributeVisitor extends IVistor {

    void visit(AggregateFunction aggregateFunction);
    void visit(Column column);
    void visit(SelectItem selectItem);
    void visit(ColumnWithDataType columnWithDataType);
}
