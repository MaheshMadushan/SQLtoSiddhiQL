import Engine.IEngine;
import Engine.MiddleEngine;
import SiddhiApp.SiddhiApp;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.*;

import Compiler.*;


public class App {

    public static void main(String[] args) throws JSQLParserException {
//        Select stmt = (Select) CCJSqlParserUtil.parse("SELECT DISTINCT ON (colg) s, SUM(col1,clo3,a) AS a, COUNT(col2) AS b, col3 AS c , col4 as d, col5 , col99 " +
//                " FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 99");
//
//        System.out.println((PlainSelect) stmt.getSelectBody()); // ((Select) stmt).getSelectBody().getClass() - > SETOPLIST
//        AST.traverseAST(AST.parseAST("SELECT DISTINCT ON (colg) s, SUM(col1,clo3,a) AS a, COUNT(col2) AS b, col3 AS c , col4 as d, col5 , col99 FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 99"),0);
//
//        /**
//         * selectListItemMapWithAliases {c=col3, d=col4}
//         * selectListItemListWithOutAliases [col5, col99]
//         * selectListItemWithFunctionsMap {SUM=[[col1, clo3, a]], COUNT=[[col2]]}
//         */
//
//        Map<String, String> selectListItemMapWithAliases = new HashMap<>(); // [ alias -> column_name,  alias -> column_name,  alias -> column_name,....]
//        List<String> selectListItemListWithOutAliases = new ArrayList<>(); // [ column_name, column_name,column_name, ...]
//        Map<String, List<List<Expression>>> selectListItemWithFunctionsMap = new HashMap<>(); //{SUM=[[col1, clo3, a]], COUNT=[[col2]]}
//
//        for (SelectItem selectItem : ((PlainSelect)stmt.getSelectBody()).getDistinct().getOnSelectItems()) {
//            selectItem.accept(new SelectItemVisitorAdapter() {
//                @Override
//                public void visit(SelectExpressionItem item) {
//                    if(item.getExpression() instanceof Column) {
//                        // do have AS
//                        if(item.getAlias() == null){
//                            selectListItemListWithOutAliases.add(item.getExpression().toString());/* column names a,b,c */
//                        }else /* no aliases (no AS) */ {
//                            selectListItemMapWithAliases.put(item.getAlias().getName(), ((Column) item.getExpression()).getName(false));
//                        }
//                    }
//                    else if(item.getExpression() instanceof Function){
//                        Function function = (Function) item.getExpression();String function_name = function.getName();
//                        if(selectListItemWithFunctionsMap.containsKey(function_name)){
//                            selectListItemWithFunctionsMap.get(function_name).add(function.getParameters().getExpressions());
//                        }else{
//                            List<List<Expression>> func_param_expressions = new ArrayList<>();
//                            func_param_expressions.add(function.getParameters().getExpressions());
//                            selectListItemWithFunctionsMap.put(function.getName(),func_param_expressions);
//                        }
//
//                    }
//                }
//            });
//        }
//
//        Expression whereCluase = ((PlainSelect) stmt.getSelectBody()).getWhere();
//        String whereClauseConditions = "";
//        if(whereCluase != null) {
//            whereClauseConditions = "[" + whereCluase.toString() + "]";
//        }
////        if(((PlainSelect) stmt.getSelectBody()).getWhere() instanceof BinaryExpression) {
////            parseBinaryExpression((BinaryExpression) ((PlainSelect) stmt.getSelectBody()).getWhere());
////        }
//
//        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//        List<String> tableList = tablesNamesFinder.getTableList(stmt);
//
//        System.out.println("===========================================================================");
//
//        // backend
//        System.out.println("selectListItemMapWithAliases -> " + selectListItemMapWithAliases); // select_list_items
//        System.out.println("selectListItemListWithOutAliases -> " + selectListItemListWithOutAliases); // select_list_items
//        System.out.println("selectListItemWithFunctionsMap -> " + selectListItemWithFunctionsMap);
//        System.out.println("tableList -> " + tableList);
//        System.out.println("whereClauseConditions string -> " + whereClauseConditions);
//
//        System.out.println("==========================================================================");
//
//        StringBuilder selectList = new StringBuilder("");
//        StringBuilder attributesForStreamDefinition = new StringBuilder("");
//        StringBuilder attributesForJSONAttributeDefinition = new StringBuilder("");
//        for(Map.Entry<String, String> alias_colName : selectListItemMapWithAliases.entrySet()){
//            attributesForJSONAttributeDefinition.append(alias_colName.getValue()).append(" = '").append(alias_colName.getValue()).append("',  ");
//            attributesForStreamDefinition.append(alias_colName.getValue()).append(" String,  ");
//            selectList.append(alias_colName.getValue());
//            selectList.append(" as ");
//            selectList.append(alias_colName.getKey()).append(",  ");
//
//        }
//
//        for (String selectListItemWithoutAlias :
//                selectListItemListWithOutAliases) {
//            attributesForJSONAttributeDefinition.append(selectListItemWithoutAlias).append(" = '").append(selectListItemWithoutAlias).append("',  ");
//            attributesForStreamDefinition.append(selectListItemWithoutAlias).append(" String,  ");
//            selectList.append(" ").append(selectListItemWithoutAlias).append(",  ");
//        }
//        selectList.deleteCharAt(selectList.length() - 3 );
//        attributesForStreamDefinition.deleteCharAt(attributesForStreamDefinition.length() - 3);
//        attributesForJSONAttributeDefinition.deleteCharAt(attributesForJSONAttributeDefinition.length() - 3);
//        System.out.println("select list as a string : " + selectList);
//        System.out.println("attribute list for stream defn : " + attributesForStreamDefinition);
//        System.out.println("attribute list for JSON defn : " + attributesForJSONAttributeDefinition);
//
//        String siddhiAppDefinition = String.format(
//                "@App:name('TestSiddhiApp0')\n" +
//                "@source(" +
//                        "\n\ttype='live', " +
//                        "\n\tsql.query='%s', " +
//                        "\n\thost.name='api-varden-4f0f3c4f.paas.macrometa.io', " +
//                        "\n\tapi.key = 'madu140_gmail.com.AccessPortal.2PL8EeyIAMn2sx7YHKWMM58tmJLES4NyIWq6Cnsj0BTMjygJyF3b14zb2sidcauXccccb8', " +
//                        "\n\t@map(" +
//                            "\n\t\ttype='json', " +
//                            "\n\t\tenclosing.element='$.properties',  " +
//                            "\n\t\t@attributes(%s)" +
//                        "\n\t\t)" +
//                    "\n\t)" +
//                "\ndefine stream inputStream (%s);\n" +
//                "@sink(type = 'log')\n" +
//                "define stream OutputStream (%s);\n" +
//                "@info(name = 'query0')\n" +
//                "from inputStream %s\n" +
//                "select %s\n" +
//                "insert into outputStream;"
//        ,stmt,attributesForJSONAttributeDefinition,attributesForStreamDefinition,attributesForStreamDefinition,whereClauseConditions,selectList);
//
//        ((PlainSelect)stmt.getSelectBody()).getSelectItems();
//
//        System.out.println("==============================================================================");
//
//        System.out.println(siddhiAppDefinition);


        //================================================================================================

        Statement statement = CCJSqlParserUtil.parse("SELECT DISTINCT ON (colg AS a,coll AS b) abcd, SUM(col1,clo3,a) AS a, COUNT(table.col2) AS b, col3 AS c , table.col4 as d, col5 , col99 " +
                " FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 99");

//        statement.accept(new StatementVisitor() {
//            @Override
//            public void visit(SavepointStatement savepointStatement) {
//
//            }
//
//            @Override
//            public void visit(RollbackStatement rollbackStatement) {
//
//            }
//
//            @Override
//            public void visit(Comment comment) {
//
//            }
//
//            @Override
//            public void visit(Commit commit) {
//
//            }
//
//            @Override
//            public void visit(Delete delete) {
//
//            }
//
//            @Override
//            public void visit(Update update) {
//
//            }
//
//            @Override
//            public void visit(Insert insert) {
//
//            }
//
//            @Override
//            public void visit(Replace replace) {
//
//            }
//
//            @Override
//            public void visit(Drop drop) {
//
//            }
//
//            @Override
//            public void visit(Truncate truncate) {
//
//            }
//
//            @Override
//            public void visit(CreateIndex createIndex) {
//
//            }
//
//            @Override
//            public void visit(CreateSchema createSchema) {
//
//            }
//
//            @Override
//            public void visit(CreateTable createTable) {
//
//            }
//
//            @Override
//            public void visit(CreateView createView) {
//
//            }
//
//            @Override
//            public void visit(AlterView alterView) {
//
//            }
//
//            @Override
//            public void visit(Alter alter) {
//
//            }
//
//            @Override
//            public void visit(Statements statements) {
//
//            }
//
//            @Override
//            public void visit(Execute execute) {
//
//            }
//
//            @Override
//            public void visit(SetStatement setStatement) {
//
//            }
//
//            @Override
//            public void visit(ResetStatement resetStatement) {
//
//            }
//
//            @Override
//            public void visit(ShowColumnsStatement showColumnsStatement) {
//
//            }
//
//            @Override
//            public void visit(ShowTablesStatement showTablesStatement) {
//
//            }
//
//            @Override
//            public void visit(Merge merge) {
//
//            }
//
//            @Override
//            public void visit(Select select) {
//                // is this a plain select
//                //  what to do with withItems
//                System.out.println("this is a select");
//                SelectBody sdb = select.getSelectBody();
//                sdb.accept(new SelectVisitor() {
//                    @Override
//                    public void visit(PlainSelect plainSelect) {
//                        System.out.println("this is a plainSelect");
//
//                        Distinct distinct = plainSelect.getDistinct();
//
//                        FromItem fromItem = plainSelect.getFromItem();
//
//                        fromItem.accept(new FromItemVisitor() {
//                            @Override
//                            public void visit(Table table) {
//
//                            }
//
//                            @Override
//                            public void visit(SubSelect subSelect) {
//
//                            }
//
//                            @Override
//                            public void visit(SubJoin subJoin) {
//
//                            }
//
//                            @Override
//                            public void visit(LateralSubSelect lateralSubSelect) {
//
//                            }
//
//                            @Override
//                            public void visit(ValuesList valuesList) {
//
//                            }
//
//                            @Override
//                            public void visit(TableFunction tableFunction) {
//
//                            }
//
//                            @Override
//                            public void visit(ParenthesisFromItem parenthesisFromItem) {
//
//                            }
//                        });
//
//                        Expression where = plainSelect.getWhere();
//                        where.accept(new ExpressionVisitor() {
//                            @Override
//                            public void visit(BitwiseRightShift bitwiseRightShift) {
//
//                            }
//
//                            @Override
//                            public void visit(BitwiseLeftShift bitwiseLeftShift) {
//
//                            }
//
//                            @Override
//                            public void visit(NullValue nullValue) {
//
//                            }
//
//                            @Override
//                            public void visit(Function function) {
//
//                            }
//
//                            @Override
//                            public void visit(SignedExpression signedExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(JdbcParameter jdbcParameter) {
//
//                            }
//
//                            @Override
//                            public void visit(JdbcNamedParameter jdbcNamedParameter) {
//
//                            }
//
//                            @Override
//                            public void visit(DoubleValue doubleValue) {
//
//                            }
//
//                            @Override
//                            public void visit(LongValue longValue) {
//
//                            }
//
//                            @Override
//                            public void visit(HexValue hexValue) {
//
//                            }
//
//                            @Override
//                            public void visit(DateValue dateValue) {
//
//                            }
//
//                            @Override
//                            public void visit(TimeValue timeValue) {
//
//                            }
//
//                            @Override
//                            public void visit(TimestampValue timestampValue) {
//
//                            }
//
//                            @Override
//                            public void visit(Parenthesis parenthesis) {
//
//                            }
//
//                            @Override
//                            public void visit(StringValue stringValue) {
//
//                            }
//
//                            @Override
//                            public void visit(Addition addition) {
//
//                            }
//
//                            @Override
//                            public void visit(Division division) {
//
//                            }
//
//                            @Override
//                            public void visit(IntegerDivision integerDivision) {
//
//                            }
//
//                            @Override
//                            public void visit(Multiplication multiplication) {
//
//                            }
//
//                            @Override
//                            public void visit(Subtraction subtraction) {
//
//                            }
//
//                            @Override
//                            public void visit(AndExpression andExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(OrExpression orExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(XorExpression xorExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(Between between) {
//
//                            }
//
//                            @Override
//                            public void visit(EqualsTo equalsTo) {
//
//                            }
//
//                            @Override
//                            public void visit(GreaterThan greaterThan) {
//
//                            }
//
//                            @Override
//                            public void visit(GreaterThanEquals greaterThanEquals) {
//
//                            }
//
//                            @Override
//                            public void visit(InExpression inExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(FullTextSearch fullTextSearch) {
//
//                            }
//
//                            @Override
//                            public void visit(IsNullExpression isNullExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(IsBooleanExpression isBooleanExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(LikeExpression likeExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(MinorThan minorThan) {
//
//                            }
//
//                            @Override
//                            public void visit(MinorThanEquals minorThanEquals) {
//
//                            }
//
//                            @Override
//                            public void visit(NotEqualsTo notEqualsTo) {
//
//                            }
//
//                            @Override
//                            public void visit(Column column) {
//
//                            }
//
//                            @Override
//                            public void visit(SubSelect subSelect) {
//
//                            }
//
//                            @Override
//                            public void visit(CaseExpression caseExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(WhenClause whenClause) {
//
//                            }
//
//                            @Override
//                            public void visit(ExistsExpression existsExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(AnyComparisonExpression anyComparisonExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(Concat concat) {
//
//                            }
//
//                            @Override
//                            public void visit(Matches matches) {
//
//                            }
//
//                            @Override
//                            public void visit(BitwiseAnd bitwiseAnd) {
//
//                            }
//
//                            @Override
//                            public void visit(BitwiseOr bitwiseOr) {
//
//                            }
//
//                            @Override
//                            public void visit(BitwiseXor bitwiseXor) {
//
//                            }
//
//                            @Override
//                            public void visit(CastExpression castExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(TryCastExpression tryCastExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(Modulo modulo) {
//
//                            }
//
//                            @Override
//                            public void visit(AnalyticExpression analyticExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(ExtractExpression extractExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(IntervalExpression intervalExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(OracleHierarchicalExpression oracleHierarchicalExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(RegExpMatchOperator regExpMatchOperator) {
//
//                            }
//
//                            @Override
//                            public void visit(JsonExpression jsonExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(JsonOperator jsonOperator) {
//
//                            }
//
//                            @Override
//                            public void visit(RegExpMySQLOperator regExpMySQLOperator) {
//
//                            }
//
//                            @Override
//                            public void visit(UserVariable userVariable) {
//
//                            }
//
//                            @Override
//                            public void visit(NumericBind numericBind) {
//
//                            }
//
//                            @Override
//                            public void visit(KeepExpression keepExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(MySQLGroupConcat mySQLGroupConcat) {
//
//                            }
//
//                            @Override
//                            public void visit(ValueListExpression valueListExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(RowConstructor rowConstructor) {
//
//                            }
//
//                            @Override
//                            public void visit(RowGetExpression rowGetExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(OracleHint oracleHint) {
//
//                            }
//
//                            @Override
//                            public void visit(TimeKeyExpression timeKeyExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(DateTimeLiteralExpression dateTimeLiteralExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(NotExpression notExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(NextValExpression nextValExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(CollateExpression collateExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(SimilarToExpression similarToExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(ArrayExpression arrayExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(ArrayConstructor arrayConstructor) {
//
//                            }
//
//                            @Override
//                            public void visit(VariableAssignment variableAssignment) {
//
//                            }
//
//                            @Override
//                            public void visit(XMLSerializeExpr xmlSerializeExpr) {
//
//                            }
//
//                            @Override
//                            public void visit(TimezoneExpression timezoneExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(JsonAggregateFunction jsonAggregateFunction) {
//
//                            }
//
//                            @Override
//                            public void visit(JsonFunction jsonFunction) {
//
//                            }
//
//                            @Override
//                            public void visit(ConnectByRootOperator connectByRootOperator) {
//
//                            }
//
//                            @Override
//                            public void visit(OracleNamedFunctionParameter oracleNamedFunctionParameter) {
//
//                            }
//
//                            @Override
//                            public void visit(AllColumns allColumns) {
//
//                            }
//
//                            @Override
//                            public void visit(AllTableColumns allTableColumns) {
//
//                            }
//
//                            @Override
//                            public void visit(AllValue allValue) {
//
//                            }
//
//                            @Override
//                            public void visit(IsDistinctExpression isDistinctExpression) {
//
//                            }
//
//                            @Override
//                            public void visit(GeometryDistance geometryDistance) {
//
//                            }
//                        });
//
//                        for (SelectItem selectItem : plainSelect.getSelectItems()) {
//                            selectItem.accept(new SelectItemVisitorAdapter() {
//                                @Override
//                                public void visit(SelectExpressionItem selectExpressionItem) {
//                                    selectExpressionItem.accept(new SelectItemVisitor() {
//                                        @Override
//                                        public void visit(AllColumns allColumns) {
//
//                                        }
//
//                                        @Override
//                                        public void visit(AllTableColumns allTableColumns) {
//
//                                        }
//
//                                        @Override
//                                        public void visit(SelectExpressionItem selectExpressionItem) {
//
//                                        }
//                                    });
////                                    if(selectExpressionItem.getExpression() instanceof Column) {
////                                        // do have AS
////                                        if(selectExpressionItem.getAlias() == null){
////                                            System.out.println(selectExpressionItem.getExpression().toString());
////                                            selectListItemListWithOutAliases.add(selectExpressionItem.getExpression().toString());/* column names a,b,c */
////                                        }else /* no aliases (no AS) */ {
////                                            System.out.println(selectExpressionItem.getAlias().getName() + " " + ((Column) selectExpressionItem.getExpression()).getName(false));
////                                            selectListItemMapWithAliases.put(selectExpressionItem.getAlias().getName(), ((Column) selectExpressionItem.getExpression()).getName(false));
////                                        }
////                                    }
////                                    else if(selectExpressionItem.getExpression() instanceof Function){
////                                        Function function = (Function) selectExpressionItem.getExpression();
////                                        String function_name = function.getName();
////                                        if(selectListItemWithFunctionsMap.containsKey(function_name)){
////                                            System.out.println(function_name + " " + function.getParameters().getExpressions());
////                                            selectListItemWithFunctionsMap.get(function_name).add(function.getParameters().getExpressions());
////                                            List<Expression> expressions = function.getParameters().getExpressions();
////                                            for(Expression expression : expressions){
////                                                expression.accept(new ExpressionVisitor() {
////                                                    @Override
////                                                    public void visit(BitwiseRightShift bitwiseRightShift) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(BitwiseLeftShift bitwiseLeftShift) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(NullValue nullValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Function function) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(SignedExpression signedExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(JdbcParameter jdbcParameter) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(JdbcNamedParameter jdbcNamedParameter) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(DoubleValue doubleValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(LongValue longValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(HexValue hexValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(DateValue dateValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(TimeValue timeValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(TimestampValue timestampValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Parenthesis parenthesis) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(StringValue stringValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Addition addition) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Division division) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(IntegerDivision integerDivision) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Multiplication multiplication) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Subtraction subtraction) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(AndExpression andExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(OrExpression orExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(XorExpression xorExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Between between) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(EqualsTo equalsTo) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(GreaterThan greaterThan) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(GreaterThanEquals greaterThanEquals) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(InExpression inExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(FullTextSearch fullTextSearch) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(IsNullExpression isNullExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(IsBooleanExpression isBooleanExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(LikeExpression likeExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(MinorThan minorThan) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(MinorThanEquals minorThanEquals) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(NotEqualsTo notEqualsTo) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Column column) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(SubSelect subSelect) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(CaseExpression caseExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(WhenClause whenClause) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(ExistsExpression existsExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(AnyComparisonExpression anyComparisonExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Concat concat) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Matches matches) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(BitwiseAnd bitwiseAnd) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(BitwiseOr bitwiseOr) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(BitwiseXor bitwiseXor) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(CastExpression castExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(TryCastExpression tryCastExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(Modulo modulo) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(AnalyticExpression analyticExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(ExtractExpression extractExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(IntervalExpression intervalExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(OracleHierarchicalExpression oracleHierarchicalExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(RegExpMatchOperator regExpMatchOperator) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(JsonExpression jsonExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(JsonOperator jsonOperator) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(RegExpMySQLOperator regExpMySQLOperator) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(UserVariable userVariable) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(NumericBind numericBind) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(KeepExpression keepExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(MySQLGroupConcat mySQLGroupConcat) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(ValueListExpression valueListExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(RowConstructor rowConstructor) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(RowGetExpression rowGetExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(OracleHint oracleHint) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(TimeKeyExpression timeKeyExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(DateTimeLiteralExpression dateTimeLiteralExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(NotExpression notExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(NextValExpression nextValExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(CollateExpression collateExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(SimilarToExpression similarToExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(ArrayExpression arrayExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(ArrayConstructor arrayConstructor) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(VariableAssignment variableAssignment) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(XMLSerializeExpr xmlSerializeExpr) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(TimezoneExpression timezoneExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(JsonAggregateFunction jsonAggregateFunction) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(JsonFunction jsonFunction) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(ConnectByRootOperator connectByRootOperator) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(OracleNamedFunctionParameter oracleNamedFunctionParameter) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(AllColumns allColumns) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(AllTableColumns allTableColumns) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(AllValue allValue) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(IsDistinctExpression isDistinctExpression) {
////
////                                                    }
////
////                                                    @Override
////                                                    public void visit(GeometryDistance geometryDistance) {
////
////                                                    }
////                                                });
////                                            }
////                                        }else{
////                                            System.out.println(function_name + " " + function.getParameters().getExpressions());
////                                            List<List<Expression>> func_param_expressions = new ArrayList<>();
////                                            func_param_expressions.add(function.getParameters().getExpressions());
////                                            selectListItemWithFunctionsMap.put(function.getName(),func_param_expressions);
////                                        }
////
////                                    }
//                                }
//                            });
//                        }
//
//                        for ( Table table : plainSelect.getIntoTables() ){
//                            System.out.println(table.getAlias() + " " + table.getName()  + " " +  table.getDatabase());
//                        }
//
//                        List<Join> joins = plainSelect.getJoins();
//                        for(Join join : joins){
//                            System.out.println(join.toString());
//                        }
//
//                        GroupByElement groupByElement = plainSelect.getGroupBy();
//                        System.out.println(groupByElement);
//
//                        List<OrderByElement> orderByElements = plainSelect.getOrderByElements();
//                        for(OrderByElement orderByElement : orderByElements){
//                            System.out.println(orderByElement.getExpression().toString());
//                        }
//
//                    }
//
//                    @Override
//                    public void visit(SetOperationList setOperationList) {
//
//                    }
//
//                    @Override
//                    public void visit(WithItem withItem) {
//
//                    }
//
//                    @Override
//                    public void visit(ValuesStatement valuesStatement) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void visit(Upsert upsert) {
//
//            }
//
//            @Override
//            public void visit(UseStatement useStatement) {
//
//            }
//
//            @Override
//            public void visit(Block block) {
//
//            }
//
//            @Override
//            public void visit(ValuesStatement valuesStatement) {
//
//            }
//
//            @Override
//            public void visit(DescribeStatement describeStatement) {
//
//            }
//
//            @Override
//            public void visit(ExplainStatement explainStatement) {
//
//            }
//
//            @Override
//            public void visit(ShowStatement showStatement) {
//
//            }
//
//            @Override
//            public void visit(DeclareStatement declareStatement) {
//
//            }
//
//            @Override
//            public void visit(Grant grant) {
//
//            }
//
//            @Override
//            public void visit(CreateSequence createSequence) {
//
//            }
//
//            @Override
//            public void visit(AlterSequence alterSequence) {
//
//            }
//
//            @Override
//            public void visit(CreateFunctionalStatement createFunctionalStatement) {
//
//            }
//
//            @Override
//            public void visit(CreateSynonym createSynonym) {
//
//            }
//
//            @Override
//            public void visit(AlterSession alterSession) {
//
//            }
//
//            @Override
//            public void visit(IfElseStatement ifElseStatement) {
//
//            }
//
//            @Override
//            public void visit(RenameTableStatement renameTableStatement) {
//
//            }
//
//            @Override
//            public void visit(PurgeStatement purgeStatement) {
//
//            }
//
//            @Override
//            public void visit(AlterSystemStatement alterSystemStatement) {
//
//            }
//        });
        SiddhiApp siddhiApp = new SiddhiApp();

        IEngine middleEngine  = new MiddleEngine().setSiddhiApp(siddhiApp);

        statement.accept(new CustomSelectStatementVisitor(middleEngine));

    }

}