import SiddhiAppComposites.Annotation.Attributes.JsonMapAttributes;
import SiddhiAppComposites.Annotation.Common.KeyValue;
import SiddhiAppComposites.Annotation.Info.QueryInfo;
import SiddhiAppComposites.Annotation.Map.JsonMap;
import SiddhiAppComposites.Annotation.Sink.LogSink;
import SiddhiAppComposites.Annotation.Source.LiveSource;
import SiddhiAppComposites.SiddhiApp;
import SiddhiAppComposites.SiddhiAppGenerator;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Java Version      : " + runtime.version().toString());
        System.out.println("Total Mem         : " + runtime.totalMemory() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Free Mem          : " + runtime.freeMemory() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Max Mem           : " + runtime.maxMemory() / (1024 * 1024 * 1024) + " GB");
        System.out.println("Num of Processors : " + runtime.availableProcessors() + "\n");

        String sqlStatement = "SELECT ip@string,browser@string,date@string as custom_alias_for_date, count(traffic@String) as sum_traffic, " +
                "eventtimestamp@long, initial_data@string FROM NetworkTrafficTable WHERE traffic@int = 884800000";

        SiddhiApp siddhiApp = SiddhiAppGenerator
                .generateSiddhiApp("SiddhiAppName-dev-custom-app-name",
                        sqlStatement,
                        new LiveSource(),
                        new JsonMap().addMapComposite(new KeyValue<>("enclosing.element", "$.properties")),
                        new JsonMapAttributes(),
                        new LogSink().addMapComposite(new JsonMap()),
                        new QueryInfo().setQueryName("SQL-SiddhiQL-dev")
                );

        System.out.println(Arrays.toString(siddhiApp.getColumnNames().toArray()));

    }

}