package Engine;

import SiddhiApp.SiddhiApp;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FunctionItem;

public class WhereExpressionHandlingBehavior extends IExpressionHandleBehavior{

    public WhereExpressionHandlingBehavior() {
    }

    @Override
    public void handleTable(Table table) {
        System.out.println("in WhereExpressionHandlingBehavior table ");
        System.out.println(table.getName());
    }

    @Override
    public void handleColumn(Column column) {
        System.out.println("in WhereExpressionHandlingBehavior column");
        System.out.println(column.getColumnName());
    }

    @Override
    public void handleFunction(Function function) {
        System.out.println("in WhereExpressionHandlingBehavior function");
        System.out.println(function.getName());
    }

    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
        System.out.println("in WhereExpressionHandlingBehavior signedExpression");
        System.out.println(signedExpression.getSign());
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
        System.out.println("in WhereExpressionHandlingBehavior doubleValue");
        System.out.println(doubleValue.getValue());
    }

    @Override
    public void handleLongValue(LongValue longValue) {
        System.out.println("in WhereExpressionHandlingBehavior longValue");
        System.out.println(longValue.getValue());
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {
        System.out.println("in WhereExpressionHandlingBehavior parenthesis");
        System.out.println(parenthesis.toString());
    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        System.out.println("in WhereExpressionHandlingBehavior stringValue");
        System.out.println(stringValue.getValue());
    }

    @Override
    public void handleAddition(Addition addition) {
        System.out.println("in WhereExpressionHandlingBehavior addition");
        System.out.println(addition.toString());
    }

    @Override
    public void handleDivision(Division division) {
        System.out.println("in WhereExpressionHandlingBehavior division");
        System.out.println(division.getStringExpression());
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        System.out.println("in WhereExpressionHandlingBehavior integerDivision");
        System.out.println(integerDivision.getStringExpression());
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        System.out.println("in WhereExpressionHandlingBehavior multiplication");
        System.out.println(multiplication.getStringExpression());
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        System.out.println("in WhereExpressionHandlingBehavior subtraction");
        System.out.println(subtraction.getStringExpression());
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        System.out.println("in WhereExpressionHandlingBehavior andExpression");
        System.out.println(andExpression.getStringExpression());
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
        System.out.println("in WhereExpressionHandlingBehavior orExpression");
        System.out.println(orExpression.getStringExpression());
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
        System.out.println("in WhereExpressionHandlingBehavior xorExpression");
        System.out.println(xorExpression.getStringExpression());
    }

    @Override
    public void handleOpenBracket() {

    }

    @Override
    public void handleCloseBracket() {

    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        System.out.println("in WhereExpressionHandlingBehavior equalsTo");
        System.out.println(equalsTo.getStringExpression());
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        System.out.println("in WhereExpressionHandlingBehavior GreaterThan");
        System.out.println(greaterThan.getStringExpression());
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        System.out.println("in WhereExpressionHandlingBehavior greaterThanEquals");
        System.out.println(greaterThanEquals.getStringExpression());
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        System.out.println("in WhereExpressionHandlingBehavior minorThan");
        System.out.println(minorThan.getStringExpression());
    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        System.out.println("in WhereExpressionHandlingBehavior minorThanEquals");
        System.out.println(minorThanEquals.getStringExpression());
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        System.out.println("in WhereExpressionHandlingBehavior notEqualsTo");
        System.out.println(notEqualsTo.getStringExpression());
    }

    @Override
    public void handleAlias(Alias alias) {
        System.out.println("in WhereExpressionHandlingBehavior alias");
        System.out.println(alias.getName());
    }
}
