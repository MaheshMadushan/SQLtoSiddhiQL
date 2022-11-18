package SiddhiApp;

public class ColumnWIthDataType implements IAttribute{

    private String dataType;
    private Column column;

    public ColumnWIthDataType(Column column, String dataType) {
        this.column = column;
        this.dataType = dataType;
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return column.getName() + " " + dataType;
    }
}
