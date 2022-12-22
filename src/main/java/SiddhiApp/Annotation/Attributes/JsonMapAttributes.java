package SiddhiApp.Annotation.Attributes;

import SiddhiApp.Annotation.AnnotationType;
import SiddhiApp.ISiddhiAppComposite;

import java.util.HashSet;
import java.util.Iterator;

public class JsonMapAttributes extends IAttributes {
    private final StringBuilder JsonMapAttributesAnnotation = new StringBuilder("");
    private final AnnotationType JsonMapAttributesSignature = AnnotationType.ATTRIBUTES;

    public JsonMapAttributes(){
        annotationComposites = new HashSet<>(10);
    }


    @Override
    public String getSiddhiAppCompositeAsString() {
        JsonMapAttributesAnnotation
                .append(JsonMapAttributesSignature.getAnnotationTypeSignature()).append("(");// @attributes(
        Iterator<ISiddhiAppComposite> JsonMapAttributesAnnotationCompositesIterator = annotationComposites.iterator();

        while(JsonMapAttributesAnnotationCompositesIterator.hasNext()){
            JsonMapAttributesAnnotation.append(JsonMapAttributesAnnotationCompositesIterator.next().getSiddhiAppCompositeAsString());
            if(JsonMapAttributesAnnotationCompositesIterator.hasNext()){
                JsonMapAttributesAnnotation.append(",");
            }
        }

        JsonMapAttributesAnnotation
                .append(")"); // @attributes(columnName = "columnName", .....)

        return JsonMapAttributesAnnotation.toString();
    }
}
