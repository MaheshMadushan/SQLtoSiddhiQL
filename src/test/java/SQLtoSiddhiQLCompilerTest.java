import Compiler.SiddhiAppGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SQLtoSiddhiQLCompilerTest {



    String siddhiAppDefinition = "";

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementTest(){

        String generalProjectionSQL = "SELECT col1 , col2 , col3 , col4 , col5 , col99 FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithWhereClauseTest(){

        String generalProjectionSQL = "SELECT col1, col2, col3, col4, col5 , col99 " +
                "FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 98;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAliasesTest(){

        String generalProjectionSQL = "SELECT col1 AS A, col2 AS B, col3 AS C , col4 as D, col5  as E, col99 as F" +
                "FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAliasesAndWhereClauseTest(){

        String generalProjectionSQL = "SELECT col1 AS A, col2 AS B, col3 AS C , col4 as D, col5 as E, col99 as F " +
                "FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 98;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithMultipleAggregateFunctionsTest(){

        String generalProjectionSQL = "SELECT SUM(col1) , STDDEV(col2), MAX(col3) , MIN(col4) , COUNT(col5) , col99 " +
                "FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithMultipleAggregateFunctionsWithAliasesTest(){

        String generalProjectionSQL = "SELECT SUM(col1) as SUM, STDDEV(col2) as STDDEV, MAX(col3) as MAX, MIN(col4) as MIN, COUNT(col5) as COUNT, col99 " +
                "FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsTest(){

        String generalProjectionSQL = "SELECT * FROM table";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseTest(){

        String generalProjectionSQL = "SELECT * FROM table WHERE (colA = 90 AND colB = 98 OR colS = 78) XOR colV = 980;";

        String siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

}
