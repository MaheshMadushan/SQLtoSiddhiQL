package SiddhiApp;

public enum SupportedDataTypes {
    STRING("string"),
    INT("int"),
    LONG("long"),
    DOUBLE("double"),
    FLOAT("float"),
    BOOL("bool");

    private final String dataTypeSignature;

    SupportedDataTypes(String dataTypeSignature){
        this.dataTypeSignature = dataTypeSignature;
    }

    public String getDataTypeSignature() {
        return dataTypeSignature;
    }

    public static boolean isDataTypeSupported(String dataTypeSignature){
        for(SupportedDataTypes supportedDataTypes : SupportedDataTypes.values()){
            if(supportedDataTypes.getDataTypeSignature().equalsIgnoreCase(dataTypeSignature)){
                return true;
            }
        }
        return false; // throw an exception
    }

}
