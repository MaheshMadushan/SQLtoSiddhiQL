package SiddhiApp.Statement.Insert;

import SiddhiApp.ISiddhiAppComposite;
import SiddhiApp.DefineStreamStatement;

public class InsertStatement implements IInsertStatement {

    private String outputStream;

    public InsertStatement(){
        outputStream = null;
    }

    public void setOutputStreamName(String outputStreamName){
        this.outputStream = outputStreamName;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "insert into " + outputStream + ";";
    }
}
