package SiddhiApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectItem implements IAttribute{
    private final List<ISiddhiAppComposite> selectItemComposites;
    private final Alias selectItemAlias;
    private Iterator<ISiddhiAppComposite> selectItemIterator;

    public SelectItem(){
        selectItemComposites = new ArrayList<ISiddhiAppComposite>();
        selectItemAlias = new Alias();

    }

    public void setSelectItemAlias(ISiddhiAppComposite selectItemAlias){
        this.selectItemAlias.setAlias(selectItemAlias.getSiddhiAppCompositeAsString());
    }

    public void addSelectItemComposite(ISiddhiAppComposite selectItemComposite){
        selectItemComposites.add(selectItemComposite);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        selectItemIterator = selectItemComposites.iterator();
        StringBuilder selectItem = new StringBuilder("");
        boolean thereIsNextComponent = selectItemIterator.hasNext();

        while(thereIsNextComponent){
            ISiddhiAppComposite selectItemComposite = selectItemIterator.next();
            thereIsNextComponent = selectItemIterator.hasNext();

            if(selectItemAlias.getAlias() == null) {
                selectItem
                        .append(selectItemComposite.getSiddhiAppCompositeAsString())
                        .append(" ");
            }else{
                selectItem
                        .append(selectItemComposite.getSiddhiAppCompositeAsString())
                        .append(" ")
                        .append(selectItemAlias.getAlias());
            }
        }
        return selectItem.toString();
    }
}
