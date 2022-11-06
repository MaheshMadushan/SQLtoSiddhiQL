package Compiler;

import Engine.IEngine;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.OrderByVisitor;

public class CustomOrderByElementVisitor implements OrderByVisitor {
    private IEngine middleEngine;

    public CustomOrderByElementVisitor(IEngine middleEngine) {
        this.middleEngine = middleEngine;
    }

    @Override
    public void visit(OrderByElement orderByElement) {
        System.out.println("in " + CustomOrderByElementVisitor.class);
        System.out.println(orderByElement.toString());

        boolean isASC = orderByElement.isAsc();
        boolean isASCDESCPresent = orderByElement.isAscDescPresent();
        System.out.println(isASC + " " + isASCDESCPresent);

        Expression orderByExpression = orderByElement.getExpression();

        orderByExpression.accept(new CustomExpressionVisitorAdaptor(middleEngine));
    }
}
