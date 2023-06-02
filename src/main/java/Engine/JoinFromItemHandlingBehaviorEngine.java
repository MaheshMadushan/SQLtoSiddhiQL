package Engine;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;


public class JoinFromItemHandlingBehaviorEngine extends IEngineExpressionHandleBehavior {
    private final int joinStreamStatementId;
    private final int defineStreamId;
    public JoinFromItemHandlingBehaviorEngine(int defineStreamId, int joinStreamStatementId) {
        this.joinStreamStatementId = joinStreamStatementId;
        this.defineStreamId = defineStreamId;
    }

    @Override
    public void handleTable(net.sf.jsqlparser.schema.Table table) {
        SiddhiAppComposites.Table siddhiAppTable = new SiddhiAppComposites.Table(table.getName());
//        siddhiAppTable.setAlias(table.getAlias().toString());
        siddhiApp.addJoinStatement(defineStreamId, joinStreamStatementId, siddhiAppTable);
        // should get column names corresponding to this table (since select items are already handled) and add to define statement
        // should add to from statement

        // siddhiApp should start handling join
        // should start define statement for this specific statement
        // should get column names corresponding to this table (since select items are already handled) and add to define statement
        // also should add items in on statement
    }


    @Override
    public void handleColumn(Column column) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }


    @Override
    public void handleFunctionExit(Function function) {

    }

    @Override
    public void handleFunctionBegin(Function function) {

    }


    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleLongValue(LongValue longValue) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleAddition(Addition addition) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleDivision(Division division) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleOpenBracket() {

    }

    @Override
    public void handleCloseBracket() {

    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void handleAlias(Alias alias) {
        throw new UnsupportedOperationException("sub joins not supported in joins");
    }

    @Override
    public void finalizeAddingThisComponentAsRequested() {

    }
}
