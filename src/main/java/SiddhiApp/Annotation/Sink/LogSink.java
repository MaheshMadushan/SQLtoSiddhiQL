package SiddhiApp.Annotation.Sink;

import SiddhiApp.Annotation.AnnotationType;
import SiddhiApp.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.Iterator;

public class LogSink extends ISink {

    private final SinkType logSink= SinkType.LOG;
    private final StringBuilder sinkAnnotation = new StringBuilder("");
    private final AnnotationType sinkSignature = AnnotationType.SINK;

    public LogSink() {
        annotationComposites = new ArrayList<>(10);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {

        sinkAnnotation
                .append(sinkSignature.getAnnotationTypeSignature())
                .append("(")
                .append("type = '")
                .append(logSink.getSinkTypeSignature() + "'"); // @sink(type = "log",

        for (ISiddhiAppComposite annotationComposite : annotationComposites) {
            sinkAnnotation
                    .append(",")
                    .append(annotationComposite);
        }

        sinkAnnotation
                .append(")\n"); // @sink(type = "log", .....)

        return sinkAnnotation
                .toString();

    }
}
