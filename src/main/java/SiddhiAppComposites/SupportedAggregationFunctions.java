package SiddhiAppComposites;

public enum SupportedAggregationFunctions {
    SUM("sum"),
    COUNT("count"), // here string mean could be any type of data.
    DISTINCT_COUNT("distinctCount"), // here String mean any type of data
    AVG("avg"),
    MAX("max"),
    MIN("min"),
    MAX_FOREVER("maxForever"),
    MIN_FOREVER("minForever"),
    STD_DEV("stdDev");

    private final String functionSignature;

    SupportedAggregationFunctions(String functionSignature){
        this.functionSignature = functionSignature;
    }

    public String getFunctionSignature() {
        return functionSignature;
    }

    public static boolean isFunctionSupported(String functionSignature){
        for(SupportedAggregationFunctions supportedAggregationFunction : SupportedAggregationFunctions.values()){
            if(supportedAggregationFunction.getFunctionSignature().equalsIgnoreCase(functionSignature)){
                return true;
            }
        }
        return false; // throw an exception
    }

}
