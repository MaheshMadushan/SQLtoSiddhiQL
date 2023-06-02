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
import java.util.Stack;

public class WhereExpressionHandlingBehaviorEngine extends IEngineExpressionHandleBehavior {
    private final int fromId;
    private final int COLUMN_NAME_INDEX = 0;
    private final int DATA_TYPE_INDEX = 1;
    private String[] ColumnNameAndDataTypeArr; // length is 2 ["columnName","dataType"]
    private AggregateFunction aggregateFunction;
    private final Stack<AggregateFunction> aggregateFunctionsStack = new Stack<>();
    private final int filterExpressionId;

    public WhereExpressionHandlingBehaviorEngine(int fromId, int filterExpressionId) {
        this.filterExpressionId = filterExpressionId;
        this.fromId = fromId;
    }

    @Override
    public void handleTable(Table table) {
        throw new UnsupportedOperationException("Table names in where statement not supported.");
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
        siddhiApp.addColumnWithDataTypeToInputStreamDefinition(filterExpressionId,new ColumnWithDataType(siddhiColumn, getDataType())); // add to stream definition
        // if still processing on function attributes add to function attribute list
        if(aggregateFunctionsStack.empty()) {
            siddhiApp.addSymbolToFilterExpression(filterExpressionId, siddhiColumn.getName());
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiColumn); // add to function (this is a function inside function) attribute
        }
    }

    @Override
    public void handleFunctionExit(Function function) {
        aggregateFunction = aggregateFunctionsStack.pop();
        if(aggregateFunctionsStack.empty()) {
            siddhiApp.addSymbolToFilterExpression(filterExpressionId, aggregateFunction.getSiddhiAppCompositeAsString()); // selectItem.addSelectItemComposite(aggregateFunction);
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
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, doubleValue.toString());
    }

    @Override
    public void handleLongValue(LongValue longValue) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, longValue.toString());
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {

    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, stringValue.toString());
    }

    @Override
    public void handleAddition(Addition addition) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, addition.getStringExpression());
    }

    @Override
    public void handleDivision(Division division) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, division.getStringExpression());
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        throw new UnsupportedOperationException("Integer division " + integerDivision.getStringExpression() + " not supported.");
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, multiplication.getStringExpression());
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, subtraction.getStringExpression());
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, andExpression.getStringExpression());
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, orExpression.getStringExpression());
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, xorExpression.getStringExpression());
    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, "==");
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, greaterThan.getStringExpression());
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, greaterThanEquals.getStringExpression());
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, minorThan.getStringExpression());
    }

    @Override
    public void handleOpenBracket() {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, "(");

    }

    @Override
    public void handleCloseBracket() {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, ")");

    }



    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, minorThanEquals.getStringExpression());
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, notEqualsTo.getStringExpression());
    }

    @Override
    public void handleAlias(Alias alias) {
        siddhiApp.addSymbolToFilterExpression(filterExpressionId, alias.getName());
    }

    @Override
    public void finalizeAddingThisComponentAsRequested() {
        siddhiApp.addFilterExpressionToFromStatement(fromId, filterExpressionId);
    }
}
