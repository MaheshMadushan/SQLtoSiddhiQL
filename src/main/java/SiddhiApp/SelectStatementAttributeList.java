package SiddhiApp;
import java.util.List;
public class SelectStatementAttributeList implements IAttributeList,ISiddhiAppComposite{
    // contains functions / columns / coulmns with aliases
    private List<ISiddhiAppComposite> attributeDataTypePairs;

    public SelectStatementAttributeList(List<ISiddhiAppComposite> attributeDataTypePairs) {
        this.attributeDataTypePairs = attributeDataTypePairs;
    }

    @Override
    public void setAttributeSetAndDataTypes(List<ISiddhiAppComposite> attributeDataTypePairs) {

    }

    @Override
    public List<ISiddhiAppComposite> getAttributeSetAndDataTypes() {
        return attributeDataTypePairs;
    }

    @Override
    public String toString() {
        StringBuilder attributeSetWithAliasesWithOutDataType = new StringBuilder("");
        for(ISiddhiAppComposite attributeDataTypePair : attributeDataTypePairs){
            attributeSetWithAliasesWithOutDataType
                    .append(attributeDataTypePair.getSiddhiAppCompositeAsString());
        }
        return attributeSetWithAliasesWithOutDataType.toString();
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return null;
    }
}
