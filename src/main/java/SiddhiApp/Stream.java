package SiddhiApp;

public class Stream implements IStream{
    private final String streamName;

    public Stream(String streamName) {
        this.streamName = streamName;
    }

    public Stream(){streamName = null;}

    @Override
    public String getSiddhiAppCompositeAsString() {
        return streamName;
    }
}