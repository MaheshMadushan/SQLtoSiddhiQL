package SiddhiApp.Annotation.Map;

import SiddhiApp.Annotation.AnnotationType;
import SiddhiApp.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.Iterator;

public class JsonMap extends IMap {
    private final MapType jsonMap = MapType.JSON;
    private final StringBuilder jsonMapAnnotation = new StringBuilder("");
    private final AnnotationType jsonMapSignature = AnnotationType.MAP;

    public JsonMap() {
        annotationComposites = new ArrayList<>(10);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        jsonMapAnnotation
                .append(jsonMapSignature.getAnnotationTypeSignature())
                .append("(").append("type = '").append(jsonMap.getMapTypeSignature()).append("',"); // @map(type = "json",
        Iterator<ISiddhiAppComposite> sourceAnnotationCompositesIterator = annotationComposites.iterator();

        while(sourceAnnotationCompositesIterator.hasNext()){
            jsonMapAnnotation.append(sourceAnnotationCompositesIterator.next().getSiddhiAppCompositeAsString());
            if(sourceAnnotationCompositesIterator.hasNext()){
                jsonMapAnnotation.append(",");
            }
        }

        jsonMapAnnotation
                .append(")"); // @map(type = "json", .....)

        return jsonMapAnnotation.toString();
    }
}
