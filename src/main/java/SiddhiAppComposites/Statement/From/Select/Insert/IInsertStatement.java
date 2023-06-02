package SiddhiAppComposites.Statement.From.Select.Insert;

import SiddhiAppComposites.ISiddhiAppComposite;
public interface IInsertStatement extends ISiddhiAppComposite {
    void setOutputStreamName(String outputStreamName);

    void setStreamName(String inputStreamName);
}