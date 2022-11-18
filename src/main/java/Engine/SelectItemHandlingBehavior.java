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
    private Stack<AggregateFunction> aggregateFunctionsStack = new Stack<>();
    private AggregateFunction aggregateFunction;

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

        // if still processing on function attributes add to function attribute list

        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiColumn);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiColumn);
        }

        // need to add to stream definition
        // need to add to select statement
    }

    @Override
    public void handleFunctionExit(Function function) {
        aggregateFunction = aggregateFunctionsStack.pop();
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(aggregateFunction);
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
        Symbol openBracket = new Symbol("(");
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(openBracket);
        }else{
            aggregateFunctionsStack.peek().addAttribute(openBracket);
        }
    }

    @Override
    public void handleCloseBracket() {
        Symbol closeBracket = new Symbol(")");
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(closeBracket);
        }else{
            aggregateFunctionsStack.peek().addAttribute(closeBracket);
        }
    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        Symbol siddhiEqualsTo = new Symbol(equalsTo.getStringExpression());
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiEqualsTo);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiEqualsTo);
        }
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        Symbol siddhiGreaterThan = new Symbol(greaterThan.getStringExpression());
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiGreaterThan);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiGreaterThan);
        }
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        Symbol siddhiGreaterThanEquals = new Symbol(greaterThanEquals.getStringExpression());
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiGreaterThanEquals);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiGreaterThanEquals);
        }
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        Symbol siddhiMinorThan = new Symbol(minorThan.getStringExpression());
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiMinorThan);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiMinorThan);
        }
    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        Symbol siddhiMinorThanEquals = new Symbol(minorThanEquals.getStringExpression());
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiMinorThanEquals);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiMinorThanEquals);
        }
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        Symbol siddhiNotEqualsTo = new Symbol(notEqualsTo.getStringExpression());
        if(aggregateFunctionsStack.empty()) {
            selectItem.addSelectItemComposite(siddhiNotEqualsTo);
        }else{
            aggregateFunctionsStack.peek().addAttribute(siddhiNotEqualsTo);
        }
    }

    @Override
    public void handleAlias(Alias alias) {
    }

    @Override
    public void addToSiddhiApp() {
        siddhiApp.addSelectItem(selectItem);
    }
}
