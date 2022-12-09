package Compiler;

import SiddhiApp.SiddhiApp;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.Node;

public class SiddhiAppGenerator {
    private static String mockSiddhiAppDefinition = "";
    private static AST abstractSyntaxTree;
    private static final int STARTING_LEVEL_OF_AST = 0;

    public static String generateSiddhiApp(String generalProjectionSQL) throws JSQLParserException {

        // parse projection SQL and get root node of AST
        Node rootNodeOfASTofSelectStatement = AST.parseAST(generalProjectionSQL);
        // traverse tree and see structure of the select statement for debugging purposes
        // AST.traverseAST(rootNodeOfASTofSelectStatement, STARTING_LEVEL_OF_AST, generalProjectionSQL);

        SiddhiApp siddhiApp = AST.generateSiddhiApp(generalProjectionSQL);
        System.out.println(siddhiApp.getSiddhiAppStringRepresentation());

        return mockSiddhiAppDefinition;
    }
}
