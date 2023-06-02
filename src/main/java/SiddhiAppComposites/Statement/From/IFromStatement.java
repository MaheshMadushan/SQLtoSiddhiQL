package SiddhiAppComposites.Statement.From;
import SiddhiAppComposites.ISiddhiAppComposite;
public interface IFromStatement extends ISiddhiAppComposite {
    void setFromStatementComposites(ISiddhiAppComposite filterStatement);
    void setStreamName(String streamName);
}