package SiddhiApp;
import java.util.ArrayList;
import java.util.List;
public class SelectStatementAttributeList implements IAttributeList{

    private final List<ISiddhiAppComposite> attributesWithOrWithoutAliases;

    public SelectStatementAttributeList() {
        this.attributesWithOrWithoutAliases = new ArrayList<>(10);
    }

    public List<ISiddhiAppComposite> getAttributesWithOrWithoutAliases() {
        return attributesWithOrWithoutAliases;
    }

    public void addAttribute(ISiddhiAppComposite attribute){
         attributesWithOrWithoutAliases.add(attribute);
    }

    @Override
    public String toString() {
        StringBuilder attributeSetWithAliasesWithOutDataType = new StringBuilder("");
        for(ISiddhiAppComposite attributeWithAlias : attributesWithOrWithoutAliases){
            attributeSetWithAliasesWithOutDataType
                    .append(attributeWithAlias.getSiddhiAppCompositeAsString()).append(",");
        }
        return attributeSetWithAliasesWithOutDataType.toString();
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return toString();
    }
}
