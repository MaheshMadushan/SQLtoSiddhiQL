package Engine;

import SiddhiAppComposites.*;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
public class JoinOnExpressionsHandlingBehaviorEngine extends IEngineExpressionHandleBehavior {
    private final int COLUMN_NAME_INDEX = 0;
    private final int DATA_TYPE_INDEX = 1;
    private String[] ColumnNameAndDataTypeArr;
    private SiddhiAppComposites.Column siddhiColumn;
    private final int defineStreamId;
    private final int joinStreamStatementId;
    private final int onExpressionId;
    public JoinOnExpressionsHandlingBehaviorEngine(int defineStreamId, int joinStreamStatementId, int onExpressionId) {
        this.defineStreamId = defineStreamId;
        this.joinStreamStatementId = joinStreamStatementId;
        this.onExpressionId = onExpressionId;
    }

    @Override
    public void handleTable(Table table) {}
    private String getColumnName(){
        return ColumnNameAndDataTypeArr[COLUMN_NAME_INDEX];
    }
    private String getDataType(){
        return ColumnNameAndDataTypeArr[DATA_TYPE_INDEX];
    }
    private void tokenizeColumnName(String sqlColumnWithDataType){
        ColumnNameAndDataTypeArr = sqlColumnWithDataType.split("[@]", 0);
        if(ColumnNameAndDataTypeArr.length != 2){
            throw new IllegalArgumentException("provided column and data type format is " +
                    "should be \"ColumnName@DataType\". but provided : " +
                    "[" + sqlColumnWithDataType + "] in Select items");
        }
    }
    @Override
    public void handleColumn(Column sqlColumnWithDataType) {
        // eg - this tokenize "ColumnName@DataType" to ["ColumnName", "DataType"]
        tokenizeColumnName(sqlColumnWithDataType.getColumnName());
        siddhiColumn = new SiddhiAppComposites.Column(getColumnName(),null);
        ColumnWithDataType columnWithDataType = new ColumnWithDataType(siddhiColumn, getDataType());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiColumn);
        siddhiApp.addColumnWithDataTypeToInputStreamDefinition(defineStreamId, columnWithDataType);
    }


    @Override
    public void handleFunctionExit(Function function) {
        throw new UnsupportedOperationException("functions not supported in on Expression");
    }

    @Override
    public void handleFunctionBegin(Function function) {
        throw new UnsupportedOperationException("functions not supported in on Expression");
    }

    @Override
    public void handleSignedExpression(SignedExpression signedExpression) {
        Symbol siddhiSignedExpression = new Symbol(String.valueOf(signedExpression.getSign()));
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiSignedExpression);
    }

    @Override
    public void handleDoubleValue(DoubleValue doubleValue) {
        Symbol siddhiDoubleValue = new Symbol(String.valueOf(doubleValue.getValue()));
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiDoubleValue);
    }

    @Override
    public void handleLongValue(LongValue longValue) {
        Symbol siddhiLongValue = new Symbol(String.valueOf(longValue.getValue()));
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiLongValue);
    }

    @Override
    public void handleParenthesis(Parenthesis parenthesis) {

    }

    @Override
    public void handleStringValue(StringValue stringValue) {
        Symbol siddhiStringValue = new Symbol(stringValue.getValue());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiStringValue);
    }

    @Override
    public void handleAddition(Addition addition) {
        Symbol siddhiAddition = new Symbol(addition.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiAddition);
    }

    @Override
    public void handleDivision(Division division) {
        Symbol siddhiDivision = new Symbol(division.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiDivision);
    }

    @Override
    public void handleIntegerDivision(IntegerDivision integerDivision) {
        Symbol siddhiIntegerDivision = new Symbol(integerDivision.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiIntegerDivision);
    }

    @Override
    public void handleMultiplication(Multiplication multiplication) {
        Symbol siddhiMultiplication = new Symbol(multiplication.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiMultiplication);
    }

    @Override
    public void handleSubtraction(Subtraction subtraction) {
        Symbol siddhiSubtraction = new Symbol(subtraction.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiSubtraction);
    }

    @Override
    public void handleAndExpression(AndExpression andExpression) {
        Symbol siddhiAndExpression = new Symbol(andExpression.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiAndExpression);
    }

    @Override
    public void handleOrExpression(OrExpression orExpression) {
        Symbol siddhiOrExpression = new Symbol(orExpression.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiOrExpression);
    }

    @Override
    public void handleXorExpression(XorExpression xorExpression) {
        Symbol siddhiXorExpression = new Symbol(xorExpression.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiXorExpression);
    }

    @Override
    public void handleOpenBracket() {
        Symbol openBracket = new Symbol("(");
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, openBracket);
    }

    @Override
    public void handleCloseBracket() {
        Symbol closeBracket = new Symbol(")");
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, closeBracket);
    }

    @Override
    public void handleEqualsTo(EqualsTo equalsTo) {
        Symbol siddhiEqualsTo = new Symbol("==");
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiEqualsTo);
    }

    @Override
    public void handleGreaterThan(GreaterThan greaterThan) {
        Symbol siddhiGreaterThan = new Symbol(greaterThan.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiGreaterThan);
    }

    @Override
    public void handleGreaterThanEquals(GreaterThanEquals greaterThanEquals) {
        Symbol siddhiGreaterThanEquals = new Symbol(greaterThanEquals.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiGreaterThanEquals);
    }

    @Override
    public void handleMinorThan(MinorThan minorThan) {
        Symbol siddhiMinorThan = new Symbol(minorThan.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiMinorThan);
    }

    @Override
    public void handleMinorThanEquals(MinorThanEquals minorThanEquals) {
        Symbol siddhiMinorThanEquals = new Symbol(minorThanEquals.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiMinorThanEquals);
    }

    @Override
    public void handleNotEqualsTo(NotEqualsTo notEqualsTo) {
        Symbol siddhiNotEqualsTo = new Symbol(notEqualsTo.getStringExpression());
        siddhiApp.addOnExpressionComposites(joinStreamStatementId, onExpressionId, siddhiNotEqualsTo);
    }

    @Override
    public void handleAlias(Alias alias) {
        // TODO : add feature to combine alias with the select item (eg - aggregateFunction has a alias)
    }

    @Override
    public void finalizeAddingThisComponentAsRequested() {

    }
}
