package SiddhiApp;

public class Column implements IAttribute{

    private String name;
    private String alias;

    public Column(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public Column(){
        this.name = null;
        this.alias = null;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String getAttributeWithAlias(){
        if(alias == null){
            return " " + name + " ";
        }else{
            return " " + name + " as " + alias + " ";
        }
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return getAttributeWithAlias();
    }
}
