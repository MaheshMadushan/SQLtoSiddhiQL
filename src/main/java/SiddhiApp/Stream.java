package SiddhiApp;

public class Stream implements IStream{
    private final String streamName;
    private final AttributeList attributeList;

    public Stream(String streamName,AttributeList attributeList) {
        this.streamName = streamName;
        this.attributeList = attributeList;
    }

    // O/P - define stream <stream name> (<attribute name> <attribute type>,<attribute name> <attribute type>, ... );
    @Override
    public String toString() {
        return String.format("define stream %s (%s);",streamName,attributeList.toString());
    }

}