package SiddhiApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreamStatementAttributeList implements IAttributeList{

    private List<ISiddhiAppComposite> attributeListWithoutAliasesWithDataType;
    private Iterator<ISiddhiAppComposite> attributeListWithoutAliasesWithDataTypeIterator;

    public StreamStatementAttributeList() {
        this.attributeListWithoutAliasesWithDataType = new ArrayList<>(10);
    }

    public void setAttributeListWithDataType(List<ISiddhiAppComposite> attributeListWithoutAliasesWithDataType){
        this.attributeListWithoutAliasesWithDataType = attributeListWithoutAliasesWithDataType;
    }

    public void addAttribute(ISiddhiAppComposite attributeWithDataType){
        // TODO : create a hashmap and avoid O(n) comparisons

        for(int i = 0; i < attributeListWithoutAliasesWithDataType.size() ; i++){

            if(attributeListWithoutAliasesWithDataType.get(i).equals(attributeWithDataType)){
                return;
            }

        }
        this.attributeListWithoutAliasesWithDataType.add(attributeWithDataType);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        StringBuilder attributeSetWithoutAliasesWithDataType = new StringBuilder(""); // eg. "" -->
        this.attributeListWithoutAliasesWithDataTypeIterator = this.attributeListWithoutAliasesWithDataType.iterator();

        boolean thereIsNextComponent = this.attributeListWithoutAliasesWithDataTypeIterator.hasNext();
        while(thereIsNextComponent){
            ISiddhiAppComposite selectItemComposite = this.attributeListWithoutAliasesWithDataTypeIterator.next();
            thereIsNextComponent = this.attributeListWithoutAliasesWithDataTypeIterator.hasNext();
            if(thereIsNextComponent){
                attributeSetWithoutAliasesWithDataType
                        .append(
                                selectItemComposite
                                        .getSiddhiAppCompositeAsString()
                        )
                        .append(","); // --> "col String, col String,"
            }else{
                attributeSetWithoutAliasesWithDataType
                        .append(
                                selectItemComposite.
                                        getSiddhiAppCompositeAsString()
                        ); // --> "col String, col String, col double"
            }
        }

        return attributeSetWithoutAliasesWithDataType
                .toString(); // --> "col String, col String, col double"
    }
}
