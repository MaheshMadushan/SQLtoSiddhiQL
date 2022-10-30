package Compiler;

public class SiddhiAppGenerator {
    private static String mockSiddhiAppDefinition = "@App:name('TestSiddhiApp0')\n" +
            "@source(\n" +
            "\ttype='live', \n" +
            "\tsql.query='SELECT SUM(col1, clo3, a) AS a, COUNT(col2) AS b, col3 AS c, col4 AS d, col5, col99 FROM table WHERE col1 = 10 AND col2 = 20 XOR col3 = 30', \n" +
            "\thost.name='api-varden-4f0f3c4f.paas.macrometa.io', \n" +
            "\tapi.key = 'madu140_gmail.com.AccessPortal.2PL8EeyIAMn2sx7YHKWMM58tmJLES4NyIWq6Cnsj0BTMjygJyF3b14zb2sidcauXccccb8', \n" +
            "\t@map(\n" +
            "\t\ttype='json', \n" +
            "\t\tenclosing.element='$.properties',  \n" +
            "\t\t@attributes(col3 = 'col3',  col4 = 'col4',  col5 = 'col5',  col99 = 'col99'  )\n" +
            "\t\t)\n" +
            "\t)\n" +
            "define stream inputStream (col3 String,  col4 String,  col5 String,  col99 String  );\n" +
            "@sink(type = 'log')\n" +
            "define stream OutputStream (col3 String,  col4 String,  col5 String,  col99 String  );\n" +
            "@info(name = 'query0')\n" +
            "from inputStream [col1 = 10 AND col2 = 20 XOR col3 = 30]\n" +
            "select col3 as c,  col4 as d,   col5,   col99  \n" +
            "insert into outputStream;";

    public static String generateSiddhiApp(String generalProjectionSQL) {
        return mockSiddhiAppDefinition;
    }
}
