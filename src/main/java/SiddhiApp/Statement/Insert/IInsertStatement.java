package SiddhiApp.Statement.Insert;

import SiddhiApp.ISiddhiAppComposite;
public interface IInsertStatement extends ISiddhiAppComposite {
    void setOutputStreamName(String outputStreamName);

    void setStreamName(String inputStreamName);
}