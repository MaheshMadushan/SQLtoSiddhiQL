package SiddhiAppComposites.Statement.GroupBy;
import SiddhiAppComposites.ISiddhiAppComposite;

import java.util.List;
import java.util.Map;

public interface IGroupByStatement extends ISiddhiAppComposite {
    void setGroupByStatementComposite(ISiddhiAppComposite filterStatement);

    void addAttribute(String column);

    Boolean containsAttributes();

    void setColumnWithStreamMap(Map<String, List<ISiddhiAppComposite>> attributesWithStream);
}