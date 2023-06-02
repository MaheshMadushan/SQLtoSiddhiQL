package SiddhiAppComposites.Statement.From.Join.OnExpression;

import SiddhiAppComposites.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.List;

public class OnExpression implements IOnExpression{
    private final List<ISiddhiAppComposite> onExpressionComposites; // filter statement,window,stream function,join, join with window...

    public OnExpression(){
        onExpressionComposites = new ArrayList<>(10);
    }
    public void addOnExpressionComposite(ISiddhiAppComposite onExpressionComposite) {
        onExpressionComposites.add(onExpressionComposite);
    }
    @Override
    public String getSiddhiAppCompositeAsString() {
        String fromWithStreamName = "on ";
        return fromWithStreamName +
                onExpressionComposites.stream().iterator().next().getSiddhiAppCompositeAsString() + "\n";
    }
}
