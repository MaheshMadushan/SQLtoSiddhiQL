package SiddhiAppComposites.Statement.From;


public enum Window {
    LENGTH("#window.length(%d)"),
    TIME("#window.time(%d)");

    private final String windowDefinition;
    Window(String windowDefinition){
        this.windowDefinition = windowDefinition;
    }

    public String setSizeOfWindowAsTimeOrLength(int windowSizeInMinutesOrLength) {
        return String.format(windowDefinition, windowSizeInMinutesOrLength);
    }

}
