import SiddhiApp.Annotation.Attributes.JsonMapAttributes;
import SiddhiApp.Annotation.Common.KeyValue;
import SiddhiApp.Annotation.Info.QueryInfo;
import SiddhiApp.Annotation.Map.JsonMap;
import SiddhiApp.Annotation.Sink.LogSink;
import SiddhiApp.Annotation.Source.LiveSource;
import SiddhiApp.SiddhiApp;
import net.sf.jsqlparser.JSQLParserException;
import Compiler.*;

public class App {
    public static void main(String[] args) throws JSQLParserException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Java Version      : " + runtime.version().toString());
        System.out.println("Total Mem         : " + runtime.totalMemory() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Free Mem          : " + runtime.freeMemory() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Max Mem           : " + runtime.maxMemory() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Num of Processors : " + runtime.availableProcessors() + "\n");

        String sqlStatement = "SELECT  ip@string,  timestamp@string, SUM(traffic@int)  " +
                " FROM networkTrafficTable WHERE (traffic@int = 1000 AND traffic@int > 2000)";

        SiddhiApp siddhiApp = SiddhiAppGenerator
                .generateSiddhiApp(
                        "SiddhiAppName-dev-custom-app-name",
                        sqlStatement,
                        new LiveSource().addSourceComposite(new KeyValue<>("newFieldToSourceAnnotation", "FieldValue")),
                        new JsonMap().addMapComposite(new KeyValue<>("enclosing.element", "$.properties")),
                        new JsonMapAttributes(),
                        new LogSink(),
                        new QueryInfo().setQueryName("SQL-SiddhiQL-dev")
                );

        System.out.println(siddhiApp.getSiddhiAppStringRepresentation());

    }

}