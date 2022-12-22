package SiddhiApp.Annotation.Map;

import SiddhiApp.Annotation.IAnnotation;
import SiddhiApp.Annotation.Common.ICommonAnnotationComposite;
import SiddhiApp.Annotation.Source.ISourceComposite;
import SiddhiApp.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.List;

public abstract class IMap implements IAnnotation, ISourceComposite {
    List<ISiddhiAppComposite> annotationComposites = new ArrayList<>(10);;

    public IMap addMapComposite(IMapComposite iMapComposite){
        this.annotationComposites.add(iMapComposite);
        return this;
    }

    public IMap addMapComposite(ICommonAnnotationComposite iCommonAnnotationComposite){
        this.annotationComposites.add(iCommonAnnotationComposite);
        return this;
    }
}
