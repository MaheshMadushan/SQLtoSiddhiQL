import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.*;

import Compiler.*;


public class App {

    public static void main(String[] args) throws JSQLParserException {

        Statement statement = CCJSqlParserUtil.parse("SELECT DISTINCT ON (colg AS a,coll AS b) abcd, SUM(col1,clo3,a) AS a, COUNT(table.col2) AS b, col3 AS c , table.col4 as d, col5 , col99 " +
                " FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 99");

        statement.accept(new CustomSelectStatementVisitor());

    }

}