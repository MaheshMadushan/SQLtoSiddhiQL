package SiddhiAppComposites.Annotation.Map;

import SiddhiAppComposites.Annotation.AnnotationType;
import SiddhiAppComposites.ISiddhiAppComposite;

import java.util.Iterator;

public class JsonMap extends IMap {
    private final MapType jsonMap = MapType.JSON;
    private final StringBuilder jsonMapAnnotation = new StringBuilder();
    private final AnnotationType jsonMapSignature = AnnotationType.MAP;

    public JsonMap() {

    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        jsonMapAnnotation.delete(0, jsonMapAnnotation.length());
        jsonMapAnnotation
                .append(jsonMapSignature.getAnnotationTypeSignature())
                .append("(").append("type = \"").append(jsonMap.getMapTypeSignature()).append("\""); // @map(type = "json",

        for (ISiddhiAppComposite annotationComposite : annotationComposites) {
            jsonMapAnnotation.append(",");
            jsonMapAnnotation.append(annotationComposite.getSiddhiAppCompositeAsString());
        }

        jsonMapAnnotation
                .append(")"); // @map(type = "json", .....)

        return jsonMapAnnotation.toString();
    }
}
