package SiddhiApp.Annotation.Attributes;

import SiddhiApp.Annotation.IAnnotation;
import SiddhiApp.Annotation.Common.ICommonAnnotationComposite;
import SiddhiApp.Annotation.Map.IMapComposite;
import SiddhiApp.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.List;

public interface IAttributes extends IAnnotation, IMapComposite {
    List<ISiddhiAppComposite> annotationComposites = new ArrayList<>(10);;

    default IAttributes addMapComposite(ICommonAnnotationComposite iCommonAnnotationComposite){
        this.annotationComposites.add(iCommonAnnotationComposite);
        return this;
    }
}
