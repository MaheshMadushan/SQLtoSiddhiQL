package Compiler;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.statement.select.SubSelect;

public class CustomItemListVisitor implements ItemsListVisitor {
    @Override
    public void visit(SubSelect subSelect) {
        System.out.println("in subSelect:" + this.getClass());
        System.out.println(subSelect.toString());
    }

    @Override
    public void visit(ExpressionList expressionList) {
        System.out.println("in subSelect:" + this.getClass());
        System.out.println(expressionList.toString());
    }

    @Override
    public void visit(NamedExpressionList namedExpressionList) {
        System.out.println("in subSelect:" + this.getClass());
        System.out.println(namedExpressionList.toString());
    }

    @Override
    public void visit(MultiExpressionList multiExpressionList) {
        System.out.println("in subSelect:" + this.getClass());
        System.out.println(multiExpressionList.toString());
    }
}
