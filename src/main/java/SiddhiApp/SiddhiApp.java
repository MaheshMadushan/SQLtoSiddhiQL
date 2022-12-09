package SiddhiApp;

import SiddhiApp.Annotation.App.App;
import SiddhiApp.Annotation.Attributes.IAttributes;
import SiddhiApp.Annotation.Attributes.JsonMapAttributes;
import SiddhiApp.Annotation.Info.IInfo;
import SiddhiApp.Annotation.Info.QueryInfo;
import SiddhiApp.Annotation.Map.IMap;
import SiddhiApp.Annotation.Map.JsonMap;
import SiddhiApp.Annotation.Sink.ISink;
import SiddhiApp.Annotation.Sink.LogSink;
import SiddhiApp.Annotation.Source.ISource;
import SiddhiApp.Annotation.Source.LiveSource;
import SiddhiApp.Statement.FilterExpressionStatement.FilterExpression;
import SiddhiApp.Statement.FilterExpressionStatement.IFilterExpression;
import SiddhiApp.Statement.From.FromStatement;
import SiddhiApp.Statement.From.IFromStatement;
import SiddhiApp.Statement.Insert.IInsertStatement;
import SiddhiApp.Statement.Insert.InsertStatement;
import SiddhiApp.Statement.Select.SelectStatement;

public class SiddhiApp {

    private final DefineStreamStatement defineStreamStatement = new DefineStreamStatement(); // create define stream // use this for output stream also
    private final SelectStatement selectStatement = new SelectStatement(); // select statement
    private final IFromStatement fromStatement = new FromStatement(); // from statement
    private final IFilterExpression filterExpression = new FilterExpression(); // filter statement
    private final IInsertStatement insertStatement = new InsertStatement(); // insert into statement
    // Annotations
    private final App annotationApp = new App();
    private ISource annotationSource = new LiveSource();
    private IAttributes annotationAttributes = new JsonMapAttributes();
    private IMap annotationMap = new JsonMap();
    private ISink annotationSink = new LogSink();
    private IInfo annotationInfo = new QueryInfo();

    private String inputOutputStreamNamePrefix = null;
    private String siddhiAppName = "defaultSiddhiAppName";

    private final StringBuilder stringSiddhiApp = new StringBuilder("");

    public void addSymbolToFilterExpression(String symbol){
        ((FilterExpression) this.filterExpression).addSymbol(symbol);
    }

    public void addSelectItem(ISiddhiAppComposite selectItem){
        selectStatement.addSelectItem(selectItem);
    }

    public void addColumnWithDataType(ISiddhiAppComposite columnWithDataType){
        defineStreamStatement.addAttributeWithDataType(columnWithDataType);
    }

    public void setStreamNamePrefix(String inputOutputStreamNamePrefix) {
        this.inputOutputStreamNamePrefix = inputOutputStreamNamePrefix;
    }

    public void setSiddhiAppName(String siddhiAppName){
        this.siddhiAppName = siddhiAppName;
    }

    public void setAnnotationSource(ISource annotationSource) {
        this.annotationSource = annotationSource;
    }

    public void setAnnotationSink(ISink annotationSink) {
        this.annotationSink = annotationSink;
    }

    public void setAnnotationInfo(IInfo annotationInfo) {
        this.annotationInfo = annotationInfo;
    }

    public void setAnnotationAttributes(IAttributes annotationAttributes) {
        this.annotationAttributes = annotationAttributes;
    }

    public void setAnnotationMap(IMap annotationMap) {
        this.annotationMap = annotationMap;
    }

    public String getSiddhiAppStringRepresentation(){
        // Annotations
            // app name
        annotationApp.setSiddhiApplicationName(siddhiAppName);
        stringSiddhiApp.append(annotationApp.getSiddhiAppCompositeAsString());
            // source
         annotationMap.addMapComposite(annotationAttributes);
        annotationSource.addSourceComposite(annotationMap);
        stringSiddhiApp.append(annotationSource.getSiddhiAppCompositeAsString());
        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // IPStream set I/P Stream Name
        stringSiddhiApp.append(defineStreamStatement.getSiddhiAppCompositeAsString());

        // Annotations
            // sink
        stringSiddhiApp.append(annotationSink.getSiddhiAppCompositeAsString());

        defineStreamStatement.setStreamName(inputOutputStreamNamePrefix + "OutputStream"); // OPStream set O/P Stream Name
        stringSiddhiApp.append(defineStreamStatement.getSiddhiAppCompositeAsString());
        // Annotations
            // info
        stringSiddhiApp.append(annotationInfo.getSiddhiAppCompositeAsString());

        fromStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // From Statement set I/P Stream Name
        fromStatement.setFromStatementComposite(filterExpression); // From statement filter expression
        stringSiddhiApp.append(fromStatement.getSiddhiAppCompositeAsString());

        stringSiddhiApp.append(selectStatement.getSiddhiAppCompositeAsString()); // select statement

        insertStatement.setOutputStreamName(inputOutputStreamNamePrefix + "OutputStream"); // insert statement set O/P Stream name
        stringSiddhiApp.append(insertStatement.getSiddhiAppCompositeAsString());
        return stringSiddhiApp.toString();
    }

    public static class SiddhiAppBuilder{

    }
}