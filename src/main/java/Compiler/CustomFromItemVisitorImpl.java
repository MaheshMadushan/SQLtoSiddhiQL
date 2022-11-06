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
    }

    @Override
    public void visit(SubJoin subJoin) {
    }

    @Override
    public void visit(LateralSubSelect lateralSubSelect) {
    }

    @Override
    public void visit(ValuesList valuesList) {
    }

    @Override
    public void visit(TableFunction tableFunction) {
    }

    @Override
    public void visit(ParenthesisFromItem parenthesisFromItem) {
    }
}
