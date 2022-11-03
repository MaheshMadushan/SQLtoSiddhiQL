package Compiler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;

public class CustomSelectItemVisitorImpl implements SelectItemVisitor {

    @Override
    public void visit(AllColumns allColumns) {
        System.out.println("in AllColumns:" + CustomSelectItemVisitorImpl.class);
        System.out.println(allColumns.toString());
    }

    @Override
    public void visit(AllTableColumns allTableColumns) {
        System.out.println("in AllTableColumns:" + CustomSelectItemVisitorImpl.class);
        System.out.println(allTableColumns.toString());
    }

    @Override
    public void visit(SelectExpressionItem selectExpressionItem) {
        Expression itemExpression = selectExpressionItem.getExpression();
        itemExpression.accept(new CustomExpressionVisitorAdaptor());
    }
}
