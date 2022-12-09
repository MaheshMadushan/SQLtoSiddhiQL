package SiddhiApp.Annotation.Sink;

import SiddhiApp.Annotation.AnnotationType;
import SiddhiApp.ISiddhiAppComposite;

import java.util.Iterator;

public class LogSink implements ISink {
    private final SinkType logSink= SinkType.LOG;
    private final StringBuilder sinkAnnotation = new StringBuilder("");
    private final AnnotationType sinkSignature = AnnotationType.SINK;

    public LogSink() {
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        sinkAnnotation
                .append(sinkSignature.getAnnotationTypeSignature())
                .append("(").append("type = '").append(logSink.getSinkTypeSignature()).append("',"); // @sink(type = "log",
        Iterator<ISiddhiAppComposite> sourceAnnotationCompositesIterator = annotationComposites.iterator();

        while(sourceAnnotationCompositesIterator.hasNext()){
            sinkAnnotation.append(sourceAnnotationCompositesIterator.next());
            if(sourceAnnotationCompositesIterator.hasNext()){
                sinkAnnotation.append(",");
            }
        }

        sinkAnnotation
                .append(")\n"); // @sink(type = "log", .....)

        return sinkAnnotation.toString();
    }
}
