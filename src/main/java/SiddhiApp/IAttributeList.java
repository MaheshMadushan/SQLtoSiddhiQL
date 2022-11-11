package SiddhiApp;

import java.util.List;

public interface IAttributeList extends ISiddhiAppComposite {
    String toString();
    void addAttribute(ISiddhiAppComposite attribute);
}
