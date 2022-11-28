package SiddhiApp;

public enum SupportedAggregationFunctions {
    SUM("sum",1, "double"),
    COUNT("count",1, "String"), // here string mean could be any type of data.
    DISTINCT_COUNT("distinctCount",1, "String"), // here String mean any type of data
    AVG("avg",1, "double"),
    MAX("max",1, "double"),
    MIN("min",1, "double"),
    MAX_FOREVER("maxForever",1, "double"),
    MIN_FOREVER("minForever",1, "double"),
    STD_DEV("stdDev",1, "double");

    private final String functionSignature;
    private final int numOfArguments;
    private final String attributeDataType;

    SupportedAggregationFunctions(String functionSignature,int numOfArguments, String attributeDataType){
        this.functionSignature = functionSignature;
        this.numOfArguments = numOfArguments;
        this.attributeDataType = attributeDataType;
    }

    public String getAttributeDataType(){
        return attributeDataType;
    }

    public String getFunctionSignature() {
        return functionSignature;
    }

    public int getNumOfArguments() {
        return numOfArguments;
    }

    public static boolean isFunctionSupported(String functionSignature){
        for(SupportedAggregationFunctions supportedAggregationFunction : SupportedAggregationFunctions.values()){
            if(supportedAggregationFunction.getFunctionSignature().equalsIgnoreCase(functionSignature)){
                return true;
            }
        }
        return false; // throw an exception
    }


    public static String getAttributeDataType(String functionSignature){
        for(SupportedAggregationFunctions supportedAggregationFunction : SupportedAggregationFunctions.values()){
            if(supportedAggregationFunction.getFunctionSignature().equalsIgnoreCase(functionSignature)){
                return supportedAggregationFunction.getAttributeDataType();
            }
        }
        return null; // throw an exception
    }

    public static int getNumOfArguments(String functionSignature){
        for(SupportedAggregationFunctions supportedAggregationFunction : SupportedAggregationFunctions.values()){
            if(supportedAggregationFunction.getFunctionSignature().equalsIgnoreCase(functionSignature)){
                return supportedAggregationFunction.getNumOfArguments();
            }
        }

        return 0; // should throw an exception
    }
}
