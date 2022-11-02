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
    public static final String ANSI_RESET = "\u001B[0m";public static final String ANSI_BLACK = "\u001B[30m";public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";public static final String ANSI_YELLOW = "\u001B[33m";public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";public static final String ANSI_CYAN = "\u001B[36m";public static final String ANSI_WHITE = "\u001B[37m";
    public static final String[] arr = {ANSI_RESET, ANSI_BLACK ,ANSI_RED , ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE};

    public static Node parseAST(String SQLStatement) throws JSQLParserException {
        return CCJSqlParserUtil.parseAST(SQLStatement);
    }

    public static void traverseAST(Node head, int level) throws JSQLParserException {
        traverseAST(head,level,CCJSqlParserUtil.parse("SELECT col1 AS A, col2 AS B, col3 AS C , col4 as D, col5 as E, col99 as F " +
                "FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30 AND col5 = 98;"));
    }

    public static void traverseAST(Node head, int level, Statement statement){
        if(head == null){

        }
        else{
            // extract data from statement
            printNode(head,level);
            if(head.toString().equals("Statement")){
                // if not a select statement give an exception
                System.out.println("Statement is " + ((statement instanceof Select) ? "Select" : "Not a Select"));
            }
            if(head.toString().equals("SetOperationList")){
                // this is called if statement is a select statement
                System.out.println("Select Statement is " + ((statement instanceof SetOperationList) ? "SetOperationList" : "Not a SetOperationList"));
            }else if(head.toString().equals("PlainSelect")){
                // this is called if statement is a select statement and not a SetOperationList
                System.out.println("Select Statement is a " + ((statement instanceof PlainSelect) ? "PlainSelect" : "Not an PlainSelect"));
            }else if(head.toString().equals("PrimaryExpression")){
                System.out.println(statement instanceof Expression);
            }else if(head.toString().equals("Column")){
                System.out.println(statement instanceof Column);
            }else if(head.toString().equals("Function")){
                System.out.println(statement instanceof Function);
            }else if(head.toString().equals("RegularCondition")){
                System.out.println(statement instanceof BinaryExpression);
            }else if(head.toString().equals("SelectItem")){
                System.out.println("SelectItem " + ((statement instanceof PlainSelect) ? "PlainSelect" : "Not and PlainSelect"));
                if(head.toString().equals("Expression")){
                    System.out.println(statement instanceof Expression);
                }
            }
            for(int i = 0; i < head.jjtGetNumChildren(); i++){
                traverseAST(head.jjtGetChild(i),level+1,statement);
            }
        }
    }

    private static void printNode(Node head, int level){
        System.out.print("|");
        for( int s = 0 ; s < level ; s++){
            System.out.print("--");
        }
        System.out.println(arr[level % 9] + head + arr[0]);
    }

    private static void extractFromStatement(Statement statement){
        // is statement is a select statement

        // is it a plain select else reject

        //
    }
}
