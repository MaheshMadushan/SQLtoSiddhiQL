package Compiler;

import Engine.IEngine;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.statement.select.SubSelect;

import java.util.List;

public class CustomItemListVisitor implements ItemsListVisitor {
    private IEngine middleEngine;

    public CustomItemListVisitor(IEngine middleEngine) {
        this.middleEngine = middleEngine;
    }


    @Override
    public void visit(SubSelect subSelect) {
        System.out.println("in subSelect:" + this.getClass());
        System.out.println(subSelect.toString());
    }

    @Override
    public void visit(ExpressionList expressionList) {
        System.out.println("in ExpressionList:" + this.getClass());
        System.out.println(expressionList.toString());
        List<Expression> expressions = expressionList.getExpressions();
        for (Expression expression : expressions) expression.accept(new CustomExpressionVisitorAdaptor(middleEngine));
    }

    @Override
    public void visit(NamedExpressionList namedExpressionList) {
        System.out.println("in NamedExpressionList:" + this.getClass());
        System.out.println(namedExpressionList.toString());
    }

    @Override
    public void visit(MultiExpressionList multiExpressionList) {
        System.out.println("in MultiExpressionList:" + this.getClass());
        System.out.println(multiExpressionList.toString());
    }
}
