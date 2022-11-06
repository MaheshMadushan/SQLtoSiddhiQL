package Compiler;

import Engine.IEngine;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.SubSelect;

import java.util.List;

public class CustomExpressionVisitorAdaptor implements ExpressionVisitor {

    private final IEngine middleEngine;

    public CustomExpressionVisitorAdaptor(IEngine middleEngine) {

        this.middleEngine = middleEngine;

    }

    // siddhi not supports this
    @Override
    public void visit(BitwiseRightShift bitwiseRightShift) {

    }

    @Override
    public void visit(BitwiseLeftShift bitwiseLeftShift) {

    }

    @Override
    public void visit(NullValue nullValue) {

    }

    @Override
    public void visit(Function function) {
        middleEngine.handleFunction(function);

        ItemsListVisitor itemsListVisitor = new CustomItemListVisitor(middleEngine);

        String functionName = function.getName();

        NamedExpressionList namedExpressionList = function.getNamedParameters();
        if(namedExpressionList != null){
            namedExpressionList.accept(itemsListVisitor);
        }

        ExpressionList expressionList = function.getParameters();
        if(expressionList != null){
            expressionList.accept(itemsListVisitor);
        }

        List<OrderByElement> orderByElementList = function.getOrderByElements();
        if(orderByElementList != null){
            for(OrderByElement orderByElement : orderByElementList){
                orderByElement.accept(new CustomOrderByElementVisitor(middleEngine));
            }
        }

//        function.getKeep() - for Oracle KEEP keyword

        Expression attribute = function.getAttribute();
        if(attribute != null) {
            attribute.accept(this);
        }

    }

    @Override
    public void visit(SignedExpression signedExpression) {

        signedExpression.getSign();

        middleEngine.handleSignedExpression(signedExpression);

        Expression signExpression = signedExpression.getExpression();
        if(signExpression != null) {
            signExpression.accept(this);
        }
    }

    @Override
    public void visit(JdbcParameter jdbcParameter) {
    }

    @Override
    public void visit(JdbcNamedParameter jdbcNamedParameter) {
    }

    @Override
    public void visit(DoubleValue doubleValue) {

        middleEngine.handleDoubleValue(doubleValue);
    }

    @Override
    public void visit(LongValue longValue) {

        middleEngine.handleLongValue(longValue);
    }

    @Override
    public void visit(HexValue hexValue) {
    }

    @Override
    public void visit(DateValue dateValue) {

    }

    @Override
    public void visit(TimeValue timeValue) {

    }

    @Override
    public void visit(TimestampValue timestampValue) {

    }

    @Override
    public void visit(Parenthesis parenthesis) {

        middleEngine.handleParenthesis(parenthesis);
        Expression expression = parenthesis.getExpression();
        if(expression != null) {
            expression.accept(this);
        }
    }

    @Override
    public void visit(StringValue stringValue) {

        middleEngine.handleStringValue(stringValue);
    }

    @Override
    public void visit(Addition addition) {
        Expression rightExpression = addition.getRightExpression();
        Expression leftExpression = addition.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleAddition(addition);
        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(Division division) {
        Expression rightExpression = division.getRightExpression();
        Expression leftExpression = division.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleDivision(division);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(IntegerDivision integerDivision) {
        Expression rightExpression = integerDivision.getRightExpression();
        Expression leftExpression = integerDivision.getLeftExpression();


        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleIntegerDivision(integerDivision);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(Multiplication multiplication) {
        Expression rightExpression = multiplication.getRightExpression();
        Expression leftExpression = multiplication.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleMultiplication(multiplication);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(Subtraction subtraction) {
        Expression rightExpression = subtraction.getRightExpression();
        Expression leftExpression = subtraction.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }
        middleEngine.handleSubtraction(subtraction);


        if(rightExpression != null){
            rightExpression.accept(this);
        }
    }

    @Override
    public void visit(AndExpression andExpression) {
        Expression rightExpression = andExpression.getRightExpression();
        Expression leftExpression = andExpression.getLeftExpression();


        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleAndExpression(andExpression);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(OrExpression orExpression) {
        Expression rightExpression = orExpression.getRightExpression();
        Expression leftExpression = orExpression.getLeftExpression();


        if(leftExpression != null){
            leftExpression.accept(this);
        }
        middleEngine.handleOrExpression(orExpression);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(XorExpression xorExpression) {
        Expression rightExpression = xorExpression.getRightExpression();
        Expression leftExpression = xorExpression.getLeftExpression();


        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleXorExpression(xorExpression);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(Between between) {

    }

    @Override
    public void visit(EqualsTo equalsTo) {
        Expression rightExpression = equalsTo.getRightExpression();
        Expression leftExpression = equalsTo.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleEqualsTo(equalsTo);

        if(rightExpression != null){
            rightExpression.accept(this);
        }
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        Expression rightExpression = greaterThan.getRightExpression();
        Expression leftExpression = greaterThan.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleGreaterThan(greaterThan);

        if(rightExpression != null){
            rightExpression.accept(this);
        }
    }

    @Override
    public void visit(GreaterThanEquals greaterThanEquals) {
        Expression rightExpression = greaterThanEquals.getRightExpression();
        Expression leftExpression = greaterThanEquals.getLeftExpression();

        if(leftExpression != null){
            leftExpression.accept(this);
        }

        middleEngine.handleGreaterThanEquals(greaterThanEquals);

        if(rightExpression != null){
            rightExpression.accept(this);
        }

    }

    @Override
    public void visit(InExpression inExpression) {

    }

    @Override
    public void visit(FullTextSearch fullTextSearch) {

    }

    @Override
    public void visit(IsNullExpression isNullExpression) {

    }

    @Override
    public void visit(IsBooleanExpression isBooleanExpression) {

    }

    @Override
    public void visit(LikeExpression likeExpression) {

    }

    @Override
    public void visit(MinorThan minorThan) {


        middleEngine.handleMinorThan(minorThan);
    }

    @Override
    public void visit(MinorThanEquals minorThanEquals) {


        middleEngine.handleMinorThanEquals(minorThanEquals);
    }

    @Override
    public void visit(NotEqualsTo notEqualsTo) {


        middleEngine.handleNotEqualsTo(notEqualsTo);
    }

    @Override
    public void visit(Column column) {
        middleEngine.handleColumn(column);
        // this function will hit by Function visitor - if function has
        // a column as an attribute or expression of multiple columns
        // then we have to extract those columns to put in "define stream" statement
        // in siddhi app (only this?) - so only to put in stream statement attribute list
        // also we have to find data type of the column. for all normal select items we provide string
        // if user doesn't specify the data type. for the function we can't do that. bcz some functions
        // don't accept strings. we can put float if user doesn't specify the data type since we only support aggregates. but how to
        // extend this? so we have to know from which function this column has extracted. then we can assume
        // it is a type that function accepts. eg. sum(column) only accept numeric values.
        // middleEngine.addColumn(column.getColumnName());

        // if we provide siddhi app as argument to this class constructor?
        // how to determine this column goes to where?

    }

    @Override
    public void visit(SubSelect subSelect) {

    }

    @Override
    public void visit(CaseExpression caseExpression) {

    }

    @Override
    public void visit(WhenClause whenClause) {

    }

    @Override
    public void visit(ExistsExpression existsExpression) {

    }

    @Override
    public void visit(AnyComparisonExpression anyComparisonExpression) {

    }

    @Override
    public void visit(Concat concat) {

    }

    @Override
    public void visit(Matches matches) {

    }

    @Override
    public void visit(BitwiseAnd bitwiseAnd) {

    }

    @Override
    public void visit(BitwiseOr bitwiseOr) {

    }

    @Override
    public void visit(BitwiseXor bitwiseXor) {

    }

    @Override
    public void visit(CastExpression castExpression) {

    }

    @Override
    public void visit(TryCastExpression tryCastExpression) {

    }

    @Override
    public void visit(Modulo modulo) {

    }

    @Override
    public void visit(AnalyticExpression analyticExpression) {

    }

    @Override
    public void visit(ExtractExpression extractExpression) {

    }

    @Override
    public void visit(IntervalExpression intervalExpression) {

    }

    @Override
    public void visit(OracleHierarchicalExpression oracleHierarchicalExpression) {

    }

    @Override
    public void visit(RegExpMatchOperator regExpMatchOperator) {

    }

    @Override
    public void visit(JsonExpression jsonExpression) {

    }

    @Override
    public void visit(JsonOperator jsonOperator) {

    }

    @Override
    public void visit(RegExpMySQLOperator regExpMySQLOperator) {

    }

    @Override
    public void visit(UserVariable userVariable) {

    }

    @Override
    public void visit(NumericBind numericBind) {

    }

    @Override
    public void visit(KeepExpression keepExpression) {

    }

    @Override
    public void visit(MySQLGroupConcat mySQLGroupConcat) {

    }

    @Override
    public void visit(ValueListExpression valueListExpression) {

    }

    @Override
    public void visit(RowConstructor rowConstructor) {

    }

    @Override
    public void visit(RowGetExpression rowGetExpression) {

    }

    @Override
    public void visit(OracleHint oracleHint) {

    }

    @Override
    public void visit(TimeKeyExpression timeKeyExpression) {

    }

    @Override
    public void visit(DateTimeLiteralExpression dateTimeLiteralExpression) {

    }

    @Override
    public void visit(NotExpression notExpression) {

    }

    @Override
    public void visit(NextValExpression nextValExpression) {

    }

    @Override
    public void visit(CollateExpression collateExpression) {

    }

    @Override
    public void visit(SimilarToExpression similarToExpression) {

    }

    @Override
    public void visit(ArrayExpression arrayExpression) {

    }

    @Override
    public void visit(ArrayConstructor arrayConstructor) {

    }

    @Override
    public void visit(VariableAssignment variableAssignment) {

    }

    @Override
    public void visit(XMLSerializeExpr xmlSerializeExpr) {

    }

    @Override
    public void visit(TimezoneExpression timezoneExpression) {

    }

    @Override
    public void visit(JsonAggregateFunction jsonAggregateFunction) {

    }

    @Override
    public void visit(JsonFunction jsonFunction) {

    }

    @Override
    public void visit(ConnectByRootOperator connectByRootOperator) {

    }

    @Override
    public void visit(OracleNamedFunctionParameter oracleNamedFunctionParameter) {

    }

    @Override
    public void visit(AllColumns allColumns) {

    }

    @Override
    public void visit(AllTableColumns allTableColumns) {

    }

    @Override
    public void visit(AllValue allValue) {

    }

    @Override
    public void visit(IsDistinctExpression isDistinctExpression) {

    }

    @Override
    public void visit(GeometryDistance geometryDistance) {

    }
}
