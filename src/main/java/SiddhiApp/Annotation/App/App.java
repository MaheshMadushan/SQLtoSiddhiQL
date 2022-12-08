package SiddhiApp.Annotation.App;

import SiddhiApp.Annotation.AnnotationType;

public class App implements IApp {
    private final AnnotationType annotationType = AnnotationType.APP;
    private final StringBuilder annotationAppString =  new StringBuilder("");
    private String siddhiApplicationName = "defaultSiddhiApp";

    public void setSiddhiApplicationName(String siddhiApplicationName){
        this.siddhiApplicationName = siddhiApplicationName;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        annotationAppString.append(annotationType.getAnnotationTypeSignature())
                .append(":")
                .append("name")
                .append("(").append(siddhiApplicationName).append(")");
        return annotationAppString.toString();
    }
}
