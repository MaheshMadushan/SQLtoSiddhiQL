package SiddhiApp;

public enum SupportedAggregationFunctions {
    SUM("sum",1),
    COUNT("count",1),
    DISTINCT_COUNT("distinctCount",1),
    AVG("avg",1),
    MAX("max",1),
    MIN("min",1),
    MAX_FOREVER("maxForever",1),
    MIN_FOREVER("minForever",1),
    STD_DEV("stdDev",1);


    private final String functionSignature;
    private final int numOfArguments;

    SupportedAggregationFunctions(String functionSignature,int numOfArguments){
        this.functionSignature = functionSignature;
        this.numOfArguments = numOfArguments;
    }

    public String getFunctionSignature() {
        return functionSignature;
    }

    public int getNumOfArguments() {
        return numOfArguments;
    }

    public static boolean isFunctionSupported(String functionSignature){
        for(SupportedAggregationFunctions supportedAggregationFunction : SupportedAggregationFunctions.values()){
            if(supportedAggregationFunction.getFunctionSignature().equals(functionSignature)){
                return true;
            }
        }
        return false;
    }

    public static int getNumOfArguments(String functionSignature){
        for(SupportedAggregationFunctions supportedAggregationFunction : SupportedAggregationFunctions.values()){
            if(supportedAggregationFunction.getFunctionSignature().equals(functionSignature)){
                return supportedAggregationFunction.getNumOfArguments();
            }
        }

        return 0; // should throw an exception
    }
}
