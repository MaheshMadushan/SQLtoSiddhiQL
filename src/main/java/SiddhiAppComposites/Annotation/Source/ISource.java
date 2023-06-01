package SiddhiAppComposites.Annotation.Source;


import SiddhiAppComposites.Annotation.IAnnotation;
import SiddhiAppComposites.Annotation.Common.ICommonAnnotationComposite;
import SiddhiAppComposites.ISiddhiAppComposite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ISource implements IAnnotation {
    List<ISiddhiAppComposite> annotationComposites = new ArrayList<>(10);

    public ISource deleteSourceComposite(ISiddhiAppComposite iSiddhiAppComposite){
        List<ISiddhiAppComposite> newAnnotationComposites = new ArrayList<>(10);
        for (ISiddhiAppComposite annotationComposite : annotationComposites) {
            if (annotationComposite.equals(iSiddhiAppComposite)) {
                continue;
            } else {
                newAnnotationComposites.add(annotationComposite);
            }
        }
        this.annotationComposites = newAnnotationComposites;
        return this;
    }

    public ISource addSourceComposite(ISourceComposite iSourceComposite){
        this.annotationComposites.add(iSourceComposite);
        return this;
    }


    public ISource addSourceComposite(ICommonAnnotationComposite iCommonAnnotationComposite){
        this.annotationComposites.add(iCommonAnnotationComposite);
        return this;
    }

}
