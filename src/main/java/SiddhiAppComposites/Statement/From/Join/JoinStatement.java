package SiddhiAppComposites.Statement.From.Join;

import SiddhiAppComposites.ISiddhiAppComposite;
import SiddhiAppComposites.Statement.From.Window;

import java.util.ArrayList;
import java.util.List;

public class JoinStatement implements IJoinStatement {

    private String streamName;
    private String window;
    private String alias;

    private final List<ISiddhiAppComposite> joinStatementComposites; // filter statement,window,stream function,join, join with window...

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JoinStatement(){
        streamName = null;
        joinStatementComposites = new ArrayList<>(10);
    }

    public void setWindowTypeAndSize(Window windowType, int sizeOfWindowAsLengthOrAsTimeInMinutes){
        this.window = windowType.setSizeOfWindowAsTimeOrLength(sizeOfWindowAsLengthOrAsTimeInMinutes);
    }

    public void setStreamName(String streamName){
        this.streamName = streamName;
    }

    public void addJoinStatementComposite(ISiddhiAppComposite joinStatementComposite) {
        joinStatementComposites.add(joinStatementComposite);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        String fromWithStreamName = "join " + streamName + window + " as " + alias + " ";
        return fromWithStreamName +
                joinStatementComposites.stream().iterator().next().getSiddhiAppCompositeAsString() + "\n";
    }
}
