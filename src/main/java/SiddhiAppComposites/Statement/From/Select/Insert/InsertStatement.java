package SiddhiAppComposites.Statement.From.Select.Insert;

public class InsertStatement implements IInsertStatement {

    public String getOutputStream() {
        return outputStream;
    }

    private String outputStream;

    public InsertStatement(){
        outputStream = null;
    }

    public void setOutputStreamName(String outputStreamName){
        this.outputStream = outputStreamName;
    }

    @Override
    public void setStreamName(String outputStreamName) {
        this.outputStream = outputStreamName;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return "insert into " + outputStream + ";\n";
    }
}
