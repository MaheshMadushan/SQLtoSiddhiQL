package Compiler;

import Engine.*;
import Engine.FromItemHandlingBehaviorEngine;
import Engine.WhereExpressionHandlingBehaviorEngine;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;

import java.util.List;

public class CustomSelectBodyVisitor implements SelectVisitor {

    private final IEngine middleEngine;

    public CustomSelectBodyVisitor(IEngine middleEngine) {
        this.middleEngine = middleEngine;
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        List<SelectItem> selectItemList = plainSelect.getSelectItems();
        int defineStatementId = 0;
        for(SelectItem selectItem : selectItemList){
            middleEngine.setExpressionHandlingBehavior(new SelectItemHandlingBehaviorEngine(defineStatementId));
            selectItem.accept(new CustomSelectItemVisitorImpl(middleEngine));
            middleEngine.finalizeAddingThisComponentAsRequested();
        }
        // seem like distinct not supported by siddhiQL
        Distinct distinct = plainSelect.getDistinct();
        if(distinct != null) {
            distinct.getOnSelectItems();
        }

        FromItem fromItem = plainSelect.getFromItem(); // should be a table name
        int id = 0;
        if(fromItem != null) {
            middleEngine.setExpressionHandlingBehavior(new FromItemHandlingBehaviorEngine(defineStatementId,id));
            fromItem.accept(new CustomFromItemVisitorImpl(middleEngine));
        }

        Expression whereExpression = plainSelect.getWhere();
        int filterExpressionId = 0;
        if(whereExpression != null) {
            middleEngine.setExpressionHandlingBehavior(new WhereExpressionHandlingBehaviorEngine(id,filterExpressionId));
            whereExpression.accept(new CustomExpressionVisitorAdaptor(middleEngine));
            middleEngine.finalizeAddingThisComponentAsRequested();
        }

        List<Join> joins = plainSelect.getJoins();
        if(joins != null){
            int joinStreamStatementId = 0;
            for ( Join join : joins){
                middleEngine.setExpressionHandlingBehavior(new JoinFromItemHandlingBehaviorEngine(++defineStatementId, joinStreamStatementId));
                FromItem rightJoinItem = join.getRightItem();
                rightJoinItem.accept(new CustomJoinFromItemVisitor(middleEngine));
                List<Expression> onExpressions = (List<Expression>) join.getOnExpressions();
                if(onExpressions != null){
                    int onExpressionId = 0;
                    for(Expression onExpression : onExpressions){
                        middleEngine
                                .setExpressionHandlingBehavior(new JoinOnExpressionsHandlingBehaviorEngine(defineStatementId,joinStreamStatementId, onExpressionId));
                        onExpression.accept(new CustomOnExpressionVisitor(middleEngine));
                        onExpressionId++;
                    }
                }
                joinStreamStatementId++;
            }
        }

        GroupByElement groupByElement = plainSelect.getGroupBy(); // not handled yet
        if(groupByElement != null) {
            groupByElement.accept(new CustomGroupByElementVisitor());
        }

        List<OrderByElement> orderByElements = plainSelect.getOrderByElements(); // not handled yet
        if(orderByElements != null) {
            for(OrderByElement orderByElement: orderByElements) {
                orderByElement.accept(new CustomOrderByElementVisitor(middleEngine));
            }
        }

        Expression havingExpression = plainSelect.getHaving(); // not handled yet
        if(havingExpression != null) {
            havingExpression.accept(new CustomExpressionVisitorAdaptor(middleEngine));
        }
    }

    @Override
    public void visit(SetOperationList setOperationList) {
        throw new UnsupportedOperationException("Set ops not supported.");
    }

    @Override
    public void visit(WithItem withItem) {
        throw new UnsupportedOperationException("With item not supported");
    }

    @Override
    public void visit(ValuesStatement valuesStatement) {
        throw new UnsupportedOperationException("values statement not supported");
    }
}
