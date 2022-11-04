package SiddhiApp;

import java.util.List;

public class StreamStatementAttributeList implements IAttributeList{
    private List<AttributeList.AttributeDataTypePair<IAttribute, String>> attributeDataTypePairs;

    @Override
    public void setAttributeSetAndDataTypes(List<AttributeList.AttributeDataTypePair<IAttribute, String>> attributeDataTypePairs) {
        this.attributeDataTypePairs = attributeDataTypePairs;
    }

    @Override
    public List<AttributeList.AttributeDataTypePair<IAttribute, String>> getAttributeSetAndDataTypes() {
        return attributeDataTypePairs;
    }

    public String toString(){
        StringBuilder attributeSetWithDataType = new StringBuilder("");
        for(AttributeList.AttributeDataTypePair<IAttribute,String> attributeDataTypePair : attributeDataTypePairs){
            attributeSetWithDataType
                    .append(attributeDataTypePair.getAttribute().getName())
                    .append(" ")
                    .append(attributeDataTypePair.getDataType())
                    .append(",  ");
        }
        attributeSetWithDataType.deleteCharAt(attributeSetWithDataType.length() - 3); // deleting redundant "," / 3 is distance from end of string to last ","
        return attributeSetWithDataType.toString();
    }
}
