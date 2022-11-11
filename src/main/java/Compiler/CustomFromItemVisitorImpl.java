package Compiler;

import Engine.FromItemHandlingBehavior;
import Engine.IEngine;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

public class CustomFromItemVisitorImpl implements FromItemVisitor {
    private IEngine middleEngine;

    public CustomFromItemVisitorImpl(IEngine middleEngine) {
        this.middleEngine = middleEngine;
    }

    @Override
    public void visit(Table table) {
        middleEngine.setExpressionHandlingBehavior(new FromItemHandlingBehavior());
        middleEngine.handleTable(table);
    }

    @Override
    public void visit(SubSelect subSelect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(SubJoin subJoin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(LateralSubSelect lateralSubSelect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(ValuesList valuesList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(TableFunction tableFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(ParenthesisFromItem parenthesisFromItem) {
        throw new UnsupportedOperationException();
    }
}
