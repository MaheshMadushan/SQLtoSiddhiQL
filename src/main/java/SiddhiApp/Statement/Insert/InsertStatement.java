package SiddhiApp.Statement.Insert;

import SiddhiApp.ISiddhiAppComposite;
import SiddhiApp.DefineStreamStatement;

public class InsertStatement implements IInsertStatement {

    private ISiddhiAppComposite outputStream;

    public InsertStatement(){
        outputStream = null;
    }

    public void setOutputStreamName(String streamName){
        outputStream = new DefineStreamStatement(streamName);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "insert into " + outputStream.getSiddhiAppCompositeAsString() + ";";
    }
}
