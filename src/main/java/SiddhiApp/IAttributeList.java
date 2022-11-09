package SiddhiApp;

import java.util.List;

public interface IAttributeList {
    String toString();
    void setAttributeSetAndDataTypes(List<ISiddhiAppComposite> attributeDataTypePairs);
    List<ISiddhiAppComposite> getAttributeSetAndDataTypes();
}
