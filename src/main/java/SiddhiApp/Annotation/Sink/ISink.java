package SiddhiApp.Annotation.Sink;

import SiddhiApp.Annotation.IAnnotation;
import SiddhiApp.Annotation.Common.ICommonAnnotationComposite;
import SiddhiApp.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.List;

public interface ISink extends IAnnotation {
    List<ISiddhiAppComposite> annotationComposites = new ArrayList<>(10);;

    default ISink addSourceComposite(ICommonAnnotationComposite iCommonAnnotationComposite){
        this.annotationComposites.add(iCommonAnnotationComposite);
        return this;
    }
}
