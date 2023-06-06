package SiddhiAppComposites.Statement.GroupBy;

import SiddhiAppComposites.Column;
import SiddhiAppComposites.ISiddhiAppComposite;
import SiddhiAppComposites.SelectItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupByStatement implements IGroupByStatement {
    private List<String> groupByStatementAttributes; // filter statement,window,stream function,join, join with window...
    private Map<String, List<ISiddhiAppComposite>> attributesWithStream;

    public GroupByStatement(){
        this.groupByStatementAttributes = new ArrayList<>();
        this.attributesWithStream = new HashMap<>();
    }

    public void addAttribute(String column) {
        groupByStatementAttributes.add(column);
    }

    public Boolean containsAttributes() {
        return (groupByStatementAttributes.size() > 0);
    }

    public void setColumnWithStreamMap(Map<String, List<ISiddhiAppComposite>> attributesWithStream) {
        this.attributesWithStream = attributesWithStream;
    }

    public String getKeyForValue(Map<String, List<ISiddhiAppComposite>> map, String targetValue) {
        for (Map.Entry<String, List<ISiddhiAppComposite>> entry : map.entrySet()) {
            List<ISiddhiAppComposite> list = entry.getValue();
            for (ISiddhiAppComposite iSiddhiAppComposite: list) {
                if (((Column) ((SelectItem) iSiddhiAppComposite).getSelectItemComposites().get(0)).getName().equals(targetValue)) {
                    return entry.getKey();
                }
            }
        }
        return null; // Return null if the value is not found
    }
    @Override
    public String getSiddhiAppCompositeAsString() {
        StringBuilder groupByStatement = new StringBuilder("group by ");
        for (String column: groupByStatementAttributes) {
            String key = getKeyForValue(attributesWithStream, column);
            if (key != null) {
                groupByStatement.append(key).append(".").append(column).append(", ");
            } else {
                groupByStatement.append(column).append(", ");
            }
        }
        groupByStatement.delete(groupByStatement.length()-2,groupByStatement.length());
        groupByStatement.append("\n");
        return groupByStatement.toString();
    }

    @Override
    public void setGroupByStatementComposite(ISiddhiAppComposite filterStatement) {

    }
}
