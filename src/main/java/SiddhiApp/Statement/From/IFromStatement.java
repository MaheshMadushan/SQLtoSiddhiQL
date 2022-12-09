package SiddhiApp.Statement.From;
import SiddhiApp.ISiddhiAppComposite;
public interface IFromStatement extends ISiddhiAppComposite {
    void setFromStatementComposite(ISiddhiAppComposite filterStatement);
    void setStreamName(String streamName);
}