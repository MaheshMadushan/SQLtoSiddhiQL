package SiddhiAppComposites;
import java.util.*;

public class SelectStatementAttributeList implements IAttributeList{

    private final List<ISiddhiAppComposite> attributesWithOrWithoutAliasesList;
    private final Map<String, List<ISiddhiAppComposite>> attributesListWithStreamName = new HashMap<>();

    public SelectStatementAttributeList() {
        this.attributesWithOrWithoutAliasesList = new ArrayList<>(10);
    }

    public List<ISiddhiAppComposite> getAttributesWithOrWithoutAliases() {
        return attributesWithOrWithoutAliasesList;
    }

    public void addAttribute(ISiddhiAppComposite attribute){
        attributesWithOrWithoutAliasesList.add(attribute);
    }

    public void addAttribute(String streamName, ISiddhiAppComposite attribute) {
        if (attributesListWithStreamName.get(streamName) != null) {
            attributesListWithStreamName.get(streamName).add(attribute);
        } else {
            List<ISiddhiAppComposite> siddhiAppComposites = new ArrayList<>();
            siddhiAppComposites.add(attribute);
            attributesListWithStreamName.put(streamName, siddhiAppComposites);
        }
    }

    public String generateString() {
        StringBuilder attributeSetWithAliasesWithOutDataType = new StringBuilder();


        for (ISiddhiAppComposite iSiddhiAppComposite: attributesWithOrWithoutAliasesList) {
            Iterator<Map.Entry<String, List<ISiddhiAppComposite>>> attributesIterator = attributesListWithStreamName.entrySet().iterator();
            boolean hasNextEntry = attributesIterator.hasNext();
            while (hasNextEntry) {
                Map.Entry<String, List<ISiddhiAppComposite>> entry = attributesIterator.next();
                String streamName = entry.getKey();
                List<ISiddhiAppComposite> attributeList = attributesListWithStreamName.get(streamName);
                if (attributeList.contains(iSiddhiAppComposite)) {
                    attributeSetWithAliasesWithOutDataType
                            .append(streamName)
                            .append(".")
                            .append(iSiddhiAppComposite.getSiddhiAppCompositeAsString().replaceAll("\\s", ""))
                            .append(", ");
                    break;
                }

            }
        }

        if (attributeSetWithAliasesWithOutDataType.length() > 1) {
            attributeSetWithAliasesWithOutDataType.setLength(attributeSetWithAliasesWithOutDataType.length() - 2);
        }

        return attributeSetWithAliasesWithOutDataType.toString();
    }
//    public String generateString() {
//        StringBuilder attributeSetWithAliasesWithOutDataType = new StringBuilder();
//
//        Iterator<Map.Entry<String, List<ISiddhiAppComposite>>> attributesIterator = attributesListWithStreamName.entrySet().iterator();
//
//        boolean hasNextEntry = attributesIterator.hasNext();
//
//        while (hasNextEntry) {
//            Map.Entry<String, List<ISiddhiAppComposite>> entry = attributesIterator.next();
//            String streamName = entry.getKey();
//            List<ISiddhiAppComposite> attributesWithOrWithoutAliasesList = entry.getValue();
//
//            Iterator<ISiddhiAppComposite> attributesWithOrWithoutAliasesIterator = attributesWithOrWithoutAliasesList.iterator();
//            boolean hasNextComponent = attributesWithOrWithoutAliasesIterator.hasNext();
//
//            while (hasNextComponent) {
//                ISiddhiAppComposite selectItemComposite = attributesWithOrWithoutAliasesIterator.next();
//                hasNextComponent = attributesWithOrWithoutAliasesIterator.hasNext();
//
//                String attributeString = selectItemComposite.getSiddhiAppCompositeAsString().replaceAll("\\s", "");
//
//                if (attributesListWithStreamName.containsKey(attributeString)) {
//                    List<ISiddhiAppComposite> streamAttributes = attributesListWithStreamName.get(attributeString);
//                    for (ISiddhiAppComposite streamAttribute : streamAttributes) {
//                        attributeSetWithAliasesWithOutDataType
//                                .append(streamName)
//                                .append(".")
//                                .append(streamAttribute.getSiddhiAppCompositeAsString().replaceAll("\\s", ""))
//                                .append(",");
//                    }
//                } else {
//                    attributeSetWithAliasesWithOutDataType
//                            .append(streamName)
//                            .append(".")
//                            .append(attributeString)
//                            .append(",");
//                }
//            }
//
//            hasNextEntry = attributesIterator.hasNext();
//            if (hasNextEntry) {
//                attributeSetWithAliasesWithOutDataType.append(",");
//            }
//        }
//
//// Remove the trailing comma if the string is not empty
//        if (attributeSetWithAliasesWithOutDataType.length() > 0) {
//            attributeSetWithAliasesWithOutDataType.setLength(attributeSetWithAliasesWithOutDataType.length() - 1);
//        }
//
//        return attributeSetWithAliasesWithOutDataType.toString();
//
//    }

    @Override
    public String toString() {
        StringBuilder attributeSetWithAliasesWithOutDataType = new StringBuilder(); // eg. - SUM( -->
        Iterator<ISiddhiAppComposite> attributesWithOrWithoutAliasesIterator = attributesWithOrWithoutAliasesList.iterator();

        boolean thereIsNextComponent = attributesWithOrWithoutAliasesIterator.hasNext();

        while(thereIsNextComponent){
            ISiddhiAppComposite selectItemComposite = attributesWithOrWithoutAliasesIterator.next();
            thereIsNextComponent = attributesWithOrWithoutAliasesIterator.hasNext();

            if(thereIsNextComponent){
                attributeSetWithAliasesWithOutDataType
                        .append(
                                selectItemComposite
                                        .getSiddhiAppCompositeAsString()
                        )
                        .append(","); // --> col ,
            }else{
                attributeSetWithAliasesWithOutDataType
                        .append(
                                selectItemComposite.
                                        getSiddhiAppCompositeAsString()
                        ); // --> col , SUM(col + col) AS alias -->
            }
        }

        return attributeSetWithAliasesWithOutDataType
                .toString(); // col , SUM(col + col) AS alias
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return toString();
    }

    public String getSiddhiAppCompositeAsStringInJoins() {
        return generateString();
    }
}
