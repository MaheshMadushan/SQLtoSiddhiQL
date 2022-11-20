package SiddhiApp;

import java.util.Objects;

public class ColumnWIthDataType implements IAttribute{

    private final String dataType;
    private final Column column;

    public ColumnWIthDataType(Column column, String dataType) {
        this.column = column;
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnWIthDataType)) return false;
        ColumnWIthDataType that = (ColumnWIthDataType) o;
        return Objects.equals(dataType, that.dataType) && Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType, column);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return column.getName() + " " + dataType;
    }
}
