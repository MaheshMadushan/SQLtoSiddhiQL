package SiddhiAppComposites;

public interface IAttributeList extends ISiddhiAppComposite {
    String toString();

    String generateString();
    void addAttribute(ISiddhiAppComposite attribute);

    void addAttribute(String streamName, ISiddhiAppComposite attribute);
}
