package Engine;

import SiddhiAppComposites.AggregateFunction;
import SiddhiAppComposites.ColumnWithDataType;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.SubJoin;

import java.util.Stack;

public class HavingExpressionHandlingBehaviorEngine extends IEngineExpressionHandleBehavior {
    private final int COLUMN_NAME_INDEX = 0;
    private final int DATA_TYPE_INDEX = 1;
    private String[] ColumnNameAndDataTypeArr; // length is 2 ["columnName","dataType"]

    private SiddhiAppComposites.Column siddhiColumn;
    private AggregateFunction aggregateFunction;
    private final Stack<AggregateFunction> aggregateFunctionsStack = new Stack<>();

    public HavingExpressionHandlingBehaviorEngine() {
    }

    @Override
    public void handleTable(Table table) {
        throw new UnsupportedOperationException("Table names in where statement not supported.");
    }

    @Override
    public void handleJoin(SubJoin subJoin) {
        throw new UnsupportedOperationException("Joins in where statement not valid.");
    }

    private String getColumnName(){
        return ColumnNameAndDataTypeArr[COLUMN_NAME_INDEX];
    }

    private String getDataType(){
        return ColumnNameAndDataTypeArr[DATA_TYPE_INDEX];
    }

    private void tokenizeColumnName(String sqlColumnWithDataType){
        ColumnNameAndDataTypeArr = sqlColumnWithDataType.split("[@]", 0);
        if(ColumnNameAndDataTypeArr.length != 2){throw new IllegalArgumentException("provided column and data type format is should be \"ColumnName@DataType\". but provided : " + "[" + sqlColumnWithDataType + "] in Where Clause");}
    }

    @Override
    public void handleColumn(Column sqlColumnWithDataType) {
        tokenizeColumnName(sqlColumnWithDataType.getColumnName()); // eg - this tokenize "ColumnName@DataType" to ["ColumnName", "DataType"]
        SiddhiAppComposites.Column siddhiColumn = new SiddhiAppComposites.Column();
        siddhiColumn.setName(getColumnName());
//        siddhiApp.addColumnWithDataTypeToInputStreamDefinition(new ColumnWithDataType(siddhiColumn, getDataType())); // add to stream definition
        // if still processing on function attributes add to function attribute list
        if(aggregateFunctionsStack.empty()) {
            siddhiApp.addHavingExpression(siddhiColumn.getName());
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiColumn); // add to function (this is a function inside function) attribute
        }
    }

    @Override
    public void handleFunctionExit(Function function) {
        aggregateFunction = aggregateFunctionsStack.pop();
        if(aggregateFunctionsStack.empty()) {
            siddhiApp.addHavingExpression(aggregateFunction.getSiddhiAppCompositeAsString()); // selectItem.addSelectItemComposite(aggregateFunction);
        }
        else{
            aggregateFunctionsStack.peek().addAttribute(aggregateFunction);
        }
    }

    @Override
    public void handleFunctionBegin(Function function) {
        aggregateFunction = new AggregateFunction(function.getName());
        aggregateFunctionsStack.push(aggregateFunction);
    }

    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
        throw new UnsupportedOperationException("Signed Expression " + signedExpression.getExpression() +" not supported.");
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
        siddhiApp.addHavingExpression(doubleValue.toString());
    }

    @Override
    public void handleLongValue(LongValue longValue) {
        siddhiApp.addHavingExpression(longValue.toString());
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {

    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        siddhiApp.addHavingExpression(stringValue.toString());
    }

    @Override
    public void handleAddition(Addition addition) {
        siddhiApp.addHavingExpression(addition.getStringExpression());
    }

    @Override
    public void handleDivision(Division division) {
        siddhiApp.addHavingExpression(division.getStringExpression());
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        throw new UnsupportedOperationException("Integer division " + integerDivision.getStringExpression() + " not supported.");
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        siddhiApp.addHavingExpression(multiplication.getStringExpression());
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        siddhiApp.addHavingExpression(subtraction.getStringExpression());
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        siddhiApp.addHavingExpression(andExpression.getStringExpression());
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
        siddhiApp.addHavingExpression(orExpression.getStringExpression());
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
        siddhiApp.addHavingExpression(xorExpression.getStringExpression());
    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        siddhiApp.addHavingExpression("==");
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        siddhiApp.addHavingExpression(greaterThan.getStringExpression());
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        siddhiApp.addHavingExpression(greaterThanEquals.getStringExpression());
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        siddhiApp.addHavingExpression(minorThan.getStringExpression());
    }

    @Override
    public void handleOpenBracket() {
        siddhiApp.addHavingExpression("(");

    }

    @Override
    public void handleCloseBracket() {
        siddhiApp.addHavingExpression(")");

    }



    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        siddhiApp.addHavingExpression(minorThanEquals.getStringExpression());
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        siddhiApp.addHavingExpression(notEqualsTo.getStringExpression());
    }

    @Override
    public void handleAlias(Alias alias) {
        siddhiApp.addHavingExpression(alias.getName());
    }

    @Override
    public void handleGroupByExpressions(Column column) {
        siddhiApp.addGroupByExpression(column.getColumnName().split("@")[0]);
    }

    @Override
    public void addToSiddhiApp(String streamName) {
        throw new UnsupportedOperationException();

    }

}
