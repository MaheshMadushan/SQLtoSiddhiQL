package Engine;

import SiddhiApp.AggregateFunction;
import SiddhiApp.SelectItem;
import SiddhiApp.Symbol;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.util.Stack;

public class SelectItemHandlingBehavior extends IExpressionHandleBehavior{

    private final SelectItem selectItem;
    private SiddhiApp.Column siddhiColumn;
    private SiddhiApp.Alias siddhiAlias;
    private Stack<AggregateFunction> aggregateFunctions = new Stack<>();

    public SelectItemHandlingBehavior() {
        selectItem  = new SelectItem();
    }

    @Override
    public void handleTable(Table table) {
    }

    @Override
    public void handleColumn(Column sqlColumn) {
        siddhiColumn = new SiddhiApp.Column();
        siddhiColumn.setName(sqlColumn.getName(false));
        selectItem.addSelectItemComposite(siddhiColumn);
        // need to add to stream definition
        // need to add to select statement
    }

    // function depth is for identifying that CustomExpressionVisitor still inside the visit(function), or it has got into
    // the nested function inside the function.
    // example :- SELECT COUNT(col_a), STDDEV(col_a + col_b)
    @Override
    public void handleFunction(Function function) {
//        selectItem.addSelectItemComposite(function.getName());
    }

    @Override
    public void handleFunctionExit(Function function) {
    }

    @Override
    public void handleFunctionBegin(Function function) {
    }

    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
    }

    @Override
    public void handleLongValue(LongValue longValue) {
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {
    }

    @Override
    public void handleStringValue(StringValue stringValue) {
    }

    @Override
    public void handleAddition(Addition addition) {
    }

    @Override
    public void handleDivision(Division division) {
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
    }

    @Override
    public void handleOpenBracket() {
        selectItem.addSelectItemComposite(new Symbol("("));
    }

    @Override
    public void handleCloseBracket() {
        selectItem.addSelectItemComposite(new Symbol(")"));
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
