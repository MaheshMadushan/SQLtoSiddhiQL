package SiddhiApp;

import java.util.ArrayList;
import java.util.List;

public class SelectItem implements IAttribute{
    private final List<ISiddhiAppComposite> selectItemComposites;

    public SelectItem(){
        selectItemComposites = new ArrayList<ISiddhiAppComposite>();
    }

    public void addSelectItemComposite(ISiddhiAppComposite selectItemComposite){
        selectItemComposites.add(selectItemComposite);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        StringBuilder selectItem = new StringBuilder("");
        for(ISiddhiAppComposite selectItemComposite : selectItemComposites){
            selectItem.append(selectItemComposite.getSiddhiAppCompositeAsString()).append(",");
        }
        return selectItem.toString();
    }
}
