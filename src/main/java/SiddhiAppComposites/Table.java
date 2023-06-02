package SiddhiAppComposites;

import java.util.Objects;

public class Table implements ISiddhiAppComposite{
    private final String tableName;
    private String alias;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table that = (Table) o;
        return Objects.equals(tableName, that.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName);
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return tableName;
    }

    public String getAlias(){
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTableName(){
        return this.tableName;
    }


}
