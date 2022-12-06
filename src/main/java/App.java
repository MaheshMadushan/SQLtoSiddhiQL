import Engine.IEngine;
import Engine.MiddleEngine;
import SiddhiApp.SiddhiApp;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.*;
import Compiler.*;

public class App {
    public static void main(String[] args) throws JSQLParserException {
//        Statement statement = CCJSqlParserUtil.parse("SELECT DISTINCT ON (colg AS a,coll AS b) abcd, SUM(col1,clo3,a) AS a, COUNT(table.col2) AS b, col3 AS c , table.col4 as d, col5 , col99 " +
//                " FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 99");
        Statement statement = CCJSqlParserUtil.parse("SELECT  abcd@string, SUM(col1@int) AS a, COUNT(table.col2@int) AS b, col3@int AS c , table.col4@int as d, col5@int , col99@int " +
                " FROM table WHERE (col1@int = 10 AND col2@int > 20) XOR col3@int = 30 AND col5@int = 99");

        SiddhiApp siddhiApp = new SiddhiApp();

        IEngine middleEngine  = new MiddleEngine().setSiddhiApp(siddhiApp);

        statement.accept(new CustomSelectStatementVisitor(middleEngine));

        System.out.println(siddhiApp.getSiddhiAppStringRepresentation());

    }

}