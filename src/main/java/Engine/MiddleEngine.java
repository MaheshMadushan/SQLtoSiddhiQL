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

public class MiddleEngine extends IEngine {

    public MiddleEngine(){super();}

    @Override
    public void handleTable(Table table) {
        engineBehavior.handleTable(table);
    }

    @Override
    public void handleColumn(Column column) {
        engineBehavior.handleColumn(column);
    }

    @Override
    public void handleFunction(Function function) {
        engineBehavior.handleFunction(function);
    }

    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
        engineBehavior.handleSignedExpression(signedExpression);
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
        engineBehavior.handleDoubleValue(doubleValue);

    }

    @Override
    public void handleLongValue(LongValue longValue) {
        engineBehavior.handleLongValue(longValue);

    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {
        engineBehavior.handleParenthesis(parenthesis);

    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        engineBehavior.handleStringValue(stringValue);

    }

    @Override
    public void handleAddition(Addition addition) {
        engineBehavior.handleAddition(addition);

    }

    @Override
    public void handleDivision(Division division) {
        engineBehavior.handleDivision(division);

    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        engineBehavior.handleIntegerDivision(integerDivision);

    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        engineBehavior.handleMultiplication(multiplication);

    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        engineBehavior.handleSubtraction(subtraction);
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        System.out.println("not yet handled");
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {

    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {

    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {

    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {

    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {

    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {

    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {

    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {

    }

    @Override
    public void handleAlias(Alias alias) {

    }
}
