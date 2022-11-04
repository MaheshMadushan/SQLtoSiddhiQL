package SiddhiApp;
import java.util.List;
public class SelectStatementAttributeList implements IAttributeList{
    private List<AttributeList.AttributeDataTypePair<IAttribute, String>> attributeDataTypePairs;

    public SelectStatementAttributeList(List<AttributeList.AttributeDataTypePair<IAttribute, String>> attributeDataTypePairs) {
        this.attributeDataTypePairs = attributeDataTypePairs;
    }

    @Override
    public void setAttributeSetAndDataTypes(List<AttributeList.AttributeDataTypePair<IAttribute, String>> attributeDataTypePairs) {

    }

    @Override
    public List<AttributeList.AttributeDataTypePair<IAttribute, String>> getAttributeSetAndDataTypes() {
        return attributeDataTypePairs;
    }

    @Override
    public String toString() {
        StringBuilder attributeSetWithAliasesWithOutDataType = new StringBuilder("");
        for(AttributeList.AttributeDataTypePair<IAttribute,String> attributeDataTypePair : attributeDataTypePairs){
            attributeSetWithAliasesWithOutDataType
                    .append(attributeDataTypePair.getAttribute().toString(true))
                    .append(",  ");
        }
        attributeSetWithAliasesWithOutDataType.deleteCharAt(attributeSetWithAliasesWithOutDataType.length() - 3); // deleting redundant "," // attributeSetWithDataType - <attribute name> AS <alias>,<attribute name> AS alias,<attribute name> AS <alias>, <attribute name> ... ;
        return attributeSetWithAliasesWithOutDataType.toString();
    }
}
