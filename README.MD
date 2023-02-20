


## High Level Architecture

![plot](./src/main/java/Blank%20diagram%20-%20Page%201.png)
## Usage
```java
String sqlStatement = "SELECT  ip@string,  timestamp@string, SUM(traffic@int) as sum_traffic " +
                " FROM networkTrafficTable WHERE (traffic@int = 1000 AND traffic@int > 2000)";

        SiddhiAppComposites siddhiApp = SiddhiAppGenerator
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
```
## Output

```SiddhiQL
@app:name('SiddhiAppName-dev-custom-app-name')
@source(type = 'live',sql.query = 'SELECT  ip,  timestamp, SUM(traffic) as sum_traffic  FROM networkTrafficTable WHERE (traffic = 1000 AND traffic > 2000)',@map(type = 'json',enclosing.element = '$.properties',@attributes(ip = 'ip',timestamp = 'timestamp',traffic = 'traffic')))
define stream networkTrafficTableInputStream(ip string,timestamp string,traffic int);
@sink(type = 'log',@map(type = 'json'))
define stream networkTrafficTableOutputStream(ip string,timestamp string,sum_traffic int);
@info(name = 'SQL-SiddhiQL-dev')
from networkTrafficTableInputStream[( traffic == 1000 AND traffic > 2000 ) ]
select  ip  , timestamp  ,SUM( traffic )  as sum_traffic 
insert into networkTrafficTableOutputStream;
```
