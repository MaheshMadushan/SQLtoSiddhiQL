package Compiler;

import Engine.IEngine;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.statement.select.GroupByElement;
import net.sf.jsqlparser.statement.select.GroupByVisitor;

public class CustomGroupByElementVisitor implements GroupByVisitor {

    private final IEngine middleEngine;

    public CustomGroupByElementVisitor(IEngine middleEngine) {
        this.middleEngine = middleEngine;
    }

    @Override
    public void visit(GroupByElement groupByElement) {

        ExpressionList groupByExpressionList = groupByElement.getGroupByExpressionList();

        groupByExpressionList.accept(new CustomGroupByItemListVisitor(middleEngine));
    }
}
