package SiddhiApp;

import java.util.List;

public class StreamStatementAttributeList implements IAttributeList, ISiddhiAppComposite{
    private List<ISiddhiAppComposite> attributeDataTypePairs;

    @Override
    public void setAttributeSetAndDataTypes(List<ISiddhiAppComposite> attributeDataTypePairs) {
        this.attributeDataTypePairs = attributeDataTypePairs;
    }

    @Override
    public List<ISiddhiAppComposite> getAttributeSetAndDataTypes() {
        return attributeDataTypePairs;
    }

    public String toString(){
        StringBuilder attributeSetWithDataType = new StringBuilder("");
        for(ISiddhiAppComposite attributeDataTypePair : attributeDataTypePairs){
            attributeSetWithDataType
                    .append(attributeDataTypePair.getSiddhiAppCompositeAsString());
        }
        return attributeSetWithDataType.toString();
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return null;
    }
}
