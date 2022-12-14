import Compiler.SiddhiAppGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import SiddhiAppComposites.SiddhiApp;
import net.sf.jsqlparser.JSQLParserException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SQLtoSiddhiQLCompilerTest {
    String siddhiAppDefinition = "";
    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementTest() throws JSQLParserException {
        String generalProjectionSQL = "SELECT a@int, b@int, c@float, d@string, e@bool FROM table WHERE a@int = 90 AND b@int > 98 OR (a@int > b@int XOR e@bool) XOR (a@int + b@int > b@int)";
        siddhiAppDefinition = "@app:name('SiddhiAppName-dev')\n" +
                "@source(type = 'live',sql.query = 'SELECT a, b, c, d, e FROM table WHERE a = 90 AND b > 98 OR (a > b XOR e) XOR (a + b > b)',@map(type = 'json',@attributes(a = 'a',b = 'b',c = 'c',d = 'd',e = 'e')))\n" +
                "define stream tableInputStream(a int,b int,c float,d string,e bool);\n@sink(type = 'log')\n" +
                "define stream tableOutputStream(a int,b int,c float,d string,e bool);\n@info(name = 'null')\n" +
                "from tableInputStream[a == 90 AND b > 98 OR ( a > b XOR e ) XOR ( a + b > b ) ]\nselect  a  , b  , c  , d  , e  \ninsert into tableOutputStream;\n";
        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp.getSiddhiAppStringRepresentation());
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithWhereClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT col1@int, col2@int, col3@int, col4@int, col5@int , col99@int " +
                "FROM table WHERE col1@int = 10 AND col2@int = 20 XOR col3@int + col4@int = 30 AND col5@int = 98;";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        siddhiAppDefinition = "@app:name('SiddhiAppName-dev')\n" +
                "@source(type = 'live',sql.query = 'SELECT col1, col2, col3, col4, col5 , col99 FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 + col4 = 30 AND col5 = 98;',@map(type = 'json',@attributes(col1 = 'col1',col2 = 'col2',col3 = 'col3',col4 = 'col4',col5 = 'col5',col99 = 'col99')))\n" +
                "define stream tableInputStream(col1 int,col2 int,col3 int,col4 int,col5 int,col99 int);\n" +
                "@sink(type = 'log')\n" +
                "define stream tableOutputStream(col1 int,col2 int,col3 int,col4 int,col5 int,col99 int);\n" +
                "@info(name = 'null')\n" +
                "from tableInputStream[col1 == 10 AND col2 == 20 XOR col3 + col4 == 30 AND col5 == 98 ]\n" +
                "select  col1  , col2  , col3  , col4  , col5  , col99  \n" +
                "insert into tableOutputStream;\n";
        assertEquals(siddhiAppDefinition,siddhiApp.getSiddhiAppStringRepresentation());
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAliasesTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT col1@int AS A, col2@int AS B, col3@int AS C , col4@int as D, " +
                "col5@long  as E, col99@string as F " +
                "FROM table";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        siddhiAppDefinition = "@app:name('SiddhiAppName-dev')\n" +
                "@source(type = 'live',sql.query = 'SELECT col1 AS A, col2 AS B, col3 AS C , col4 as D, col5  as E, col99 as F FROM table',@map(type = 'json',@attributes(A = 'A',B = 'B',C = 'C',D = 'D',E = 'E',F = 'F',col1 = 'col1',col2 = 'col2',col3 = 'col3',col4 = 'col4',col5 = 'col5',col99 = 'col99')))\n" +
                "define stream tableInputStream(col1 int,col2 int,col3 int,col4 int,col5 long,col99 string);\n" +
                "@sink(type = 'log')\n" +
                "define stream tableOutputStream(A int,B int,C int,D int,E long,F string);\n" +
                "@info(name = 'null')\n" +
                "from tableInputStream\n" +
                "select  col1 as A  , col2 as B  , col3 as C  , col4 as D  , col5 as E  , col99 as F  \n" +
                "insert into tableOutputStream;\n";
        assertEquals(siddhiAppDefinition,siddhiApp.getSiddhiAppStringRepresentation());
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithAliasesAndWhereClauseTest() throws JSQLParserException {

        String generalProjectionSQL =
                "SELECT " +
                        "a@int AS A, " +
                        "b@int AS B, " +
                        "c@float AS C, " +
                        "d@string AS D, " +
                        "e@bool AS E " +
                "FROM " +
                        "table" +
                " WHERE " +
                        "a@int = 90 AND b@int > 98 OR " +
                        "(a@int > b@int XOR e@bool) XOR " +
                        "(a@int + b@int > b@int) ";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        siddhiAppDefinition = "@app:name('SiddhiAppName-dev')\n" +
                "@source(type = 'live',sql.query = 'SELECT a AS A, b AS B, c AS C, d AS D, e AS E FROM table WHERE a = 90 AND b > 98 OR (a > b XOR e) XOR (a + b > b) ',@map(type = 'json',@attributes(b = 'b',B = 'B',d = 'd',D = 'D',a = 'a',A = 'A',c = 'c',C = 'C',e = 'e',E = 'E')))\n" +
                "define stream tableInputStream(a int,b int,c float,d string,e bool);\n" +
                "@sink(type = 'log')\n" +
                "define stream tableOutputStream(A int,B int,C float,D string,E bool);\n" +
                "@info(name = 'null')\n" +
                "from tableInputStream[a == 90 AND b > 98 OR ( a > b XOR e ) XOR ( a + b > b ) ]\n" +
                "select  a as A  , b as B  , c as C  , d as D  , e as E  \n" +
                "insert into tableOutputStream;\n";
        assertEquals(siddhiAppDefinition,siddhiApp.getSiddhiAppStringRepresentation());
    }

    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithMultipleAggregateFunctionsTest() throws JSQLParserException {

        String generalProjectionSQL =
                "SELECT " +
                        "SUM(col1@int) AS sum, " +
                        "STDDEV(col2@int) AS stddev, " +
                        "MAX(col3@int) AS max, " +
                        "MIN(col4@int) AS min ," +
                        "COUNT(col5@int) AS count , " +
                        "col99@string " +
                "FROM " +
                        "table";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        siddhiAppDefinition = "@app:name('SiddhiAppName-dev')\n" +
                "@source(type = 'live',sql.query = 'SELECT SUM(col1) AS sum, STDDEV(col2) AS stddev, MAX(col3) AS max, MIN(col4) AS min ,COUNT(col5) AS count , col99 FROM table',@map(type = 'json',@attributes(col1 = 'col1',col2 = 'col2',col3 = 'col3',col4 = 'col4',col5 = 'col5',col99 = 'col99',max = 'max',min = 'min',sum = 'sum',count = 'count',stddev = 'stddev')))\n" +
                "define stream tableInputStream(col1 int,col2 int,col3 int,col4 int,col5 int,col99 string);\n" +
                "@sink(type = 'log')\n" +
                "define stream tableOutputStream(sum double,stddev double,max double,min double,count long,col99 string);\n" +
                "@info(name = 'null')\n" +
                "from tableInputStream\n" +
                "select SUM( col1 )  as sum ,STDDEV( col2 )  as stddev ,MAX( col3 )  as max ,MIN( col4 )  as min ,COUNT( col5 )  as count , col99  \n" +
                "insert into tableOutputStream;\n";
        assertEquals(siddhiAppDefinition,siddhiApp.getSiddhiAppStringRepresentation());
    }

    @Disabled
    @Test
    void generateSiddhiAppForSimpleSQLSelectStatementWithMultipleAggregateFunctionsWithAliasesTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT SUM(col1) " +
                "as SUM, STDDEV(col2) as STDDEV, MAX(col3) as MAX, MIN(col4) " +
                "as MIN, COUNT(col5) as COUNT, col99 " +
                "FROM table";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    @Disabled
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM table JOIN table90 ON table.id = table90.id";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    @Disabled
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM table WHERE (colA = 90 AND colB = 98 OR colS = 78) XOR colV = 980;";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    @Disabled
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndOrderByClauseTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM Customers\n" +
                "ORDER BY Country, CustomerName;";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    @Disabled
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndOrderByClauseWithASCAndDescTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT * FROM Customers " +
                "ORDER BY Country ASC, CustomerName DESC;";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    @Disabled
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndOrderByClauseAndGroupByAndHavingClauseWithASCAndDescTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT COUNT(CustomerID@int - l@int), Country@string\n" +
                "FROM Customers@string\n" +
                "GROUP BY Country@string\n" +
                "HAVING COUNT(CustomerID@string) > 5\n" +
                "ORDER BY COUNT(CustomerID@string) DESC;";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

    @Test
    @Disabled
    void generateSiddhiAppForSimpleSQLSelectStatementWithAllColumnsAndWHereClauseAndGroupByClauseWithASCAndDescTest() throws JSQLParserException {

        String generalProjectionSQL = "SELECT COUNT(CustomerID), Country FROM Customers GROUP BY Country ORDER BY COUNT(CustomerID) DESC ";

        SiddhiApp siddhiApp = SiddhiAppGenerator.generateSiddhiApp(generalProjectionSQL);
        assertEquals(siddhiAppDefinition,siddhiApp);
    }

}