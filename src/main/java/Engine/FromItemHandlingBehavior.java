package Engine;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FunctionItem;

public class FromItemHandlingBehavior extends IExpressionHandleBehavior{
    @Override
    public void handleTable(Table table) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleColumn(Column columnName) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleFunction(Function function) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleLongValue(LongValue longValue) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleAddition(Addition addition) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleDivision(Division division) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        System.out.println("in FromItemHandlingBehavior");
    }

    @Override
    public void handleAlias(Alias alias) {
        System.out.println("in FromItemHandlingBehavior");
    }
}
