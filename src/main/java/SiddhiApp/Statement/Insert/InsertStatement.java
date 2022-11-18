package SiddhiApp.Statement.Insert;

import SiddhiApp.ISiddhiAppComposite;
import SiddhiApp.Stream;

public class InsertStatement implements IInsertStatement {

    private ISiddhiAppComposite outputStream;

    public InsertStatement(){
        outputStream = null;
    }

    public void setOutputStreamName(String streamName){
        outputStream = new Stream(streamName);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "insert into " + outputStream.getSiddhiAppCompositeAsString() + ";";
    }
}
