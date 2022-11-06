package SiddhiApp;

import java.util.List;

public interface IAttributeList {
    String toString();
    void setAttributeSetAndDataTypes(List<AttributeList.AttributeDataTypePair<IAttribute,String>> attributeDataTypePairs);
    List<AttributeList.AttributeDataTypePair<IAttribute,String>> getAttributeSetAndDataTypes();
}
