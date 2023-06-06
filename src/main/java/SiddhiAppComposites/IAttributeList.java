package SiddhiAppComposites;

import java.util.List;
import java.util.Map;

public interface IAttributeList extends ISiddhiAppComposite {
    String toString();

    String generateString();
    void addAttribute(ISiddhiAppComposite attribute);

    void addAttribute(String streamName, ISiddhiAppComposite attribute);

    Map<String, List<ISiddhiAppComposite>> getAttributesListWithStreamName();
}
