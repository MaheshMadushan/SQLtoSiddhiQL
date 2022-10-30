import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void parseBinaryExpression(BinaryExpression binaryExpression){
        if(binaryExpression instanceof OrExpression){
            System.out.println(" in OrExpression ");
            System.out.println( "   " +binaryExpression.getLeftExpression());
            System.out.println( "   " +binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getLeftExpression());
        }else if(binaryExpression instanceof AndExpression){
            System.out.println(" in AndExpression ");
            System.out.println( "   " +binaryExpression.getLeftExpression());
            System.out.println( "   " +binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getLeftExpression());
        }else if(binaryExpression instanceof XorExpression){
            System.out.println(" in XorExpression ");
            System.out.println( "   " +binaryExpression.getLeftExpression());
            System.out.println( "   " +binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getLeftExpression());
        }
        else if(binaryExpression instanceof GreaterThan){
            System.out.println(" in GreaterThan ");
            System.out.println( "   " +binaryExpression.getLeftExpression().getClass());
            System.out.println( "   " + ((AnyComparisonExpression) binaryExpression.getRightExpression()).getSubSelect());
            parseBinaryExpression((BinaryExpression) binaryExpression.getRightExpression());
            parseBinaryExpression((BinaryExpression) binaryExpression.getLeftExpression());
        }
    }

    public static void main(String[] args) throws JSQLParserException {
        Select stmt = (Select) CCJSqlParserUtil.parse("SELECT SUM(col1,clo3,a) AS a, COUNT(col2) AS b, col3 AS c , col4 as d, col5 , col99 " +
                "FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 > ALL ( SELECT avg(col5) FROM table)");
        /**
         * selectListItemMapWithAliases {c=col3, d=col4}
         * selectListItemListWithOutAliases [col5, col99]
         * selectListItemWithFunctionsMap {SUM=[[col1, clo3, a]], COUNT=[[col2]]}
         */

        Map<String, String> selectListItemMapWithAliases = new HashMap<>(); // [ alias -> column_name,  alias -> column_name,  alias -> column_name,....]
        List<String> selectListItemListWithOutAliases = new ArrayList<>(); // [ column_name, column_name,column_name, ...]
        Map<String, List<List<Expression>>> selectListItemWithFunctionsMap = new HashMap<>(); //{SUM=[[col1, clo3, a]], COUNT=[[col2]]}

        for (SelectItem selectItem : ((PlainSelect)stmt.getSelectBody()).getSelectItems()) {
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {
                    if(item.getExpression() instanceof Column) {
                        // do have AS
                        if(item.getAlias() == null){
                            selectListItemListWithOutAliases.add(item.getExpression().toString());/* column names a,b,c */
                        }else /* no aliases (no AS) */ {
                            selectListItemMapWithAliases.put(item.getAlias().getName(), ((Column) item.getExpression()).getName(false));
                        }
                    }
                    else if(item.getExpression() instanceof Function){
                        Function function = (Function) item.getExpression();String function_name = function.getName();
                        if(selectListItemWithFunctionsMap.containsKey(function_name)){
                            selectListItemWithFunctionsMap.get(function_name).add(function.getParameters().getExpressions());
                        }else{
                            List<List<Expression>> func_param_expressions = new ArrayList<>();
                            func_param_expressions.add(function.getParameters().getExpressions());
                            selectListItemWithFunctionsMap.put(function.getName(),func_param_expressions);
                        }

                    }
                }
            });
        }

        Expression whereCluase = ((PlainSelect) stmt.getSelectBody()).getWhere();
        String whereClauseConditions = "";
        if(whereCluase != null) {
            whereClauseConditions = "[" + whereCluase.toString() + "]";
        }
        if(((PlainSelect) stmt.getSelectBody()).getWhere() instanceof BinaryExpression) {
            parseBinaryExpression((BinaryExpression) ((PlainSelect) stmt.getSelectBody()).getWhere());
        }

        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(stmt);

        System.out.println("===========================================================================");

        // backend
        System.out.println("selectListItemMapWithAliases -> " + selectListItemMapWithAliases); // select_list_items
        System.out.println("selectListItemListWithOutAliases -> " + selectListItemListWithOutAliases); // select_list_items
        System.out.println("selectListItemWithFunctionsMap -> " + selectListItemWithFunctionsMap);
        System.out.println("tableList -> " + tableList);
        System.out.println("whereClauseConditions string -> " + whereClauseConditions);

        System.out.println("==========================================================================");

        StringBuilder selectList = new StringBuilder("");
        StringBuilder attributesForStreamDefinition = new StringBuilder("");
        StringBuilder attributesForJSONAttributeDefinition = new StringBuilder("");
        for(Map.Entry<String, String> alias_colName : selectListItemMapWithAliases.entrySet()){
            attributesForJSONAttributeDefinition.append(alias_colName.getValue()).append(" = '").append(alias_colName.getValue()).append("',  ");
            attributesForStreamDefinition.append(alias_colName.getValue()).append(" String,  ");
            selectList.append(alias_colName.getValue());
            selectList.append(" as ");
            selectList.append(alias_colName.getKey()).append(",  ");

        }

        for (String selectListItemWithoutAlias :
                selectListItemListWithOutAliases) {
            attributesForJSONAttributeDefinition.append(selectListItemWithoutAlias).append(" = '").append(selectListItemWithoutAlias).append("',  ");
            attributesForStreamDefinition.append(selectListItemWithoutAlias).append(" String,  ");
            selectList.append(" ").append(selectListItemWithoutAlias).append(",  ");
        }
        selectList.deleteCharAt(selectList.length() - 3 );
        attributesForStreamDefinition.deleteCharAt(attributesForStreamDefinition.length() - 3);
        attributesForJSONAttributeDefinition.deleteCharAt(attributesForJSONAttributeDefinition.length() - 3);
        System.out.println("select list as a string : " + selectList);
        System.out.println("attribute list for stream defn : " + attributesForStreamDefinition);
        System.out.println("attribute list for JSON defn : " + attributesForJSONAttributeDefinition);

        String siddhiAppDefinition = String.format(
                "@App:name('TestSiddhiApp0')\n" +
                "@source(" +
                        "\n\ttype='live', " +
                        "\n\tsql.query='%s', " +
                        "\n\thost.name='api-varden-4f0f3c4f.paas.macrometa.io', " +
                        "\n\tapi.key = 'madu140_gmail.com.AccessPortal.2PL8EeyIAMn2sx7YHKWMM58tmJLES4NyIWq6Cnsj0BTMjygJyF3b14zb2sidcauXccccb8', " +
                        "\n\t@map(" +
                            "\n\t\ttype='json', " +
                            "\n\t\tenclosing.element='$.properties',  " +
                            "\n\t\t@attributes(%s)" +
                        "\n\t\t)" +
                    "\n\t)" +
                "\ndefine stream inputStream (%s);\n" +
                "@sink(type = 'log')\n" +
                "define stream OutputStream (%s);\n" +
                "@info(name = 'query0')\n" +
                "from inputStream %s\n" +
                "select %s\n" +
                "insert into outputStream;"
        ,stmt,attributesForJSONAttributeDefinition,attributesForStreamDefinition,attributesForStreamDefinition,whereClauseConditions,selectList);

        ((PlainSelect)stmt.getSelectBody()).getSelectItems();

        System.out.println("==============================================================================");

        System.out.println(siddhiAppDefinition);

    }
}