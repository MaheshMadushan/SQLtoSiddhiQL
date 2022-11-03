package Compiler;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.Node;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SetOperationList;


public class AST {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String[] colors = {
            ANSI_RESET, ANSI_BLACK ,ANSI_RED , ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE,
            ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE
    };

    public static Node parseAST(String SQLStatement) throws JSQLParserException {
        return CCJSqlParserUtil.parseAST(SQLStatement);
    }

    public static void traverseAST(Node head, int level, String statement) throws JSQLParserException {
        traverseAST(head,level,CCJSqlParserUtil.parse(statement));
    }

    private static void traverseAST(Node rootNode, int level, Statement statement){
        if(rootNode == null){

        }
        else{
            for(int i = 0; i < rootNode.jjtGetNumChildren(); i++){
                traverseAST(rootNode.jjtGetChild(i),level+1, statement);
            }
        }
    }

    private static void printNode(Node head, int level){
        System.out.print("|");
        for( int s = 0 ; s < level ; s++){
            System.out.print("--");
        }
        System.out.println(colors[level % 9] + head + colors[0]);
    }

    public static void generateSiddhiApp(String StringSQLSelectstatement){
        Statement statement = null;
        try {
            statement = CCJSqlParserUtil.parse(StringSQLSelectstatement);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        statement.accept(new CustomSelectStatementVisitor());
    }
}
