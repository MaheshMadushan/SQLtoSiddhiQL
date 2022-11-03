package Compiler;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

public class CustomFromItemVisitorImpl implements FromItemVisitor {
    @Override
    public void visit(Table table) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(table.toString());
    }

    @Override
    public void visit(SubSelect subSelect) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(subSelect.toString());
    }

    @Override
    public void visit(SubJoin subJoin) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(subJoin.toString());
    }

    @Override
    public void visit(LateralSubSelect lateralSubSelect) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(lateralSubSelect.toString());
    }

    @Override
    public void visit(ValuesList valuesList) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(valuesList.toString());
    }

    @Override
    public void visit(TableFunction tableFunction) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(tableFunction.toString());
    }

    @Override
    public void visit(ParenthesisFromItem parenthesisFromItem) {
        System.out.println("in " + CustomFromItemVisitorImpl.class);
        System.out.println(parenthesisFromItem.toString());
    }
}
