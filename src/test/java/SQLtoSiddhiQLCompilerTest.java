import Compiler.SiddhiAppGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import net.sf.jsqlparser.JSQLParserException;
import org.junit.jupiter.api.Test;

public class SQLtoSiddhiQLCompilerTest {

    String siddhiAppDefinition = "";

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT col1 , col2 , col3  = col8, col4 , col5 , col99 = colb FROM table" +
                " WHERE col = 90 AND col > 98 OR " +
                "(col_98 > col_97 XOR col_90) XOR (col_90 > col_98) " +
                "UNION SELECT * FROM TABLE";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithWhereClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT col1, col2, col3, col4, col5 , col99 " +
                "FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 + col4 = 30 AND col5 = 98;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAliasesTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT col1 AS A, col2 AS B, col3 AS C , col4 as D, " +
                "col5  as E, col99 as F " +
                "FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAliasesAndWhereClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT " +
                "col1 AS A, col2 AS B, col3 AS C , " +
                "col4 as D, col5 as E, col99 as F " +
                "FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 98;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithMultipleAggregateFunctionsTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT SUM(col1) , STDDEV(col2), MAX(col3) , MIN(col4) ," +
                " COUNT(col5) , col99 " +
                "FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithMultipleAggregateFunctionsWithAliasesTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT SUM(col1) " +
                "as SUM, STDDEV(col2) as STDDEV, MAX(col3) as MAX, MIN(col4) " +
                "as MIN, COUNT(col5) as COUNT, col99 " +
                "FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM table JOIN table90 ON table.id = table90.id";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM table WHERE (colA = 90 AND colB = 98 OR colS = 78) XOR colV = 980;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndOrderByClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM Customers\n" +
                "ORDER BY Country, CustomerName;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndOrderByClauseWithASCAndDescTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM Customers " +
                "ORDER BY Country ASC, CustomerName DESC;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndOrderByClauseAndGroupByAndHavingClauseWithASCAndDescTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT COUNT(CustomerID - l), Country\n" +
                "FROM Customers\n" +
                "GROUP BY Country\n" +
                "HAVING COUNT(CustomerID) > 5\n" +
                "ORDER BY COUNT(CustomerID) DESC;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndGroupByClauseWithASCAndDescTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT COUNT(CustomerID), Country FROM Customers GROUP BY Country ORDER BY COUNT(CustomerID) DESC ";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void flowing() throws JSQLParserException {

        String generalProjectionSQL_with_setOps = "SELECT STDDEV(a + b) FROM table WHERE (colA = 90 AND colB = 98 OR colS = 78) XOR colV = 980 UNION SELECT * FROM table WHERE (colA = 90 AND colB = 98 OR colS = 78) XOR colV = 980;";
        String generalProjectionSQL_not_setOps = "SELECT a+b+l,l FROM table WHERE (colA = 90 AND colB = 98 OR colS = 78) XOR colV = 980;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL_not_setOps);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }
}