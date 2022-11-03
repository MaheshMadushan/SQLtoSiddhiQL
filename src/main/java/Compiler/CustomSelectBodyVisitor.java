package Compiler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;

import java.util.Collection;
import java.util.List;

public class CustomSelectBodyVisitor implements SelectVisitor {



    @Override
    public void visit(PlainSelect plainSelect) {
        System.out.println("in PlainSelect :" + CustomSelectBodyVisitor.class);
        System.out.println(plainSelect.toString());

        List<SelectItem> selectItemList = plainSelect.getSelectItems();
        for(SelectItem selectItem : selectItemList){
            selectItem.accept(new CustomSelectItemVisitorImpl());
        }

        Distinct distinct = plainSelect.getDistinct();
        if(distinct != null) {
            List<SelectItem> OnSelectItemList = distinct.getOnSelectItems();
            if (OnSelectItemList != null) {
                for (SelectItem OnSelectItem : OnSelectItemList) {
                    OnSelectItem.accept(new CustomSelectItemVisitorImpl());
                }
            }
        }

        Expression whereExpression = plainSelect.getWhere();
        if(whereExpression != null) {
            whereExpression.accept(new CustomExpressionVisitorAdaptor());
        }

        List<Join> joins = plainSelect.getJoins();
        if(joins != null){
            for ( Join join : joins){
                System.out.println(join.toString());
                System.out.println("JoinWindow:" + join.getJoinWindow());
                System.out.println("OnExpressions:" + join.getOnExpressions());
                System.out.println("RightItem:" + join.getRightItem());
                System.out.println("UsingColumns:" + join.getUsingColumns());

                List<Expression> onExpressions = (List<Expression>) join.getOnExpressions();
                if(onExpressions != null){
                    for(Expression onExpression : onExpressions){
                        onExpression.accept(new CustomExpressionVisitorAdaptor());
                    }
                }
            }
        }

        FromItem fromItem = plainSelect.getFromItem();
        if(fromItem != null) {
            fromItem.accept(new CustomFromItemVisitorImpl());
        }
    }

    @Override
    public void visit(SetOperationList setOperationList) {
        System.out.println("in SetOperationList :" + CustomSelectBodyVisitor.class);
        System.out.println(setOperationList.toString());
    }

    @Override
    public void visit(WithItem withItem) {
        System.out.println("in WithItem :" + CustomSelectBodyVisitor.class);
        System.out.println(withItem.toString());
    }

    @Override
    public void visit(ValuesStatement valuesStatement) {
        System.out.println("in ValuesStatement :" + CustomSelectBodyVisitor.class);
        System.out.println(valuesStatement.toString());
    }
}
